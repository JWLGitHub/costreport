package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 10/2/15.
 */
public class FileTrailer extends CostReportRecord
{
    // Position 5 - 14, Length 10
    private String submitterID;

    // Positions 15 - 19, Length 5
    private String applicationCount;

    // Positions 20 - 35, Length 16 - Format: S9999999999999v99
    private String grandEstimatedPremium;

    // Positions 36 - 51, Length 16 - Format: S9999999999999v99
    private String grandGrossRetireeCost;

    // Positions 52 - 67, Length 16 - Format: S9999999999999v99
    private String grandThresholdReduction;

    // Positions 68 - 83, Length 16 - Format: S9999999999999v99
    private String grandLimitReduction;

    // Positions 84 - 99, Length 16 - Format: S9999999999999v99
    private String grandEstimatedCostAdjustment;

    // Positions 100 - 110, Length 11
    private String filler;


    public FileTrailer()
    {
    }


    public FileTrailer(String recordType,
                       String submitterID,
                       String applicationCount,
                       String grandEstimatedPremium,
                       String grandGrossRetireeCost,
                       String grandThresholdReduction,
                       String grandLimitReduction,
                       String grandEstimatedCostAdjustment,
                       String filler)
    {
        super(recordType);
        this.submitterID = submitterID;
        this.applicationCount = applicationCount;
        this.grandEstimatedPremium = grandEstimatedPremium;
        this.grandGrossRetireeCost = grandGrossRetireeCost;
        this.grandThresholdReduction = grandThresholdReduction;
        this.grandLimitReduction = grandLimitReduction;
        this.grandEstimatedCostAdjustment = grandEstimatedCostAdjustment;
        this.filler = filler;
    }


    // --- Getter(s) ---
    public String getSubmitterID() {
        return submitterID;
    }

    public String getApplicationCount() {
        return applicationCount;
    }

    public String getGrandEstimatedPremium() {
        return grandEstimatedPremium;
    }

    public String getGrandGrossRetireeCost() {
        return grandGrossRetireeCost;
    }

    public String getGrandThresholdReduction() {
        return grandThresholdReduction;
    }

    public String getGrandLimitReduction() {
        return grandLimitReduction;
    }

    public String getGrandEstimatedCostAdjustment() {
        return grandEstimatedCostAdjustment;
    }

    public String getFiller() {
        return filler;
    }


    // --- Setter(s) ---
    public void setSubmitterID(String submitterID) {
        this.submitterID = submitterID;
    }

    public void setApplicationCount(String applicationCount) {
        this.applicationCount = applicationCount;
    }

    public void setGrandEstimatedPremium(String grandEstimatedPremium) {
        this.grandEstimatedPremium = grandEstimatedPremium;
    }

    public void setGrandGrossRetireeCost(String grandGrossRetireeCost) {
        this.grandGrossRetireeCost = grandGrossRetireeCost;
    }

    public void setGrandThresholdReduction(String grandThresholdReduction) {
        this.grandThresholdReduction = grandThresholdReduction;
    }

    public void setGrandLimitReduction(String grandLimitReduction) {
        this.grandLimitReduction = grandLimitReduction;
    }

    public void setGrandEstimatedCostAdjustment(String grandEstimatedCostAdjustment) {
        this.grandEstimatedCostAdjustment = grandEstimatedCostAdjustment;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }


    @Override
    public String toString() {
        return super.toString() + ", " +
                "SubmitterID: " + submitterID + ", " +
                "ApplicationCount: " + applicationCount + ", " +
                "GrandEstimatedPremium: " + grandEstimatedPremium + ", " +
                "GrandGrossRetireeCost: " + grandGrossRetireeCost + ", " +
                "GrandThresholdReduction: " + grandThresholdReduction + ", " +
                "GrandLimitReduction: " + grandLimitReduction + ", " +
                "GrandEstimatedCostAdjustment: " + grandEstimatedCostAdjustment + ", " +
                "Filler: " + filler;
    }


    @Override
    public String toFixedString()
    {
        return super.toFixedString() +
               submitterID +
               applicationCount +
               grandEstimatedPremium +
               grandGrossRetireeCost +
               grandThresholdReduction +
               grandLimitReduction +
               grandEstimatedCostAdjustment +
               filler;
    }
}