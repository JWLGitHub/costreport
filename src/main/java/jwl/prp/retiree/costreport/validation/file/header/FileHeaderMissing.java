package jwl.prp.retiree.costreport.validation.file.header;


import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class FileHeaderMissing extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderMissing.class.getName();
    private static String SIMPLE_NAME = FileHeaderMissing.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (costReportRecord instanceof ApplicationHeader  ||
            costReportRecord instanceof ApplicationDetail  ||
            costReportRecord instanceof ApplicationTrailer ||
            costReportRecord instanceof FileTrailer)
        {
            // VALID Cost Report Record
        }
        else
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        if (fileContext.getFileHeaderCounter() != 1)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_MISSING,
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
