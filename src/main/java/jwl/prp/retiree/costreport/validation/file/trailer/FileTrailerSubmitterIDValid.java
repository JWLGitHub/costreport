package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 12/28/15.
 */
public class FileTrailerSubmitterIDValid extends BaseValidator
{
    private static String CLASS_NAME  = FileTrailerSubmitterIDValid.class.getName();
    private static String SIMPLE_NAME = FileTrailerSubmitterIDValid.class.getSimpleName();


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

        if (fileTrailer.getSubmitterID().trim().isEmpty())
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_SUBMITTER_IS_INVALID,
                                       "File Trailer Submitter ID: " + fileTrailer.getSubmitterID());

        if (!fileTrailer.getSubmitterID().equalsIgnoreCase(fileContext.getFileNameSubmitterID()))
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_TRAILER_SUBMITTER_ID_DOES_NOT_MATCH_HEADER,
                                       "File Name Submitter ID: " + fileContext.getFileNameSubmitterID() + " File Trailer Submitter ID: " + fileTrailer.getSubmitterID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}