package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.entity.CostReportRecord;

/**
 * Created by jwleader on 10/26/15.
 */
public class TrueValidator implements Validator<CostReportRecord, FileContext>
{
    private static String CLASS_NAME  = TrueValidator.class.getName();
    private static String SIMPLE_NAME = TrueValidator.class.getSimpleName();


    public ValidationError validate(CostReportRecord costReportRecord,
                                    FileContext      fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}