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
    public ValidationError validate(CostReportRecord costReportRecord,
                                    FileContext      fileContext)
                                    throws Exception
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationDetail))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;

        PlanOptions planOptions = planOptionsDAO.findByApplicationIdGroupNumber(fileContext.getApplicationID(),
                                                                                applicationDetail.getUniqueBenefitOptionIdentifier());

        if (null == planOptions)
            return new ValidationError(ErrRef.INVALID_UBOI_ON_DETL,
                                       "Application ID: " +
                                       fileContext.getApplicationID() +
                                       " Unique Benefit Option ID: " +
                                       applicationDetail.getUniqueBenefitOptionIdentifier());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
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