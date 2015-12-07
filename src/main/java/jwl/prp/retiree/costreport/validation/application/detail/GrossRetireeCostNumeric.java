package jwl.prp.retiree.costreport.validation.application.detail;

import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;

import java.math.BigDecimal;


/**
 * Created by jwleader on 11/17/15.
 */
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

        if (!(costReportRecord instanceof ApplicationDetail))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;

        BigDecimal grossRetireeCost;

        try
        {
            grossRetireeCost = stringv99ToBigDecimal(applicationDetail.getGrossRetireeCost());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.APPLICATION_DETAIL_RET_COST_IS_NON_NUMERIC,
                                       applicationDetail.toString());
        }

        fileContext.setApplicationGrossRetireeCost(fileContext.getApplicationGrossRetireeCost().add(grossRetireeCost));

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}