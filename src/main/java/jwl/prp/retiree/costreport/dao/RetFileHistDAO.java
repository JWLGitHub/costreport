package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.dao.mapper.RetFileHistMapper;
import jwl.prp.retiree.costreport.entity.RetFileHist;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;


public class RetFileHistDAO
{
    private static String CLASS_NAME  = RetFileHistDAO.class.getName();
    private static String SIMPLE_NAME = RetFileHistDAO.class.getSimpleName();

    private static final String RETFILEHIST_TABLE_NAME = "RETFILEHIST";

    protected String getSelectSQL()
    {
        return "SELECT * " +
               "FROM " + RETFILEHIST_TABLE_NAME + " " +
               "WHERE FILE_ID = ? ";
    }

    protected String getUpdateSQL()
    {
        return "UPDATE " + RETFILEHIST_TABLE_NAME + " SET " +
               "PS_ID = ?, " +
               "APPL_ID = ?, " +
               "RECEIPT_DT = ?, " +
               "RESPONSE_DT = ?, "  +
               "STATUS = ?, " +
               "PREV_STATUS = ?, " +
               "ERROR_REASON = ?, " +
               "ADD_CNT = ?, " +
               "UPDATE_CNT = ?, " +
               "DELETE_CNT = ?, " +
               "ERROR_CNT = ?, " +
               "TOTAL_CNT = ?, " +
               "TRANS_METHOD = ?, " +
               "INPUT_FILE = ?, " +
               "RESPONSE_FILE = ?, " +
               "MAILBOX_FILE = ?, " +
               "COBC_PLAN_NUM = ?, " +
               "UPDT_PGM = ?, " +
               "TIMESTAMP = ?, " +
               "ORIGIN_ID = ? " +
               "WHERE FILE_ID = ? ";
    }

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;



    public void insertRetFileHist(RetFileHist retFileHist)
    {
        final String METHOD_NAME = "insertRetFileHist";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(21);
        parameters.put("FILE_ID",       retFileHist.getFileId());
        parameters.put("PS_ID",         retFileHist.getPsId());
        parameters.put("APPL_ID",       retFileHist.getApplId());
        parameters.put("RECEIPT_DT",    retFileHist.getReceiptDt());
        parameters.put("RESPONSE_DT",   retFileHist.getResponseDt());
        parameters.put("STATUS",        retFileHist.getStatus());
        parameters.put("PREV_STATUS",   retFileHist.getPrevStatus());
        parameters.put("ERROR_REASON",  retFileHist.getErrorReason());
        parameters.put("ADD_CNT",       retFileHist.getAddCnt());
        parameters.put("UPDATE_CNT",    retFileHist.getUpdateCnt());
        parameters.put("DELETE_CNT",    retFileHist.getDeleteCnt());
        parameters.put("ERROR_CNT",     retFileHist.getErrorCnt());
        parameters.put("TOTAL_CNT",     retFileHist.getTotalCnt());
        parameters.put("TRANS_METHOD",  retFileHist.getTransMethod());
        parameters.put("INPUT_FILE",    retFileHist.getInputFile());
        parameters.put("RESPONSE_FILE", retFileHist.getResponseFile());
        parameters.put("MAILBOX_FILE",  retFileHist.getMailboxFile());
        parameters.put("COBC_PLAN_NUM", retFileHist.getCobcPlanNum());
        parameters.put("UPDT_PGM",      retFileHist.getUpdtPgm());
        parameters.put("TIMESTAMP",     retFileHist.getTimeStamp());
        parameters.put("ORIGIN_ID",     retFileHist.getOriginId());

        simpleJdbcInsert.execute(parameters);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    public RetFileHist findByFileId(int fileId)
    {
        final String METHOD_NAME = "findByFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RetFileHist retFileHist = null;

        try
        {
            retFileHist = jdbcTemplate.queryForObject(getSelectSQL(),
                                                      new Object[]{fileId},
                                                      new RetFileHistMapper());
        }
        catch (EmptyResultDataAccessException erdaException)
        {
            // returns null
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return retFileHist;
    }


    public RetFileHist updateRetFileHist(RetFileHist retFileHist)
    {
        final String METHOD_NAME = "updateRetFileHist";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        jdbcTemplate.update(getUpdateSQL(),
                            retFileHist.getPsId(),
                            retFileHist.getApplId(),
                            retFileHist.getReceiptDt(),
                            retFileHist.getResponseDt(),
                            retFileHist.getStatus(),
                            retFileHist.getPrevStatus(),
                            retFileHist.getErrorReason(),
                            retFileHist.getAddCnt(),
                            retFileHist.getUpdateCnt(),
                            retFileHist.getDeleteCnt(),
                            retFileHist.getErrorCnt(),
                            retFileHist.getTotalCnt(),
                            retFileHist.getTransMethod(),
                            retFileHist.getInputFile(),
                            retFileHist.getResponseFile(),
                            retFileHist.getMailboxFile(),
                            retFileHist.getCobcPlanNum(),
                            retFileHist.getUpdtPgm(),
                            retFileHist.getTimeStamp(),
                            retFileHist.getOriginId(),
                            retFileHist.getFileId());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return retFileHist;
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(RETFILEHIST_TABLE_NAME);
    }
}