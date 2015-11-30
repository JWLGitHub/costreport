package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/30/15.
 */
public class IncrementFileHeaderCounter extends BaseValidator
{
    private static String CLASS_NAME  = IncrementFileHeaderCounter.class.getName();
    private static String SIMPLE_NAME = IncrementFileHeaderCounter.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        fileContext.setFileHeaderCounter(fileContext.getFileHeaderCounter() + 1);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}