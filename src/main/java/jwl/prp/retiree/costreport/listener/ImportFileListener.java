package jwl.prp.retiree.costreport.listener;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileStatus;
import org.springframework.batch.core.*;
import org.springframework.batch.core.listener.SkipListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * Created by jwleader on 10/28/15.
 */
public class ImportFileListener extends    SkipListenerSupport<CostReportRecord, CostReportRecord>
                                implements StepExecutionListener
{
    private static String CLASS_NAME  = ImportFileListener.class.getName();
    private static String SIMPLE_NAME = ImportFileListener.class.getSimpleName();

    private String inputFilePath;

    private JobExecution jobExecution;

    private StepExecution stepExecution;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_FILE_STATUS = "INSERT INTO FILE_STATUS (FILE_NAME, FILE_STATUS, FILE_STATUS_REASON, FILE_ADD_USER, FILE_ADD_DATETIME, FILE_JOB_NAME, FILE_STEP_NAME) values (?,?,?,?,?,?,?)";


    public void beforeStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "beforeStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.stepExecution = stepExecution;
        this.jobExecution  = stepExecution.getJobExecution();
    }


    @Override
    public void onSkipInRead(Throwable exception)
    {
        final String METHOD_NAME = "onSkipInRead";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        insertFileStatus(FileStatus.FILE_STATUS_TYPE.INVALID,
                exception.getMessage(),
                SIMPLE_NAME + "." + METHOD_NAME,
                jobExecution.getJobInstance().getJobName(),
                stepExecution.getStepName());
    }


    @Override
    public void onSkipInProcess(CostReportRecord costReportRecord,
                                Throwable        exception)
    {
        final String METHOD_NAME = "onSkipInProcess";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        insertFileStatus(FileStatus.FILE_STATUS_TYPE.INVALID,
                         exception.getMessage(),
                         SIMPLE_NAME + "." + METHOD_NAME,
                         jobExecution.getJobInstance().getJobName(),
                         stepExecution.getStepName());
    }


    public ExitStatus afterStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "afterStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }


    private void insertFileStatus(FileStatus.FILE_STATUS_TYPE fileStatusType,
                                  String                      fileStatusReason,
                                  String                      fileAddUser,
                                  String                      fileJobName,
                                  String                      fileStepName)
    {
        final String METHOD_NAME = "insertFileStatus";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        jdbcTemplate.update(INSERT_FILE_STATUS,
                            inputFilePath,
                            fileStatusType.name(),
                            fileStatusReason,
                            fileAddUser,
                            new Date(),
                            fileJobName,
                            fileStepName);

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
}
