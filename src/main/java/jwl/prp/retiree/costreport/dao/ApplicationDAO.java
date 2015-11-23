package jwl.prp.retiree.costreport.dao;


import jwl.prp.retiree.costreport.dao.mapper.ApplicationMapper;
import jwl.prp.retiree.costreport.entity.Application;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


public class ApplicationDAO
{
    private static String CLASS_NAME  = ApplicationDAO.class.getName();
    private static String SIMPLE_NAME = ApplicationDAO.class.getSimpleName();

    private JdbcTemplate jdbcTemplate;

    private static final String APPLICATION_TABLE_NAME = "APPLICATION";

    private static final String APPLICATION_ID = "APPLICATION_ID";

    protected String getSelectSQL()
    {
        return "SELECT * " +
               "FROM " + APPLICATION_TABLE_NAME + " " +
               "WHERE " + APPLICATION_ID + " = ?";
    }


    public Application findByApplicationId(String applicationId)
    {
        final String METHOD_NAME = "findByApplicationId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Application application = null;

        try
        {
            application = jdbcTemplate.queryForObject(getSelectSQL(),
                                                      new Object[]{applicationId},
                                                      new ApplicationMapper());
        }
        catch (EmptyResultDataAccessException erdaException)
        {
            // returns null
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return application;
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
