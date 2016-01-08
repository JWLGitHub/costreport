package jwl.prp.retiree.costreport.utils;


import org.springframework.batch.item.ExecutionContext;


public class ExecutionContextHandler
{
    private static String CLASS_NAME  = ExecutionContextHandler.class.getName();
    private static String SIMPLE_NAME = ExecutionContextHandler.class.getSimpleName();


    public static Integer getIntegerFromExecutionContext(ExecutionContext executionContext,
                                                         String           fieldName)
    {
        final String METHOD_NAME = "getIntegerFromExecutionContext";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object fieldValue = executionContext.get(fieldName);
        if (null == fieldValue ||
            fieldValue.toString().equalsIgnoreCase(""))
            throw new RuntimeException("'" + fieldName + "' MISSING from ExecutionContext");

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(fieldValue.toString());
    }


    public static String getStringFromExecutionContext(ExecutionContext executionContext,
                                                       String           fieldName)
    {
        final String METHOD_NAME = "getStringFromExecutionContext";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object fieldValue = executionContext.get(fieldName);
        if (null == fieldValue ||
            fieldValue.toString().equalsIgnoreCase(""))
            throw new RuntimeException("'" + fieldName + "' MISSING from ExecutionContext");

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return fieldValue.toString();
    }
}
