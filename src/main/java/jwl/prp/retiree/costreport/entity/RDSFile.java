package jwl.prp.retiree.costreport.entity;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jwleader on 11/6/15.
 */
public class RDSFile
{
    private int       fileId;
    private String    fileDirCd;
    private String    fileTypeCd;
    private Timestamp fileDtTm;
    private String    fileName;
    private String    fileDescTxt;
    private String    submOrgId;
    private String    orgTypCd;
    private String    orgId;
    private String    stusCtgryCd;
    private String    stusCd;
    private Timestamp stusTs;
    private String    stusPgm;
    private String    uptdPgm;
    private Timestamp updtTs;
    private Date      processDt;
    private Date      receiptDt;



    public RDSFile()
    {
    }


    public RDSFile(int       fileId,
                   String    fileDirCd,
                   String    fileTypeCd,
                   Timestamp fileDtTm,
                   String    fileName,
                   String    fileDescTxt,
                   String    submOrgId,
                   String    orgTypCd,
                   String    orgId,
                   String    stusCtgryCd,
                   String    stusCd,
                   Timestamp stusTs,
                   String    stusPgm,
                   String    uptdPgm,
                   Timestamp updtTs,
                   Date      processDt,
                   Date      receiptDt)
    {
        this.fileId    = fileId;
        this.fileDirCd = fileDirCd;
        this.fileTypeCd = fileTypeCd;
        this.fileDtTm = fileDtTm;
        this.fileName = fileName;
        this.fileDescTxt = fileDescTxt;
        this.submOrgId = submOrgId;
        this.orgTypCd = orgTypCd;
        this.orgId = orgId;
        this.stusCtgryCd = stusCtgryCd;
        this.stusCd = stusCd;
        this.stusTs = stusTs;
        this.stusPgm = stusPgm;
        this.uptdPgm = uptdPgm;
        this.updtTs = updtTs;
        this.processDt = processDt;
        this.receiptDt = receiptDt;
    }


    //
    // --- Getter(s) ---
    //
    public int getFileId() {
        return fileId;
    }

    public String getFileDirCd() {
        return fileDirCd;
    }

    public String getFileTypeCd() {
        return fileTypeCd;
    }

    public Timestamp getFileDtTm() {
        return fileDtTm;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDescTxt() {
        return fileDescTxt;
    }

    public String getSubmOrgId() {
        return submOrgId;
    }

    public String getOrgTypCd() {
        return orgTypCd;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getStusCtgryCd() {
        return stusCtgryCd;
    }

    public String getStusCd() {
        return stusCd;
    }

    public Timestamp getStusTs() {
        return stusTs;
    }

    public String getStusPgm() {
        return stusPgm;
    }

    public String getUptdPgm() {
        return uptdPgm;
    }

    public Timestamp getUpdtTs() {
        return updtTs;
    }

    public Date getProcessDt() {
        return processDt;
    }

    public Date getReceiptDt() {
        return receiptDt;
    }


    //
    // --- Setter(s) ---
    //
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setFileDirCd(String fileDirCd) {
        this.fileDirCd = fileDirCd;
    }

    public void setFileTypeCd(String fileTypeCd) {
        this.fileTypeCd = fileTypeCd;
    }

    public void setFileDtTm(Timestamp fileDtTm) { this.fileDtTm = fileDtTm; }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileDescTxt(String fileDescTxt) {
        this.fileDescTxt = fileDescTxt;
    }

    public void setSubmOrgId(String submOrgId) {
        this.submOrgId = submOrgId;
    }

    public void setOrgTypCd(String orgTypCd) {
        this.orgTypCd = orgTypCd;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setStusCtgryCd(String stusCtgryCd) {
        this.stusCtgryCd = stusCtgryCd;
    }

    public void setStusCd(String stusCd) {
        this.stusCd = stusCd;
    }

    public void setStusTs(Timestamp stusTs) {
        this.stusTs = stusTs;
    }

    public void setStusPgm(String stusPgm) {
        this.stusPgm = stusPgm;
    }

    public void setUptdPgm(String uptdPgm) {
        this.uptdPgm = uptdPgm;
    }

    public void setUpdtTs(Timestamp updtTs) {
        this.updtTs = updtTs;
    }

    public void setProcessDt(Date processDt) {
        this.processDt = processDt;
    }

    public void setReceiptDt(Date receiptDt) {
        this.receiptDt = receiptDt;
    }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "fileId: " + fileId + ", " +
                "fileDirCd: " + fileDirCd + ", " +
                "fileTypeCd: " + fileTypeCd + ", " +
                "fileDtTm: " + fileDtTm + ", " +
                "fileName: " + fileName + ", " +
                "fileDescTxt: " + fileDescTxt + ", " +
                "submOrgId: " + submOrgId + ", " +
                "orgTypCd: " + orgTypCd + ", " +
                "orgId: " + orgId + ", " +
                "stusCtgryCd: " + stusCtgryCd + ", " +
                "stusCd: " + stusCd + ", " +
                "stusTs: " + stusTs + ", " +
                "stusPgm: " + stusPgm + ", " +
                "uptdPgm: " + uptdPgm + ", " +
                "updtTs: " + updtTs + ", " +
                "processDt: " + processDt + ", " +
                "receiptDt: " + receiptDt;
    }
}
