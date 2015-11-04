package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.dao.mapper.CostReportFileMapper;
import jwl.prp.retiree.costreport.entity.CostReportFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwleader on 10/31/15.
 */
public class CostReportFileDAO
{
    private static String CLASS_NAME = CostReportFileDAO.class.getName();
    private static String SIMPLE_NAME = CostReportFileDAO.class.getSimpleName();

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String COST_REPORT_FILE_TABLE_NAME = "CST_RPRT_FL";

    private static final String COST_REPORT_FILE_ID = "CST_RPRT_FL_ID";

    private static final String INSERT_COST_REPORT_FILE =
            "INSERT INTO " + COST_REPORT_FILE_TABLE_NAME + " " +
            "(CST_RPRT_FL_NM," +
            " CST_RPRT_FL_STTS," +
            " CST_RPRT_FL_ADD_JB," +
            " CST_RPRT_FL_ADD_STP," +
            " CST_RPRT_FL_ADD_PRGRM," +
            " CST_RPRT_FL_ADD_MTHD," +
            " CST_RPRT_FL_ADD_DTTIM," +
            " CST_RPRT_FL_UPDT_JB," +
            " CST_RPRT_FL_UPDT_STP," +
            " CST_RPRT_FL_UPDT_PRGRM," +
            " CST_RPRT_FL_UPDT_MTHD," +
            " CST_RPRT_FL_UPDT_DTTIM)" +
            " values (?,?,?,?,?,?,?,?,?,?,?,?)";

    protected String getKey() { return COST_REPORT_FILE_ID; }

    protected String getSelectString()
    {
        return "SELECT " +
               "CST_RPRT_FL_ID, " +
               "CST_RPRT_FL_NM, " +
               "CST_RPRT_FL_STTS, " +
               "CST_RPRT_FL_ADD_JB, " +
               "CST_RPRT_FL_ADD_STP, " +
               "CST_RPRT_FL_ADD_PRGRM, " +
               "CST_RPRT_FL_ADD_MTHD, " +
               "CST_RPRT_FL_ADD_DTTIM, " +
               "CST_RPRT_FL_UPDT_JB, " +
               "CST_RPRT_FL_UPDT_STP, " +
               "CST_RPRT_FL_UPDT_PRGRM, " +
               "CST_RPRT_FL_UPDT_MTHD, " +
               "CST_RPRT_FL_UPDT_DTTIM " +
               "FROM CST_RPRT_FL " +
               "WHERE RPRT_FL_ID ";
    }

    protected String getDeleteString() { return "DELETE FROM CST_RPRT_FL WHERE RPRT_FL_ID "; }



    public CostReportFile findByCostReportFileID(int costReportFileID)
    {
        final String METHOD_NAME = "findByCostReportFileID";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        String selectSQL = "SELECT * FROM CST_RPRT_FL WHERE CST_RPRT_FL_ID = ?";
        CostReportFile costReportFile = jdbcTemplate.queryForObject(selectSQL,
                                                                    new Object[] { costReportFileID },
                                                                    new CostReportFileMapper());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return costReportFile;
    }


    public void insertCostReportFile(CostReportFile costReportFile)
    {
        final String METHOD_NAME = "insertCostReportFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(12);
        parameters.put("CST_RPRT_FL_NM",         costReportFile.getName());
        parameters.put("CST_RPRT_FL_STTS",       costReportFile.getStatus());
        parameters.put("CST_RPRT_FL_ADD_JB",     costReportFile.getAddJob());
        parameters.put("CST_RPRT_FL_ADD_STP",    costReportFile.getAddStep());
        parameters.put("CST_RPRT_FL_ADD_PRGRM",  costReportFile.getAddProgram());
        parameters.put("CST_RPRT_FL_ADD_MTHD",   costReportFile.getAddMethod());
        parameters.put("CST_RPRT_FL_ADD_DTTIM",  costReportFile.getAddDateTime());
        parameters.put("CST_RPRT_FL_UPDT_JB",    costReportFile.getUpdateJob());
        parameters.put("CST_RPRT_FL_UPDT_STP",   costReportFile.getUpdateStep());
        parameters.put("CST_RPRT_FL_UPDT_PRGRM", costReportFile.getUpdateProgram());
        parameters.put("CST_RPRT_FL_UPDT_MTHD",  costReportFile.getUpdateMethod());
        parameters.put("CST_RPRT_FL_UPDT_DTTIM", costReportFile.getUpdateDateTime());

        Number fileID = simpleJdbcInsert.executeAndReturnKey(parameters);
        costReportFile.setId(fileID.intValue());
        /*
        jdbcTemplate.update(INSERT_COST_REPORT_FILE,
                costReportFile.getName(),
                costReportFile.getStatus(),
                costReportFile.getAddJob(),
                costReportFile.getAddStep(),
                costReportFile.getAddProgram(),
                costReportFile.getAddMethod(),
                costReportFile.getAddDateTime(),
                costReportFile.getUpdateJob(),
                costReportFile.getUpdateStep(),
                costReportFile.getUpdateProgram(),
                costReportFile.getUpdateMethod(),
                costReportFile.getUpdateDateTime());
        */

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    public CostReportFile updateCostReportFile(CostReportFile costReportFile)
    {
        final String METHOD_NAME = "updateCostReportFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        String updateSQL = "UPDATE CST_RPRT_FL SET " +
                           "CST_RPRT_FL_NM = ?, " +
                           "CST_RPRT_FL_STTS = ?, " +
                           "CST_RPRT_FL_ADD_JB = ?, " +
                           "CST_RPRT_FL_ADD_STP = ?, " +
                           "CST_RPRT_FL_ADD_PRGRM = ?, " +
                           "CST_RPRT_FL_ADD_MTHD = ?, " +
                           "CST_RPRT_FL_ADD_DTTIM = ?, " +
                           "CST_RPRT_FL_UPDT_JB = ?, " +
                           "CST_RPRT_FL_UPDT_STP = ?, " +
                           "CST_RPRT_FL_UPDT_PRGRM = ?, " +
                           "CST_RPRT_FL_UPDT_MTHD = ?, " +
                           "CST_RPRT_FL_UPDT_DTTIM = ? " +
                           "WHERE " + COST_REPORT_FILE_ID + " = ?";

        jdbcTemplate.update(updateSQL,
                            costReportFile.getName(),
                            costReportFile.getStatus(),
                            costReportFile.getAddJob(),
                            costReportFile.getAddStep(),
                            costReportFile.getAddProgram(),
                            costReportFile.getAddMethod(),
                            costReportFile.getAddDateTime(),
                            costReportFile.getUpdateJob(),
                            costReportFile.getUpdateStep(),
                            costReportFile.getUpdateProgram(),
                            costReportFile.getUpdateMethod(),
                            costReportFile.getUpdateDateTime(),
                            costReportFile.getId());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return costReportFile;
    }


    /*
    *****                                       *****
    *****     -----     SETTER(s)     -----     *****
    *****                                       *****
    */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(COST_REPORT_FILE_TABLE_NAME).usingGeneratedKeyColumns(COST_REPORT_FILE_ID);
    }
}
