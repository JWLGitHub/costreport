package jwl.prp.retiree.costreport.validation.file.header;


import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileHeaderDateNotFuture extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderDateNotFuture.class.getName();
    private static String SIMPLE_NAME = FileHeaderDateNotFuture.class.getSimpleName();

    private static final String           MMddyyyy         = "MMddyyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MMddyyyy);


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileHeader))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);

        FileHeader fileHeader = (FileHeader) costReportRecord;

        try
        {
            simpleDateFormat.setLenient(false);
            Date submitterDate = simpleDateFormat.parse(fileHeader.getSubmitterDate());
            Date currentDate   = new Date();
            if (submitterDate.compareTo(currentDate) > 0)
                return new ValidationError(fileContext.getFileRecordCounter(),
                                           ErrRef.HEADER_IS_FUTURE_DATED,
                                           "FHDR Submitter Date: " + submitterDate + " Current Date: " + currentDate);
        }
        catch (ParseException parseException)
        {
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.HEADER_DATE_IS_INVALID_DATE_FORMAT,
                                       "EXPECTED FORMAT: '" + MMddyyyy + "'. " + costReportRecord.toString());
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}