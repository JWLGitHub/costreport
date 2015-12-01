package jwl.prp.retiree.costreport.validation.application.detail;

import jwl.prp.retiree.costreport.entity.ApplicationDetail;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 12/1/15.
 */
public class ApplicationDetailOutOfSequence extends BaseValidator
{
    private static String CLASS_NAME  = ApplicationDetailOutOfSequence.class.getName();
    private static String SIMPLE_NAME = ApplicationDetailOutOfSequence.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof ApplicationDetail))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        ApplicationDetail applicationDetail = (ApplicationDetail) costReportRecord;

        if (fileContext.getFileHeaderCounter()             != 1                                       ||
            fileContext.getApplicationHeaderCounter() - 1  != fileContext.getFileApplicationCount()   ||
            fileContext.getFileTrailerCounter()            != 0)
            return new ValidationError(ErrRef.APPLICATION_DETAIL_RECORD_OUT_OF_SEQUENCE,
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
