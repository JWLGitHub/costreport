package jwl.prp.retiree.costreport.entity;


/**
 * Created by jwleader on 9/30/15.
 */
public class FileHeader extends CostReportRecord
{
    // Position 5, Length 1
    private String submitterType;

    // Position 6 - 15, Length 10
    private String submitterID;

    // Positions 16 - 23, Length 8 - Format: MMDDCCYY
    private String submitterDate;

    // Positions 24 - 31, Length 8 - Format: HH:MM:SS
    private String submitterTime;

    // Positions 32 - 110, Length 79
    private String filler;


    public FileHeader()
    {
    }


    public FileHeader(String recordType,
                      String submitterType,
                      String submitterID,
                      String submitterDate,
                      String submitterTime,
                      String filler)
    {
        super(recordType);
        this.submitterType = submitterType;
        this.submitterID   = submitterID;
        this.submitterDate = submitterDate;
        this.submitterTime = submitterTime;
        this.filler = filler;
    }


    // --- Getter(s) ---
    public String getSubmitterType() {
        return submitterType;
    }

    public String getSubmitterID() {
        return submitterID;
    }

    public String getSubmitterDate() {
        return submitterDate;
    }

    public String getSubmitterTime() {
        return submitterTime;
    }

    public String getFiller() {
        return filler;
    }


    // --- Setter(s) ---
    public void setSubmitterType(String submitterType) {
        this.submitterType = submitterType;
    }

    public void setSubmitterID(String submitterID) {
        this.submitterID = submitterID;
    }

    public void setSubmitterDate(String submitterDate) {
        this.submitterDate = submitterDate;
    }

    public void setSubmitterTime(String submitterTime) {
        this.submitterTime = submitterTime;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "SubmitterType: " + submitterType + ", " +
                "SubmitterID: " + submitterID + ", " +
                "SubmitterDate: " + submitterDate + ", " +
                "submitterTime: " + submitterTime + ", " +
                "Filler: " + filler;
    }
}
