package jwl.prp.retiree.costreport.tasklet;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import jwl.prp.retiree.costreport.dao.CostReportFileDAO;
import jwl.prp.retiree.costreport.dao.CostReportFileProcessDAO;
import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.*;

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
    private FileErrDAO fileErrDAO;


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
            saveRDSFileIdToStepExecution(rdsFile.getFileId());
        else
            stepExecution.setExitStatus(ExitStatus.FAILED);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private RDSFile createRDSFileInfo(String rdsFileStusCd)
    {
        final String METHOD_NAME = "createRDSFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + inputFilePath + ": " + rdsFileStusCd);

        RDSFile rdsFile = insertRDSFile(rdsFileStusCd);

        if (rdsFile.getStusCd().equalsIgnoreCase(MISSING))
            insertFileErr(rdsFile.getFileId());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return rdsFile;
    }


    private RDSFile insertRDSFile(String rdsFileStusCd)
    {
        final String METHOD_NAME = "insertRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = new RDSFile(0,
                                      "D",
                                      "12",
                                      new Date(),
                                      inputFilePath,
                                      null,
                                      null,
                                      "O",
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


    private FileErr insertFileErr(int rdsFileId)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileErr fileErr = new FileErr(rdsFileId,
                                      "0440",
                                      "FE",
                                      1,
                                      null);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return fileErr;
    }


    private void saveRDSFileIdToStepExecution(int rdsFileId)
    {
        final String METHOD_NAME = "saveRDSFileToStepExecution";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        stepExecutionContext.put("RDSFileId", String.valueOf(rdsFileId));

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


    public void setFileErrDAO(FileErrDAO fileErrDAO) { this.fileErrDAO = fileErrDAO; }
}
