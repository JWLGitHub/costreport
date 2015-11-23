package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 11/10/15.
 */
public enum StusCtgry
{

    APPLICATION_STATUS  ("AS", "application Status"),
    BENEFICIARY_STATUS  ("BN", "Beneficiary Status"),
    FILE_STATUS         ("FS", "file Status"),
    ORGANIZATION_STATUS ("OS", "Organization Status");


    private final String stusCtgryCd;
    private final String description;


    StusCtgry(String stusCtgryCd,
              String description)
    {
        this.stusCtgryCd = stusCtgryCd;
        this.description = description;
    }


    public String getStusCtgryCd()
    {
        return stusCtgryCd;
    }


    public String getDescription()
    {
        return description;
    }
}
