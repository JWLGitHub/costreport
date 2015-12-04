package jwl.prp.retiree.costreport.validation;


import jwl.prp.retiree.costreport.entity.CostReportRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FileContext
{

    /*
     *---   FILE VARIABLES
     */
    private int               fileHeaderCounter;
    private int               fileApplicationCount;
    private ArrayList<String> fileApplicationIDs   = new ArrayList<String>();
    private BigDecimal        fileGrossRetireeCost = new BigDecimal("0");
    private int               fileTrailerCounter;


    /*
     *---   APPLICATION VARIABLES
     */

    private List<CostReportRecord> applicationRecords = new ArrayList<CostReportRecord>();
    private boolean    applicationValid;

    // --- HEADER ---
    private int        applicationHeaderCounter;
    private String     applicationID;

    // --- TRAILER ---
    private int        applicationRecordCount;
    private BigDecimal applicationGrossRetireeCost = new BigDecimal("0");




    public void addFileApplicationID(String applicationID)
    {
        getFileApplicationIDs().add(applicationID);
    }

    public void addApplicationRecord(CostReportRecord costReportRecord) { getApplicationRecords().add(costReportRecord); }


    public void setApplicationHeaderVariables(String applicationID)
    {
        setApplicationHeaderCounter(getApplicationHeaderCounter() + 1);
        setApplicationID(applicationID);
        addFileApplicationID(applicationID);
        setApplicationValid(true);
        setApplicationRecords(new ArrayList<CostReportRecord>());
    }


    /*
     *---   GETTER(S)
     */

    // --- FILE ---
    public int getFileHeaderCounter() { return fileHeaderCounter; }

    public int getFileApplicationCount() { return fileApplicationCount; }

    public ArrayList<String> getFileApplicationIDs() { return fileApplicationIDs; }

    public BigDecimal getFileGrossRetireeCost() { return fileGrossRetireeCost; }

    public int getFileTrailerCounter() { return fileTrailerCounter; }

    // --- APPLICATION ---
    public boolean isApplicationValid() { return applicationValid; }

    public List<CostReportRecord> getApplicationRecords() { return applicationRecords; }

    public int getApplicationHeaderCounter() { return applicationHeaderCounter; }

    public String getApplicationID() { return applicationID; }

    public int getApplicationRecordCount() { return applicationRecordCount; }

    public BigDecimal getApplicationGrossRetireeCost() { return applicationGrossRetireeCost; }


    /*
     *---   SETTER(S)
     */

    // --- FILE ---
    public void setFileHeaderCounter(int fileHeaderCounter) { this.fileHeaderCounter = fileHeaderCounter; }

    public void setFileApplicationCount(int fileApplicationCount) { this.fileApplicationCount = fileApplicationCount; }

    public void setFileApplicationIDs(ArrayList<String> fileApplicationIDs) { this.fileApplicationIDs = fileApplicationIDs; }

    public void setFileGrossRetireeCost(BigDecimal fileGrossRetireeCost) { this.fileGrossRetireeCost = fileGrossRetireeCost; }

    public void setFileTrailerCounter(int fileTrailerCounter) { this.fileTrailerCounter = fileTrailerCounter; }

    // --- APPLICATION ---
    public void setApplicationValid(boolean applicationValid) { this.applicationValid = applicationValid; }

    public void setApplicationRecords(List<CostReportRecord> applicationRecords) { this.applicationRecords = applicationRecords; }

    public void setApplicationHeaderCounter(int applicationHeaderCounter) { this.applicationHeaderCounter = applicationHeaderCounter; }

    public void setApplicationID(String applicationID) { this.applicationID = applicationID; }

    public void setApplicationRecordCount(int applicationRecordCount) { this.applicationRecordCount = applicationRecordCount; }

    public void setApplicationGrossRetireeCost(BigDecimal applicationGrossRetireeCost) { this.applicationGrossRetireeCost = applicationGrossRetireeCost; }
}
