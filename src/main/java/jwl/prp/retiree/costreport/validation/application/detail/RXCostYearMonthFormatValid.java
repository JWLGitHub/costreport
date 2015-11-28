package jwl.prp.retiree.costreport.validation.application.detail;

import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.DateFormatValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/28/15.
 */
public class RXCostYearMonthFormatValid extends BaseValidator
{
    private static String CLASS_NAME  = RXCostYearMonthFormatValid.class.getName();
    private static String SIMPLE_NAME = RXCostYearMonthFormatValid.class.getSimpleName();

    private static final String yyyyMM = "yyyyMM";


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

        if (!DateFormatValidator.isDateValid(applicationDetail.getRxCostYearMonth(), yyyyMM))
            return new ValidationError(ErrRef.APPLICATION_DETAIL_DATE_IS_INVALID,
                                       "EXPECTED FORMAT: '" +
                                       yyyyMM +
                                       "'. " +
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
