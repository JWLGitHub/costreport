package jwl.prp.retiree.costreport.decider;

import jwl.prp.retiree.costreport.validation.FileContext;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ExecutionContext;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jwleader on 1/7/16.
 */
public class ImportFiles implements JobExecutionDecider
{
    private static String CLASS_NAME  = ImportFiles.class.getName();
    private static String SIMPLE_NAME = ImportFiles.class.getSimpleName();

    public static final String IMPORT_FILE = "IMPORT FILE";

    private String  importDirectory;

    private LinkedList<String> importFilePaths;


    public FlowExecutionStatus decide(JobExecution  jobExecution,
                                      StepExecution stepExecution)
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        if (!jobExecution.getExecutionContext().containsKey(FileContext.IMPORT_FILE_PATH))
        {
            File fileDirectory = new File(importDirectory);

            if (!fileDirectory.isDirectory())
                return FlowExecutionStatus.FAILED;

            importFilePaths = getImportFilePaths(fileDirectory);
        }

        String importFile = importFilePaths.poll();
        if (null != importFile)
        {
            jobExecution.getExecutionContext().put(FileContext.IMPORT_FILE_PATH, importFile);
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + FileContext.IMPORT_FILE_PATH + ": " + importFile);
            return new FlowExecutionStatus(IMPORT_FILE);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return FlowExecutionStatus.COMPLETED;
    }


    private LinkedList getImportFilePaths(File fileDirectory)
    {
        final String METHOD_NAME = "getImportFilePaths";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        LinkedList<String> importFiles = new LinkedList<String>();

        for (File file : fileDirectory.listFiles())
        {
            if (file.isFile())
            {
                System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - File Path: " + file.getPath());
                importFiles.addLast(file.getPath());
            }
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return importFiles;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setImportDirectory(String importDirectory)
    {
        this.importDirectory = importDirectory;
    }
}
