package jwl.prp.retiree.costreport.writer;


import jwl.prp.retiree.costreport.dao.CostReportImportDAO;
import jwl.prp.retiree.costreport.entity.CostReportImport;
import jwl.prp.retiree.costreport.entity.CostReportRecord;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import java.util.Date;
import java.util.List;


/**
 * Created by jwleader on 11/23/15.
 */
public class DatabaseItemWriter implements ItemWriter<CostReportRecord>
{
    private static String CLASS_NAME  = DatabaseItemWriter.class.getName();
    private static String SIMPLE_NAME = DatabaseItemWriter.class.getSimpleName();

    private static final String RDS_FILE_ID = "rdsFileId";

    private JobExecution        jobExecution;
    private ExecutionContext    jobExecutionContext;
    private StepExecution       stepExecution;

    private CostReportImportDAO costReportImportDAO;

    /*
    *---   JOB EXECUTION CONTEXT
    */
    private int rdsFileId;


    @Override
    public void write(List<? extends CostReportRecord> costReportRecords)
            throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        for (CostReportRecord costReportRecord : costReportRecords)
             System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + costReportRecord.toFixedString());

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
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setCostReportImportDAO(CostReportImportDAO costReportImportDAO) { this.costReportImportDAO = costReportImportDAO; }
}

