package jwl.prp.retiree.costreport.Validation.FileHeader;

import jwl.prp.retiree.costreport.Validation.Validator;
import jwl.prp.retiree.costreport.entity.CostReportRecord;

/**
 * Created by jwleader on 10/26/15.
 */
public class TrueValidator implements Validator<CostReportRecord>
{
    private static String CLASS_NAME  = TrueValidator.class.getName();
    private static String SIMPLE_NAME = TrueValidator.class.getSimpleName();


    public boolean validate(CostReportRecord costReportRecord)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return true;
    }
}