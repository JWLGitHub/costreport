package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 11/20/15.
 */
public class PlanOptions
{
    private String  applicationID;
    private String  groupNumber;


    public PlanOptions()
    {
    }


    public PlanOptions(String applicationID,
                       String groupNumber)
    {
        this.applicationID = applicationID;
        this.groupNumber   = groupNumber;
    }


    //
    // --- Getter(s) ---
    //
    public String getApplicationID() { return applicationID; }

    public String getGroupNumber() { return groupNumber; }


    //
    // --- Setter(s) ---
    //
    public void setApplicationID(String applicationID) { this.applicationID = applicationID; }

    public void setGroupNumber(String groupNumber) { this.groupNumber = groupNumber; }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "applicationID: " + applicationID + ", " +
                "groupNumber: " + groupNumber;
    }
}

