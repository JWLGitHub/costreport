package jwl.prp.retiree.costreport.validation.application.detail;

import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;


/**
 * Created by jwleader on 11/17/15.
 */
public class GrossRetireeCostNumeric extends BaseValidator
{

    private static String CLASS_NAME  = GrossRetireeCostNumeric.class.getName();
    private static String SIMPLE_NAME = GrossRetireeCostNumeric.class.getSimpleName();


    @Override
    public ValidationError validate(CostReportRecord costReportRecord,
                                    FileContext      fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;

        try
        {
            stringv99ToBigDecimal(applicationDetail.getGrossRetireeCost());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(ErrRef.APPLICATION_DETAIL_RET_COST_IS_NON_NUMERIC,
                                       applicationDetail.toString());
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}