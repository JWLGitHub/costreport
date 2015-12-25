package jwl.prp.retiree.costreport.validation;


import jwl.prp.retiree.costreport.entity.CostReportRecord;
import java.math.BigDecimal;


/**
 * Created by jwleader on 11/18/15.
 */
public abstract class BaseValidator
{
    private static String CLASS_NAME  = BaseValidator.class.getName();
    private static String SIMPLE_NAME = BaseValidator.class.getSimpleName();

    public static final String     ONE_HUNDRED  = "100";
    public static final BigDecimal ZERO_DOLLARS = stringv99ToBigDecimal("0.00");


    public static BigDecimal stringv99ToBigDecimal(String amountv99)
    {
        final String METHOD_NAME = "stringv99ToBigDecimal";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return new BigDecimal(amountv99).divide(new BigDecimal(ONE_HUNDRED));
    }


    abstract public ValidationError execute(CostReportRecord costReportRecord,
                                            FileContext      fileContext)
                                            throws Exception;
}
