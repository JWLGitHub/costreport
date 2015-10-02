package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 10/2/15.
 */
public class ApplicationTrailer extends CostReportRecord {
    // Position 5 - 14, Length 10
    private String applicationID;

    // Positions 15 - 21, Length 7
    private String recordCount;

    // Positions 22 - 36, Length 15 - Format: S999999999999v99
    private String totalEstimatedPremium;

    // Positions 37 - 51, Length 15 - Format: S999999999999v99
    private String totalGrossRetireeCost;

    // Positions 52 - 66, Length 15 - Format: S999999999999v99
    private String totalThresholdReduction;

    // Positions 67 - 81, Length 15 - Format: S999999999999v99
    private String totalLimitReduction;

    // Positions 82 - 96, Length 15 - Format: S999999999999v99
    private String totalEstimatedCostAdjustment;

    // Positions 97 - 110, Length 14
    private String filler;


    public ApplicationTrailer() {
    }


    public ApplicationTrailer(String recordType,
                              String applicationID,
                              String recordCount,
                              String totalEstimatedPremium,
                              String totalGrossRetireeCost,
                              String totalThresholdReduction,
                              String totalLimitReduction,
                              String totalEstimatedCostAdjustment,
                              String filler) {
        super(recordType);
        this.applicationID = applicationID;
        this.recordCount = recordCount;
        this.totalEstimatedPremium = totalEstimatedPremium;
        this.totalGrossRetireeCost = totalGrossRetireeCost;
        this.totalThresholdReduction = totalThresholdReduction;
        this.totalLimitReduction = totalLimitReduction;
        this.totalEstimatedCostAdjustment = totalEstimatedCostAdjustment;
        this.filler = filler;
    }


    // --- Getter(s) ---
    public String getApplicationID() {
        return applicationID;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public String getTotalEstimatedPremium() {
        return totalEstimatedPremium;
    }

    public String getTotalGrossRetireeCost() {
        return totalGrossRetireeCost;
    }

    public String getTotalThresholdReduction() {
        return totalThresholdReduction;
    }

    public String getTotalLimitReduction() {
        return totalLimitReduction;
    }

    public String getTotalEstimatedCostAdjustment() {
        return totalEstimatedCostAdjustment;
    }

    public String getFiller() {
        return filler;
    }


    // --- Setter(s) ---
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public void setTotalEstimatedPremium(String totalEstimatedPremium) {
        this.totalEstimatedPremium = totalEstimatedPremium;
    }

    public void setTotalGrossRetireeCost(String totalGrossRetireeCost) {
        this.totalGrossRetireeCost = totalGrossRetireeCost;
    }

    public void setTotalThresholdReduction(String totalThresholdReduction) {
        this.totalThresholdReduction = totalThresholdReduction;
    }

    public void setTotalLimitReduction(String totalLimitReduction) {
        this.totalLimitReduction = totalLimitReduction;
    }

    public void setTotalEstimatedCostAdjustment(String totalEstimatedCostAdjustment) {
        this.totalEstimatedCostAdjustment = totalEstimatedCostAdjustment;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }


    @Override
    public String toString() {
        return super.toString() + ", " +
                "ApplicationID: " + applicationID + ", " +
                "RecordCount: " + recordCount + ", " +
                "TotalEstimatedPremium: " + totalEstimatedPremium + ", " +
                "TotalGrossRetireeCost: " + totalGrossRetireeCost + ", " +
                "TotalThresholdReduction: " + totalThresholdReduction + ", " +
                "TotalLimitReduction: " + totalLimitReduction + ", " +
                "TotalEstimatedCostAdjustment: " + totalEstimatedCostAdjustment + ", " +
                "Filler: " + filler;
    }
}




