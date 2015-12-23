package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.dao.RdsOrgDAO;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.entity.RDSFile;
import jwl.prp.retiree.costreport.entity.RdsOrg;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.util.Calendar;


public class FileHeaderSubmitterIDValid extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderSubmitterIDValid.class.getName();
    private static String SIMPLE_NAME = FileHeaderSubmitterIDValid.class.getSimpleName();

    private RdsOrgDAO  rdsOrgDAO;
    private RDSFileDAO rdsFileDAO;


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileHeader fileHeader = (FileHeader) costReportRecord;

        if (fileHeader.getSubmitterID().trim().isEmpty())
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_SUBMITTER_ID_MISSING,
                                       costReportRecord.toString());

        RdsOrg rdsOrg = rdsOrgDAO.findByOrgIdOrgTypCd(fileHeader.getSubmitterID(),
                                                      fileHeader.getSubmitterType());

        if (null == rdsOrg)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_SUBMITTER_ID_DOES_NOT_MATCH_DSN,
                                       costReportRecord.toString());

        updateRDSFile(fileContext.getRdsFileId(),
                      fileHeader.getSubmitterID(),
                      fileHeader.getSubmitterType());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }


    private void updateRDSFile(int    rdsFileId,
                               String fileSubmitterID,
                               String fileSubmitterType)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(rdsFileId);
        rdsFile.setOrgId(fileSubmitterID);
        rdsFile.setOrgTypCd(fileSubmitterType);
        rdsFile.setUptdPgm(SIMPLE_NAME);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setRdsOrgDAO(RdsOrgDAO rdsOrgDAO)
    {
        this.rdsOrgDAO = rdsOrgDAO;
    }

    public void setRdsFileDAO(RDSFileDAO rdsFileDAO) { this.rdsFileDAO = rdsFileDAO; }
}