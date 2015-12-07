package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;

/**
 * Created by jwleader on 10/26/15.
 */
public class FalseValidator extends BaseValidator
{
    private static String CLASS_NAME  = FalseValidator.class.getName();
    private static String SIMPLE_NAME = FalseValidator.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);


        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return new ValidationError(fileContext.getFileRecordCounter(),
                                   ErrRef.CRFILE_RECFM_ERROR,
                                   SIMPLE_NAME + " " + METHOD_NAME + " - FALSE");
    }
}
