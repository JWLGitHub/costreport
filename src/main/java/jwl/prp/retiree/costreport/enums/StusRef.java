package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 11/10/15.
 */
public enum StusRef
{

    ACCEPTED                            (StusCtgry.APPLICATION_STATUS.getStusCtgryCd(),  "AA", "Accepted"),
    APPLICATION_DELETED                 (StusCtgry.APPLICATION_STATUS.getStusCtgryCd(),  "AD", "application deleted"),
    REJECTED_FILE                       (StusCtgry.APPLICATION_STATUS.getStusCtgryCd(),  "AF", "Rejected (file)"),
    PROCESSING                          (StusCtgry.APPLICATION_STATUS.getStusCtgryCd(),  "AP", "Processing"),
    REJECTED                            (StusCtgry.APPLICATION_STATUS.getStusCtgryCd(),  "AR", "Rejected"),
    ACTIVE                              (StusCtgry.BENEFICIARY_STATUS.getStusCtgryCd(),  "AC", "Active"),
    DELETED                             (StusCtgry.BENEFICIARY_STATUS.getStusCtgryCd(),  "DL", "Deleted"),
    BENE_DELETE_TERMINATE_COVERAGE      (StusCtgry.BENEFICIARY_STATUS.getStusCtgryCd(),  "DM", "Bene Delete - Terminate Coverage"),
    FILE_PROCESSING_COMPLETED           (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "CM", "file processing completed"),
    FILE_DELETED                        (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "F-", "file deleted"),
    FILE_ACCEPTED_NO_ERRORS             (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FA", "file accepted (no errors)"),
    FILE_REJECTED_BAD_DATA              (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FD", "file rejected - bad data"),
    FILE_ACCEPTED_WITH_EERORS           (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FE", "file accepted with errors"),
    FILE_MISSING                        (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FI", "file missing"),
    FILE_EMPTY                          (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FM", "file empty"),
    FILE_PROCESSING_1ST_PASS            (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FP", "file processing - 1st pass"),
    FILE_PROCESSING_2ND_PASS            (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FQ", "file processing - 2nd pass"),
    FILE_REJECTED_BAD_STRUCTURE         (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FR", "file rejected - bad structure"),
    ALL_APPLICATIONS_REJECTED           (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FU", "All applications rejected"),
    FILE_EXISTS                         (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FX", "file exists"),
    NO_RECORDDS_IN_FILE_EXCEPT_HDR_TRLR (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "FZ", "No records in a file except for header and trailer"),
    NEW_FILE                            (StusCtgry.FILE_STATUS.getStusCtgryCd(),         "NW", "New file"),
    ORGANIZATION_DELETED                (StusCtgry.ORGANIZATION_STATUS.getStusCtgryCd(), "DL", "Organization deleted"),
    NEW_ORGANIZATION_ADDED              (StusCtgry.ORGANIZATION_STATUS.getStusCtgryCd(), "NW", "New organization added");


    private final String    stusCtgryCd;
    private final String    stusCd;
    private final String    description;



    StusRef(String stusCtgryCd,
            String stusCd,
            String description)
    {
        this.stusCtgryCd = stusCtgryCd;
        this.stusCd      = stusCd;
        this.description = description;
    }


    public String getStusCtgryCd()
    {
        return stusCtgryCd;
    }


    public String getStusCd()
    {
        return stusCd;
    }


    public String getDescription()
    {
        return description;
    }
}
