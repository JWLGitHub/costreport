package jwl.prp.retiree.costreport.enums;

/**
 * Created by jwleader on 11/10/15.
 */
public enum ErrRef
{

    RX_GROUP_NUMBER_NOT_AN_ERR                      (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0000", "RX GROUP NUMBER (NOT AN ERROR)"),
    INVALID_APPLICATION_ID_ON_AHDR                  (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0010", "INVALID APPLICATION ID ON AHDR"),
    INVALID_UBOI_ON_DETL                            (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0020", "INVALID UBOI ON DETL"),
    UNMATCHED_APPLICATION_ID_IN_ATRL                (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0030", "UNMATCHED APPLICATION ID IN ATRL"),
    APPLICATION_TRAILER_RECORD_COUNT_ERROR          (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0040", "APPLICATION TRAILER RECORD COUNT ERROR"),
    APPLICATION_TRAILER_RECORD_COUNT_NON_NUMERIC    (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0050", "APPLICATION TRAILER RECORD COUNT NON-NUMERIC"),
    APPLICATION_TRAILER_PREMIUM_AMOUNT_INCORRECT    (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0060", "APPLICATION TRAILER PREMIUM AMOUNT INCORRECT"),
    APPLICATION_TRAILER_PREMIUM_AMOUNT_NON_NUMERIC  (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0070", "APPLICATION TRAILER PREMIUM AMOUNT NON-NUMERIC"),
    APPLICATION_TRAILER_RET_COST_INCORRECT          (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0080", "APPLICATION TRAILER RET COST INCORRECT"),
    APPLICATION_TRAILER_RET_COST_NON_NUMERIC        (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0090", "APPLICATION TRAILER RET COST NON-NUMERIC"),
    APPLICATION_TRAILER_THRESHOLD_INCORRECT         (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0100", "APPLICATION TRAILER THRESHOLD INCORRECT"),
    APPLICATION_TRAILER_THRESHOLD_NON_NUMERIC       (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0110", "APPLICATION TRAILER THRESHOLD NON-NUMERIC"),
    APPLICATION_TRAILER_LIMIT_INCORRECT             (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0120", "APPLICATION TRAILER LIMIT INCORRECT"),
    APPLICATION_TRAILER_LIMIT_NON_NUMERIC           (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0130", "APPLICATION TRAILER LIMIT NON-NUMERIC"),
    APPLICATION_TRAILER_COST_ADJUST_INCORRECT       (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0140", "APPLICATION TRAILER COST ADJUST INCORRECT"),
    APPLICATION_TRAILER_COST_ADJUST_NON_NUMERIC     (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0150", "APPLICATION TRAILER COST ADJUST NON-NUMERIC"),
    APPLICATION_DETAIL_DATE_IS_INVALID              (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0160", "APPLICATION DETAIL DATE IS INVALID"),
    APPLICATION_DETAIL_PREMIUM_IS_NON_NUMERIC       (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0170", "APPLICATION DETAIL PREMIUM IS NON-NUMERIC"),
    APPLICATION_DETAIL_RET_COST_IS_NON_NUMERIC      (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0180", "APPLICATION DETAIL RET COST IS NON-NUMERIC"),
    APPLICATION_DETAIL_THRESHOLD_IS_NON_NUMERIC     (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0190", "APPLICATION DETAIL THRESHOLD IS NON_NUMERIC"),
    APPLICATION_DETAIL_LIMIT_IS_NON_NUMERIC         (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0200", "APPLICATION DETAIL LIMIT IS NON-NUMERIC"),
    APPLICATION_DETAIL_COST_ADJUST_IS_NON_NUMERIC   (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0210", "APPLICATION DETAIL COST ADJUST IS NON-NUMERIC"),
    DUPLICATE_APPLICATION_WITHIN_A_FILE             (ErrCtgRef.APPLICATION_ERROR.getErrCtgryCd(), "0220", "DUPLICATE APPLICATION WITHIN A FILE"),

    LIST_RECORD_CONTAINS_INVALID_DSN                (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0010", "LIST RECORD CONTAINS INVALID DSN"),
    LIST_RECORD_PLAN_SPONSOR_DOES_NOT_EXIST         (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0020", "LIST RECORD - PLAN SPONSOR DOES NOT EXIST"),
    CRFILE_ALLOCATION_ERROR                         (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0030", "CRFILE ALLOCATION ERROR"),
    CRFILE_OPEN_ERROR                               (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0040", "CRFILE OPEN ERROR"),
    CRFILE_DSORG_ERROR                              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0050", "CRFILE DSORG ERROR"),
    CRFILE_RECFM_ERROR                              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0060", "CRFILE RECFM ERROR"),
    CRFILE_LRECL_ERROR                              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0070", "CRFILE LRECL ERROR"),
    CRFILE_READ_ERROR                               (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0080", "CRFILE READ ERROR"),
    CRFILE_BAD_RECORD_TYPE                          (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0090", "CRFILE - BAD RECORD TYPE"),
    CRFILE_IS_EMPTY                                 (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0100", "CRFILE IS EMPTY"),
    CRFILE_CONTAINS_NO_APPLICATIONS                 (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0110", "CRFILE CONTAINS NO APPLICATIONS"),
    ALL_APPLICATIONS_REJECTED                       (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0120", "ALL APPLICATIONS REJECTED"),
    FILE_HEADER_MISSING                             (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0130", "FILE HEADER MISSING"),
    FILE_HEADER_OUT_OF_SEQUENCE                     (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0140", "FILE HEADER OUT OF SEQUENCE"),
    FILE_TRAILER_OUT_OF_SEQUENCE                    (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0150", "FILE TRAILER OUT OF SEQUENCE"),
    FILE_TRAILER_MISSING                            (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0160", "FILE TRAILER MISSING"),
    APPLICATION_HEADER_OUT_OF_SEQUENCE              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0170", "APPLICATION HEADER OUT OF SEQUENCE"),
    APPLICATION_DETAIL_RECORD_OUT_OF_SEQUENCE       (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0180", "APPLICATION DETAIL RECORD OUT OF SEQUENCE"),
    APPLICATION_TRAILER_RECORD_OUT_OF_SEQUENCE      (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0190", "APPLICATION TRAILER RECORD OUT OF SEQUENCE"),
    UNEXPECTED_EOF_FILE_SEQUENCE_ERROR              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0200", "UNEXPECTED EOF. FILE SEQUENCE ERROR"),
    FILE_HEADER_SUBMITTER_TYPE_DOES_NOT_MATCH_DSN   (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0210", "FILE HEADER SUBMITTER TYPE DOES NOT MATCH DSN"),
    FILE_HEADER_SUBMITTER_ID_MISSING                (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0220", "FILE HEADER SUBMITTER ID MISSING"),
    FILE_HEADER_SUBMITTER_ID_DOES_NOT_MATCH_DSN     (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0230", "FILE HEADER SUBMITTER ID DOES NOT MATCH DSN"),
    HEADER_DATE_IS_INVALID_DATE_FORMAT              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0240", "HEADER DATE IS INVALID DATE FORMAT"),
    HEADER_IS_FUTURE_DATED                          (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0250", "HEADER IS FUTURE DATED"),
    HEADER_DATETIME_IS_OUT_OF_SEQUENCE              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0260", "HEADER DATE/TIME IS OUT OF SEQUENCE"),
    FILE_HEADER_TIME_IS_INVALID                     (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0270", "FILE HEADER TIME IS INVALID"),
    FILE_TRAILER_SUBMITTER_IS_INVALID               (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0280", "FILE TRAILER SUBMITTER IS INVALID"),
    FILE_TRAILER_SUBMITTER_ID_DOES_NOT_MATCH_HEADER (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0290", "FILE TRAILER SUBMITTER ID DOES NOT MATCH HEADER"),
    FILE_TRAILER_APPLICATION_COUNT_INCORRECT        (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0300", "FILE TRAILER APPLICATION COUNT INCORRECT"),
    FILE_TRAILER_APPLICATION_COUNT_NON_NUMERIC      (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0310", "FILE TRAILER APPLICATION COUNT NON-NUMERIC"),
    FILE_TRAILER_PREMIUM_AMOUNT_INCORRECT           (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0320", "FILE TRAILER PREMIUM AMOUNT INCORRECT"),
    FILE_TRAILER_PREMIUM_AMOUNT_NON_NUMERIC         (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0330", "FILE TRAILER PREMIUM AMOUNT NON-NUMERIC"),
    FILE_TRAILER_RET_COST_AMOUNT_INCORRECT          (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0340", "FILE TRAILER RET COST AMOUNT INCORRECT"),
    FILE_TRAILER_RET_COST_AMOUNT_NON_NUMERI         (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0350", "FILE TRAILER RET COST AMOUNT NON-NUMERI"),
    FILE_TRAILER_THRESHOLD_AMOUNT_INCORRECT         (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0360", "FILE TRAILER THRESHOLD AMOUNT INCORRECT"),
    FILE_TRAILER_THRESHOLD_AMOUNT_NON_NUMER         (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0370", "FILE TRAILER THRESHOLD AMOUNT NON-NUMER"),
    FILE_TRAILER_LIMIT_AMOUNT_INCORRECT             (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0380", "FILE TRAILER LIMIT AMOUNT INCORRECT"),
    FILE_TRAILER_LIMIT_AMOUNT_NON_NUMERIC           (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0390", "FILE TRAILER LIMIT AMOUNT NON-NUMERIC"),
    FILE_TRAILER_COST_ADJUST_INCORRECT              (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0400", "FILE TRAILER COST ADJUST INCORRECT"),
    FILE_TRAILER_COST_ADJUST_NON_NUMERIC            (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0410", "FILE TRAILER COST ADJUST NON-NUMERIC"),
    APPLICATIONS_DROPPED_FROM_FILE                  (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0420", "APPLICATION(S) DROPPED FROM FILE"),
    NON_NUMERIC_FIELD_IN_ATRL                       (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0430", "NON-NUMERIC FIELD IN ATRL"),
    CRFILE_IS_MISSING                               (ErrCtgRef.FILE_ERROR.getErrCtgryCd(),        "0440", "CRFILE IS MISSING");


    private final String    errCtgryCd;
    private final String    errCd;
    private final String    descTxt;


    ErrRef(String errCtgryCd,
           String errCd,
           String descTxt)
    {
        this.errCtgryCd = errCtgryCd;
        this.errCd      = errCd;
        this.descTxt    = descTxt;
    }


    public String getErrCtgryCd()
    {
        return errCtgryCd;
    }


    public String getErrCd()
    {
        return errCd;
    }


    public String getDescTxt()
    {
        return descTxt;
    }
}
