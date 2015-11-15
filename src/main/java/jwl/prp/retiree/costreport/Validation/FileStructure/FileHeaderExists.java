package jwl.prp.retiree.costreport.Validation.FileStructure;

import jwl.prp.retiree.costreport.Validation.Validator;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;

/**
 * Created by jwleader on 11/15/15.
 */
public class FileHeaderExists implements Validator<CostReportRecord, FileContext>
{
    private static String CLASS_NAME  = FileHeaderExists.class.getName();
    private static String SIMPLE_NAME = FileHeaderExists.class.getSimpleName();


    public ErrRef validate(CostReportRecord costReportRecord,
                            FileContext     fileContext)
    {
        final String METHOD_NAME = "validate";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!fileContext.isFileHeaderExists())
            return ErrRef.FILE_HEADER_MISSING;

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
