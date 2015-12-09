package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.exception.CostReportException;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrCtgRef;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;

import org.springframework.batch.core.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;

import java.util.Calendar;
import java.util.List;


public class CostReportFileProcessor implements StepExecutionListener,
                                                ItemProcessor<CostReportRecord, CostReportRecord>
{
    private static String CLASS_NAME  = CostReportFileProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportFileProcessor.class.getSimpleName();

    private RDSFileDAO rdsFileDAO;
    private FileErrDAO fileErrDAO;

    private FileContext fileContext = new FileContext();


    /*
     *---   Validators
     */
    private List<BaseValidator> fileHeaderValidators;

    private List<BaseValidator> applicationHeaderValidators;

    private List<BaseValidator> applicationDetailValidators;

    private List<BaseValidator> applicationTrailerValidators;

    private List<BaseValidator> fileTrailerValidators;


    /*
    *---   Err Ref(s) NOT Error(s)
    */
    private List<ErrRef> errRefsNotErrors;


    /*
     *****                                         *****
     *****     -----     BEFORE STEP     -----     *****
     *****                                         *****
     */
    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "beforeStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setRdsFileId(getRdsFileId(stepExecution));
        updateRDSFile(StusRef.FILE_PROCESSING_1ST_PASS);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private int getRdsFileId(StepExecution stepExecution)
    {
        final String METHOD_NAME = "getRdsFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object rdsFileId = stepExecution.getJobExecution().getExecutionContext().get(FileContext.RDS_FILE_ID);
        if (null == rdsFileId ||
            rdsFileId.toString().equalsIgnoreCase(""))
            throw new RuntimeException("'" + FileContext.RDS_FILE_ID + "' MISSING from jobExecutionContext");

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(rdsFileId.toString());
    }


    /*
    *****                                     *****
    *****     -----     PROCESS     -----     *****
    *****                                     *****
    */
    @Override
    public CostReportRecord process(CostReportRecord costReportRecord)
                                    throws Exception
    {
        final String METHOD_NAME = "process";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setFileRecordCounter(fileContext.getFileRecordCounter() + 1);

        if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FHDR.name()))
        {
            validateCostReportRecord(costReportRecord,
                                     fileHeaderValidators);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.AHDR.name()))
        {
            validateCostReportRecord(costReportRecord,
                                     applicationHeaderValidators);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.DETL.name()))
        {
            validateCostReportRecord(costReportRecord,
                                     applicationDetailValidators);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.ATRL.name()))
        {
            validateCostReportRecord(costReportRecord,
                                     applicationTrailerValidators);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FTRL.name()))
        {
            validateCostReportRecord(costReportRecord,
                    fileTrailerValidators);
        }
        else
            throw new ValidationException("INVALID COST REPORT RECORD: " + costReportRecord);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return null;
    }


    private CostReportRecord validateCostReportRecord(CostReportRecord    costReportRecord,
                                                      List<BaseValidator> costReportValidators)
                                                      throws Exception
    {
        final String METHOD_NAME = "validateCostReportRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - CostReportRecord: " + costReportRecord);

        if (null != costReportValidators &&
            !costReportValidators.isEmpty())
        {
            for (BaseValidator costReportValidator : costReportValidators)
            {
                ValidationError validationError = costReportValidator.execute(costReportRecord,
                                                                              fileContext);

                if (null != validationError)
                {
                    if (errRefsNotErrors.contains(validationError.getErrRef()))
                    {
                        // *** NOT AN ERROR ***    (BUT . . .  We'll still track it)
                        insertFileErr(validationError.getErrRef().getErrCd(),
                                      validationError.getErrRef().getErrCtgryCd(),
                                      validationError.getRecordNbrErrMessage());
                    }
                    else
                        throw new CostReportException(validationError);
                }
            }
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return costReportRecord;
    }


    private void updateRDSFile(StusRef fileStatus)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(fileContext.getRdsFileId());
        rdsFile.setStusCtgryCd(StusCtgry.FILE_STATUS.getStusCtgryCd());
        rdsFile.setStusCd(fileStatus.getStusCd());
        rdsFile.setUptdPgm(SIMPLE_NAME);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertFileErr(String errCd,
                               String errCtgryCd,
                               String errInfo)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setFileErrSeqNum(fileContext.getFileErrSeqNum() + 1);

        FileErr fileErr = new FileErr(fileContext.getRdsFileId(),
                                      errCd,
                                      errCtgryCd,
                                      fileContext.getFileErrSeqNum(),
                                      errInfo);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
    *****                                         *****
    *****     -----     AFTER STEP     -----      *****
    *****                                         *****
    */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "afterStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (stepExecution.getFailureExceptions().size() > 0)
            handleFailureExceptions(stepExecution);
        else if (fileContext.getFileHeaderCounter() != 1                                              ||
                 fileContext.getApplicationHeaderCounter() != fileContext.getFileApplicationCount()   ||
                 fileContext.getFileTrailerCounter() == 0)
            handleEndOfFileError(stepExecution);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return null;
    }


    private void handleFailureExceptions(StepExecution stepExecution)
    {
        final String METHOD_NAME = "handleFailureExceptions";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        stepExecution.setExitStatus(ExitStatus.FAILED);

        for (Throwable throwable : stepExecution.getFailureExceptions())
        {
            if (throwable instanceof FlatFileParseException)
                handleFlatFileParseException((FlatFileParseException) throwable);
            else if (throwable instanceof CostReportException)
                handleCostReportException((CostReportException) throwable);
        }

        updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void handleFlatFileParseException(FlatFileParseException flatFileParseException)
    {
        final String METHOD_NAME = "handleFlatFileParseException";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        String processText = "Record No.: " + flatFileParseException.getLineNumber() +
                             " Record Layout: " + flatFileParseException.getInput();

        if (flatFileParseException.getInput().length() != FileContext.COST_REPORT_RECORD_LENGTH)
            insertFileErr(ErrRef.CRFILE_READ_ERROR.getErrCd(),
                          ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                          processText);
        else
        {
            String inputRecordType = flatFileParseException.getInput().substring(0, 4);
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - InputRecordType: " + inputRecordType);
            if (!FileContext.VALID_RECORD_TYPES.contains(inputRecordType))
                insertFileErr(ErrRef.CRFILE_BAD_RECORD_TYPE.getErrCd(),
                              ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                              processText);
            else
                insertFileErr(ErrRef.CRFILE_RECFM_ERROR.getErrCd(),
                              ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                              processText);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void handleCostReportException(CostReportException costReportException)
    {
        final String METHOD_NAME = "handleCostReportException";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        ValidationError validationError = costReportException.getValidationError();

        insertFileErr(validationError.getErrRef().getErrCd(),
                      validationError.getErrRef().getErrCtgryCd(),
                      validationError.getRecordNbrErrMessage());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void handleEndOfFileError(StepExecution stepExecution)
    {
        final String METHOD_NAME = "handleEndOfFileError";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        stepExecution.setExitStatus(ExitStatus.FAILED);

        if (fileContext.getFileHeaderCounter() != 1         ||
            fileContext.getApplicationHeaderCounter() != fileContext.getFileApplicationCount())
            insertFileErr(ErrRef.UNEXPECTED_EOF_FILE_SEQUENCE_ERROR.getErrCd(),
                          ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                          "EOF Reached - " + ErrRef.UNEXPECTED_EOF_FILE_SEQUENCE_ERROR.getDescTxt());
        else
            insertFileErr(ErrRef.FILE_TRAILER_MISSING.getErrCd(),
                          ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                          "EOF Reached - " + ErrRef.FILE_TRAILER_MISSING.getDescTxt());

        updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setRdsFileDAO(RDSFileDAO rdsFileDAO) { this.rdsFileDAO = rdsFileDAO; }

    public void setFileErrDAO(FileErrDAO fileErrDAO) { this.fileErrDAO = fileErrDAO; }

    public void setFileHeaderValidators(List<BaseValidator> fileHeaderValidators)
    {
        final String METHOD_NAME = "setFileHeaderValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileHeaderValidators = fileHeaderValidators;
    }


    public void setApplicationHeaderValidators(List<BaseValidator> applicationHeaderValidators)
    {
        final String METHOD_NAME = "setApplicationHeaderValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationHeaderValidators = applicationHeaderValidators;
    }


    public void setApplicationDetailValidators(List<BaseValidator> applicationDetailValidators)
    {
        final String METHOD_NAME = "setApplicationDetailValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationDetailValidators = applicationDetailValidators;
    }


    public void setApplicationTrailerValidators(List<BaseValidator> applicationTrailerValidators)
    {
        final String METHOD_NAME = "setApplicationTrailerValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationTrailerValidators = applicationTrailerValidators;
    }


    public void setFileTrailerValidators(List<BaseValidator> fileTrailerValidators)
    {
        final String METHOD_NAME = "setFileTrailerValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileTrailerValidators = fileTrailerValidators;
    }


    public void setErrRefsNotErrors(List<ErrRef> errRefsNotErrors)
    {
        final String METHOD_NAME = "setErrRefsNotErrors";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.errRefsNotErrors = errRefsNotErrors;
    }
}
