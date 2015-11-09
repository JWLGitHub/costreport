package jwl.prp.retiree.costreport.tasklet;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import jwl.prp.retiree.costreport.dao.CostReportFileDAO;
import jwl.prp.retiree.costreport.dao.CostReportFileProcessDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.CostReportFile;
import jwl.prp.retiree.costreport.entity.CostReportFileProcess;
import jwl.prp.retiree.costreport.entity.FileStatus;

import jwl.prp.retiree.costreport.entity.RDSFile;
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

    private static final String EXISTS  = "EX";
    private static final String MISSING = "MI";

    private RDSFileDAO rdsFileDAO;
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

        String rdsFileStusCd;
        if (inputFile.exists())
            rdsFileStusCd = EXISTS;
        else
            rdsFileStusCd = MISSING;

        RDSFile rdsFile = createRDSFileInfo(rdsFileStusCd);

        if (rdsFile.getStusCd().equalsIgnoreCase(EXISTS))
            saveRDSFileToStepExecution(rdsFile);
        else
            stepExecution.setExitStatus(ExitStatus.FAILED);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private RDSFile createRDSFileInfo(String rdsFileStusCd)
    {
        final String METHOD_NAME = "createRDSFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + inputFilePath + ": " + rdsFileStusCd);

        RDSFile rdsFile = insertRDSFile(rdsFileStusCd);

        //insertCostReportFileProcess(costReportFile.getId(),
        //                            costReportFileStatusType);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return rdsFile;
    }


    private RDSFile insertRDSFile(String rdsFileStusCd)
    {
        final String METHOD_NAME = "insertRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = new RDSFile(0,
                                      'D',
                                      "12",
                                      new Date(),
                                      inputFilePath,
                                      null,
                                      null,
                                      'O',
                                      "1234567890",
                                      "XX",
                                      rdsFileStusCd,
                                      new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                                      SIMPLE_NAME,
                                      null,
                                      null,
                                      new Date(),
                                      new Date());

        rdsFileDAO.insertRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return rdsFile;
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


    private void saveRDSFileToStepExecution(RDSFile rdsFile)
    {
        final String METHOD_NAME = "saveRDSFileToStepExecution";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        stepExecutionContext.put("rdsFileId", String.valueOf(rdsFile.getFileId()));

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


    public void setRdsFileDAO(RDSFileDAO rdsFileDAO)
    {
        this.rdsFileDAO = rdsFileDAO;
    }

    public void setCostReportFileProcessDAO(CostReportFileProcessDAO costReportFileProcessDAO)
    {
        this.costReportFileProcessDAO = costReportFileProcessDAO;
    }
}
