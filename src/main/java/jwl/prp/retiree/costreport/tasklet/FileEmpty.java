package jwl.prp.retiree.costreport.tasklet;


import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.RDSFile;
import jwl.prp.retiree.costreport.entity.FileErr;

import jwl.prp.retiree.costreport.enums.ErrCtgRef;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.util.Calendar;


/**
 * Created by jwleader on 11/3/15.
 */
public class FileEmpty implements Tasklet
{
    private static String CLASS_NAME  = FileEmpty.class.getName();
    private static String SIMPLE_NAME = FileEmpty.class.getSimpleName();

    private JobExecution      jobExecution;
    private ExecutionContext  jobExecutionContext;
    private StepExecution     stepExecution;

    private String            inputFilePath;

    private static final String RDS_FILE_ID = "rdsFileId";

    private RDSFileDAO        rdsFileDAO;
    private FileErrDAO        fileErrDAO;


    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        stepExecution       = chunkContext.getStepContext().getStepExecution();
        jobExecution        = stepExecution.getJobExecution();
        jobExecutionContext = jobExecution.getExecutionContext();

        // System.out.println(System.getProperty("user.dir"));

        File inputFile = new File( inputFilePath );

        if (inputFile.length() == 0)
        {
            int rdsFileId = getRdsFileId();
            updateRDSFile(rdsFileId);
            insertFileErr(rdsFileId);
            stepExecution.setExitStatus(ExitStatus.FAILED);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private int getRdsFileId()
    {
        final String METHOD_NAME = "getRdsFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object rdsFileId = jobExecutionContext.get(RDS_FILE_ID);
        if (null == rdsFileId  ||
            rdsFileId.toString().equalsIgnoreCase(""))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + RDS_FILE_ID + ": MISSING");
            throw new RuntimeException("'" + RDS_FILE_ID + "' MISSING from jobExecutionContext");
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(rdsFileId.toString());
    }


    private void updateRDSFile(int rdsFileId)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(rdsFileId);
        rdsFile.setStusCtgryCd(StusCtgry.FILE_STATUS.getStusCtgryCd());
        rdsFile.setStusCd(StusRef.FILE_EMPTY.getStusCd());
        rdsFile.setUptdPgm(SIMPLE_NAME);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertFileErr(int rdsFileId)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileErr fileErr = new FileErr(rdsFileId,
                                      ErrRef.CRFILE_IS_EMPTY.getErrCd(),
                                      ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                      1,
                                      ErrRef.CRFILE_IS_EMPTY.getDescTxt() + ": " + inputFilePath);

        fileErrDAO.insertFileErr(fileErr);

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
