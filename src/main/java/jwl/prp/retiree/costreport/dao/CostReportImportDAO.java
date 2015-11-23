package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.entity.CostReportImport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwleader on 11/23/15.
 */
public class CostReportImportDAO
{
    private static String CLASS_NAME  = CostReportImportDAO.class.getName();
    private static String SIMPLE_NAME = CostReportImportDAO.class.getSimpleName();

    private static final String COST_REPORT_IMPORT_TABLE_NAME = "COST_REPORT_IMPORT";

    private static final String INSERT_COST_REPORT_IMPORT = "INSERT INTO " + COST_REPORT_IMPORT_TABLE_NAME + " " +
                                                            "(FILE_ID," +
                                                            " FILE_DT_TM," +
                                                            " RECORD_CONTENTS)" +
                                                            " values (?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;



    public void insertCostReportImport(CostReportImport costReportImport)
    {
        final String METHOD_NAME = "insertCostReportImport";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("FILE_ID",         costReportImport.getFileId());
        parameters.put("FILE_DT_TM",      costReportImport.getFileDtTm());
        parameters.put("RECORD_CONTENTS", costReportImport.getRecordContents());

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
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(COST_REPORT_IMPORT_TABLE_NAME);
    }
}
