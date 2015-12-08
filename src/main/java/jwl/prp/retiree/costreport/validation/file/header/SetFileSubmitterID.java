package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 12/8/15.
 */
public class SetFileSubmitterID extends BaseValidator
{
    private static String CLASS_NAME  = SetFileSubmitterID.class.getName();
    private static String SIMPLE_NAME = SetFileSubmitterID.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext fileContext)
            throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileHeader fileHeader = (FileHeader) costReportRecord;
        fileContext.setFileSubmitterID(fileHeader.getSubmitterID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}