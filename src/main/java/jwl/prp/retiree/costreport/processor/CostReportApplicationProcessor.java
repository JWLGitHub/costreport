package jwl.prp.retiree.costreport.processor;


import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import jwl.prp.retiree.costreport.dao.ApplErrDAO;
import jwl.prp.retiree.costreport.dao.FileApplDAO;
import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;
import jwl.prp.retiree.costreport.exception.CostReportException;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import java.util.Calendar;
import java.util.List;


public class CostReportApplicationProcessor implements StepExecutionListener,
                                                       ItemProcessor<CostReportRecord, List<CostReportRecord>>
{
    private static String CLASS_NAME  = CostReportApplicationProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportApplicationProcessor.class.getSimpleName();

    private RDSFileDAO  rdsFileDAO;
    private FileErrDAO  fileErrDAO;
    private FileApplDAO fileApplDAO;
    private ApplErrDAO  applErrDAO;

    private FileContext fileContext = new FileContext();


    /*
    *---   JOB EXECUTION CONTEXT
    */


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
        updateRDSFile(StusRef.FILE_PROCESSING_2ND_PASS);

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
    public List<CostReportRecord> process(CostReportRecord costReportRecord)
                                          throws Exception
    {
        final String METHOD_NAME = "process";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setFileRecordCounter(fileContext.getFileRecordCounter() + 1);

        if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FHDR.name()))
            validateCostReportRecord(costReportRecord,
                                     fileHeaderValidators);
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.AHDR.name()))
        {
            validateCostReportRecord(costReportRecord,
                                     applicationHeaderValidators);

            insertFileAppl(StusRef.PROCESSING,
                           fileContext.getValidApplicationID());
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.DETL.name()))
        {
            if (fileContext.isApplicationValid())
                validateCostReportRecord(costReportRecord,
                                         applicationDetailValidators);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.ATRL.name()))
        {
            if (fileContext.isApplicationValid())
                validateCostReportRecord(costReportRecord,
                                         applicationTrailerValidators);

            if (fileContext.isApplicationValid())
            {
                updateFileAppl(StusRef.ACCEPTED);
                return fileContext.getApplicationRecords();
            }
            else
                updateFileAppl(StusRef.REJECTED);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FTRL.name()))
        {
            // DO NOTHING
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
                    insertFileErr(validationError);

                    if (!errRefsNotErrors.contains(validationError.getErrRef()))
                    {
                        fileContext.setApplicationValid(false);
                        break;
                    }
                }
            }
        }

        if (fileContext.isApplicationValid())
            fileContext.addApplicationRecord(costReportRecord);

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


    private void insertFileErr(ValidationError validationError)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setFileErrSeqNum(fileContext.getFileErrSeqNum() +1);

        FileErr fileErr = new FileErr(fileContext.getRdsFileId(),
                                      validationError.getErrRef().getErrCd(),
                                      validationError.getErrRef().getErrCtgryCd(),
                                      fileContext.getFileErrSeqNum(),
                                      validationError.getRecordNbrErrMessage());

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertFileAppl(StusRef stusRef,
                                String  applId)
    {
        final String METHOD_NAME = "insertFileAppl";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setApplSeqNum(fileContext.getApplSeqNum() + 1);

        FileAppl fileAppl = new FileAppl(fileContext.getRdsFileId(),
                                         fileContext.getApplSeqNum(),
                                         fileContext.getApplicationHeaderApplicationID(),
                                         fileContext.getFileSubmitterID(),
                                         null,
                                         null,
                                         new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                                         SIMPLE_NAME,
                                         stusRef.getStusCtgryCd(),
                                         stusRef.getStusCd(),
                                         applId);

        fileApplDAO.insertFileAppl(fileAppl);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertApplErr(ValidationError validationError)
    {
        final String METHOD_NAME = "insertApplErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setApplSeqNum(fileContext.getApplSeqNum() + 1);

        ApplErr applErr = new ApplErr(fileContext.getRdsFileId(),
                                      fileContext.getApplSeqNum(),
                                      validationError.getErrRef().getErrCtgryCd(),
                                      validationError.getErrRef().getErrCd(),
                                      int     errSeqNum,
                                      int     costYearNum,
                                      int     costMnthNum,
                                      String  rxGrpNum,
                                      validationError.getRecordNbrErrMessage());

        fileApplDAO.insertFileAppl(applErr);

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
            if (throwable instanceof CostReportException)
                handleCostReportException((CostReportException) throwable);
        }

        updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void handleCostReportException(CostReportException costReportException)
    {
        final String METHOD_NAME = "handleCostReportException";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        ValidationError validationError = costReportException.getValidationError();

        insertFileErr(validationError);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setRdsFileDAO(RDSFileDAO rdsFileDAO) { this.rdsFileDAO = rdsFileDAO; }

    public void setFileErrDAO(FileErrDAO fileErrDAO) { this.fileErrDAO = fileErrDAO; }

    public void setFileApplDAO(FileApplDAO fileApplDAO) { this.fileApplDAO = fileApplDAO; }

    public void setApplErrDAO(ApplErrDAO applErrDAO) { this.applErrDAO = applErrDAO; }

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
