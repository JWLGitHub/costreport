package jwl.prp.retiree.costreport.validation.file.header;


import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.OrgTypRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class FileHeaderSubmitterTypeValid extends BaseValidator
{
    private static String CLASS_NAME  = FileHeaderSubmitterTypeValid.class.getName();
    private static String SIMPLE_NAME = FileHeaderSubmitterTypeValid.class.getSimpleName();

    private static String[] VALID_SUBMITTER_TYPES = {OrgTypRef.PLAN_SPONSOR.getOrgTypCd(),
                                                     OrgTypRef.VENDOR.getOrgTypCd()};


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

        if (!validSubmitterType(fileHeader.getSubmitterType()))
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.FILE_HEADER_SUBMITTER_TYPE_DOES_NOT_MATCH_DSN,
                                       "VALID SUBMITTER TYPE(S): " + validSubmitterTypesToString() + ". " + costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }


    private boolean validSubmitterType(String submitterTypCd)
    {
        final String METHOD_NAME = "validSubmitterType";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        boolean isValidSubmitterType = false;

        for (String validSubmitterType : VALID_SUBMITTER_TYPES)
        {
            if (submitterTypCd.equalsIgnoreCase(validSubmitterType))
            {
                isValidSubmitterType = true;
                break;
            }
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return isValidSubmitterType;
    }


    private String validSubmitterTypesToString()
    {
        final String METHOD_NAME = "validSubmitterTypesToString";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        StringBuffer formattedValidSubmitterTypes = new StringBuffer();
        int validSubmitterTypesCtr = 0;

        for (String validSubmitterType : VALID_SUBMITTER_TYPES)
        {
            if (validSubmitterTypesCtr > 0)
                formattedValidSubmitterTypes.append(", ");

            formattedValidSubmitterTypes.append(validSubmitterType);
            validSubmitterTypesCtr++;
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return formattedValidSubmitterTypes.toString();
    }
}
