package jwl.prp.retiree.costreport.dao.mapper;


import jwl.prp.retiree.costreport.entity.RDSFile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RDSFileMapper implements RowMapper<RDSFile>
{
    public RDSFile mapRow(ResultSet resultSet,
                          int       rowNumber)
                          throws SQLException
    {
        RDSFile rdsFile = new RDSFile();
        rdsFile.setFileId(resultSet.getInt("FILE_ID"));
        rdsFile.setFileDirCd(resultSet.getString("FILE_DIR_CD"));
        rdsFile.setFileTypeCd(resultSet.getString("FILE_TYPE_CD"));
        rdsFile.setFileDtTm(resultSet.getTimestamp("FILE_DT_TM"));
        rdsFile.setFileName(resultSet.getString("FILE_NAME"));
        rdsFile.setFileDescTxt(resultSet.getString("FILE_DESC_TXT"));
        rdsFile.setSubmOrgId(resultSet.getString("SUBM_ORG_ID"));
        rdsFile.setOrgTypCd(resultSet.getString("ORG_TYP_CD"));
        rdsFile.setOrgId(resultSet.getString("ORG_ID"));
        rdsFile.setStusCtgryCd(resultSet.getString("STUS_CTGRY_CD"));
        rdsFile.setStusCd(resultSet.getString("STUS_CD"));
        rdsFile.setStusTs(resultSet.getTimestamp("STUS_TS"));
        rdsFile.setStusPgm(resultSet.getString("STUS_PGM"));
        rdsFile.setUptdPgm(resultSet.getString("UPTD_PGM"));
        rdsFile.setUpdtTs(resultSet.getTimestamp("UPDT_TS"));
        rdsFile.setProcessDt(resultSet.getDate("PROCESS_DT"));
        rdsFile.setReceiptDt(resultSet.getDate("RECEIPT_DT"));
        return rdsFile;
    }
}
