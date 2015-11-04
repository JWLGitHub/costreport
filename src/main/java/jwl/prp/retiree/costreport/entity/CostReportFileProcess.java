package jwl.prp.retiree.costreport.entity;

import java.util.Date;

/**
 * Created by jwleader on 11/1/15.
 */
public class CostReportFileProcess
{
    private int processId;
    private int fileId;
    private String process;
    private String processText;
    private String addJob;
    private String addStep;
    private String addProgram;
    private String addMethod;
    private Date addDateTime;


    public CostReportFileProcess()
    {
    }


    public CostReportFileProcess(int processId,
                                 int fileId,
                                 String process,
                                 String processText,
                                 String addJob,
                                 String addStep,
                                 String addProgram,
                                 String addMethod,
                                 Date addDateTime)
    {
        this.processId = processId;
        this.fileId = fileId;
        this.process = process;
        this.processText = processText;
        this.addJob = addJob;
        this.addStep = addStep;
        this.addProgram = addProgram;
        this.addMethod = addMethod;
        this.addDateTime = addDateTime;
    }


    //
    // --- Getter(s) ---
    //
    public int getProcessId() {
        return processId;
    }

    public int getFileId() {
        return fileId;
    }

    public String getProcess() {
        return process;
    }

    public String getProcessText() {
        return processText;
    }

    public String getAddJob() {
        return addJob;
    }

    public String getAddStep() {
        return addStep;
    }

    public String getAddProgram() {
        return addProgram;
    }

    public String getAddMethod() {
        return addMethod;
    }

    public Date getAddDateTime() {
        return addDateTime;
    }


    //
    // --- Setter(s) ---
    //
    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setProcessText(String processText) {
        this.processText = processText;
    }

    public void setAddJob(String addJob) {
        this.addJob = addJob;
    }

    public void setAddStep(String addStep) {
        this.addStep = addStep;
    }

    public void setAddProgram(String addProgram) {
        this.addProgram = addProgram;
    }

    public void setAddMethod(String addMethod) {
        this.addMethod = addMethod;
    }

    public void setAddDateTime(Date addDateTime) {
        this.addDateTime = addDateTime;
    }
}
