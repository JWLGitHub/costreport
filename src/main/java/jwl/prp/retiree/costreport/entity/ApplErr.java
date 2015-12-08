package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 12/8/15.
 */
public class ApplErr
{
    private int       fileId;
    private int       applSeqNum;
    private String    errCtgryCd;
    private String    errCd;
    private int       errSeqNum;
    private int       costYearNum;
    private int       costMnthNum;
    private String    rxGrpNum;
    private String    errInfo;


    public ApplErr()
    {
    }


    public ApplErr(int     fileId,
                   int     applSeqNum,
                   String  errCtgryCd,
                   String  errCd,
                   int     errSeqNum,
                   int     costYearNum,
                   int     costMnthNum,
                   String  rxGrpNum,
                   String  errInfo)
    {
        this.fileId    = fileId;
        this.applSeqNum  = applSeqNum;
        this.errCtgryCd = errCtgryCd;
        this.errCd = errCd;
        this.errSeqNum = errSeqNum;
        this.costYearNum = costYearNum;
        this.costMnthNum = costMnthNum;
        this.rxGrpNum = rxGrpNum;
        this.errInfo = errInfo;
    }


    //
    // --- Getter(s) ---
    //
    public int getFileId() {
        return fileId;
    }

    public int getApplSeqNum() { return applSeqNum; }

    public String getErrCtgryCd() {
        return errCtgryCd;
    }

    public String getErrCd() {
        return errCd;
    }

    public int getErrSeqNum() {
        return errSeqNum;
    }

    public int getCostYearNum() { return costYearNum; }

    public int getCostMnthNum() { return costMnthNum; }

    public String getRxGrpNum() { return rxGrpNum; }

    public String getErrInfo() {
        return errInfo;
    }


    //
    // --- Setter(s) ---
    //
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setApplSeqNum(int applSeqNum) { this.applSeqNum = applSeqNum; }

    public void setErrCtgryCd(String errCtgryCd) {
        this.errCtgryCd = errCtgryCd;
    }

    public void setErrCd(String errCd) {
        this.errCd = errCd;
    }

    public void setErrSeqNum(int errSeqNum) {
        this.errSeqNum = errSeqNum;
    }

    public void setCostYearNum(int costYearNum) { this.costYearNum = costYearNum; }

    public void setCostMnthNum(int costMnthNum) { this.costMnthNum = costMnthNum; }

    public void setRxGrpNum(String rxGrpNum) { this.rxGrpNum = rxGrpNum; }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "fileId: " + fileId + ", " +
                "applSeqNum: " + applSeqNum + ", " +
                "errCtgryCd: " + errCtgryCd + ", " +
                "errCd: " + errCd + ", " +
                "errSeqNum: " + errSeqNum + ", " +
                "costYearNum: " +  costYearNum + ", " +
                "costMnthNum: " +  costMnthNum + ", " +
                "rxGrpNum: " +  rxGrpNum + ", " +
                "errInfo: " + errInfo;
    }
}
