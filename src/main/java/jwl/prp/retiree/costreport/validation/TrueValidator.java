package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.entity.CostReportRecord;

/**
 * Created by jwleader on 10/26/15.
 */
public class TrueValidator extends BaseValidator
{
    private static String CLASS_NAME  = TrueValidator.class.getName();
    private static String SIMPLE_NAME = TrueValidator.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}