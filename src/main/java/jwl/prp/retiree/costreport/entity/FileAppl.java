package jwl.prp.retiree.costreport.entity;


import java.sql.Timestamp;


public class FileAppl
{

    private int       fileId;
    private int       applSeqNum;
    private String    submApplId;
    private String    psId;
    private String    pstgPgm;
    private Timestamp pstgTs;
    private Timestamp stusTs;
    private String    stusPgm;
    private String    stusCtgryCd;
    private String    stusCd;
    private String    applId;


    public FileAppl()
    {
    }


    public FileAppl(int       fileId,
                    int       applSeqNum,
                    String    submApplId,
                    String    psId,
                    String    pstgPgm,
                    Timestamp pstgTs,
                    Timestamp stusTs,
                    String    stusPgm,
                    String    stusCtgryCd,
                    String    stusCd,
                    String    applId)
    {
        this.fileId      = fileId;
        this.applSeqNum  = applSeqNum;
        this.submApplId  = submApplId;
        this.psId        = psId;
        this.pstgPgm     = pstgPgm;
        this.pstgTs      = pstgTs;
        this.stusTs      = stusTs;
        this.stusPgm     = stusPgm;
        this.stusCtgryCd = stusCtgryCd;
        this.stusCd      = stusCd;
        this.applId      = applId;
    }



    //
    // --- Getter(s) ---
    //
    public int getFileId() {
        return fileId;
    }

    public int getApplSeqNum() { return applSeqNum; }

    public String getSubmApplId() { return submApplId; }

    public String getPsId() { return psId; }

    public String getPstgPgm() { return pstgPgm; }

    public Timestamp getPstgTs() { return pstgTs; }

    public Timestamp getStusTs() { return stusTs; }

    public String getStusPgm() { return stusPgm; }

    public String getStusCtgryCd() { return stusCtgryCd; }

    public String getStusCd() { return stusCd; }

    public String getApplId() { return applId; }


    //
    // --- Setter(s) ---
    //
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setApplSeqNum(int applSeqNum) { this.applSeqNum = applSeqNum; }

    public void setSubmApplId(String submApplId) { this.submApplId = submApplId; }

    public void setPsId(String psId) { this.psId = psId; }

    public void setPstgPgm(String pstgPgm) { this.pstgPgm = pstgPgm; }

    public void setPstgTs(Timestamp pstgTs) { this.pstgTs = pstgTs; }

    public void setStusTs(Timestamp stusTs) { this.stusTs = stusTs; }

    public void setStusPgm(String stusPgm) { this.stusPgm = stusPgm; }

    public void setStusCtgryCd(String stusCtgryCd) { this.stusCtgryCd = stusCtgryCd; }

    public void setStusCd(String stusCd) { this.stusCd = stusCd; }

    public void setApplId(String applId) { this.applId = applId; }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
              "fileId: " + fileId + ", " +
              "applSeqNum: " + applSeqNum + ", " +
              "submApplId: " + submApplId + ", " +
              "psId: " + psId + ", " +
              "pstgPgm: " + pstgPgm + ", " +
              "pstgTs: " + pstgTs + ", " +
              "stusTs: " + stusTs + ", " +
              "stusPgm: " + stusPgm + ", " +
              "stusCtgryCd: " + stusCtgryCd + ", " +
              "stusCd: " + stusCd + ", " +
              "applId: " + applId;
    }
}
