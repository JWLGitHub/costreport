package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 12/1/15.
 */
public class FileTrailerOutOfSequence extends BaseValidator
{
    private static String CLASS_NAME  = FileTrailerOutOfSequence.class.getName();
    private static String SIMPLE_NAME = FileTrailerOutOfSequence.class.getSimpleName();


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

        if (fileContext.getFileHeaderCounter()         != 1                                       ||
            fileContext.getApplicationHeaderCounter()  != fileContext.getFileApplicationCount()   ||
            fileContext.getFileTrailerCounter()        != 0)
            return new ValidationError(ErrRef.FILE_TRAILER_OUT_OF_SEQUENCE,
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
