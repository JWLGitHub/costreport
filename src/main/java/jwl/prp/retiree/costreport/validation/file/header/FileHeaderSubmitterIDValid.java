package jwl.prp.retiree.costreport.validation.file.header;

import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.dao.RdsOrgDAO;
import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class FileHeaderSubmitterIDValid extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderSubmitterIDValid.class.getName();
    private static String SIMPLE_NAME = FileHeaderSubmitterIDValid.class.getSimpleName();


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

        if (fileHeader.getSubmitterID().trim().isEmpty())
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_SUBMITTER_ID_MISSING,
                                       "File Header Submitter ID: " + fileHeader.getSubmitterID());

        if (!fileHeader.getSubmitterID().equalsIgnoreCase(fileContext.getFileNameSubmitterID()))
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_SUBMITTER_ID_DOES_NOT_MATCH_DSN,
                                       "File Name Submitter ID: " + fileContext.getFileNameSubmitterID() + " File Header Submitter ID: " + fileHeader.getSubmitterID());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}