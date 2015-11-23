package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;

/**
 * Created by jwleader on 10/26/15.
 */
public class FalseValidator implements Validator<CostReportRecord, FileContext>
{
    private static String CLASS_NAME  = FalseValidator.class.getName();
    private static String SIMPLE_NAME = FalseValidator.class.getSimpleName();


    public ValidationError validate(CostReportRecord costReportRecord,
                            FileContext      fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);


        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return new ValidationError(ErrRef.CRFILE_RECFM_ERROR,
                                   SIMPLE_NAME + " " + METHOD_NAME + " - FALSE");
    }
}
