package jwl.prp.retiree.costreport.validation.file.header;


import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class FileHeaderSubmitterTypeValid extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderSubmitterTypeValid.class.getName();
    private static String SIMPLE_NAME = FileHeaderSubmitterTypeValid.class.getSimpleName();


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

        if (!fileHeader.getSubmitterType().equalsIgnoreCase(fileContext.getFileNameSubmitterType()))
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_SUBMITTER_TYPE_DOES_NOT_MATCH_DSN,
                                       "File Name Submitter Type: " + fileContext.getFileNameSubmitterType() + " File Header Submitter Type: " + fileHeader.getSubmitterType());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
