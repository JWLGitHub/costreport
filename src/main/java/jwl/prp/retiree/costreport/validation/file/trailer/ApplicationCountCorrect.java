package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;


/**
 * Created by jwleader on 11/17/15.
 */
public class ApplicationCountCorrect extends BaseValidator
{
    private static String CLASS_NAME  = ApplicationCountCorrect.class.getName();
    private static String SIMPLE_NAME = ApplicationCountCorrect.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileTrailer fileTrailer = (FileTrailer) costReportRecord;

        int applicationCount;

        try
        {
            applicationCount = Integer.valueOf(fileTrailer.getApplicationCount());
        }
        catch (NumberFormatException nfe)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_APPLICATION_COUNT_NON_NUMERIC,
                                       fileTrailer.toString());
        }

        if (applicationCount != fileContext.getFileApplicationCount())
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_APPLICATION_COUNT_INCORRECT,
                                       "FTRL Application Count: " + applicationCount + " Computed Application Count: " + fileContext.getFileApplicationCount());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
