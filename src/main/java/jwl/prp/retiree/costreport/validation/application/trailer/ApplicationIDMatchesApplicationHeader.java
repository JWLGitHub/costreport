package jwl.prp.retiree.costreport.validation.application.trailer;


import jwl.prp.retiree.costreport.entity.ApplicationTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class ApplicationIDMatchesApplicationHeader extends BaseValidator
{
    private static String CLASS_NAME  = ApplicationIDMatchesApplicationHeader.class.getName();
    private static String SIMPLE_NAME = ApplicationIDMatchesApplicationHeader.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationTrailer applicationTrailer = (ApplicationTrailer) costReportRecord;

        if (!applicationTrailer.getApplicationID().equalsIgnoreCase(fileContext.getApplicationID()))
            return new ValidationError(ErrRef.UNMATCHED_APPLICATION_ID_IN_ATRL,
                                       "ATRL Application ID: " +
                                       applicationTrailer.getApplicationID() +
                                       " Expected Application ID: " +
                                       fileContext.getApplicationID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}