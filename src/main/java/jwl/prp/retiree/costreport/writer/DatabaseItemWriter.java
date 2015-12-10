package jwl.prp.retiree.costreport.writer;


import jwl.prp.retiree.costreport.dao.CostReportImportDAO;
import jwl.prp.retiree.costreport.entity.CostReportImport;
import jwl.prp.retiree.costreport.entity.CostReportRecord;

import jwl.prp.retiree.costreport.validation.FileContext;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.Date;
import java.util.List;


public class DatabaseItemWriter implements StepExecutionListener,
                                           ItemWriter<List<CostReportRecord>>
{
    private static String CLASS_NAME  = DatabaseItemWriter.class.getName();
    private static String SIMPLE_NAME = DatabaseItemWriter.class.getSimpleName();

    private CostReportImportDAO costReportImportDAO;

    private FileContext fileContext = new FileContext();


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

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private int getRdsFileId(StepExecution stepExecution)
    {
        final String METHOD_NAME = "getRdsFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object rdsFileId = stepExecution.getJobExecution().getExecutionContext().get(FileContext.RDS_FILE_ID);
        if (null == rdsFileId  ||
            rdsFileId.toString().equalsIgnoreCase(""))
            throw new RuntimeException("'" + FileContext.RDS_FILE_ID + "' MISSING from jobExecutionContext");

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(rdsFileId.toString());
    }


    /*
    *****                                   *****
    *****     -----     WRITE     -----     *****
    *****                                   *****
    */
    @Override
    public void write(List<? extends List<CostReportRecord>> costReportRecordsLists)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        for (List<CostReportRecord> costReportRecordList : costReportRecordsLists)
        {
            for (CostReportRecord costReportRecord : costReportRecordList)
                insertCostReportImport(costReportRecord);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertCostReportImport(CostReportRecord costReportRecord)
    {
        final String METHOD_NAME = "insertCostReportImport";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + costReportRecord.toString());

        CostReportImport costReportImport = new CostReportImport(fileContext.getRdsFileId(),
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

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setCostReportImportDAO(CostReportImportDAO costReportImportDAO) { this.costReportImportDAO = costReportImportDAO; }
}