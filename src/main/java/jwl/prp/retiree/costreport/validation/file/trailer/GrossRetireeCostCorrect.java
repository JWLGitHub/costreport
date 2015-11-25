package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;

import java.math.BigDecimal;

/**
 * Created by jwleader on 11/17/15.
 */
public class GrossRetireeCostCorrect extends BaseValidator
{
    private static String CLASS_NAME       = GrossRetireeCostCorrect.class.getName();
    private static String SIMPLE_NAME      = GrossRetireeCostCorrect.class.getSimpleName();


    @Override
    public ValidationError validate(CostReportRecord costReportRecord,
                                    FileContext      fileContext)
                                    throws Exception
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileTrailer fileTrailer = (FileTrailer) costReportRecord;

        BigDecimal grandGrossRetireeCost;

        try
        {
            grandGrossRetireeCost = stringv99ToBigDecimal(fileTrailer.getGrandGrossRetireeCost());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(ErrRef.FILE_TRAILER_RET_COST_AMOUNT_NON_NUMERI,
                                       fileTrailer.toString());
        }

        if (grandGrossRetireeCost.subtract(fileContext.getFileGrossRetireeCost()) != ZERO_DOLLARS)
            return new ValidationError(ErrRef.FILE_TRAILER_RET_COST_AMOUNT_INCORRECT,
                                       "FTRL Gross Retiree Cost: " +
                                       grandGrossRetireeCost +
                                       " Computed Gross Retiree Cost : " +
                                       fileContext.getFileGrossRetireeCost());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
