package jwl.prp.retiree.costreport.validation.application.detail;

import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;


public class EstimatedCostAdjustmentNumeric extends BaseValidator
{

    private static String CLASS_NAME  = EstimatedCostAdjustmentNumeric.class.getName();
    private static String SIMPLE_NAME = EstimatedCostAdjustmentNumeric.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationDetail))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;

        BigDecimal estimatedCostAdjustment;

        try
        {
            estimatedCostAdjustment = stringv99ToBigDecimal(applicationDetail.getEstimatedCostAdjustment());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_DETAIL_COST_ADJUST_IS_NON_NUMERIC,
                                       applicationDetail.toString());
        }

        fileContext.setApplicationEstimatedCostAdjustment(fileContext.getApplicationEstimatedCostAdjustment().add(estimatedCostAdjustment));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}