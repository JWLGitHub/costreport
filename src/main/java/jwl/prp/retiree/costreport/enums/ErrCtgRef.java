package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 11/10/15.
 */
public enum ErrCtgRef
{

    APPLICATION_ERROR ("AE", "Application Error"),
    FILE_ERROR        ("FE", "File Error");


    private final String errCtgryCd;
    private final String descTxt;


    ErrCtgRef(String errCtgryCd,
              String descTxt)
    {
        this.errCtgryCd = errCtgryCd;
        this.descTxt = descTxt;
    }


    public String getErrCtgryCd()
    {
        return errCtgryCd;
    }


    public String getDescTxt()
    {
        return descTxt;
    }
}
