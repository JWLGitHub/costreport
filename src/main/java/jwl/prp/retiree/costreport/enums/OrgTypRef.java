package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 12/23/15.
 */
public enum OrgTypRef
{
    PLAN_SPONSOR        ("P", "Plan Sponsor"),
    RDS                 ("R", "RDS"),
    VENDOR              ("V", "Vendor"),
    ORGANIZATION_STATUS ("X", "Reserved");


    private final String orgTypCd;
    private final String descTxt;


    OrgTypRef(String orgTypCd,
              String descTxt)
    {
        this.orgTypCd = orgTypCd;
        this.descTxt  = descTxt;
    }


    public String getOrgTypCd()
    {
        return orgTypCd;
    }


    public String getDescTxt()
    {
        return descTxt;
    }
}
