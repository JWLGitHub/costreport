package jwl.prp.retiree.costreport.Validation.FileHeader;

import jwl.prp.retiree.costreport.Validation.Validator;
import jwl.prp.retiree.costreport.entity.CostReportRecord;

import java.util.LinkedList;

/**
 * Created by jwleader on 10/29/15.
 */
public class ApplicationValidator implements Validator<LinkedList<CostReportRecord>>
{
    private static String CLASS_NAME  = ApplicationValidator.class.getName();
    private static String SIMPLE_NAME = ApplicationValidator.class.getSimpleName();


    public boolean validate(LinkedList<CostReportRecord> costReportRecords)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return true;
    }
}
