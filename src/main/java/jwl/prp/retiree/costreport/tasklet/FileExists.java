package jwl.prp.retiree.costreport.tasklet;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Created by jwleader on 9/28/15.
 */
public class FileExists implements Tasklet
{
    private static String CLASS_NAME = FileExists.class.getName();
    private static final String INSERT_FILE_STATUS = "INSERT INTO FILE_STATUS (FILE_NAME, FILE_STATUS, FILE_ADD_USER, FILE_ADD_DATETIME, FILE_JOB_NAME, FILE_STEP_NAME) values (?,?,?,?,?,?)";
    private static final String EXISTS  = "Exists";
    private static final String MISSING = "Missing";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String inputFilePath;


    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(CLASS_NAME + " " + METHOD_NAME + " - BEGIN " + METHOD_NAME);

        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();

        JobParameters jobParameters = stepExecution.getJobParameters();

        // System.out.println(System.getProperty("user.dir"));

        File inputFile = new File( inputFilePath );

        String fileStatus;
        if (inputFile.exists())
            fileStatus = EXISTS;
        else
            fileStatus = MISSING;

        System.out.println(CLASS_NAME + " " + METHOD_NAME + " - " + inputFilePath + ": " + fileStatus);

        System.out.println(CLASS_NAME + " " + METHOD_NAME +  " - " + INSERT_FILE_STATUS + " - BEGIN");

        jdbcTemplate.update( INSERT_FILE_STATUS,
                             inputFilePath,
                             fileStatus,
                             "FileExits." + METHOD_NAME,
                             new Date(),
                             jobExecution.getJobInstance().getJobName(),
                             stepExecution.getStepName());

        System.out.println(CLASS_NAME + " " + METHOD_NAME + " - " + INSERT_FILE_STATUS + " - END");

        if (fileStatus.equalsIgnoreCase(MISSING))
            stepExecution.setTerminateOnly();

        System.out.println(CLASS_NAME + " " + METHOD_NAME +  " - END   " + METHOD_NAME);
        return RepeatStatus.FINISHED;
    }


    public String getInputFilePath()
    {
        return inputFilePath;
    }


    public void setInputFilePath(String inputFilePath)
    {
        this.inputFilePath = inputFilePath;
    }
}
