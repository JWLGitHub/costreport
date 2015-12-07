package jwl.prp.retiree.costreport.validation.file.trailer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import jwl.prp.retiree.costreport.entity.FileTrailer;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.BaseValidator;
import jwl.prp.retiree.costreport.validation.FileContext;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/28/15.
 */
public class ApplicationsExist extends BaseValidator
{
    private static String CLASS_NAME  = ApplicationsExist.class.getName();
    private static String SIMPLE_NAME = ApplicationsExist.class.getSimpleName();


    @Override
    public ValidationError execute(CostReportRecord costReportRecord,
                                   FileContext      fileContext)
                                   throws Exception
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!(costReportRecord instanceof FileTrailer))
            throw new RuntimeException(SIMPLE_NAME + " " + METHOD_NAME + " - Validator passed INVALID CostReportRecord Type: " + costReportRecord);
        
        if (fileContext.getFileApplicationCount() == 0)
            return new ValidationError(fileContext.getFileRecordCounter(),
                                       ErrRef.CRFILE_CONTAINS_NO_APPLICATIONS,
                                       "Computed Application Count: " + fileContext.getFileApplicationCount());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
