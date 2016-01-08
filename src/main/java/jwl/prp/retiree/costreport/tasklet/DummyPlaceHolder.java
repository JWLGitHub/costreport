package jwl.prp.retiree.costreport.tasklet;


import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class DummyPlaceHolder implements Tasklet
{
    private static String CLASS_NAME  = DummyPlaceHolder.class.getName();
    private static String SIMPLE_NAME = DummyPlaceHolder.class.getSimpleName();


    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        // System.out.println(System.getProperty("user.dir"));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - END");
        return RepeatStatus.FINISHED;
    }
}