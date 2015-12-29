package jwl.prp.retiree.costreport.validation.application.trailer;


import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;


public class LimitReductionCorrect extends BaseValidator
{
    private static String CLASS_NAME  = LimitReductionCorrect.class.getName();
    private static String SIMPLE_NAME = LimitReductionCorrect.class.getSimpleName();


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

        BigDecimal totalLimitReduction;

        try
        {
            totalLimitReduction = stringv99ToBigDecimal(applicationTrailer.getTotalLimitReduction());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_LIMIT_NON_NUMERIC,
                                       applicationTrailer.toString());
        }

        if (totalLimitReduction.subtract(fileContext.getApplicationLimitReduction()) != ZERO_DOLLARS)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_TRAILER_LIMIT_INCORRECT,
                                       "Application ID: " + applicationTrailer.getApplicationID() + " ATRL Limit Reduction: " + totalLimitReduction + " Computed Limit Reduction: " + fileContext.getApplicationLimitReduction());

        fileContext.setFileLimitReduction(fileContext.getFileLimitReduction().add(totalLimitReduction));
        fileContext.setApplicationLimitReduction(new BigDecimal("0"));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}

