package jwl.prp.retiree.costreport.validation.application.trailer;

import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/26/15.
 */
public class IncrementApplicationCount extends BaseValidator
{

    private static String CLASS_NAME  = IncrementApplicationCount.class.getName();
    private static String SIMPLE_NAME = IncrementApplicationCount.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        fileContext.setFileApplicationCount(fileContext.getFileApplicationCount() + 1);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
