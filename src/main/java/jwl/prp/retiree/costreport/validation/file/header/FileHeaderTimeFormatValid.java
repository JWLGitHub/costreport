package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.DateFormatValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/28/15.
 */
public class FileHeaderTimeFormatValid extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderTimeFormatValid.class.getName();
    private static String SIMPLE_NAME = FileHeaderTimeFormatValid.class.getSimpleName();

    private static final String HHmmss = "HH:mm:ss";


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext fileContext)
            throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileHeader fileHeader = (FileHeader) costReportRecord;

        if (!DateFormatValidator.isDateValid(fileHeader.getSubmitterTime(), HHmmss))
            return new ValidationError(ErrRef.FILE_HEADER_TIME_IS_INVALID,
                                       "EXPECTED FORMAT: '" +
                                       HHmmss +
                                       "'. " +
                                       costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}