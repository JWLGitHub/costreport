package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;


public class GrossRetireeCostNumeric extends BaseValidator
{
    private static String CLASS_NAME  = GrossRetireeCostNumeric.class.getName();
    private static String SIMPLE_NAME = GrossRetireeCostNumeric.class.getSimpleName();


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

        try
        {
            stringv99ToBigDecimal(fileTrailer.getGrandGrossRetireeCost());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(ErrRef.FILE_TRAILER_RET_COST_AMOUNT_NON_NUMERI,
                                       fileTrailer.toString());
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
