package jwl.prp.retiree.costreport.validation;


import jwl.prp.retiree.costreport.entity.CostReportRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileContext
{
    private static String CLASS_NAME  = FileContext.class.getName();
    private static String SIMPLE_NAME = FileContext.class.getSimpleName();

    public static final String       RDS_FILE_ID              = "rdsFileId";
    public static final String       FILE_NAME_SUBMITTER_TYPE = "fileNameSubmitterType";
    public static final String       FILE_NAME_SUBMITTER_ID   = "fileNameSubmitterID";

    public static final List<String> VALID_RECORD_TYPES = Arrays.asList("FHDR", "AHDR", "DETL", "ATRL", "FTRL");

    public static final int          COST_REPORT_RECORD_LENGTH = 110;


    /*
     *---   FILE VARIABLES
     */
    private int               rdsFileId;
    private String            fileNameSubmitterType;
    private String            fileNameSubmitterID;
    private int               fileRecordCounter;
    private int               fileHeaderCounter;
    private String            fileSubmitterID;
    private int               fileApplicationCount;
    private int               fileApplicationAcceptedCount;
    private int               fileApplicationRejectedCount;
    private ArrayList<String> fileApplicationIDs   = new ArrayList<String>();
    private BigDecimal        fileEstimatedPremium = new BigDecimal("0");
    private BigDecimal        fileGrossRetireeCost = new BigDecimal("0");
    private int               fileTrailerCounter;
    private int               fileErrSeqNum;


    /*
     *---   APPLICATION VARIABLES
     */

    private int        applSeqNum;
    private int        applErrSeqNum;
    private boolean    applicationValid;
    private List<CostReportRecord> applicationRecords = new ArrayList<CostReportRecord>();

    // --- HEADER ---
    private int        applicationHeaderCounter;
    private String     applicationHeaderApplicationID;
    private String     validApplicationID;

    // --- TRAILER ---
    private int        applicationRecordCount;
    private BigDecimal applicationEstimatedPremium = new BigDecimal("0");
    private BigDecimal applicationGrossRetireeCost = new BigDecimal("0");



    public void addFileApplicationID(String applicationID)
    {
        getFileApplicationIDs().add(applicationID);
    }

    public void addApplicationRecord(CostReportRecord costReportRecord) { getApplicationRecords().add(costReportRecord); }


    public void setApplicationHeaderVariables(String applicationID)
    {
        addFileApplicationID(applicationID);
        setApplicationValid(true);
        setApplicationRecords(new ArrayList<CostReportRecord>());
    }


    /*
     *---   GETTER(S)
     */

    // --- FILE ---
    public int getRdsFileId() { return rdsFileId; }

    public String getFileNameSubmitterType() { return fileNameSubmitterType; }

    public String getFileNameSubmitterID() { return fileNameSubmitterID; }

    public int getFileRecordCounter() { return fileRecordCounter; }

    public int getFileHeaderCounter() { return fileHeaderCounter; }

    public String getFileSubmitterID() { return fileSubmitterID; }

    public int getFileApplicationCount() { return fileApplicationCount; }

    public int getFileApplicationAcceptedCount() { return fileApplicationAcceptedCount; }

    public int getFileApplicationRejectedCount() { return fileApplicationRejectedCount; }

    public ArrayList<String> getFileApplicationIDs() { return fileApplicationIDs; }

    public BigDecimal getFileEstimatedPremium() { return fileEstimatedPremium; }

    public BigDecimal getFileGrossRetireeCost() { return fileGrossRetireeCost; }

    public int getFileTrailerCounter() { return fileTrailerCounter; }

    public int getFileErrSeqNum() { return fileErrSeqNum; }

    // --- APPLICATION ---
    public int getApplSeqNum() { return applSeqNum; }

    public int getApplErrSeqNum() { return applErrSeqNum; }

    public boolean isApplicationValid() { return applicationValid; }

    public List<CostReportRecord> getApplicationRecords() { return applicationRecords; }

    public int getApplicationHeaderCounter() { return applicationHeaderCounter; }

    public String getApplicationHeaderApplicationID() { return applicationHeaderApplicationID; }

    public String getValidApplicationID() { return validApplicationID; }

    public int getApplicationRecordCount() { return applicationRecordCount; }

    public BigDecimal getApplicationEstimatedPremium() { return applicationEstimatedPremium; }

    public BigDecimal getApplicationGrossRetireeCost() { return applicationGrossRetireeCost; }


    /*
     *---   SETTER(S)
     */

    // --- FILE ---
    public void setRdsFileId(int rdsFileId) { this.rdsFileId = rdsFileId; }

    public void setFileNameSubmitterType(String fileNameSubmitterType) { this.fileNameSubmitterType = fileNameSubmitterType; }

    public void setFileNameSubmitterID(String fileNameSubmitterID) { this.fileNameSubmitterID = fileNameSubmitterID; }

    public void setFileRecordCounter(int fileRecordCounter) { this.fileRecordCounter = fileRecordCounter; }

    public void setFileHeaderCounter(int fileHeaderCounter) { this.fileHeaderCounter = fileHeaderCounter; }

    public void setFileSubmitterID(String fileSubmitterID) { this.fileSubmitterID = fileSubmitterID; }

    public void setFileApplicationCount(int fileApplicationCount) { this.fileApplicationCount = fileApplicationCount; }

    public void setFileApplicationAcceptedCount(int fileApplicationAcceptedCount) { this.fileApplicationAcceptedCount = fileApplicationAcceptedCount; }

    public void setFileApplicationRejectedCount(int fileApplicationRejectedCount) { this.fileApplicationRejectedCount = fileApplicationRejectedCount; }

    public void setFileApplicationIDs(ArrayList<String> fileApplicationIDs) { this.fileApplicationIDs = fileApplicationIDs; }

    public void setFileEstimatedPremium(BigDecimal fileEstimatedPremium) { this.fileEstimatedPremium = fileEstimatedPremium; }

    public void setFileGrossRetireeCost(BigDecimal fileGrossRetireeCost) { this.fileGrossRetireeCost = fileGrossRetireeCost; }

    public void setFileTrailerCounter(int fileTrailerCounter) { this.fileTrailerCounter = fileTrailerCounter; }

    public void setFileErrSeqNum(int fileErrSeqNum) { this.fileErrSeqNum = fileErrSeqNum; }

    // --- APPLICATION ---
    public void setApplSeqNum(int applSeqNum) { this.applSeqNum = applSeqNum; }

    public void setApplErrSeqNum(int applErrSeqNum) { this.applErrSeqNum = applErrSeqNum; }

    public void setApplicationValid(boolean applicationValid) { this.applicationValid = applicationValid; }

    public void setApplicationRecords(List<CostReportRecord> applicationRecords) { this.applicationRecords = applicationRecords; }

    public void setApplicationHeaderCounter(int applicationHeaderCounter) { this.applicationHeaderCounter = applicationHeaderCounter; }

    public void setApplicationHeaderApplicationID(String applicationHeaderApplicationID) { this.applicationHeaderApplicationID = applicationHeaderApplicationID; }

    public void setValidApplicationID(String validApplicationID) { this.validApplicationID = validApplicationID; }

    public void setApplicationRecordCount(int applicationRecordCount) { this.applicationRecordCount = applicationRecordCount; }

    public void setApplicationEstimatedPremium(BigDecimal applicationEstimatedPremium) { this.applicationEstimatedPremium = applicationEstimatedPremium; }

    public void setApplicationGrossRetireeCost(BigDecimal applicationGrossRetireeCost) { this.applicationGrossRetireeCost = applicationGrossRetireeCost; }
}
