package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 11/20/15.
 */
public class Application
{
    private String  applicationID;


    public Application()
    {
    }


    public Application(String applicationID)
    {
        this.applicationID = applicationID;
    }


    //
    // --- Getter(s) ---
    //
    public String getApplicationID() { return applicationID; }


    //
    // --- Setter(s) ---
    //
    public void setApplicationID(String applicationID) { this.applicationID = applicationID; }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "applicationID: " + applicationID;
    }
}
