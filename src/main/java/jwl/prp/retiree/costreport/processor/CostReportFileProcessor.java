package jwl.prp.retiree.costreport.processor;

import jwl.prp.retiree.costreport.dao.CostReportFileDAO;
import jwl.prp.retiree.costreport.dao.CostReportFileProcessDAO;
import jwl.prp.retiree.costreport.entity.CostReportFile;
import jwl.prp.retiree.costreport.entity.CostReportFileProcess;
import jwl.prp.retiree.costreport.entity.CostReportRecord;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.listener.SkipListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * Created by jwleader on 11/5/15.
 */
public class CostReportFileProcessor extends    SkipListenerSupport<CostReportRecord, CostReportRecord>
                                     implements StepExecutionListener
{
    private static String CLASS_NAME  = CostReportFileProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportFileProcessor.class.getSimpleName();

    private JobExecution     jobExecution;
    private ExecutionContext jobExecutionContext;
    private StepExecution    stepExecution;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CostReportFileDAO        costReportFileDAO;
    private CostReportFileProcessDAO costReportFileProcessDAO;

    private String inputFilePath;

    private int costReportFileID;

    /*
    *---   FILE HEADER WORK AREA
    */
    private int fileHeaderCount = 0;
    private boolean validFileHeader = false;
    private String fileHeaderSubmitterID;

    /*
     *---   APPLICATION WORK AREA
     */
    private int applicationHeaderCount = 0;
    private boolean validApplicationHeader = false;
    private String applicationHeaderApplicationID;
    private int applicationTrailerCount = 0;

    /*
     *---   FILE TRAILER WORK AREA
     */
    private int fileTrailerCount = 0;


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

        this.stepExecution       = stepExecution;
        this.jobExecution        = stepExecution.getJobExecution();
        this.jobExecutionContext = jobExecution.getExecutionContext();

        getCostReportFileID();
        updateCostReportFileInfo(CostReportFile.STATUS_TYPE.PROCESSING);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void getCostReportFileID()
    {
        final String METHOD_NAME = "getCostReportFileID";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object costReportFileID = jobExecutionContext.get("costReportFileID");
        if (null == costReportFileID ||
            costReportFileID.toString().equalsIgnoreCase(""))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - costReportFileID: MISSING");
            throw new RuntimeException("'costReportFileID' MISSING from jobExecutionContext");
        }

        this.costReportFileID = Integer.parseInt(costReportFileID.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                             *****
     *****     -----     ON SKIP IN READ     -----     *****
     *****                                             *****
     */
    @Override
    public void onSkipInRead(Throwable exception)
    {
        final String METHOD_NAME = "onSkipInRead";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (exception instanceof FlatFileParseException)
        {
            FlatFileParseException flatFileParseException = (FlatFileParseException) exception;
            String processText = "Record No.: " + flatFileParseException.getLineNumber() +
                                 " Record Layout: " + flatFileParseException.getInput();
            insertCostReportFileProcess(CostReportFileProcess.PROCESS_TYPE.CRFILE_BAD_RECORD_TYPE,
                                        processText);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                                *****
     *****     -----     ON SKIP IN PROCESS     -----     *****
     *****                                                *****
     */
    @Override
    public void onSkipInProcess(CostReportRecord costReportRecord,
                                Throwable        exception)
    {
        final String METHOD_NAME = "onSkipInProcess";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                         *****
     *****     -----     AFTER STEP     -----     *****
     *****                                         *****
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "afterStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        updateCostReportFileInfo(CostReportFile.STATUS_TYPE.ACCEPTED);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return null;
    }


    private void updateCostReportFileInfo(CostReportFile.STATUS_TYPE costReportFileStatusType)
    {
        final String METHOD_NAME = "updateCostReportFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportFile costReportFile = costReportFileDAO.findByCostReportFileID(this.costReportFileID);
        costReportFile.setStatus(costReportFileStatusType.name());
        costReportFile.setUpdateJob(jobExecution.getJobInstance().getJobName());
        costReportFile.setUpdateStep(stepExecution.getStepName());
        costReportFile.setUpdateProgram(SIMPLE_NAME);
        costReportFile.setUpdateMethod(METHOD_NAME);
        costReportFile.setUpdateDateTime(new Date());
        costReportFileDAO.updateCostReportFile(costReportFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertCostReportFileProcess(CostReportFileProcess.PROCESS_TYPE costReportFileProcessType,
                                             String                             processText)
    {
        final String METHOD_NAME = "insertCostReportFileProcess";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportFileProcess costReportFileProcess = new CostReportFileProcess(0,
                                                                                this.costReportFileID,
                                                                                costReportFileProcessType.name(),
                                                                                processText,
                                                                                jobExecution.getJobInstance().getJobName(),
                                                                                stepExecution.getStepName(),
                                                                                SIMPLE_NAME,
                                                                                METHOD_NAME,
                                                                                new Date());

        costReportFileProcessDAO.insertCostReportFileProcess(costReportFileProcess);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setInputFilePath(String inputFilePath)
    {
        this.inputFilePath = inputFilePath;
    }

    public void setCostReportFileDAO(CostReportFileDAO costReportFileDAO)
    {
        this.costReportFileDAO = costReportFileDAO;
    }

    public void setCostReportFileProcessDAO(CostReportFileProcessDAO costReportFileProcessDAO)
    {
        this.costReportFileProcessDAO = costReportFileProcessDAO;
    }
}
