package jwl.prp.retiree.costreport.validation;


/**
 * Created by jwleader on 10/26/15.
 */
public interface Validator<R, C>
{
    ValidationError validate(R record, C context);
}
