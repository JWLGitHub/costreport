package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 10/1/15.
 */
public class ApplicationHeader extends CostReportRecord
{
    // Position 5 - 14, Length 10
    private String applicationID;

    // Positions 15 - 110, Length 96
    private String filler;


    public ApplicationHeader()
    {
    }


    public ApplicationHeader(String recordType,
                             String applicationID,
                             String filler)
    {
        super(recordType);
        this.applicationID = applicationID;
        this.filler = filler;
    }


    // --- Getter(s) ---
    public String getApplicationID() {
        return applicationID;
    }

    public String getFiller() {
        return filler;
    }


    // --- Setter(s) ---
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "ApplicationID: " + applicationID + ", " +
                "Filler: " + filler;
    }


    @Override
    public String toFixedString()
    {
        return super.toFixedString() +
               applicationID +
               filler;
    }
}