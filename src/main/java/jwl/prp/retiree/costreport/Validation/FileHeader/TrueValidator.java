package jwl.prp.retiree.costreport.Validation.FileHeader;

import jwl.prp.retiree.costreport.Validation.FileStructure.FileContext;
import jwl.prp.retiree.costreport.Validation.Validator;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;

/**
 * Created by jwleader on 10/26/15.
 */
public class TrueValidator implements Validator<CostReportRecord, FileContext>
{
    private static String CLASS_NAME  = TrueValidator.class.getName();
    private static String SIMPLE_NAME = TrueValidator.class.getSimpleName();


    public ErrRef validate(CostReportRecord costReportRecord,
                            FileContext     fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}