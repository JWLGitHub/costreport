package jwl.prp.retiree.costreport.tasklet;


import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by jwleader on 11/22/15.
 */
public class MoveFile implements Tasklet
{
    private static String CLASS_NAME  = MoveFile.class.getName();
    private static String SIMPLE_NAME = MoveFile.class.getSimpleName();

    private String inputFilePath;
    private String outputFileDirectory;


    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        File outputDirectory = new File( outputFileDirectory );
        if (!outputDirectory.exists())
            outputDirectory.mkdir();

        File inputFile = new File( inputFilePath );

        String outputFilePath =  outputFileDirectory +
                                 new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date()) +
                                 inputFile.getName();

        inputFile.renameTo(new File( outputFilePath ));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
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

    public void setOutputFileDirectory(String outputFileDirectory)
    {
        this.outputFileDirectory = outputFileDirectory;
    }
}
