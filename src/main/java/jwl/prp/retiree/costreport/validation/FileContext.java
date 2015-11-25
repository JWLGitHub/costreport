package jwl.prp.retiree.costreport.validation;


import java.math.BigDecimal;


public class FileContext
{

    /*
     *---   FILE VARIABLES
     */
    private int        fileHeaderCounter;

    private int        fileApplicationCount;
    private BigDecimal fileGrossRetireeCost = new BigDecimal("0");


    /*
     *---   APPLICATION VARIABLES
     */
    private String     applicationID;
    private int        applicationRecordCount;
    private BigDecimal applicationGrossRetireeCost = new BigDecimal("0");


    public void initializeFileVariables()
    {
        setFileHeaderCounter(getFileHeaderCounter() + 1);
    }


    public void initializeApplicationVariables(String applicationID)
    {
        setApplicationID(applicationID);
        setApplicationRecordCount(0);
        setApplicationGrossRetireeCost(new BigDecimal("0"));
    }


    // --- Getter(s) ---
    public int getFileHeaderCounter() { return fileHeaderCounter; }

    public String getApplicationID() { return applicationID; }

    public int getApplicationRecordCount() { return applicationRecordCount; }

    public BigDecimal getApplicationGrossRetireeCost() { return applicationGrossRetireeCost; }

    public int getFileApplicationCount() { return fileApplicationCount; }

    public BigDecimal getFileGrossRetireeCost() { return fileGrossRetireeCost; }


    // --- Setter(s) ---
    public void setFileHeaderCounter(int fileHeaderCounter) { this.fileHeaderCounter = fileHeaderCounter; }

    public void setApplicationID(String applicationID) { this.applicationID = applicationID; }

    public void setApplicationRecordCount(int applicationRecordCount) { this.applicationRecordCount = applicationRecordCount; }

    public void setApplicationGrossRetireeCost(BigDecimal applicationGrossRetireeCost) { this.applicationGrossRetireeCost = applicationGrossRetireeCost; }

    public void setFileApplicationCount(int fileApplicationCount) { this.fileApplicationCount = fileApplicationCount; }

    public void setFileGrossRetireeCost(BigDecimal fileGrossRetireeCost) { this.fileGrossRetireeCost = fileGrossRetireeCost; }
}
