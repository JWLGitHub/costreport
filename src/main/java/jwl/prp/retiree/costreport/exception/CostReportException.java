package jwl.prp.retiree.costreport.exception;

import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.validation.ValidationError;

/**
 * Created by jwleader on 11/23/15.
 */
public class CostReportException extends Exception
{
    private static String CLASS_NAME  = CostReportException.class.getName();
    private static String SIMPLE_NAME = CostReportException.class.getSimpleName();

    protected ValidationError validationError = null;


    public CostReportException() { super(); }

    public CostReportException(String message) { super(message); }

    public CostReportException(Throwable throwable) { super(throwable); }

    public CostReportException(String message, Throwable throwable ) { super(message, throwable); }

    public CostReportException(ValidationError validationError)
    {
        super();
        this.validationError = validationError;
    }


    //
    // --- Getter(s) ---
    //
    public ValidationError getValidationError()
    {
        Throwable throwable = this.getCause();
        if (null != throwable   &&
            throwable instanceof CostReportException)
            return ((CostReportException) throwable).getValidationError();

        return validationError;
    }

    //
    // --- Getter(s) ---
    //
    public void setValidationError(ValidationError validationError) { this.validationError = validationError; }
}
