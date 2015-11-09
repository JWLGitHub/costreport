package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.dao.mapper.RDSFileMapper;
import jwl.prp.retiree.costreport.entity.RDSFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwleader on 11/7/15.
 */
public class RDSFileDAO {
    private static String CLASS_NAME = RDSFileDAO.class.getName();
    private static String SIMPLE_NAME = RDSFileDAO.class.getSimpleName();

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String RDS_FILE_TABLE_NAME = "RDS_FILE";

    private static final String RDS_FILE_ID = "FILE_ID";

    private static final String INSERT_RDS_FILE =
                    "INSERT INTO " + RDS_FILE_TABLE_NAME + " " +
                    "(FILE_DIR_CD," +
                    " FILE_TYPE_CD," +
                    " FILE_DT_TM," +
                    " FILE_NAME," +
                    " FILE_DESC_TXT," +
                    " SUBM_ORG_ID," +
                    " ORG_TYP_CD," +
                    " ORG_ID," +
                    " STUS_CTGRY_CD," +
                    " STUS_CD," +
                    " STUS_TS," +
                    " STUS_PGM," +
                    " UPTD_PGM," +
                    " UPDT_TS," +
                    " PROCESS_DT," +
                    " RECEIPT_DT)" +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


    protected String getKey() {
        return RDS_FILE_ID;
    }

    protected String getSelectString()
    {
        return "SELECT " +
                RDS_FILE_ID + ", " +
                "FILE_DIR_CD, " +
                "FILE_TYPE_CD, " +
                "FILE_DT_TM, " +
                "FILE_NAME, " +
                "FILE_DESC_TXT, " +
                "SUBM_ORG_ID, " +
                "ORG_TYP_CD, " +
                "ORG_ID, " +
                "STUS_CTGRY_CD, " +
                "STUS_CD, " +
                "STUS_TS, " +
                "STUS_PGM, " +
                "UPTD_PGM, " +
                "UPDT_TS, " +
                "PROCESS_DT, " +
                "RECEIPT_DT " +
                "FROM " + RDS_FILE_TABLE_NAME + " " +
                "WHERE " + RDS_FILE_ID + " ";
    }

    protected String getDeleteString()
    {
        return "DELETE FROM " + RDS_FILE_TABLE_NAME + " WHERE " + RDS_FILE_ID + " ";
    }


    public RDSFile findByFileId(int fileId)
    {
        final String METHOD_NAME = "findByFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        String selectSQL = "SELECT * FROM " + RDS_FILE_TABLE_NAME + " WHERE " + RDS_FILE_ID + " = ?";
        RDSFile rdsFile = jdbcTemplate.queryForObject(selectSQL,
                                                      new Object[]{fileId},
                                                      new RDSFileMapper());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return rdsFile;
    }


    public void insertRDSFile(RDSFile rdsFile)
    {
        final String METHOD_NAME = "insertRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(16);
        parameters.put("FILE_DIR_CD",   rdsFile.getFileDirCd());
        parameters.put("FILE_TYPE_CD",  rdsFile.getFileTypeCd());
        parameters.put("FILE_DT_TM",    rdsFile.getFileDtTm());
        parameters.put("FILE_NAME",     rdsFile.getFileName());
        parameters.put("FILE_DESC_TXT", rdsFile.getFileDescTxt());
        parameters.put("SUBM_ORG_ID",   rdsFile.getSubmOrgId());
        parameters.put("ORG_TYP_CD",    rdsFile.getOrgTypCd());
        parameters.put("ORG_ID",        rdsFile.getOrgId());
        parameters.put("STUS_CTGRY_CD", rdsFile.getStusCtgryCd());
        parameters.put("STUS_CD",       rdsFile.getStusCd());
        parameters.put("STUS_TS",       rdsFile.getStusTs());
        parameters.put("STUS_PGM",      rdsFile.getStusPgm());
        parameters.put("UPTD_PGM",      rdsFile.getUptdPgm());
        parameters.put("UPDT_TS",       rdsFile.getUpdtTs());
        parameters.put("PROCESS_DT",    rdsFile.getProcessDt());
        parameters.put("RECEIPT_DT",    rdsFile.getReceiptDt());

        Number fileId = simpleJdbcInsert.executeAndReturnKey(parameters);
        rdsFile.setFileId(fileId.intValue());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    public RDSFile updateRDSFile(RDSFile rdsFile)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        String updateSQL =
                "UPDATE " + RDS_FILE_TABLE_NAME + " SET " +
                "FILE_DIR_CD = ?, " +
                "FILE_TYPE_CD = ?, " +
                "FILE_DT_TM = ?, " +
                "FILE_NAME = ?, " +
                "FILE_DESC_TXT = ?, " +
                "SUBM_ORG_ID = ?, " +
                "ORG_TYP_CD = ?, " +
                "ORG_ID = ?, " +
                "STUS_CTGRY_CD = ?, " +
                "STUS_CD = ?, " +
                "STUS_TS = ?, " +
                "STUS_PGM = ?, " +
                "UPTD_PGM = ?, " +
                "UPDT_TS = ?, " +
                "PROCESS_DT = ?, " +
                "RECEIPT_DT = ? " +
                "WHERE " + RDS_FILE_ID + " = ?";

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        jdbcTemplate.update(updateSQL,
                            rdsFile.getFileDirCd(),
                            rdsFile.getFileTypeCd(),
                            rdsFile.getFileDtTm(),
                            rdsFile.getFileName(),
                            rdsFile.getFileDescTxt(),
                            rdsFile.getSubmOrgId(),
                            rdsFile.getOrgTypCd(),
                            rdsFile.getOrgId(),
                            rdsFile.getStusCtgryCd(),
                            rdsFile.getStusCd(),
                            rdsFile.getStusTs(),
                            rdsFile.getStusPgm(),
                            rdsFile.getUptdPgm(),
                            rdsFile.getUpdtTs(),
                            rdsFile.getProcessDt(),
                            rdsFile.getReceiptDt(),
                            rdsFile.getFileId());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return rdsFile;
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(RDS_FILE_TABLE_NAME).usingGeneratedKeyColumns(RDS_FILE_ID);
    }
}
