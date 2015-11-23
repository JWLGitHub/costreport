package jwl.prp.retiree.costreport.validation;


import java.math.BigDecimal;


public class FileContext
{

    /*
     *---   FILE HEADER VARIABLES
     */
    private int fileHeaderCounter;



    /*
     *---   APPLICATION VARIABLES
     */
    private String     applicationApplicationID;
    private int        applicationRecordCount;
    private BigDecimal applicationGrossRetireeCost = new BigDecimal("0");


    /*
     *---   FILE TRAILER VARIABLES
     */
    private int        fileTrailerApplicationCount;
    private BigDecimal fileTrailerGrossRetireeCost = new BigDecimal("0");


    public void initializeApplicationVariables(String applicationApplicationID)
    {
        setApplicationApplicationID(applicationApplicationID);
        setApplicationRecordCount(0);
        setApplicationGrossRetireeCost(new BigDecimal("0"));
    }


    // --- Getter(s) ---
    public int getFileHeaderCounter() { return fileHeaderCounter; }

    public String getApplicationApplicationID() { return applicationApplicationID; }

    public int getApplicationRecordCount() { return applicationRecordCount; }

    public BigDecimal getApplicationGrossRetireeCost() { return applicationGrossRetireeCost; }

    public int getFileTrailerApplicationCount() { return fileTrailerApplicationCount; }

    public BigDecimal getFileTrailerGrossRetireeCost() { return fileTrailerGrossRetireeCost; }




    // --- Setter(s) ---
    public void setFileHeaderCounter(int fileHeaderCounter) { this.fileHeaderCounter = fileHeaderCounter; }

    public void setApplicationApplicationID(String applicationApplicationID) { this.applicationApplicationID = applicationApplicationID; }

    public void setApplicationRecordCount(int applicationRecordCount) { this.applicationRecordCount = applicationRecordCount; }

    public void setApplicationGrossRetireeCost(BigDecimal applicationGrossRetireeCost) { this.applicationGrossRetireeCost = applicationGrossRetireeCost; }

    public void setFileTrailerApplicationCount(int fileTrailerApplicationCount) { this.fileTrailerApplicationCount = fileTrailerApplicationCount; }

    public void setFileTrailerGrossRetireeCost(BigDecimal fileTrailerGrossRetireeCost) { this.fileTrailerGrossRetireeCost = fileTrailerGrossRetireeCost; }
}
