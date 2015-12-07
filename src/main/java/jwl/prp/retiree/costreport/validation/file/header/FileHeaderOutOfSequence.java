package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.entity.*;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/28/15.
 */
public class FileHeaderOutOfSequence extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderOutOfSequence.class.getName();
    private static String SIMPLE_NAME = FileHeaderOutOfSequence.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        if (fileContext.getFileHeaderCounter() != 0)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_OUT_OF_SEQUENCE,
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}

