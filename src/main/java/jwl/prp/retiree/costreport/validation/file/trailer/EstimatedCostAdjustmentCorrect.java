package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;


public class EstimatedCostAdjustmentCorrect extends BaseValidator
{
    private static String CLASS_NAME   = EstimatedCostAdjustmentCorrect.class.getName();
    private static String SIMPLE_NAME  = EstimatedCostAdjustmentCorrect.class.getSimpleName();


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

        BigDecimal grandEstimatedCostAdjustment;

        try
        {
            grandEstimatedCostAdjustment = stringv99ToBigDecimal(fileTrailer.getGrandEstimatedCostAdjustment());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_COST_ADJUST_NON_NUMERIC,
                                       fileTrailer.toString());
        }

        if (grandEstimatedCostAdjustment.subtract(fileContext.getFileEstimatedCostAdjustment()).compareTo(ZERO_DOLLARS) != 0)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_COST_ADJUST_INCORRECT,
                                       "FTRL Estimated Cost Adjustment: " + grandEstimatedCostAdjustment + " Computed Estimated Cost Adjustment: " + fileContext.getFileEstimatedCostAdjustment());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
