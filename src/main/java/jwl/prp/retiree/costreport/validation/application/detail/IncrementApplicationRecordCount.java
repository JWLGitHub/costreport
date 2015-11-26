package jwl.prp.retiree.costreport.validation.application.detail;

import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.math.BigDecimal;

/**
 * Created by jwleader on 11/26/15.
 */
public class IncrementApplicationRecordCount extends BaseValidator
{

    private static String CLASS_NAME  = IncrementApplicationRecordCount.class.getName();
    private static String SIMPLE_NAME = IncrementApplicationRecordCount.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationDetail))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        fileContext.setApplicationRecordCount(fileContext.getApplicationRecordCount() + 1);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
