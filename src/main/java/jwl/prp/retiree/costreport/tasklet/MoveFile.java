package jwl.prp.retiree.costreport.tasklet;


import jwl.prp.retiree.costreport.utils.ExecutionContextHandler;
import jwl.prp.retiree.costreport.validation.FileContext;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MoveFile implements Tasklet
{
    private static String CLASS_NAME  = MoveFile.class.getName();
    private static String SIMPLE_NAME = MoveFile.class.getSimpleName();

    private String outputFileDirectory;


    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        File outputDirectory = new File( outputFileDirectory );
        if (!outputDirectory.exists())
            outputDirectory.mkdir();

        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        String importFilePath = ExecutionContextHandler.getStringFromExecutionContext(stepExecution.getJobExecution().getExecutionContext(),
                                                                                      FileContext.IMPORT_FILE_PATH);

        File importFile = new File( importFilePath );

        String outputFilePath =  outputFileDirectory +
                                 new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date()) +
                                 importFile.getName();

        importFile.renameTo(new File( outputFilePath ));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setOutputFileDirectory(String outputFileDirectory)
    {
        this.outputFileDirectory = outputFileDirectory;
    }
}
