package jwl.prp.retiree.costreport.validation;


import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateFormatValidator
{
    private static String CLASS_NAME  = DateFormatValidator.class.getName();
    private static String SIMPLE_NAME = DateFormatValidator.class.getSimpleName();


    public static boolean isDateValid(String dateToValidate,
                                      String dateFormat)
    {
        final String METHOD_NAME = "isDateValid";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (dateToValidate == null)
            return false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        try
        {
            simpleDateFormat.parse(dateToValidate);
        }
        catch (ParseException parseException)
        {
            return false;
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return true;
    }
}
