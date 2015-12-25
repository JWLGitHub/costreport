package jwl.prp.retiree.costreport.tasklet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrCtgRef;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusRef;

import jwl.prp.retiree.costreport.validation.FileContext;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;


/**
 * Created by jwleader on 9/28/15.
 */
public class FileExists implements Tasklet
{
    private static String CLASS_NAME  = FileExists.class.getName();
    private static String SIMPLE_NAME = FileExists.class.getSimpleName();

    private JobExecution       jobExecution;
    private StepExecution      stepExecution;
    private ExecutionContext   stepExecutionContext;

    private String  inputFilePath;

    private RDSFileDAO rdsFileDAO;
    private FileErrDAO fileErrDAO;


    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        stepExecution = chunkContext.getStepContext().getStepExecution();
        jobExecution = stepExecution.getJobExecution();
        stepExecutionContext = stepExecution.getExecutionContext();

        // System.out.println(System.getProperty("user.dir"));

        File inputFile = new File( inputFilePath );

        StusRef fileStatus;
        if (inputFile.exists())
            fileStatus = StusRef.FILE_EXISTS;
         else
            fileStatus= StusRef.FILE_MISSING;

        RDSFile rdsFile = createRDSFileInfo(inputFile,
                                            fileStatus);

        if (fileStatus == StusRef.FILE_EXISTS)
            saveRdsFileIdToStepExecution(rdsFile.getFileId());
        else
            stepExecution.setExitStatus(ExitStatus.FAILED);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private RDSFile createRDSFileInfo(File    inputFile,
                                      StusRef fileStatus)
    {
        final String METHOD_NAME = "createRDSFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = insertRDSFile(inputFile,
                                        fileStatus);

        if (fileStatus ==  StusRef.FILE_MISSING)
            insertFileErr(rdsFile.getFileId());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return rdsFile;
    }


    private RDSFile insertRDSFile(File    inputFile,
                                  StusRef fileStatus)
    {
        final String METHOD_NAME = "insertRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Timestamp fileDtTm = null;
        if (fileStatus == StusRef.FILE_EXISTS)
            fileDtTm = new Timestamp(new Date(inputFile.lastModified()).getTime());

        RDSFile rdsFile = new RDSFile(0,
                                  "D",
                                  "12",
                                  fileDtTm,
                                  inputFilePath,
                                  null,
                                  null,
                                  null,
                                  null,
                                  fileStatus.getStusCtgryCd(),
                                  fileStatus.getStusCd(),
                                  new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
                                  SIMPLE_NAME,
                                  null,
                                  null,
                                  new Date(),
                                  new Date());

        rdsFileDAO.insertRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return rdsFile;
    }


    private FileErr insertFileErr(int rdsFileId)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileErr fileErr = new FileErr(rdsFileId,
                                      ErrRef.CRFILE_IS_MISSING.getErrCd(),
                                      ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                      1,
                                      ErrRef.CRFILE_IS_MISSING.getDescTxt() + " - " + inputFilePath);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return fileErr;
    }


    private void saveRdsFileIdToStepExecution(int rdsFileId)
    {
        final String METHOD_NAME = "saveRdsFileIdToStepExecution";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        stepExecutionContext.put(FileContext.RDS_FILE_ID, String.valueOf(rdsFileId));

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
