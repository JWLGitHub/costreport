package jwl.prp.retiree.costreport.tasklet;


import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.RDSFile;
import jwl.prp.retiree.costreport.entity.FileErr;
import jwl.prp.retiree.costreport.enums.ErrCtgRef;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;
import jwl.prp.retiree.costreport.utils.ExecutionContextHandler;
import jwl.prp.retiree.costreport.validation.FileContext;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;

import java.util.Calendar;


public class FileEmpty implements Tasklet
{
    private static String CLASS_NAME  = FileEmpty.class.getName();
    private static String SIMPLE_NAME = FileEmpty.class.getSimpleName();


    private RDSFileDAO        rdsFileDAO;
    private FileErrDAO        fileErrDAO;


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

        if (importFile.length() == 0)
        {
            int rdsFileId = ExecutionContextHandler.getIntegerFromExecutionContext(stepExecution.getJobExecution().getExecutionContext(),
                                                                                   FileContext.RDS_FILE_ID);

            updateRDSFile(rdsFileId);
            insertFileErr(rdsFileId,
                          importFilePath);

            stepExecution.setExitStatus(ExitStatus.FAILED);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
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


    private void insertFileErr(int    rdsFileId,
                               String importFilePath)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileErr fileErr = new FileErr(rdsFileId,
                                      ErrRef.CRFILE_IS_EMPTY.getErrCd(),
                                      ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                      1,
                                      ErrRef.CRFILE_IS_EMPTY.getDescTxt() + " - " + importFilePath);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
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
