package jwl.prp.retiree.costreport.validation.file.name;


import jwl.prp.retiree.costreport.entity.FileName;
import jwl.prp.retiree.costreport.validation.ValidationError;


public abstract class FileNameValidator
{
    private static String CLASS_NAME  = FileNameValidator.class.getName();
    private static String SIMPLE_NAME = FileNameValidator.class.getSimpleName();


    abstract public ValidationError execute(FileName fileName);
}
