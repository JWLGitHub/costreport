package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.dao.FileErrDAO;
import jwl.prp.retiree.costreport.dao.RDSFileDAO;
import jwl.prp.retiree.costreport.entity.*;

import jwl.prp.retiree.costreport.enums.ErrCtgRef;
import jwl.prp.retiree.costreport.enums.ErrRef;
import jwl.prp.retiree.costreport.enums.StusCtgry;
import jwl.prp.retiree.costreport.enums.StusRef;
import org.springframework.batch.core.*;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * Created by jwleader on 11/5/15.
 */
public class CostReportFileProcessor implements StepExecutionListener,
                                                ItemReadListener<CostReportRecord>,
                                                ItemProcessor<CostReportRecord, CostReportRecord>
{
    private static String CLASS_NAME  = CostReportFileProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportFileProcessor.class.getSimpleName();

    private JobExecution     jobExecution;
    private ExecutionContext jobExecutionContext;
    private StepExecution    stepExecution;

    @Autowired
    private JdbcTemplate     jdbcTemplate;

    private RDSFileDAO       rdsFileDAO;
    private FileErrDAO       fileErrDAO;

    private String           inputFilePath;

    private static final String RDS_FILE_ID = "rdsFileId";

    private static final List<String> VALID_RECORD_TYPES = Arrays.asList("FHDR", "AHDR", "DETL", "ATRL", "FTRL");


    /*
    *---   JOB EXECUTION CONTEXT
    */
    private int              rdsFileId;

    /*
    *---   FILE HEADER WORK AREA
    */
    private int     fileErrSeqNum   = 0;
    private int     fileHeaderCount = 0;
    private boolean validFileHeader = false;
    private String  fileHeaderSubmitterID;

    /*
     *---   APPLICATION WORK AREA
     */
    private int applicationHeaderCount = 0;
    private boolean validApplicationHeader = false;
    private String applicationHeaderApplicationID;
    private int applicationTrailerCount = 0;

    /*
     *---   FILE TRAILER WORK AREA
     */
    private int fileTrailerCount = 0;


    /*
     *****                                         *****
     *****     -----     BEFORE STEP     -----     *****
     *****                                         *****
     */
    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "beforeStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.stepExecution       = stepExecution;
        this.jobExecution        = stepExecution.getJobExecution();
        this.jobExecutionContext = jobExecution.getExecutionContext();

        this.rdsFileId = getRdsFileId();
        updateRDSFile(StusRef.FILE_PROCESSING);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private int getRdsFileId()
    {
        final String METHOD_NAME = "getRdsFileId";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        Object rdsFileId = jobExecutionContext.get(RDS_FILE_ID);
        if (null == rdsFileId  ||
            rdsFileId.toString().equalsIgnoreCase(""))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + RDS_FILE_ID + ": MISSING");
            throw new RuntimeException("'" + RDS_FILE_ID + "' MISSING from jobExecutionContext");
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return Integer.parseInt(rdsFileId.toString());
    }


    /*
    *****                                        *****
    *****     -----     BEFORE READ    -----     *****
    *****                                        *****
    */
    @Override
    public void beforeRead()
    {
        final String METHOD_NAME = "beforeRead";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                          *****
     *****     -----     ON READ ERROR    -----     *****
     *****                                          *****
     */
    @Override
    public void onReadError(Exception exception)
    {
        final String METHOD_NAME = "onReadError";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
    *****                                       *****
    *****     -----     AFTER READ    -----     *****
    *****                                       *****
    */
    @Override
    public void afterRead(CostReportRecord costReportRecord)
    {
        final String METHOD_NAME = "afterRead";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
    *****                                    *****
    *****     -----     PROCESS    -----     *****
    *****                                    *****
    */
    @Override
    public CostReportRecord process(CostReportRecord costReportRecord)
                                    throws Exception
    {
        final String METHOD_NAME = "process";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FHDR.name()))
        {
            processFileHeaderRecord((FileHeader) costReportRecord);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.AHDR.name()))
        {
            processApplicationHeaderRecord((ApplicationHeader) costReportRecord);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.DETL.name()))
        {
            processApplicationDetailRecord((ApplicationDetail) costReportRecord);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.ATRL.name()))
        {
            processApplicationTrailerRecord((ApplicationTrailer) costReportRecord);
        }
        else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FTRL.name()))
        {
            processFileTrailerRecord((FileTrailer) costReportRecord);
        }
        else
            throw new ValidationException("INVALID COST REPORT RECORD: " + costReportRecord);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return costReportRecord;
    }


    /*
     *****                                    *****
     *****     PROCESS FILE HEADER RECORD     *****
     *****                                    *****
     */
    private CostReportRecord processFileHeaderRecord(FileHeader fileHeader)
                                                     throws Exception
    {
        final String METHOD_NAME = "processFileHeaderRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return fileHeader;
    }


    /*
    *****                                           *****
    *****     PROCESS APPLICATION HEADER RECORD     *****
    *****                                           *****
    */
    private CostReportRecord processApplicationHeaderRecord(ApplicationHeader applicationHeader)
                                                            throws Exception
    {
        final String METHOD_NAME = "processApplicationHeaderRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return applicationHeader;
    }


    /*
    *****                                           *****
    *****     PROCESS APPLICATION DETAIL RECORD     *****
    *****                                           *****
    */
    private CostReportRecord processApplicationDetailRecord(ApplicationDetail applicationDetail)
                                                            throws Exception
    {
        final String METHOD_NAME = "processApplicationDetailRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return applicationDetail;
    }


    /*
    *****                                            *****
    *****     PROCESS APPLICATION TRAILER RECORD     *****
    *****                                            *****
    */
    private CostReportRecord processApplicationTrailerRecord(ApplicationTrailer applicationTrailer)
                                                             throws Exception
    {
        final String METHOD_NAME = "processApplicationTrailerRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return applicationTrailer;
    }


    /*
    *****                                     *****
    *****     PROCESS FILE TRAILER RECORD     *****
    *****                                     *****
    */
    private CostReportRecord processFileTrailerRecord(FileTrailer fileTrailer)
                                                      throws Exception
    {
        final String METHOD_NAME = "processFileTrailerRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return fileTrailer;
    }


    /*
     *****                                         *****
     *****     -----     AFTER STEP     -----      *****
     *****                                         *****
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "afterStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        List<Throwable> throwables = stepExecution.getFailureExceptions();

        if (stepExecution.getFailureExceptions().size() > 0)
        {
            for (Throwable throwable : throwables)
            {
                System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + throwable.toString());
                if (throwable instanceof FlatFileParseException)
                {
                    FlatFileParseException flatFileParseException = (FlatFileParseException) throwable;
                    String processText = "Record No.: " + flatFileParseException.getLineNumber() +
                                         " Record Layout: " + flatFileParseException.getInput();

                    if (flatFileParseException.getInput().length() != 110)
                    {
                        insertFileErr(ErrRef.CRFILE_READ_ERROR.getErrCd(),
                                      ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                      processText);
                    }
                    else
                    {
                        String inputRecordType = flatFileParseException.getInput().substring(0, 4);
                        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - InputRecordType: " + inputRecordType);
                        if (!VALID_RECORD_TYPES.contains(inputRecordType))
                        {
                            insertFileErr(ErrRef.CRFILE_BAD_RECORD_TYPE.getErrCd(),
                                          ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                          processText);
                        }
                        else
                        {
                            insertFileErr(ErrRef.CRFILE_READ_ERROR.getErrCd(),
                                          ErrCtgRef.FILE_ERROR.getErrCtgryCd(),
                                          processText);
                        }
                    }

                    updateRDSFile(StusRef.FILE_REJECTED_BAD_STRUCTURE);
                }
            }
        }
        else
            updateRDSFile(StusRef.FILE_ACCEPTED_NO_ERRORS);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        return null;
    }


    private void updateRDSFile(StusRef fileStatus)
    {
        final String METHOD_NAME = "updateRDSFile";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        RDSFile rdsFile = rdsFileDAO.findByFileId(this.rdsFileId);
        rdsFile.setStusCtgryCd(StusCtgry.FILE_STATUS.getStusCtgryCd());
        rdsFile.setStusCd(fileStatus.getStusCd());
        rdsFile.setUptdPgm(SIMPLE_NAME);
        rdsFile.setUpdtTs(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        rdsFileDAO.updateRDSFile(rdsFile);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void insertFileErr(String errCd,
                               String errCtgryCd,
                               String errInfo)
    {
        final String METHOD_NAME = "insertFileErr";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        fileErrSeqNum++;

        FileErr fileErr = new FileErr(this.rdsFileId,
                                      errCd,
                                      errCtgryCd,
                                      fileErrSeqNum,
                                      errInfo);

        fileErrDAO.insertFileErr(fileErr);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setInputFilePath(String inputFilePath)
    {
        this.inputFilePath = inputFilePath;
    }

    public void setRdsFileDAO(RDSFileDAO rdsFileDAO)
    {
        this.rdsFileDAO = rdsFileDAO;
    }

    public void setFileErrDAO(FileErrDAO fileErrDAO) { this.fileErrDAO = fileErrDAO; }
}
