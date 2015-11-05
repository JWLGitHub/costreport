package jwl.prp.retiree.costreport.tasklet;

import java.io.File;
import java.util.Date;

import jwl.prp.retiree.costreport.dao.CostReportFileDAO;
import jwl.prp.retiree.costreport.dao.CostReportFileProcessDAO;
import jwl.prp.retiree.costreport.entity.CostReportFile;
import jwl.prp.retiree.costreport.entity.CostReportFileProcess;
import jwl.prp.retiree.costreport.entity.FileStatus;

import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;



/**
 * Created by jwleader on 9/28/15.
 */
public class FileExists implements Tasklet
{
    private static String CLASS_NAME  = FileExists.class.getName();
    private static String SIMPLE_NAME = FileExists.class.getSimpleName();

    private JobExecution       jobExecution;
    private StepExecution      stepExecution;
    private ExecutionContext   stepExecutionContext;

    private String  inputFilePath;

    private CostReportFileDAO        costReportFileDAO;
    private CostReportFileProcessDAO costReportFileProcessDAO;


    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        stepExecution = chunkContext.getStepContext().getStepExecution();
        jobExecution = stepExecution.getJobExecution();
        stepExecutionContext = stepExecution.getExecutionContext();

        // System.out.println(System.getProperty("user.dir"));

        File inputFile = new File( inputFilePath );

        CostReportFile.STATUS_TYPE costReportFileStatusType;
        if (inputFile.exists())
            costReportFileStatusType = CostReportFile.STATUS_TYPE.EXISTS;
        else
            costReportFileStatusType = CostReportFile.STATUS_TYPE.MISSING;

        CostReportFile costReportFile = createCostReportFileInfo(costReportFileStatusType);

        if (costReportFileStatusType.toString().equalsIgnoreCase(FileStatus.FILE_STATUS_TYPE.EXISTS.name()))
            saveCostReportFileToStepExecution(costReportFile);
        else
            stepExecution.setExitStatus(ExitStatus.FAILED);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private CostReportFile createCostReportFileInfo(CostReportFile.STATUS_TYPE costReportFileStatusType)
    {
        final String METHOD_NAME = "createCostReportFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + inputFilePath + ": " + costReportFileStatusType.name());

        CostReportFile costReportFile = insertCostReportFile(costReportFileStatusType);

        //insertCostReportFileProcess(costReportFile.getId(),
        //                            costReportFileStatusType);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return costReportFile;
    }


    private CostReportFile insertCostReportFile(CostReportFile.STATUS_TYPE costReportFileStatusType)
    {
        final String METHOD_NAME = "insertCostReportFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportFile costReportFile = new CostReportFile(0,
                                                           inputFilePath,
                                                           costReportFileStatusType.name(),
                                                           jobExecution.getJobInstance().getJobName(),
                                                           stepExecution.getStepName(),
                                                           SIMPLE_NAME,
                                                           METHOD_NAME,
                                                           new Date(),
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null);

        costReportFileDAO.insertCostReportFile(costReportFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return costReportFile;
    }


    private CostReportFileProcess insertCostReportFileProcess(int                        costReportFileID,
                                                              CostReportFile.STATUS_TYPE costReportFileStatusType)
    {
        final String METHOD_NAME = "insertCostReportFileProcess";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        CostReportFileProcess costReportFileProcess = new CostReportFileProcess(0,
                                                                                costReportFileID,
                                                                                costReportFileStatusType.name(),
                                                                                null,
                                                                                jobExecution.getJobInstance().getJobName(),
                                                                                stepExecution.getStepName(),
                                                                                SIMPLE_NAME,
                                                                                METHOD_NAME,
                                                                                new Date());

        costReportFileProcessDAO.insertCostReportFileProcess(costReportFileProcess);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return costReportFileProcess;
    }


    private void saveCostReportFileToStepExecution(CostReportFile costReportFile)
    {
        final String METHOD_NAME = "saveCostReportFileToStepExecution";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        stepExecutionContext.put("costReportFileID", String.valueOf(costReportFile.getId()));

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
