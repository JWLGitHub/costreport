package jwl.prp.retiree.costreport.validation.application.trailer;

import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 12/29/15.
 */
public class LimitReductionNumeric extends BaseValidator
{
    private static String CLASS_NAME  = LimitReductionNumeric.class.getName();
    private static String SIMPLE_NAME = LimitReductionNumeric.class.getSimpleName();


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

        try
        {
            stringv99ToBigDecimal(applicationTrailer.getTotalLimitReduction());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_LIMIT_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}