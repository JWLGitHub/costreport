package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.exception.CostReportException;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusRef;

import org.springframework.batch.core.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;

import java.util.List;


public class CostReportFileProcessor extends    CostReportBaseProcessor
                                     implements StepExecutionListener,
                                                ItemProcessor<CostReportRecord, CostReportRecord>
{
    private static String CLASS_NAME  = CostReportFileProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportFileProcessor.class.getSimpleName();


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
        updateRDSFile(StusRef.FILE_PROCESSING_1ST_PASS,
                      SIMPLE_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
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
                        insertFileErr(validationError.getErrRef(),
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

        updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE,
                      SIMPLE_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void handleFlatFileParseException(FlatFileParseException flatFileParseException)
    {
        final String METHOD_NAME = "handleFlatFileParseException";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        String processText = "Record No.: " + flatFileParseException.getLineNumber() +
                             " Record Layout: " + flatFileParseException.getInput();

        if (flatFileParseException.getInput().length() != FileContext.COST_REPORT_RECORD_LENGTH)
            insertFileErr(ErrRef.CRFILE_READ_ERROR,
                          processText);
        else
        {
            String inputRecordType = flatFileParseException.getInput().substring(0, 4);
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - InputRecordType: " + inputRecordType);
            if (!FileContext.VALID_RECORD_TYPES.contains(inputRecordType))
                insertFileErr(ErrRef.CRFILE_BAD_RECORD_TYPE,
                              processText);
            else
                insertFileErr(ErrRef.CRFILE_RECFM_ERROR,
                              processText);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void handleCostReportException(CostReportException costReportException)
    {
        final String METHOD_NAME = "handleCostReportException";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        ValidationError validationError = costReportException.getValidationError();

        insertFileErr(validationError.getErrRef(),
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
            insertFileErr(ErrRef.UNEXPECTED_EOF_FILE_SEQUENCE_ERROR,
                          "EOF Reached - " + ErrRef.UNEXPECTED_EOF_FILE_SEQUENCE_ERROR.getDescTxt());
        else
            insertFileErr(ErrRef.FILE_TRAILER_MISSING,
                          "EOF Reached - " + ErrRef.FILE_TRAILER_MISSING.getDescTxt());

        updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE,
                      SIMPLE_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
}
