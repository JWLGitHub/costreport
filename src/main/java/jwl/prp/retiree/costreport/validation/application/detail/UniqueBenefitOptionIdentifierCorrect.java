package jwl.prp.retiree.costreport.validation.application.detail;


import jwl.prp.retiree.costreport.dao.PlanOptionsDAO;
import jwl.prp.retiree.costreport.entity.PlanOptions;
import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class UniqueBenefitOptionIdentifierCorrect extends BaseValidator
{

    private static String CLASS_NAME  = UniqueBenefitOptionIdentifierCorrect.class.getName();
    private static String SIMPLE_NAME = UniqueBenefitOptionIdentifierCorrect.class.getSimpleName();

    private PlanOptionsDAO planOptionsDAO;


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationDetail))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;

        PlanOptions planOptions = planOptionsDAO.findByApplicationIdGroupNumber(fileContext.getValidApplicationID(),
                                                                                applicationDetail.getUniqueBenefitOptionIdentifier());

        if (null == planOptions)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.INVALID_UBOI_ON_DETL,
                                       "Application ID: " + fileContext.getValidApplicationID() + " Unique Benefit Option ID: " + applicationDetail.getUniqueBenefitOptionIdentifier());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        // *** NOT AN ERROR ***    (BUT . . .  We'll still track it)
        return new ValidationError(fileContext.getFileRecordCounter(),
                                   ErrRef.RX_GROUP_NUMBER_NOT_AN_ERR,
                                   "Application ID: " + fileContext.getValidApplicationID() + " Unique Benefit Option ID: " + applicationDetail.getUniqueBenefitOptionIdentifier());
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setPlanOptionsDAO(PlanOptionsDAO planOptionsDAO)
    {
        this.planOptionsDAO = planOptionsDAO;
    }
}