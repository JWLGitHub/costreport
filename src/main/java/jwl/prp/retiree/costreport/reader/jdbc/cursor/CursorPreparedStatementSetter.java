package jwl.prp.retiree.costreport.reader.jdbc.cursor;


import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CursorPreparedStatementSetter implements PreparedStatementSetter
{
    private static String CLASS_NAME  = CursorPreparedStatementSetter.class.getName();
    private static String SIMPLE_NAME = CursorPreparedStatementSetter.class.getSimpleName();


    public void setValues(PreparedStatement preparedStatement)
                          throws SQLException
    {
        final String METHOD_NAME = "setValues";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        preparedStatement.setString(1, "00");

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }
}
