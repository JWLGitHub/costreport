package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.entity.FileErr;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwleader on 11/8/15.
 */
public class FileErrDAO
{
    private static String CLASS_NAME  = FileErrDAO.class.getName();
    private static String SIMPLE_NAME = FileErrDAO.class.getSimpleName();

    private static final String FILE_ERR_TABLE_NAME = "FILE_ERR";

    private static final String INSERT_FILE_ERR = "INSERT INTO " + FILE_ERR_TABLE_NAME + " " +
                                                  "(FILE_ID," +
                                                  " ERR_CD," +
                                                  " ERR_CTGRY_CD," +
                                                  " ERR_SEQ_NUM," +
                                                  " ERR_INFO)" +
                                                  " values (?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;



    public void insertFileErr(FileErr fileErr)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(5);
        parameters.put("FILE_ID",      fileErr.getFileId());
        parameters.put("ERR_CD",       fileErr.getErrCd());
        parameters.put("ERR_CTGRY_CD", fileErr.getErrCtgryCd());
        parameters.put("ERR_SEQ_NUM",  fileErr.getErrSeqNum());
        parameters.put("ERR_INFO",     fileErr.getErrInfo());

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
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(FILE_ERR_TABLE_NAME);
    }
}
