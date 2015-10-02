package jwl.prp.retiree.costreport.entity;


import java.util.Date;

/**
 * Created by jwleader on 9/28/15.
 */
public class FileStatus
{
    private int      fileId;
    private String   fileName;
    private String   fileStatus;
    private String   fileAddUser;
    private Date     fileAddDateTime;
    private String   jobName;
    private String   stepName;


    public FileStatus()
    {
    }


    public FileStatus(int fileId,
                      String fileName,
                      String fileStatus,
                      String fileAddUser,
                      Date   fileAddDateTime,
                      String jobName,
                      String stepName)
    {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileStatus = fileStatus;
        this.fileAddUser = fileAddUser;
        this.fileAddDateTime = fileAddDateTime;
        this.jobName = jobName;
        this.stepName = stepName;
    }

    // --- Getter(s) ---
    public int getFileId()
    {
        return fileId;
    }

    public String getFileStatus() { return fileStatus;}

    public String getFileName()
    {
        return fileName;
    }

    public String getFileAddUser()
    {
        return fileAddUser;
    }

    public Date getFileAddDateTime()
    {
        return fileAddDateTime;
    }

    public String getJobName() { return jobName; }

    public String getStepName() { return stepName; }

    // --- Setter(s) ---
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setFileStatus(String fileStatus) { this.fileStatus = fileStatus; }

    public void setFileAddUser(String fileAddUser) {this.fileAddUser = fileAddUser;}

    public void setFileAddDateTime(Date fileAddDateTime)
    {
        this.fileAddDateTime = fileAddDateTime;
    }

    public void setJobName(String jobName) { this.jobName = jobName; }

    public void setStepName(String stepName) { this.stepName = stepName; }
}
