package jwl.prp.retiree.costreport.entity;

/**
 * Created by jwleader on 10/2/15.
 */
public class CostReportRecord
{
    public enum RecordType
    {
        FHDR, AHDR, DETL, ATRL
    }


    // Positions 1 - 4, Length 4
    private String recordType;


    public CostReportRecord()
    {
    }

    public CostReportRecord(String recordType)
    {
        this.recordType = recordType;
    }

    // --- Getter(s) ---
    public String getRecordType()
    {
        return recordType;
    }

    // --- Setter(s) ---
    public void setRecordType(String recordType)
    {
        this.recordType = recordType;
    }

    @Override
    public String toString()
    {
        return "RecordType: " + recordType;
    }
}
