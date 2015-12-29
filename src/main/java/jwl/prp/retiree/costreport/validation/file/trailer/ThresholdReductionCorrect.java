package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;

/**
 * Created by jwleader on 12/29/15.
 */
public class ThresholdReductionCorrect extends BaseValidator
{
    private static String CLASS_NAME       = ThresholdReductionCorrect.class.getName();
    private static String SIMPLE_NAME      = ThresholdReductionCorrect.class.getSimpleName();


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

        BigDecimal grandThresholdReduction;

        try
        {
            grandThresholdReduction = stringv99ToBigDecimal(fileTrailer.getGrandThresholdReduction());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_THRESHOLD_AMOUNT_NON_NUMER,
                                       fileTrailer.toString());
        }

        if (grandThresholdReduction.subtract(fileContext.getFileThresholdReduction()).compareTo(ZERO_DOLLARS) != 0)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_THRESHOLD_AMOUNT_INCORRECT,
                                       "FTRL Threshold Reduction: " + grandThresholdReduction + " Computed Threshold Reduction: " + fileContext.getFileThresholdReduction());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
