package jwl.prp.retiree.costreport.entity;

import java.util.Date;

/**
 * Created by jwleader on 10/31/15.
 */
public class CostReportFile {
    private int id;
    private String name;
    private String status;
    private String addJob;
    private String addStep;
    private String addProgram;
    private String addMethod;
    private Date addDateTime;
    private String updateJob;
    private String updateStep;
    private String updateProgram;
    private String updateMethod;
    private Date updateDateTime;


    public CostReportFile() {
    }


    public CostReportFile(int id,
                          String name,
                          String status,
                          String addJob,
                          String addStep,
                          String addProgram,
                          String addMethod,
                          Date addDateTime,
                          String updateJob,
                          String updateStep,
                          String updateProgram,
                          String updateMethod,
                          Date updateDateTime)
    {
        this.id = id;
        this.name = name;
        this.status = status;
        this.addJob = addJob;
        this.addStep = addStep;
        this.addProgram = addProgram;
        this.addMethod = addMethod;
        this.addDateTime = addDateTime;
        this.updateJob = updateJob;
        this.updateStep = updateStep;
        this.updateProgram = updateProgram;
        this.updateMethod = updateMethod;
        this.updateDateTime = updateDateTime;
    }


    // --- Getter(s) ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
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

    public String getUpdateJob() {
        return updateJob;
    }

    public String getUpdateStep() {
        return updateStep;
    }

    public String getUpdateProgram() {
        return updateProgram;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }


    // --- Setter(s) ---
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setUpdateJob(String updateJob) {
        this.updateJob = updateJob;
    }

    public void setUpdateStep(String updateStep) {
        this.updateStep = updateStep;
    }

    public void setUpdateProgram(String updateProgram) {
        this.updateProgram = updateProgram;
    }

    public void setUpdateMethod(String updateMethod) {
        this.updateMethod = updateMethod;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "ID: " + id + ", " +
                "Name: " + name + ", " +
                "Status: " + status + ", " +
                "AddJob: " + addJob + ", " +
                "AddStep: " + addStep + ", " +
                "AddProgram: " + addProgram + ", " +
                "AddMethod: " + addMethod;
    }


    public static enum STATUS_TYPE
    {
        EXISTS,
        MISSING,
        PROCESSING,
        ACCEPTED,
        ACCEPTED_WITH_ERRORS,
        REJECTED_DUE_TO_STRUCTURAL_ERRORS,
        REJECTED_DUE_TO_DATE_ERRORS,
        REJECTED_DUE_TO_ALL_APPLICATIONS_BEING_REJECTED,
        FILE_IS_EMPTY
    }
}