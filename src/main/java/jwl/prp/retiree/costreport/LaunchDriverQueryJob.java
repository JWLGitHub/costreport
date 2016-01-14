package jwl.prp.retiree.costreport;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;


public class LaunchDriverQueryJob
{
    private static String CLASS_NAME = LaunchBatchJob.class.getName();

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(CLASS_NAME + " - BEGIN " + METHOD_NAME);

        // Initialize Application Context
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/jobs/driverQueryJob.xml");
        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
        Job         job         = (Job) applicationContext.getBean("driverQueryJob");

        // Create Job Parameter(s)
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("runDate", new Date());

        // Run Job
        jobLauncher.run(job,
                jobParametersBuilder.toJobParameters());
    }
}
