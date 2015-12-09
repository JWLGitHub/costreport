package jwl.prp.retiree.costreport.dao;


import jwl.prp.retiree.costreport.dao.mapper.FileApplMapper;
import jwl.prp.retiree.costreport.entity.FileAppl;

import jwl.prp.retiree.costreport.entity.RDSFile;
import org.springframework.dao.EmptyResultDataAccessException;
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

    protected String getSelectSQL()
    {
        return "SELECT * " +
               "FROM " + FILE_APPL_TABLE_NAME + " " +
               "WHERE FILE_ID = ? " +
               "AND   APPL_SEQ_NUM = ?";
    }

    protected String getUpdateSQL()
    {
        return "UPDATE " + FILE_APPL_TABLE_NAME + " SET " +
                "SUBM_APPL_ID = ?, " +
                "PS_ID = ?, " +
                "PSTG_PGM = ?, " +
                "PSTG_TS = ?, " +
                "STUS_TS = ?, " +
                "STUS_PGM = ?, " +
                "STUS_CTGRY_CD = ?, " +
                "STUS_CD = ?, " +
                "APPL_ID = ? " +
                "WHERE FILE_ID = ? " +
                "AND   APPL_SEQ_NUM = ?";
    }

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


    public FileAppl findByFileIdApplSeqNum(int fileId,
                                           int applSeqNum)
    {
        final String METHOD_NAME = "findByFileIdApplSeqNum";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        FileAppl fileAppl = null;

        try
        {
            fileAppl = jdbcTemplate.queryForObject(getSelectSQL(),
                                                   new Object[]{fileId, applSeqNum},
                                                   new FileApplMapper());
        }
        catch (EmptyResultDataAccessException erdaException)
        {
            // returns null
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return fileAppl;
    }

    public FileAppl updateFileAppl(FileAppl fileAppl)
    {
        final String METHOD_NAME = "updateFileAppl";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        jdbcTemplate.update(getUpdateSQL(),
                            fileAppl.getSubmApplId(),
                            fileAppl.getPsId(),
                            fileAppl.getPstgPgm(),
                            fileAppl.getPstgTs(),
                            fileAppl.getStusTs(),
                            fileAppl.getStusPgm(),
                            fileAppl.getStusCtgryCd(),
                            fileAppl.getStusCd(),
                            fileAppl.getApplId(),
                            fileAppl.getFileId(),
                            fileAppl.getApplSeqNum());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return fileAppl;
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
