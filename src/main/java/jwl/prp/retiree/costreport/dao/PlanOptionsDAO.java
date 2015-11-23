package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.dao.mapper.PlanOptionsMapper;
import jwl.prp.retiree.costreport.entity.PlanOptions;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by jwleader on 11/20/15.
 */
public class PlanOptionsDAO
{
    private static String CLASS_NAME  = PlanOptionsDAO.class.getName();
    private static String SIMPLE_NAME = PlanOptionsDAO.class.getSimpleName();

    private JdbcTemplate jdbcTemplate;

    private static final String PLANOPTIONS_TABLE_NAME = "PLANOPTIONS";

    protected String getSelectSQL()
    {
        return "SELECT * " +
                "FROM " + PLANOPTIONS_TABLE_NAME + " " +
                "WHERE APPLICATION_ID = ? " +
                "AND   GROUP_NUMBER = ?";
    }


    public PlanOptions findByApplicationIdGroupNumber(String applicationId,
                                                      String groupNumber)
    {
        final String METHOD_NAME = "findByApplicationIdGroupNumber";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        PlanOptions planOptions = null;

        try
        {
            planOptions = jdbcTemplate.queryForObject(getSelectSQL(),
                                                      new Object[]{applicationId, groupNumber},
                                                      new PlanOptionsMapper());
        }
        catch (EmptyResultDataAccessException erdaException)
        {
            // returns null
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return planOptions;
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
