package jwl.prp.retiree.costreport.validation;


import java.math.BigDecimal;
import java.util.ArrayList;


public class FileContext
{

    /*
     *---   FILE VARIABLES
     */
    private int               fileHeaderCounter;
    private int               fileApplicationCount;
    private ArrayList<String> fileApplicationIDs;
    private BigDecimal        fileGrossRetireeCost;


    /*
     *---   APPLICATION VARIABLES
     */
    private String     applicationID;
    private int        applicationRecordCount;
    private BigDecimal applicationGrossRetireeCost;


    public void initializeFileVariables()
    {
        setFileHeaderCounter(getFileHeaderCounter() + 1);
        setFileApplicationCount(0);
        setFileApplicationIDs(new ArrayList<String>());
        setFileGrossRetireeCost(new BigDecimal("0"));
    }


    public void addFileApplicationID(String applicationID)
    {
        getFileApplicationIDs().add(applicationID);
    }


    public void initializeApplicationVariables(String applicationID)
    {
        setApplicationID(applicationID);
        addFileApplicationID(applicationID);
        setApplicationRecordCount(0);
        setApplicationGrossRetireeCost(new BigDecimal("0"));
    }


    /*
     *---   GETTER(S)
     */

    // --- FILE ---
    public int getFileHeaderCounter() { return fileHeaderCounter; }

    public int getFileApplicationCount() { return fileApplicationCount; }

    public ArrayList<String> getFileApplicationIDs() { return fileApplicationIDs; }

    public BigDecimal getFileGrossRetireeCost() { return fileGrossRetireeCost; }

    // --- APPLICATION ---
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

    // --- APPLICATION ---
    public void setApplicationID(String applicationID) { this.applicationID = applicationID; }

    public void setApplicationRecordCount(int applicationRecordCount) { this.applicationRecordCount = applicationRecordCount; }

    public void setApplicationGrossRetireeCost(BigDecimal applicationGrossRetireeCost) { this.applicationGrossRetireeCost = applicationGrossRetireeCost; }
}
