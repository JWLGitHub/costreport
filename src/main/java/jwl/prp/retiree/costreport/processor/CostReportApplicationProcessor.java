package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.dao.ApplErrDAO;
import jwl.prp.retiree.costreport.dao.FileApplDAO;
import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusRef;
import jwl.prp.retiree.costreport.utils.ExecutionContextHandler;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import java.util.Calendar;
import java.util.List;


public class CostReportApplicationProcessor extends    CostReportBaseProcessor
                                            implements StepExecutionListener,
                                                       ItemProcessor<CostReportRecord, List<CostReportRecord>>
{
    private static String CLASS_NAME  = CostReportApplicationProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportApplicationProcessor.class.getSimpleName();

    private FileApplDAO fileApplDAO;
    private ApplErrDAO  applErrDAO;


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

        ExecutionContext jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();

        fileContext.setRdsFileId(ExecutionContextHandler.getIntegerFromExecutionContext(jobExecutionContext,
                                                                                        FileContext.RDS_FILE_ID));

        updateRDSFile(StusRef.FILE_PROCESSING_2ND_PASS,
                      SIMPLE_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
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
            insertFileAppl(StusRef.PROCESSING,
                           (ApplicationHeader) costReportRecord);

            validateCostReportRecord(costReportRecord,
                                     applicationHeaderValidators);
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
                fileContext.setFileApplicationAcceptedCount(fileContext.getFileApplicationAcceptedCount() + 1);
                return fileContext.getApplicationRecords();
            }
            else
            {
                updateFileAppl(StusRef.REJECTED);
                fileContext.setFileApplicationRejectedCount(fileContext.getFileApplicationRejectedCount() + 1);
            }
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
                    insertApplErr(validationError,
                                  costReportRecord);

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


    /*
    *****                                       *****
    *****     -----     FILE APPL     -----     *****
    *****                                       *****
    */
    private void insertFileAppl(StusRef           stusRef,
                                ApplicationHeader applicationHeader)
    {
        final String METHOD_NAME = "insertFileAppl";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setApplSeqNum(fileContext.getApplSeqNum() + 1);

        FileAppl fileAppl = new FileAppl(fileContext.getRdsFileId(),
                                         fileContext.getApplSeqNum(),
                                         applicationHeader.getApplicationID(),
                                         fileContext.getFileSubmitterID(),
                                         null,
                                         null,
                                         new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                                         SIMPLE_NAME,
                                         stusRef.getStusCtgryCd(),
                                         stusRef.getStusCd(),
                                         null);

        fileApplDAO.insertFileAppl(fileAppl);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void updateFileAppl(StusRef fileStatus)
    {
        final String METHOD_NAME = "updateFileAppl";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileAppl fileAppl = fileApplDAO.findByFileIdApplSeqNum(fileContext.getRdsFileId(),
                                                               fileContext.getApplSeqNum());

        fileAppl.setStusCd(fileStatus.getStusCd());
        fileAppl.setStusTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        fileAppl.setStusPgm(SIMPLE_NAME);
        fileAppl.setApplId(fileContext.getValidApplicationID());
        fileApplDAO.updateFileAppl(fileAppl);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
    *****                                      *****
    *****     -----     APPL ERR     -----     *****
    *****                                      *****
    */
    private void insertApplErr(ValidationError  validationError,
                               CostReportRecord costReportRecord)
    {
        final String METHOD_NAME = "insertApplErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setApplErrSeqNum(fileContext.getApplErrSeqNum() + 1);

        ApplErr applErr;

        if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.DETL.name()))
        {
            ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;
            applErr = new ApplErr(fileContext.getRdsFileId(),
                                  fileContext.getApplSeqNum(),
                                  validationError.getErrRef().getErrCtgryCd(),
                                  validationError.getErrRef().getErrCd(),
                                  fileContext.getApplErrSeqNum(),
                                  applicationDetail.getRxCostYearMonth().substring(0,4),
                                  applicationDetail.getRxCostYearMonth().substring(4,6),
                                  applicationDetail.getUniqueBenefitOptionIdentifier(),
                                  validationError.getRecordNbrErrMessage());
        }
        else
        {
            applErr = new ApplErr(fileContext.getRdsFileId(),
                                  fileContext.getApplSeqNum(),
                                  validationError.getErrRef().getErrCtgryCd(),
                                  validationError.getErrRef().getErrCd(),
                                  fileContext.getApplErrSeqNum(),
                                  null,
                                  null,
                                  null,
                                  validationError.getRecordNbrErrMessage());
        }

        applErrDAO.insertApplErr(applErr);

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
        {
            stepExecution.setExitStatus(ExitStatus.FAILED);
            updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE,
                          SIMPLE_NAME);
        }
        else if (fileContext.getFileApplicationAcceptedCount() == 0)
        {
            stepExecution.setExitStatus(ExitStatus.FAILED);

            insertFileErr(ErrRef.ALL_APPLICATIONS_REJECTED,
                          "Application Accepted Count: " + fileContext.getFileApplicationAcceptedCount() + " Applications Rejected Count: " + fileContext.getFileApplicationRejectedCount());

            updateRDSFile(StusRef.ALL_APPLICATIONS_REJECTED,
                          SIMPLE_NAME);
        }
        else
            updateRDSFile(StusRef.FILE_PROCESSING_COMPLETED,
                          SIMPLE_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return null;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setFileApplDAO(FileApplDAO fileApplDAO) { this.fileApplDAO = fileApplDAO; }

    public void setApplErrDAO(ApplErrDAO applErrDAO) { this.applErrDAO = applErrDAO; }
}
