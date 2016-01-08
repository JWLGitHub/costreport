package jwl.prp.retiree.costreport.tasklet;


import java.io.File;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Date;

import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrCtgRef;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusRef;
import jwl.prp.retiree.costreport.utils.ExecutionContextHandler;
import jwl.prp.retiree.costreport.validation.FileContext;

import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class FileExists implements Tasklet
{
    private static String CLASS_NAME  = FileExists.class.getName();
    private static String SIMPLE_NAME = FileExists.class.getSimpleName();


    private RDSFileDAO rdsFileDAO;
    private FileErrDAO fileErrDAO;


    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext     chunkContext)
                                throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN");

        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        String importFilePath = ExecutionContextHandler.getStringFromExecutionContext(stepExecution.getJobExecution().getExecutionContext(),
                                                                                      FileContext.IMPORT_FILE_PATH);

        File importFile = new File( importFilePath );

        StusRef fileStatus;
        if (importFile.exists())
            fileStatus = StusRef.FILE_EXISTS;
         else
            fileStatus= StusRef.FILE_MISSING;

        RDSFile rdsFile = createRDSFileInfo(importFile,
                                            fileStatus);

        if (fileStatus == StusRef.FILE_EXISTS)
            stepExecution.getExecutionContext().put(FileContext.RDS_FILE_ID, String.valueOf(rdsFile.getFileId()));
        else
            stepExecution.setExitStatus(ExitStatus.FAILED);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private RDSFile createRDSFileInfo(File    importFile,
                                      StusRef fileStatus)
    {
        final String METHOD_NAME = "createRDSFileInfo";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = insertRDSFile(importFile,
                                        fileStatus);

        if (fileStatus == StusRef.FILE_MISSING)
            insertFileErr(rdsFile.getFileId(),
                          importFile.getPath());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return rdsFile;
    }


    private RDSFile insertRDSFile(File    importFile,
                                  StusRef fileStatus)
    {
        final String METHOD_NAME = "insertRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Timestamp fileDtTm = null;
        if (fileStatus == StusRef.FILE_EXISTS)
            fileDtTm = new Timestamp(new Date(importFile.lastModified()).getTime());

        RDSFile rdsFile = new RDSFile(0,
                                  "D",
                                  "12",
                                  fileDtTm,
                                  importFile.getPath(),
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


    private FileErr insertFileErr(int    rdsFileId,
                                  String importFilePath)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileErr fileErr = new FileErr(rdsFileId,
                                      ErrRef.CRFILE_IS_MISSING.getErrCd(),
                                      ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                      1,
                                      ErrRef.CRFILE_IS_MISSING.getDescTxt() + " - " + importFilePath);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return fileErr;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setRdsFileDAO(RDSFileDAO rdsFileDAO)
    {
        this.rdsFileDAO = rdsFileDAO;
    }

    public void setFileErrDAO(FileErrDAO fileErrDAO) { this.fileErrDAO = fileErrDAO; }
}
