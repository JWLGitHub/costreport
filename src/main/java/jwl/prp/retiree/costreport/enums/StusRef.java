package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 11/10/15.
 */
public enum StusRef
{
    ACCEPTED                          (StusCtgry.FILE_STATUS.getStusCtgryCd(), "AC", "ACCEPTED"),
    EMPTY                             (StusCtgry.FILE_STATUS.getStusCtgryCd(), "EM", "EMPTY"),
    EXISTS                            (StusCtgry.FILE_STATUS.getStusCtgryCd(), "EX", "EXISTS"),
    MISSING                           (StusCtgry.FILE_STATUS.getStusCtgryCd(), "MI", "MISSING"),
    PROCESSING                        (StusCtgry.FILE_STATUS.getStusCtgryCd(), "PR", "PROCESSING"),
    REJECTED_DUE_TO_STRUCTURAL_ERRORS (StusCtgry.FILE_STATUS.getStusCtgryCd(), "RS", "REJECTED DUE TO STRUCTURAL ERRORS");


    private final String    stusCtgryCd;
    private final String    stusCd;
    private final String    description;



    StusRef(String stusCtgryCd,
            String stusCd,
            String description)
    {
        this.stusCtgryCd = stusCtgryCd;
        this.stusCd      = stusCd;
        this.description = description;
    }


    public String getStusCtgryCd()
    {
        return stusCtgryCd;
    }


    public String getStusCd()
    {
        return stusCd;
    }


    public String getDescription()
    {
        return description;
    }
}
