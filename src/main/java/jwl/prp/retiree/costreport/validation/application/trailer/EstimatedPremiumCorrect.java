package jwl.prp.retiree.costreport.validation.application.trailer;

import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;

/**
 * Created by jwleader on 12/28/15.
 */
public class EstimatedPremiumCorrect extends BaseValidator
{
    private static String CLASS_NAME  = EstimatedPremiumCorrect.class.getName();
    private static String SIMPLE_NAME = EstimatedPremiumCorrect.class.getSimpleName();


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

        BigDecimal totalEstimatedPremium;

        try
        {
            totalEstimatedPremium = stringv99ToBigDecimal(applicationTrailer.getTotalEstimatedPremium());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_PREMIUM_AMOUNT_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        if (totalEstimatedPremium.subtract(fileContext.getApplicationEstimatedPremium()) != ZERO_DOLLARS)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_PREMIUM_AMOUNT_INCORRECT,
                                       "Application ID: " + applicationTrailer.getApplicationID() + " ATRL Estimated Premium: " + totalEstimatedPremium + " Computed Estimated Premium : " + fileContext.getApplicationEstimatedPremium());

        fileContext.setFileEstimatedPremium(fileContext.getFileEstimatedPremium().add(totalEstimatedPremium));
        fileContext.setApplicationEstimatedPremium(new BigDecimal("0"));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
