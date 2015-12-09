package jwl.prp.retiree.costreport.dao.mapper;


import jwl.prp.retiree.costreport.entity.FileAppl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FileApplMapper implements RowMapper<FileAppl>
{
    public FileAppl mapRow(ResultSet resultSet,
                           int       rowNumber)
                           throws SQLException
    {
        FileAppl fileAppl = new FileAppl();
        fileAppl.setFileId(resultSet.getInt("FILE_ID"));
        fileAppl.setApplSeqNum(resultSet.getInt("APPL_SEQ_NUM"));
        fileAppl.setSubmApplId(resultSet.getString("SUBM_APPL_ID"));
        fileAppl.setPsId(resultSet.getString("PS_ID"));
        fileAppl.setPstgPgm(resultSet.getString("PSTG_PGM"));
        fileAppl.setPstgTs(resultSet.getTimestamp("PSTG_TS"));
        fileAppl.setStusTs(resultSet.getTimestamp("STUS_TS"));
        fileAppl.setStusPgm(resultSet.getString("STUS_PGM"));
        fileAppl.setStusCtgryCd(resultSet.getString("STUS_CTGRY_CD"));
        fileAppl.setStusCd(resultSet.getString("STUS_CD"));
        fileAppl.setApplId(resultSet.getString("APPL_ID"));
        return fileAppl;
    }
}

