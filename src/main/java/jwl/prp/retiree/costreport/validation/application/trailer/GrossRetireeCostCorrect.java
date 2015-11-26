package jwl.prp.retiree.costreport.validation.application.trailer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;


/**
 * Created by jwleader on 11/18/15.
 */
public class GrossRetireeCostCorrect extends BaseValidator
{
    private static String CLASS_NAME  = GrossRetireeCostCorrect.class.getName();
    private static String SIMPLE_NAME = GrossRetireeCostCorrect.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationTrailer applicationTrailer = (ApplicationTrailer) costReportRecord;

        BigDecimal totalGrossRetireeCost;

        try
        {
            totalGrossRetireeCost = stringv99ToBigDecimal(applicationTrailer.getTotalGrossRetireeCost());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(ErrRef.APPLICATION_TRAILER_RET_COST_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        if (totalGrossRetireeCost.subtract(fileContext.getApplicationGrossRetireeCost()) != ZERO_DOLLARS)
            return new ValidationError(ErrRef.APPLICATION_TRAILER_RET_COST_INCORRECT,
                                       "Application ID: " +
                                       applicationTrailer.getApplicationID() +
                                       " ATRL Gross Retiree Cost: " +
                                       totalGrossRetireeCost +
                                       " Computed Gross Retiree Cost : " +
                                       fileContext.getApplicationGrossRetireeCost());

        fileContext.setFileGrossRetireeCost(fileContext.getFileGrossRetireeCost().add(totalGrossRetireeCost));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
