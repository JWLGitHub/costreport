package jwl.prp.retiree.costreport.dao.mapper;


import jwl.prp.retiree.costreport.entity.Application;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by jwleader on 11/20/15.
 */
public class ApplicationMapper implements RowMapper<Application>
{
    public Application mapRow(ResultSet resultSet,
                              int       rowNumber)
                              throws SQLException
    {
        Application application = new Application();
        application.setApplicationID(resultSet.getString("APPLICATION_ID"));
        return application;
    }
}

