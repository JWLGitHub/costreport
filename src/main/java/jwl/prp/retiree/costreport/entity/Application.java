package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 11/20/15.
 */
public class Application
{
    private String  applicationID;
    private String  status;


    public Application()
    {
    }


    public Application(String applicationID,
                       String status)
    {
        this.applicationID = applicationID;
        this.status        = status;
    }


    //
    // --- Getter(s) ---
    //
    public String getApplicationID() { return applicationID; }

    public String getStatus() { return status; }


    //
    // --- Setter(s) ---
    //
    public void setApplicationID(String applicationID) { this.applicationID = applicationID; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "applicationID: " + applicationID + ", " +
                "status: " + status;
    }
}
