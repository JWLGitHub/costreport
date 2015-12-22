package jwl.prp.retiree.costreport;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by jwleader on 9/29/15.
 */


public class LaunchBatchJob
{
    private static String CLASS_NAME = LaunchBatchJob.class.getName();

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(CLASS_NAME + " - BEGIN " + METHOD_NAME);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/jobs/costReportJob.xml");
        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
        Job         job         = applicationContext.getBean(Job.class);

        // Create Job Parameter(s)
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("runDate", new Date());
        jobParametersBuilder.addString("inputFilePath",     "input/invalidCostReport.txt");
        jobParametersBuilder.addString("goodFileDirectory", "goodFiles/");
        jobParametersBuilder.addString("badFileDirectory",  "badFiles/");

        // Run Job
        jobLauncher.run(job,
                        jobParametersBuilder.toJobParameters());
    }
}