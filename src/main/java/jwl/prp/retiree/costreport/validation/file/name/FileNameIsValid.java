package jwl.prp.retiree.costreport.validation.file.name;


import jwl.prp.retiree.costreport.entity.FileName;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.ValidationError;


public class FileNameIsValid extends FileNameValidator
{
    private static String CLASS_NAME  = FileNameIsValid.class.getName();
    private static String SIMPLE_NAME = FileNameIsValid.class.getSimpleName();


    @Override
    public ValidationError execute(FileName fileName)
    {
        final String METHOD_NAME = "execute";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (!fileName.isValid())
            return new ValidationError(0,
                    ErrRef.LIST_RECORD_CONTAINS_INVALID_DSN,
                    ErrRef.LIST_RECORD_CONTAINS_INVALID_DSN.getDescTxt() + " - " + fileName.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }
}
