package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.entity.CostReportRecord;

/**
 * Created by jwleader on 10/29/15.
 */
public class ApplicationValidator implements Validator<CostReportRecord, FileContext>
{
    private static String CLASS_NAME  = ApplicationValidator.class.getName();
    private static String SIMPLE_NAME = ApplicationValidator.class.getSimpleName();


    public ValidationError validate(CostReportRecord costReportRecord,
                                    FileContext      fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
