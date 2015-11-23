package jwl.prp.retiree.costreport.entity;

import java.util.Date;

/**
 * Created by jwleader on 11/23/15.
 */
public class CostReportImport
{
    private int     fileId;
    private Date    fileDtTm;
    private String  recordContents;


    public CostReportImport()
    {
    }


    public CostReportImport(int     fileId,
                            Date    fileDtTm,
                            String  recordContents)
    {
        this.fileId         = fileId;
        this.fileDtTm       = fileDtTm;
        this.recordContents = recordContents;
    }


    //
    // --- Getter(s) ---
    //
    public int getFileId() {
        return fileId;
    }

    public Date getFileDtTm() { return fileDtTm; }

    public String getRecordContents() { return recordContents; }


    //
    // --- Setter(s) ---
    //
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setFileDtTm(Date fileDtTm) { this.fileDtTm = fileDtTm; }

    public void setRecordContents(String recordContents) { this.recordContents = recordContents; }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "fileId: " + fileId + ", " +
                "fileDtTm: " + fileDtTm + ", " +
                "recordContents: " + recordContents;
    }
}
