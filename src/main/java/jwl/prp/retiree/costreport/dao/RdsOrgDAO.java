package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.dao.mapper.RdsOrgMapper;
import jwl.prp.retiree.costreport.entity.RdsOrg;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by jwleader on 12/23/15.
 */
public class RdsOrgDAO
{
    private static String CLASS_NAME  = RdsOrgDAO.class.getName();
    private static String SIMPLE_NAME = RdsOrgDAO.class.getSimpleName();

    private JdbcTemplate jdbcTemplate;

    private static final String RDS_ORG_TABLE_NAME = "RDS_ORG";

    protected String getSelectSQL()
    {
        return "SELECT * " +
                "FROM " + RDS_ORG_TABLE_NAME + " " +
                "WHERE ORG_ID = ? " +
                "AND   ORG_TYP_CD = ?";
    }


    public RdsOrg findByOrgIdOrgTypCd(String orgId,
                                      String orgTypCd)
    {
        final String METHOD_NAME = "findByOrgIdOrgTypCd";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RdsOrg rdsOrg = null;

        try
        {
            rdsOrg = jdbcTemplate.queryForObject(getSelectSQL(),
                                                 new Object[]{orgId, orgTypCd},
                                                 new RdsOrgMapper());
        }
        catch (EmptyResultDataAccessException erdaException)
        {
            // returns null
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return rdsOrg;
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
}
