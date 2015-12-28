package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;


public class EstimatedPremiumCorrect extends BaseValidator
{
    private static String CLASS_NAME       = EstimatedPremiumCorrect.class.getName();
    private static String SIMPLE_NAME      = EstimatedPremiumCorrect.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileTrailer fileTrailer = (FileTrailer) costReportRecord;

        BigDecimal grandEstimatedPremium;

        try
        {
            grandEstimatedPremium = stringv99ToBigDecimal(fileTrailer.getGrandEstimatedPremium());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_PREMIUM_AMOUNT_NON_NUMERIC,
                                       fileTrailer.toString());
        }

        if (grandEstimatedPremium.subtract(fileContext.getFileEstimatedPremium()) != ZERO_DOLLARS)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_PREMIUM_AMOUNT_INCORRECT,
                                       "FTRL Estimated Premium: " + grandEstimatedPremium + " Computed Estimated Premium : " + fileContext.getFileEstimatedPremium());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
