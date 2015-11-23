package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 10/2/15.
 */
public class ApplicationDetail extends CostReportRecord
{
    // Position 5 - 24, Length 20
    private String uniqueBenefitOptionIdentifier;

    // Positions 25 - 30, Length 6 - Format: YYYYMM
    private String rxCostYearMonth;

    // Positions 31 - 42, Length 12 - Format: S999999999v99
    private String estimatedPremium;

    // Positions 43 - 54, Length 12 - Format: S999999999v99
    private String grossRetireeCost;

    // Positions 55 - 66, Length 12 - Format: S999999999v99
    private String thresholdReduction;

    // Positions 67 - 78, Length 12 - Format: S999999999v99
    private String limitReduction;

    // Positions 79 - 90, Length 12 - Format: S999999999v99
    private String estimatedCostAdjustment;

    // Positions 91 - 110, Length 20
    private String filler;


    public ApplicationDetail()
    {
    }


    public ApplicationDetail(String recordType,
                             String uniqueBenefitOptionIdentifier,
                             String rxCostYearMonth,
                             String estimatedPremium,
                             String grossRetireeCost,
                             String thresholdReduction,
                             String limitReduction,
                             String estimatedCostAdjustment,
                             String filler)
    {
        super(recordType);
        this.uniqueBenefitOptionIdentifier = uniqueBenefitOptionIdentifier;
        this.rxCostYearMonth =rxCostYearMonth;
        this.estimatedPremium = estimatedPremium;
        this.grossRetireeCost = grossRetireeCost;
        this.thresholdReduction = thresholdReduction;
        this.limitReduction = limitReduction;
        this.estimatedCostAdjustment = estimatedCostAdjustment;
        this.filler = filler;
    }


    // --- Getter(s) ---
    public String getUniqueBenefitOptionIdentifier() {
        return uniqueBenefitOptionIdentifier;
    }


    public String getRxCostYearMonth() {
        return rxCostYearMonth;
    }

    public String getEstimatedPremium() {
        return estimatedPremium;
    }

    public String getGrossRetireeCost() {
        return grossRetireeCost;
    }

    public String getThresholdReduction() {
        return thresholdReduction;
    }

    public String getLimitReduction() {
        return limitReduction;
    }

    public String getEstimatedCostAdjustment() {
        return estimatedCostAdjustment;
    }

    public String getFiller() {
        return filler;
    }


    // --- Setter(s) ---
    public void setUniqueBenefitOptionIdentifier(String uniqueBenefitOptionIdentifier) {
        this.uniqueBenefitOptionIdentifier = uniqueBenefitOptionIdentifier;
    }

    public void setRxCostYearMonth(String rxCostYearMonth) {
        this.rxCostYearMonth = rxCostYearMonth;
    }

    public void setEstimatedPremium(String estimatedPremium) {
        this.estimatedPremium = estimatedPremium;
    }

    public void setGrossRetireeCost(String grossRetireeCost) {
        this.grossRetireeCost = grossRetireeCost;
    }

    public void setThresholdReduction(String thresholdReduction) {
        this.thresholdReduction = thresholdReduction;
    }

    public void setLimitReduction(String limitReduction) {
        this.limitReduction = limitReduction;
    }

    public void setEstimatedCostAdjustment(String estimatedCostAdjustment) {
        this.estimatedCostAdjustment = estimatedCostAdjustment;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }


    @Override
    public String toString()
    {
        return super.toString() + ", " +
                "UniqueBenefitOptionIdentifier: " + uniqueBenefitOptionIdentifier + ", " +
                "RxCostYearMonth: " +  rxCostYearMonth + ", " +
                "EstimatedPremium: " +  estimatedPremium + ", " +
                "GrossRetireeCost: " +  grossRetireeCost + ", " +
                "ThresholdReduction: " + thresholdReduction + ", " +
                "LimitReduction: " + limitReduction + ", " +
                "EstimatedCostAdjustment: " + estimatedCostAdjustment + ", " +
                "Filler: " + filler;
    }


    @Override
    public String toFixedString()
    {
        return super.toFixedString() +
               uniqueBenefitOptionIdentifier + ", " +
               rxCostYearMonth + ", " +
               estimatedPremium + ", " +
               grossRetireeCost + ", " +
               thresholdReduction + ", " +
               limitReduction + ", " +
               estimatedCostAdjustment + ", " +
               filler;
    }
}
