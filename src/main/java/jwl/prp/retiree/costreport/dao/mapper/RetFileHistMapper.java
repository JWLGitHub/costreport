package jwl.prp.retiree.costreport.dao.mapper;


import jwl.prp.retiree.costreport.entity.RetFileHist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RetFileHistMapper implements RowMapper<RetFileHist>
{
    public RetFileHist mapRow(ResultSet resultSet,
                              int       rowNumber)
                              throws SQLException
    {
        RetFileHist retFileHist = new RetFileHist();
        retFileHist.setFileId(resultSet.getString("FILE_ID"));
        retFileHist.setPsId(resultSet.getString("PS_ID"));
        retFileHist.setApplId(resultSet.getString("APPL_ID"));
        retFileHist.setReceiptDt(resultSet.getInt("RECEIPT_DT"));
        retFileHist.setResponseDt(resultSet.getInt("RESPONSE_DT"));
        retFileHist.setStatus(resultSet.getString("STATUS"));
        retFileHist.setPrevStatus(resultSet.getString("PREV_STATUS"));
        retFileHist.setErrorReason(resultSet.getString("ERROR_REASON"));
        retFileHist.setAddCnt(resultSet.getInt("ADD_CNT"));
        retFileHist.setUpdateCnt(resultSet.getInt("UPDATE_CNT"));
        retFileHist.setDeleteCnt(resultSet.getInt("DELETE_CNT"));
        retFileHist.setErrorCnt(resultSet.getInt("ERROR_CNT"));
        retFileHist.setTotalCnt(resultSet.getInt("TOTAL_CNT"));
        retFileHist.setTransMethod(resultSet.getString("TRANS_METHOD"));
        retFileHist.setInputFile(resultSet.getString("INPUT_FILE"));
        retFileHist.setResponseFile(resultSet.getString("RESPONSE_FILE"));
        retFileHist.setMailboxFile(resultSet.getString("MAILBOX_FILE"));
        retFileHist.setCobcPlanNum(resultSet.getString("COBC_PLAN_NUM"));
        retFileHist.setUpdtPgm(resultSet.getString("UPDT_PGM"));
        retFileHist.setTimeStamp(resultSet.getTimestamp("TIMESTAMP"));
        retFileHist.setOriginId(resultSet.getString("ORIGIN_ID"));
        return retFileHist;
    }
}