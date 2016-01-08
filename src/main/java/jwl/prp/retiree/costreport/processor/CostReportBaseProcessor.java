package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.FileErr;
import jwl.prp.retiree.costreport.entity.RDSFile;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;

import java.util.Calendar;
import java.util.List;


public abstract class CostReportBaseProcessor
{
    private static String CLASS_NAME  = CostReportBaseProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportBaseProcessor.class.getSimpleName();

    protected RDSFileDAO  rdsFileDAO;
    protected FileErrDAO  fileErrDAO;

    protected FileContext fileContext = new FileContext();

    /*
     *---   Validators
     */
    protected List<BaseValidator> fileHeaderValidators;

    protected List<BaseValidator> applicationHeaderValidators;

    protected List<BaseValidator> applicationDetailValidators;

    protected List<BaseValidator> applicationTrailerValidators;

    protected List<BaseValidator> fileTrailerValidators;

    /*
     *---   Err Ref(s) NOT Error(s)
     */
    protected List<ErrRef> errRefsNotErrors;


    protected void updateRDSFile(StusRef fileStatus,
                                 String  programName)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(fileContext.getRdsFileId());
        rdsFile.setStusCtgryCd(StusCtgry.FILE_STATUS.getStusCtgryCd());
        rdsFile.setStusCd(fileStatus.getStusCd());
        rdsFile.setUptdPgm(programName);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }



    protected void insertFileErr(ErrRef errRef,
                                 String errInfo)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileContext.setFileErrSeqNum(fileContext.getFileErrSeqNum() + 1);

        FileErr fileErr = new FileErr(fileContext.getRdsFileId(),
                errRef.getErrCd(),
                errRef.getErrCtgryCd(),
                fileContext.getFileErrSeqNum(),
                errInfo);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setRdsFileDAO(RDSFileDAO rdsFileDAO) { this.rdsFileDAO = rdsFileDAO; }

    public void setFileErrDAO(FileErrDAO fileErrDAO) { this.fileErrDAO = fileErrDAO; }

    public void setFileHeaderValidators(List<BaseValidator> fileHeaderValidators)
    {
        final String METHOD_NAME = "setFileHeaderValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileHeaderValidators = fileHeaderValidators;
    }


    public void setApplicationHeaderValidators(List<BaseValidator> applicationHeaderValidators)
    {
        final String METHOD_NAME = "setApplicationHeaderValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationHeaderValidators = applicationHeaderValidators;
    }


    public void setApplicationDetailValidators(List<BaseValidator> applicationDetailValidators)
    {
        final String METHOD_NAME = "setApplicationDetailValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationDetailValidators = applicationDetailValidators;
    }


    public void setApplicationTrailerValidators(List<BaseValidator> applicationTrailerValidators)
    {
        final String METHOD_NAME = "setApplicationTrailerValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationTrailerValidators = applicationTrailerValidators;
    }


    public void setFileTrailerValidators(List<BaseValidator> fileTrailerValidators)
    {
        final String METHOD_NAME = "setFileTrailerValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileTrailerValidators = fileTrailerValidators;
    }


    public void setErrRefsNotErrors(List<ErrRef> errRefsNotErrors)
    {
        final String METHOD_NAME = "setErrRefsNotErrors";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.errRefsNotErrors = errRefsNotErrors;
    }
}
