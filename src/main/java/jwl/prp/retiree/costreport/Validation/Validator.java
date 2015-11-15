package jwl.prp.retiree.costreport.Validation;


import jwl.prp.retiree.costreport.enums.ErrRef;


/**
 * Created by jwleader on 10/26/15.
 */
public interface Validator<R, C>
{
    ErrRef validate(R record, C context);
}
