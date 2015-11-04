package jwl.prp.retiree.costreport.Validation;

/**
 * Created by jwleader on 10/26/15.
 */
public interface Validator<T>
{
    boolean validate(T value);
}
