package jwl.prp.retiree.costreport.validation.application.trailer;


import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;


public class ThresholdReductionCorrect extends BaseValidator
{
    private static String CLASS_NAME  = ThresholdReductionCorrect.class.getName();
    private static String SIMPLE_NAME = ThresholdReductionCorrect.class.getSimpleName();


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

        BigDecimal totalThresholdReduction;

        try
        {
            totalThresholdReduction = stringv99ToBigDecimal(applicationTrailer.getTotalThresholdReduction());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_THRESHOLD_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        if (totalThresholdReduction.subtract(fileContext.getApplicationThresholdReduction()) != ZERO_DOLLARS)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_THRESHOLD_INCORRECT,
                                       "Application ID: " + applicationTrailer.getApplicationID() + " ATRL Threshold Reduction: " + totalThresholdReduction + " Computed Threshold Reduction: " + fileContext.getApplicationThresholdReduction());

        fileContext.setFileThresholdReduction(fileContext.getFileThresholdReduction().add(totalThresholdReduction));
        fileContext.setApplicationThresholdReduction(new BigDecimal("0"));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
