package jwl.prp.retiree.costreport.dao;


import jwl.prp.retiree.costreport.entity.FileAppl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;


public class FileApplDAO
{
    private static String CLASS_NAME  = FileApplDAO.class.getName();
    private static String SIMPLE_NAME = FileApplDAO.class.getSimpleName();

    private static final String FILE_APPL_TABLE_NAME = "FILE_APPL";

    private static final String INSERT_FILE_APPL = "INSERT INTO " + FILE_APPL_TABLE_NAME + " " +
                                                   "(FILE_ID," +
                                                   " APPL_SEQ_NUM," +
                                                   " SUBM_APPL_ID," +
                                                   " PS_ID," +
                                                   " PSTG_PGM," +
                                                   " PSTG_TS," +
                                                   " STUS_TS," +
                                                   " STUS_PGM," +
                                                   " STUS_CTGRY_CD," +
                                                   " STUS_CD," +
                                                   " APPL_ID)" +
                                                   " values (?,?,?,?,?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;



    public void insertFileAppl(FileAppl fileAppl)
    {
        final String METHOD_NAME = "insertFileAppl";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(11);
        parameters.put("FILE_ID",       fileAppl.getFileId());
        parameters.put("APPL_SEQ_NUM",  fileAppl.getApplSeqNum());
        parameters.put("SUBM_APPL_ID",  fileAppl.getSubmApplId());
        parameters.put("PS_ID",         fileAppl.getPsId());
        parameters.put("PSTG_PGM",      fileAppl.getPstgPgm());
        parameters.put("PSTG_TS",       fileAppl.getPstgTs());
        parameters.put("STUS_TS",       fileAppl.getStusTs());
        parameters.put("STUS_PGM",      fileAppl.getStusPgm());
        parameters.put("STUS_CTGRY_CD", fileAppl.getStusCtgryCd());
        parameters.put("STUS_CD",       fileAppl.getStusCd());
        parameters.put("APPL_ID",       fileAppl.getApplId());

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
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(FILE_APPL_TABLE_NAME);
    }
}
