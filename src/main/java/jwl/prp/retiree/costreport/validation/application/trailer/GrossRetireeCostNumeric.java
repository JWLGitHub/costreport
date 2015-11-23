package jwl.prp.retiree.costreport.validation.application.trailer;

import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


/**
 * Created by jwleader on 11/18/15.
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

        ApplicationTrailer applicationTrailer = (ApplicationTrailer) costReportRecord;

        try
        {
            stringv99ToBigDecimal(applicationTrailer.getTotalGrossRetireeCost());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(ErrRef.APPLICATION_TRAILER_RET_COST_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}