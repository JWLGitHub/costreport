package jwl.prp.retiree.costreport.validation.application.trailer;

import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;

/**
 * Created by jwleader on 12/30/15.
 */
public class EstimatedCostAdjustmentCorrect extends BaseValidator
{
    private static String CLASS_NAME  = EstimatedCostAdjustmentCorrect.class.getName();
    private static String SIMPLE_NAME = EstimatedCostAdjustmentCorrect.class.getSimpleName();


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

        BigDecimal totalEstimatedCostAdjustment;

        try
        {
            totalEstimatedCostAdjustment = stringv99ToBigDecimal(applicationTrailer.getTotalEstimatedCostAdjustment());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_COST_ADJUST_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        if (totalEstimatedCostAdjustment.subtract(fileContext.getApplicationEstimatedCostAdjustment()).compareTo(ZERO_DOLLARS) != 0)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_COST_ADJUST_INCORRECT,
                                       "Application ID: " + applicationTrailer.getApplicationID() + " ATRL Estimated Cost Adjustment: " + totalEstimatedCostAdjustment + " Computed Estimated Cost Adjustment: " + fileContext.getApplicationEstimatedCostAdjustment());

        fileContext.setFileEstimatedCostAdjustment(fileContext.getFileEstimatedCostAdjustment().add(totalEstimatedCostAdjustment));
        fileContext.setApplicationEstimatedCostAdjustment(new BigDecimal("0"));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
