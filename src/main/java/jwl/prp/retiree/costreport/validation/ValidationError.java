package jwl.prp.retiree.costreport.validation;

import jwl.prp.retiree.costreport.enums.ErrRef;

/**
 * Created by jwleader on 11/17/15.
 */
public class ValidationError
{

    private int     errRecordNbr;
    private ErrRef  errRef;
    private String  errMessage;


    public ValidationError(int    errRecordNbr,
                           ErrRef errRef,
                           String errMessage)
    {
        this.errRecordNbr = errRecordNbr;
        this.errRef       = errRef;
        this.errMessage   = errMessage;
    }


    /*
     *---   GETTER(S)
     */
    public int getErrRecordNbr() { return errRecordNbr; }

    public ErrRef getErrRef()
    {
        return errRef;
    }

    public String getErrMessage() { return errMessage; }

    public String getRecordNbrErrMessage()
    {
        if (getErrRecordNbr() > 0)
            return "Record No.: " +
                    getErrRecordNbr() + " " +
                    getErrMessage();
        else
            return getErrMessage();
    }


    /*
     *---   SETTER(S)
     */

    public void setErrRecordNbr(int errRecordNbr) { this.errRecordNbr = errRecordNbr; }

    public void setErrRef(ErrRef errRef)
    {
        this.errRef = errRef;
    }

    public void setErrMessage(String errMessage)
    {
        this.errMessage = errMessage;
    }
}
