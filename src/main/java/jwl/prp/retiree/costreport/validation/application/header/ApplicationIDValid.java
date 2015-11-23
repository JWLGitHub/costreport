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
    public ValidationError validate(CostReportRecord costReportRecord,
                                    FileContext      fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        ApplicationHeader applicationHeader = (ApplicationHeader) costReportRecord;
        Application application = applicationDAO.findByApplicationId(applicationHeader.getApplicationID());

        if (null == application)
            return new ValidationError(ErrRef.INVALID_APPLICATION_ID_ON_AHDR,
                                       applicationHeader.toString());

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

