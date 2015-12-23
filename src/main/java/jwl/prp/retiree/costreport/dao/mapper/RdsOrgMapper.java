package jwl.prp.retiree.costreport.dao.mapper;


import jwl.prp.retiree.costreport.entity.RdsOrg;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RdsOrgMapper implements RowMapper<RdsOrg>
{
    public RdsOrg mapRow(ResultSet resultSet,
                         int       rowNumber)
                         throws SQLException
    {
        RdsOrg rdsOrg = new RdsOrg();
        rdsOrg.setOrgId(resultSet.getString("ORG_ID"));
        rdsOrg.setOrgTypCd(resultSet.getString("ORG_TYP_CD"));
        rdsOrg.setOrgName(resultSet.getString("ORG_NAME"));
        rdsOrg.setTransMethod(resultSet.getString("TRANS_METHOD"));
        rdsOrg.setStusCtgryCd(resultSet.getString("STUS_CTGRY_CD"));
        rdsOrg.setStusCd(resultSet.getString("STUS_CD"));
        rdsOrg.setStusPgm(resultSet.getString("STUS_PGM"));
        rdsOrg.setStusTs(resultSet.getTimestamp("STUS_TS"));
        rdsOrg.setUptdPgm(resultSet.getString("UPTD_PGM"));
        rdsOrg.setUpdtTs(resultSet.getTimestamp("UPDT_TS"));
        return rdsOrg;
    }
}
