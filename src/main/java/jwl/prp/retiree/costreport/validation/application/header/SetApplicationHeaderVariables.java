package jwl.prp.retiree.costreport.validation.application.header;

import jwl.prp.retiree.costreport.entity.ApplicationHeader;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/30/15.
 */
public class SetApplicationHeaderVariables extends BaseValidator
{
    private static String CLASS_NAME  = SetApplicationHeaderVariables.class.getName();
    private static String SIMPLE_NAME = SetApplicationHeaderVariables.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationHeader applicationHeader = (ApplicationHeader) costReportRecord;

        fileContext.setApplicationHeaderVariables(applicationHeader.getApplicationID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}