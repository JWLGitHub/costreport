package jwl.prp.retiree.costreport.validation.file.header;


import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class InitializeFileVariables extends BaseValidator
{
    private static String CLASS_NAME  = InitializeFileVariables.class.getName();
    private static String SIMPLE_NAME = InitializeFileVariables.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        fileContext.initializeFileVariables();

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}