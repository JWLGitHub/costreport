package jwl.prp.retiree.costreport.entity;


import java.sql.Timestamp;


public class RdsOrg
{
    private String    orgId;
    private String    orgTypCd;
    private String    orgName;
    private String    transMethod;
    private String    stusCtgryCd;
    private String    stusCd;
    private String    stusPgm;
    private Timestamp stusTs;
    private String    uptdPgm;
    private Timestamp updtTs;


    public RdsOrg()
    {
    }


    public RdsOrg(String    orgId,
                  String    orgTypCd,
                  String    orgName,
                  String    transMethod,
                  String    stusCtgryCd,
                  String    stusCd,
                  String    stusPgm,
                  Timestamp stusTs,
                  String    uptdPgm,
                  Timestamp updtTs)
    {
        this.orgId = orgId;
        this.orgTypCd = orgTypCd;
        this.orgName = orgName;
        this.transMethod = transMethod;
        this.stusCtgryCd = stusCtgryCd;
        this.stusCd = stusCd;
        this.stusPgm = stusPgm;
        this.stusTs = stusTs;
        this.uptdPgm = uptdPgm;
        this.updtTs = updtTs;
    }


    //
    // --- Getter(s) ---
    //
    public String getOrgId() { return orgId; }

    public String getOrgTypCd() { return orgTypCd; }

    public String getOrgName() { return orgName; }

    public String getTransMethod() { return transMethod; }

    public String getStusCtgryCd() { return stusCtgryCd; }

    public String getStusCd() { return stusCd; }

    public String getStusPgm() { return stusPgm; }

    public Timestamp getStusTs() { return stusTs; }

    public String getUptdPgm() { return uptdPgm; }

    public Timestamp getUpdtTs() { return updtTs; }


    //
    // --- Setter(s) ---
    //
    public void setOrgId(String orgId) { this.orgId = orgId; }

    public void setOrgTypCd(String orgTypCd) { this.orgTypCd = orgTypCd; }

    public void setOrgName(String orgName) { this.orgName = orgName; }

    public void setTransMethod(String transMethod) { this.transMethod = transMethod; }

    public void setStusCtgryCd(String stusCtgryCd) { this.stusCtgryCd = stusCtgryCd; }

    public void setStusCd(String stusCd) { this.stusCd = stusCd; }

    public void setStusPgm(String stusPgm) { this.stusPgm = stusPgm; }

    public void setStusTs(Timestamp stusTs) { this.stusTs = stusTs; }

    public void setUptdPgm(String uptdPgm) { this.uptdPgm = uptdPgm; }

    public void setUpdtTs(Timestamp updtTs) { this.updtTs = updtTs; }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "orgId: " + orgId + ", " +
                "orgTypCd: " + orgTypCd + ", " +
                "orgName: " + orgName + ", " +
                "transMethod: " + transMethod + ", " +
                "stusCtgryCd: " + stusCtgryCd + ", " +
                "stusCd: " + stusCd + ", " +
                "stusPgm: " + stusPgm + ", " +
                "stusTs: " + stusTs + ", " +
                "uptdPgm: " + uptdPgm + ", " +
                "updtTs: " + updtTs;
    }
}
