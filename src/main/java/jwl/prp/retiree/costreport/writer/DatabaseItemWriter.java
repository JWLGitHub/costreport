package jwl.prp.retiree.costreport.writer;


import jwl.prp.retiree.costreport.dao.CostReportImportDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.CostReportImport;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.RDSFile;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;

import jwl.prp.retiree.costreport.validation.FileContext;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DatabaseItemWriter implements StepExecutionListener,
                                           ItemWriter<CostReportRecord>
{
    private static String CLASS_NAME  = DatabaseItemWriter.class.getName();
    private static String SIMPLE_NAME = DatabaseItemWriter.class.getSimpleName();

    private RDSFileDAO          rdsFileDAO;
    private CostReportImportDAO costReportImportDAO;

    /*
    *---   JOB EXECUTION CONTEXT
    */
    private int rdsFileId;


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

        this.rdsFileId = getRdsFileId(stepExecution);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private int getRdsFileId(StepExecution stepExecution)
    {
        final String METHOD_NAME = "getRdsFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object rdsFileId = stepExecution.getJobExecution().getExecutionContext().get(FileContext.RDS_FILE_ID);
        if (null == rdsFileId  ||
            rdsFileId.toString().equalsIgnoreCase(""))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + FileContext.RDS_FILE_ID + ": MISSING");
            throw new RuntimeException("'" + FileContext.RDS_FILE_ID + "' MISSING from jobExecutionContext");
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(rdsFileId.toString());
    }


    /*
    *****                                   *****
    *****     -----     WRITE     -----     *****
    *****                                   *****
    */
    @Override
    public void write(List<? extends CostReportRecord> costReportRecords)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        for (CostReportRecord costReportRecord : costReportRecords)
             insertCostReportImport(costReportRecord);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertCostReportImport(CostReportRecord costReportRecord)
    {
        final String METHOD_NAME = "insertCostReportImport";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportImport costReportImport = new CostReportImport(this.rdsFileId,
                                                                 new Date(),
                                                                 costReportRecord.toFixedString());

        costReportImportDAO.insertCostReportImport(costReportImport);

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

            updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE);
        }
        else
            updateRDSFile(StusRef.FILE_ACCEPTED_NO_ERRORS);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return null;
    }


    private void updateRDSFile(StusRef fileStatus)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(this.rdsFileId);
        rdsFile.setStusCtgryCd(StusCtgry.FILE_STATUS.getStusCtgryCd());
        rdsFile.setStusCd(fileStatus.getStusCd());
        rdsFile.setUptdPgm(SIMPLE_NAME);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setRdsFileDAO(RDSFileDAO rdsFileDAO) { this.rdsFileDAO = rdsFileDAO; }

    public void setCostReportImportDAO(CostReportImportDAO costReportImportDAO) { this.costReportImportDAO = costReportImportDAO; }
}