package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 11/10/15.
 */
public enum StusCtgry
{

    APPLICATION_STATUS("AS", "Application Status"),
    FILE_STATUS("FS", "File Status");


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
