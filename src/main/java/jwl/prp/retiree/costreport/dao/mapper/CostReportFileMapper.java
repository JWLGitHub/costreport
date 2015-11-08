package jwl.prp.retiree.costreport.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import jwl.prp.retiree.costreport.entity.CostReportFile;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by jwleader on 11/3/15.
 */


public class CostReportFileMapper implements RowMapper<CostReportFile>
{
    public CostReportFile mapRow(ResultSet resultSet,
                                 int       rowNumber)
                                 throws SQLException
    {
        CostReportFile costReportFile = new CostReportFile();
        costReportFile.setId(resultSet.getInt("CST_RPRT_FL_ID"));
        costReportFile.setName(resultSet.getString("CST_RPRT_FL_NM"));
        costReportFile.setStatus(resultSet.getString("CST_RPRT_FL_STTS"));
        costReportFile.setAddJob(resultSet.getString("CST_RPRT_FL_ADD_JB"));
        costReportFile.setAddStep(resultSet.getString("CST_RPRT_FL_ADD_STP"));
        costReportFile.setAddProgram(resultSet.getString("CST_RPRT_FL_ADD_PRGRM"));
        costReportFile.setAddMethod(resultSet.getString("CST_RPRT_FL_ADD_MTHD"));
        costReportFile.setAddDateTime(resultSet.getTimestamp("CST_RPRT_FL_ADD_DTTIM"));
        costReportFile.setUpdateJob(resultSet.getString("CST_RPRT_FL_UPDT_JB"));
        costReportFile.setUpdateStep(resultSet.getString("CST_RPRT_FL_UPDT_STP"));
        costReportFile.setUpdateProgram(resultSet.getString("CST_RPRT_FL_UPDT_PRGRM"));
        costReportFile.setUpdateMethod(resultSet.getString("CST_RPRT_FL_UPDT_MTHD"));
        costReportFile.setUpdateDateTime(resultSet.getTimestamp("CST_RPRT_FL_UPDT_DTTIM"));
        return costReportFile;
    }
}
