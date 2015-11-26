package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;
import jwl.prp.retiree.costreport.enums.ErrRef;


/**
 * Created by jwleader on 11/15/15.
 */

public class FileHeaderExists extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderExists.class.getName();
    private static String SIMPLE_NAME = FileHeaderExists.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (costReportRecord instanceof ApplicationHeader   ||
            costReportRecord instanceof ApplicationDetail   ||
            costReportRecord instanceof ApplicationTrailer  ||
            costReportRecord instanceof FileTrailer)
        {
            // VALID Cost Report Record
        }
        else
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        if (fileContext.getFileHeaderCounter() != 1)
            return new ValidationError(ErrRef.FILE_HEADER_MISSING,
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
