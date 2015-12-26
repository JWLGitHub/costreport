package jwl.prp.retiree.costreport.validation.file.name;


import jwl.prp.retiree.costreport.dao.RdsOrgDAO;
import jwl.prp.retiree.costreport.entity.FileName;
import jwl.prp.retiree.costreport.entity.RdsOrg;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class FileNameSubmitterExists extends FileNameValidator
{
    private static String CLASS_NAME  = FileNameSubmitterExists.class.getName();
    private static String SIMPLE_NAME = FileNameSubmitterExists.class.getSimpleName();

    private RdsOrgDAO rdsOrgDAO;


    @Override
    public ValidationError execute(FileName fileName)
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RdsOrg rdsOrg = rdsOrgDAO.findByOrgIdOrgTypCd(fileName.getSubmitterID(),
                                                      fileName.getSubmitterType());

        if (null == rdsOrg)
            return new ValidationError(0,
                                       ErrRef.LIST_RECORD_PLAN_SPONSOR_DOES_NOT_EXIST,
                                       ErrRef.LIST_RECORD_PLAN_SPONSOR_DOES_NOT_EXIST.getDescTxt() + " - FileName Submitter Type: " + fileName.getSubmitterType() + " FileName Submitter ID: " + fileName.getSubmitterID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
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
}
