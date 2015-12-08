package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.entity.ApplErr;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwleader on 12/8/15.
 */
public class ApplErrDAO
{
    private static String CLASS_NAME  = ApplErrDAO.class.getName();
    private static String SIMPLE_NAME = ApplErrDAO.class.getSimpleName();

    private static final String APPL_ERR_TABLE_NAME = "APPL_ERR";

    private static final String INSERT_APPL_ERR =
                                "INSERT INTO " + APPL_ERR_TABLE_NAME + " " +
                                "(FILE_ID," +
                                " APPL_SEQ_NUM," +
                                " ERR_CTGRY_CD," +
                                " ERR_CD," +
                                " ERR_SEQ_NUM," +
                                " COST_YEAR_NUM," +
                                " COST_MNTH_NUM," +
                                " RX_GRP_NUM," +
                                " ERR_INFO)" +
                                " values (?,?,?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;



    public void insertApplErr(ApplErr applErr)
    {
        final String METHOD_NAME = "insertApplErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(9);
        parameters.put("FILE_ID",       applErr.getFileId());
        parameters.put("APPL_SEQ_NUM",  applErr.getApplSeqNum());
        parameters.put("ERR_CTGRY_CD",  applErr.getErrCtgryCd());
        parameters.put("ERR_CD",        applErr.getErrCd());
        parameters.put("ERR_SEQ_NUM",   applErr.getErrSeqNum());
        parameters.put("COST_YEAR_NUM", applErr.getCostYearNum());
        parameters.put("COST_MNTH_NUM", applErr.getCostMnthNum());
        parameters.put("RX_GRP_NUM",    applErr.getRxGrpNum());
        parameters.put("ERR_INFO",      applErr.getErrInfo());

        simpleJdbcInsert.execute(parameters);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(APPL_ERR_TABLE_NAME);
    }
}
