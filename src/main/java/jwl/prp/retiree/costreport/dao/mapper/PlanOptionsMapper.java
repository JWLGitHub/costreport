package jwl.prp.retiree.costreport.dao.mapper;

import jwl.prp.retiree.costreport.entity.PlanOptions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jwleader on 11/20/15.
 */
public class PlanOptionsMapper implements RowMapper<PlanOptions>
{
    public PlanOptions mapRow(ResultSet resultSet,
                              int       rowNumber)
                              throws SQLException
    {
        PlanOptions planOptions = new PlanOptions();
        planOptions.setApplicationID(resultSet.getString("APPLICATION_ID"));
        planOptions.setGroupNumber(resultSet.getString("GROUP_NUMBER"));
        return planOptions;
    }
}