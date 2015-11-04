package jwl.prp.retiree.costreport.dao;

import jwl.prp.retiree.costreport.entity.CostReportFile;
import jwl.prp.retiree.costreport.entity.CostReportFileProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwleader on 11/1/15.
 */
public class CostReportFileProcessDAO
{
    private static String CLASS_NAME  = CostReportFileProcessDAO.class.getName();
    private static String SIMPLE_NAME = CostReportFileProcessDAO.class.getSimpleName();

    private static final String COST_REPORT_FILE_PROCESS_TABLE_NAME = "CST_RPRT_FL_PRCSS";

    private static final String COST_REPORT_FILE_PROCESS_ID = "CST_RPRT_FL_PRCSS_ID";

    private static final String INSERT_COST_REPORT_FILE =
            "INSERT INTO " + COST_REPORT_FILE_PROCESS_TABLE_NAME + " " +
            "(CST_RPRT_FL_ID," +
            " CST_RPRT_FL_PRCSS," +
            " CST_RPRT_FL_PRCSS_TXT, " +
            " CST_RPRT_FL_PRCSS_ADD_JB," +
            " CST_RPRT_FL_PRCSS_ADD_STP," +
            " CST_RPRT_FL_PRCSS_ADD_PRGRM," +
            " CST_RPRT_FL_PRCSS_ADD_MTHD," +
            " CST_RPRT_FL_PRCSS_ADD_DTTIM)" +
            " values (?,?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;



    public void insertCostReportFileProcess(CostReportFileProcess costReportFileProcess)
    {
        final String METHOD_NAME = "insertCostReportFileProcess";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Map<String, Object> parameters = new HashMap<String, Object>(8);
        parameters.put("CST_RPRT_FL_ID",              costReportFileProcess.getFileId());
        parameters.put("CST_RPRT_FL_PRCSS",           costReportFileProcess.getProcess());
        parameters.put("CST_RPRT_FL_PRCSS_TXT",       costReportFileProcess.getProcessText());
        parameters.put("CST_RPRT_FL_PRCSS_ADD_JB",    costReportFileProcess.getAddJob());
        parameters.put("CST_RPRT_FL_PRCSS_ADD_STP",   costReportFileProcess.getAddStep());
        parameters.put("CST_RPRT_FL_PRCSS_ADD_PRGRM", costReportFileProcess.getAddProgram());
        parameters.put("CST_RPRT_FL_PRCSS_ADD_MTHD",  costReportFileProcess.getAddMethod());
        parameters.put("CST_RPRT_FL_PRCSS_ADD_DTTM",  costReportFileProcess.getAddDateTime());

        Number processID = simpleJdbcInsert.executeAndReturnKey(parameters);
        costReportFileProcess.setProcessId(processID.intValue());

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
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(COST_REPORT_FILE_PROCESS_TABLE_NAME).usingGeneratedKeyColumns(COST_REPORT_FILE_PROCESS_ID);
    }
}
