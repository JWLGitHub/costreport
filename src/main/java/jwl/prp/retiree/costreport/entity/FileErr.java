package jwl.prp.retiree.costreport.entity;



/**
 * Created by jwleader on 11/8/15.
 */
public class FileErr
{
    private int       fileId;
    private String    errCd;
    private String    errCtgryCd;
    private int       errSeqNum;
    private String    errInfo;


    public FileErr()
    {
    }


    public FileErr(int     fileId,
                   String  errCd,
                   String  errCtgryCd,
                   int     errSeqNum,
                   String  errInfo)
    {
        this.fileId    = fileId;
        this.errCd = errCd;
        this.errCtgryCd = errCtgryCd;
        this.errSeqNum = errSeqNum;
        this.errInfo = errInfo;
    }


    //
    // --- Getter(s) ---
    //
    public int getFileId() {
        return fileId;
    }

    public String getErrCd() {
        return errCd;
    }

    public String getErrCtgryCd() {
        return errCtgryCd;
    }

    public int getErrSeqNum() {
        return errSeqNum;
    }

    public String getErrInfo() {
        return errInfo;
    }


    //
    // --- Setter(s) ---
    //
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setErrCd(String errCd) {
        this.errCd = errCd;
    }

    public void setErrCtgryCd(String errCtgryCd) {
        this.errCtgryCd = errCtgryCd;
    }

    public void setErrSeqNum(int errSeqNum) {
        this.errSeqNum = errSeqNum;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "fileId: " + fileId + ", " +
                "errCd: " + errCd + ", " +
                "errCtgryCd: " + errCtgryCd + ", " +
                "errSeqNum: " + errSeqNum + ", " +
                "errInfo: " + errInfo;
    }
}
