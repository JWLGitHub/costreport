package jwl.prp.retiree.costreport.tasklet;

import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.FileErr;
import jwl.prp.retiree.costreport.entity.FileName;
import jwl.prp.retiree.costreport.entity.RDSFile;
import jwl.prp.retiree.costreport.enums.StusRef;
import jwl.prp.retiree.costreport.utils.ExecutionContextHandler;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.validation.file.name.FileNameValidator;
import jwl.prp.retiree.costreport.validation.FileContext;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;

import java.util.Calendar;
import java.util.List;


public class FileNameValid implements Tasklet
{
    private static String CLASS_NAME  = FileNameValid.class.getName();
    private static String SIMPLE_NAME = FileNameValid.class.getSimpleName();


    private RDSFileDAO rdsFileDAO;
    private FileErrDAO fileErrDAO;

    /*
     *---   Validators
     */
    protected List<FileNameValidator> fileNameValidators;


    @Override
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
        FileName importFileName = new FileName(importFile.getName());

        ValidationError validationError = validateFileName(importFileName);

        int rdsFileId = ExecutionContextHandler.getIntegerFromExecutionContext(stepExecution.getJobExecution().getExecutionContext(),
                                                                               FileContext.RDS_FILE_ID);

        updateRDSFile(rdsFileId,
                      importFileName,
                      validationError);

        if (null == validationError)
        {
            stepExecution.getExecutionContext().put(FileContext.FILE_NAME_SUBMITTER_TYPE, importFileName.getSubmitterType());
            stepExecution.getExecutionContext().put(FileContext.FILE_NAME_SUBMITTER_ID,   importFileName.getSubmitterID());
        }
        else
        {
            // *** FILE NAME ERROR EXISTS ***
            insertFileErr(rdsFileId,
                          validationError);

            stepExecution.setExitStatus(ExitStatus.FAILED);
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME +  " - END");
        return RepeatStatus.FINISHED;
    }


    private ValidationError validateFileName(FileName fileName)
    {
        final String METHOD_NAME = "validateFileName";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        ValidationError validationError = null;

        if (null != fileNameValidators &&
            !fileNameValidators.isEmpty())
        {
            for (FileNameValidator fileNameValidator : fileNameValidators)
            {
                validationError = fileNameValidator.execute(fileName);

                if (null != validationError)
                    break;

            }
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return validationError;
    }


    private void updateRDSFile(int             rdsFileId,
                               FileName        fileName,
                               ValidationError validationError)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(rdsFileId);

        if (null == validationError)
        {
            rdsFile.setOrgTypCd(fileName.getSubmitterType());
            rdsFile.setOrgId(fileName.getSubmitterID());
        }
        else
        {
            // *** FILE NAME ERROR EXISTS ***
            rdsFile.setStusCd(StusRef.FILE_REJECTED_BAD_STRUCTURE.getStusCd());
        }

        rdsFile.setSubmOrgId(fileName.getSubmitterID());
        rdsFile.setUptdPgm(SIMPLE_NAME);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertFileErr(int             rdsFileId,
                               ValidationError validationError)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileErr fileErr = new FileErr(rdsFileId,
                                      validationError.getErrRef().getErrCd(),
                                      validationError.getErrRef().getErrCtgryCd(),
                                      1,
                                      validationError.getErrMessage());

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

    public void setFileNameValidators(List<FileNameValidator> fileNameValidators)
    {
        final String METHOD_NAME = "setFileNameValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileNameValidators = fileNameValidators;
    }
}
