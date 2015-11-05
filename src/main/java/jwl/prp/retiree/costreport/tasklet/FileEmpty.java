package jwl.prp.retiree.costreport.tasklet;

import jwl.prp.retiree.costreport.dao.CostReportFileDAO;
import jwl.prp.retiree.costreport.dao.CostReportFileProcessDAO;
import jwl.prp.retiree.costreport.entity.CostReportFile;
import jwl.prp.retiree.costreport.entity.CostReportFileProcess;
import jwl.prp.retiree.costreport.entity.FileStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.util.Date;

/**
 * Created by jwleader on 11/3/15.
 */
public class FileEmpty implements Tasklet
{
    private static String CLASS_NAME  = FileEmpty.class.getName();
    private static String SIMPLE_NAME = FileEmpty.class.getSimpleName();

    private JobExecution      jobExecution;
    private ExecutionContext  jobExecutionContext;
    private StepExecution     stepExecution;

    private String            inputFilePath;

    private CostReportFileDAO        costReportFileDAO;
    private CostReportFileProcessDAO costReportFileProcessDAO;


    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        stepExecution = chunkContext.getStepContext().getStepExecution();
        jobExecution = stepExecution.getJobExecution();
        jobExecutionContext = jobExecution.getExecutionContext();

        // System.out.println(System.getProperty("user.dir"));

        File inputFile = new File( inputFilePath );

        if (inputFile.length() == 0)
        {
            int costReportFileID = getCostReportFileID();
            updateCostReportFileInfo(costReportFileID);
            insertCostReportFileProcess(costReportFileID);
            stepExecution.setExitStatus(ExitStatus.FAILED);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private int getCostReportFileID()
    {
        final String METHOD_NAME = "getCostReportFileID";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object costReportFileID = jobExecutionContext.get("costReportFileID");
        if (null == costReportFileID  ||
            costReportFileID.toString().equalsIgnoreCase(""))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - costReportFileID: MISSING");
            throw new RuntimeException("'costReportFileID' MISSING from jobExecutionContext");
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(costReportFileID.toString());
    }


    private void updateCostReportFileInfo(int costReportFileID)
    {
        final String METHOD_NAME = "updateCostReportFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportFile costReportFile = costReportFileDAO.findByCostReportFileID(costReportFileID);
        costReportFile.setStatus(CostReportFile.STATUS_TYPE.FILE_IS_EMPTY.name());
        costReportFile.setUpdateJob(jobExecution.getJobInstance().getJobName());
        costReportFile.setUpdateStep(stepExecution.getStepName());
        costReportFile.setUpdateProgram(SIMPLE_NAME);
        costReportFile.setUpdateMethod(METHOD_NAME);
        costReportFile.setUpdateDateTime(new Date());
        costReportFileDAO.updateCostReportFile(costReportFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertCostReportFileProcess(int costReportFileID)
    {
        final String METHOD_NAME = "insertCostReportFileProcess";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportFileProcess costReportFileProcess = new CostReportFileProcess(0,
                                                                                costReportFileID,
                                                                                CostReportFileProcess.PROCESS_TYPE.CRFILE_IS_EMPTY.name(),
                                                                                null,
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
