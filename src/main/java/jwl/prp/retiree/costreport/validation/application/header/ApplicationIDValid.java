package jwl.prp.retiree.costreport.validation.application.header;

import jwl.prp.retiree.costreport.dao.ApplicationDAO;
import jwl.prp.retiree.costreport.entity.Application;
import jwl.prp.retiree.costreport.entity.ApplicationHeader;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/20/15.
 */
public class ApplicationIDValid extends BaseValidator
{
    private static String CLASS_NAME  = ApplicationIDValid.class.getName();
    private static String SIMPLE_NAME = ApplicationIDValid.class.getSimpleName();


    private ApplicationDAO applicationDAO;


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationHeader applicationHeader = (ApplicationHeader) costReportRecord;
        fileContext.setApplicationHeaderApplicationID(applicationHeader.getApplicationID());
        Application application = applicationDAO.findByApplicationId(applicationHeader.getApplicationID());

        if (null == application)
        {
            fileContext.setValidApplicationID(null);
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.INVALID_APPLICATION_ID_ON_AHDR,
                                       applicationHeader.toString());
        }
        else
            fileContext.setValidApplicationID(applicationHeader.getApplicationID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setApplicationDAO(ApplicationDAO applicationDAO)
    {
        this.applicationDAO = applicationDAO;
    }
}

