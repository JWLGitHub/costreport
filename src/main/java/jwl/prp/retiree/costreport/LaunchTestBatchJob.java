package jwl.prp.retiree.costreport;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by jwleader on 1/7/16.
 */
public class LaunchTestBatchJob
{

    private static String CLASS_NAME = LaunchTestBatchJob.class.getName();

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(CLASS_NAME + " - BEGIN " + METHOD_NAME);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/jobs/testLoopFlowJob.xml");
        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
        Job         job         = applicationContext.getBean(Job.class);

        // Create Job Parameter(s)
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("runDate", new Date());

        // Run Job
        jobLauncher.run(job,
                        jobParametersBuilder.toJobParameters());
    }
}
