package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.enums.ErrRef;

/**
 * Created by jwleader on 11/17/15.
 */
public class ValidationError
{

    private ErrRef  errRef;
    private String  errMessage;


    public ValidationError(ErrRef errRef,
                           String errMessage)
    {
        this.errRef = errRef;
        this.errMessage = errMessage;
    }


    public ErrRef getErrRef()
    {
        return errRef;
    }


    public String getErrMessage()
    {
        return errMessage;
    }


    public void setErrRef(ErrRef errRef)
    {
        this.errRef = errRef;
    }


    public void setErrMessage(String errMessage)
    {
        this.errMessage = errMessage;
    }
}
