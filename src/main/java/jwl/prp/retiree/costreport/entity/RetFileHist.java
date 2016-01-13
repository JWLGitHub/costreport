package jwl.prp.retiree.costreport.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jwleader on 1/12/16.
 */
public class RetFileHist
{
    private int       fileId;
    private String    psId;
    private String    applId;
    private int       receiptDt;
    private int       responseDt;
    private String    status;
    private String    prevStatus;
    private String    errorReason;
    private int       addCnt;
    private int       updateCnt;
    private int       deleteCnt;
    private int       errorCnt;
    private int       totalCnt;
    private String    transMethod;
    private String    inputFile;
    private String    responseFile;
    private String    mailboxFile;
    private String    cobcPlanNum;
    private String    updtPgm;
    private Timestamp timeStamp;
    private String    originId;


    public RetFileHist()
    {
    }


    public RetFileHist(int       fileId,
                       String    psId,
                       String    applId,
                       int       receiptDt,
                       int       responseDt,
                       String    status,
                       String    prevStatus,
                       String    errorReason,
                       int       addCnt,
                       int       updateCnt,
                       int       deleteCnt,
                       int       errorCnt,
                       int       totalCnt,
                       String    transMethod,
                       String    inputFile,
                       String    responseFile,
                       String    mailboxFile,
                       String    cobcPlanNum,
                       String    updtPgm,
                       Timestamp timeStamp,
                       String    originId)
    {
        this.fileId = fileId;
        this.psId = psId;
        this.applId = applId;
        this.receiptDt = receiptDt;
        this.responseDt = responseDt;
        this.status = status;
        this.prevStatus = prevStatus;
        this.errorReason = errorReason;
        this.addCnt = addCnt;
        this.updateCnt = updateCnt;
        this.deleteCnt = deleteCnt;
        this.errorCnt = errorCnt;
        this.totalCnt = totalCnt;
        this.transMethod = transMethod;
        this.inputFile = inputFile;
        this.responseFile = responseFile;
        this.mailboxFile = mailboxFile;
        this.cobcPlanNum = cobcPlanNum;
        this.updtPgm = updtPgm;
        this.timeStamp = timeStamp;
        this.originId = originId;
    }


    //
    // --- Getter(s) ---
    //
    public int getFileId() { return fileId; }

    public String getPsId() { return psId; }

    public String getApplId() { return applId; }

    public int getReceiptDt() { return receiptDt; }

    public int getResponseDt() { return responseDt; }

    public String getStatus() { return status; }

    public String getPrevStatus() { return prevStatus; }

    public String getErrorReason() { return errorReason; }

    public int getAddCnt() { return addCnt; }

    public int getUpdateCnt() { return updateCnt; }

    public int getDeleteCnt() { return deleteCnt; }

    public int getErrorCnt() { return errorCnt; }

    public int getTotalCnt() { return totalCnt; }

    public String getTransMethod() { return transMethod; }

    public String getInputFile() { return inputFile; }

    public String getResponseFile() { return responseFile; }

    public String getMailboxFile() { return mailboxFile; }

    public String getCobcPlanNum() { return cobcPlanNum; }

    public String getUpdtPgm() { return updtPgm; }

    public Timestamp getTimeStamp() { return timeStamp; }

    public String getOriginId() { return originId; }


    //
    // --- Setter(s) ---
    //
    public void setFileId(int fileId) { this.fileId = fileId; }

    public void setPsId(String psId) { this.psId = psId; }

    public void setApplId(String applId) { this.applId = applId; }

    public void setReceiptDt(int receiptDt) { this.receiptDt = receiptDt; }

    public void setResponseDt(int responseDt) { this.responseDt = responseDt; }

    public void setStatus(String status) { this.status = status; }

    public void setPrevStatus(String prevStatus) { this.prevStatus = prevStatus; }

    public void setErrorReason(String errorReason) { this.errorReason = errorReason; }

    public void setAddCnt(int addCnt) { this.addCnt = addCnt; }

    public void setUpdateCnt(int updateCnt) { this.updateCnt = updateCnt; }

    public void setDeleteCnt(int deleteCnt) { this.deleteCnt = deleteCnt; }

    public void setErrorCnt(int errorCnt) { this.errorCnt = errorCnt; }

    public void setTotalCnt(int totalCnt) { this.totalCnt = totalCnt; }

    public void setTransMethod(String transMethod) { this.transMethod = transMethod; }

    public void setInputFile(String inputFile) { this.inputFile = inputFile; }

    public void setResponseFile(String responseFile) { this.responseFile = responseFile; }

    public void setMailboxFile(String mailboxFile) { this.mailboxFile = mailboxFile; }

    public void setCobcPlanNum(String cobcPlanNum) { this.cobcPlanNum = cobcPlanNum; }

    public void setUpdtPgm(String updtPgm) { this.updtPgm = updtPgm; }

    public void setTimeStamp(Timestamp timeStamp) { this.timeStamp = timeStamp; }

    public void setOriginId(String originId) { this.originId = originId; }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "RetFileHist{" +
                " fileId='" + fileId + '\'' +
                ", psId='" + psId + '\'' +
                ", applId='" + applId + '\'' +
                ", receiptDt=" + receiptDt +
                ", responseDt=" + responseDt +
                ", status='" + status + '\'' +
                ", prevStatus='" + prevStatus + '\'' +
                ", errorReason='" + errorReason + '\'' +
                ", addCnt=" + addCnt +
                ", updateCnt=" + updateCnt +
                ", deleteCnt=" + deleteCnt +
                ", errorCnt=" + errorCnt +
                ", totalCnt=" + totalCnt +
                ", transMethod='" + transMethod + '\'' +
                ", inputFile='" + inputFile + '\'' +
                ", responseFile='" + responseFile + '\'' +
                ", mailboxFile='" + mailboxFile + '\'' +
                ", cobcPlanNum='" + cobcPlanNum + '\'' +
                ", updtPgm='" + updtPgm + '\'' +
                ", timeStamp=" + timeStamp +
                ", originId='" + originId + '\'' +
                " }";
    }
}
