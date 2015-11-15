package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.Validation.FileStructure.FileContext;
import jwl.prp.retiree.costreport.Validation.Validator;
import jwl.prp.retiree.costreport.entity.*;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by jwleader on 10/25/15.
 */
public class CostReportItemProcessor implements StepExecutionListener,
                                                ItemProcessor<CostReportRecord, List<CostReportRecord>>
{
    private static String CLASS_NAME  = CostReportItemProcessor.class.getName();
    private static String SIMPLE_NAME = CostReportItemProcessor.class.getSimpleName();

    private String inputFilePath;

    private JobExecution jobExecution;

    private StepExecution stepExecution;

    private List<CostReportRecord> costReportRecords;

    private List<Validator> fileHeaderValidators;

    private List<Validator> applicationHeaderValidators;

    private List<Validator> applicationDetailValidators;

    private List<Validator> applicationTrailerValidators;

    private List<Validator> applicationValidators;

    private List<Validator> fileTrailerValidators;

    /*
     *---   FILE HEADER WORK AREA
     */
    private int     fileHeaderCount        = 0;
    private boolean validFileHeader        = false;
    private String  fileHeaderSubmitterID;

    /*
     *---   APPLICATION WORK AREA
     */
    private int     applicationHeaderCount  = 0;
    private boolean validApplicationHeader  = false;
    private String  applicationHeaderApplicationID;
    private int     applicationTrailerCount = 0;

    /*
     *---   FILE TRAILER WORK AREA
     */
    private int     fileTrailerCount       = 0;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_FILE_STATUS = "INSERT INTO FILE_STATUS (FILE_NAME, FILE_STATUS, FILE_STATUS_REASON, FILE_ADD_USER, FILE_ADD_DATETIME, FILE_JOB_NAME, FILE_STEP_NAME) values (?,?,?,?,?,?,?)";


    public void beforeStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "beforeStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        this.stepExecution = stepExecution;
        this.jobExecution  = stepExecution.getJobExecution();
    }


    public ExitStatus afterStep(StepExecution stepExecution)
    {
        final String METHOD_NAME = "afterStep";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }

    @Override
    public List<CostReportRecord> process(CostReportRecord costReportRecord) throws Exception
    {
        final String METHOD_NAME = "process";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FHDR.name()))
        {
            return processFileHeaderRecord((FileHeader) costReportRecord);
        }
        else if (validFileHeader)
        {
            if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.AHDR.name()))
            {
                processApplicationHeaderRecord((ApplicationHeader) costReportRecord);
                return null;
            }
            else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.DETL.name()))
            {
                if (validApplicationHeader)
                    processApplicationDetailRecord((ApplicationDetail) costReportRecord);

                return null;
            }
            else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.ATRL.name()))
            {
                if (validApplicationHeader)
                    return processApplicationTrailerRecord((ApplicationTrailer) costReportRecord);
                else
                    return null;
            } else if (costReportRecord.getRecordType().equalsIgnoreCase(CostReportRecord.RecordType.FTRL.name()))
            {
                return processFileTrailerRecord((FileTrailer) costReportRecord);
            }
            else
                throw new ValidationException("INVALID COST REPORT RECORD: " + costReportRecord);
        }
        else
            return null;
    }


    /*
     *****                                    *****
     *****     PROCESS FILE HEADER RECORD     *****
     *****                                    *****
     */
    private List<CostReportRecord> processFileHeaderRecord(FileHeader fileHeader)
                                                           throws Exception
    {
        final String METHOD_NAME = "processFileHeaderRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        validFileHeader = false;
        fileHeaderCount = fileHeaderCount + 1;
        if (fileHeaderCount > 1)
            throw new ValidationException("MULTIPLE COST REPORT FILE HEADERS - SubmitterID: " + fileHeader.getSubmitterID());

        if (validateCostReportRecord(fileHeader, fileHeaderValidators))
        {
            validFileHeader = true;
            fileHeaderSubmitterID = new String(fileHeader.getSubmitterID());
            costReportRecords = new LinkedList<CostReportRecord>();
            costReportRecords.add(fileHeader);
            return costReportRecords;
        }
        else
            throw new ValidationException("INVALID COST REPORT FILE HEADER - SubmitterID: " + fileHeader.getSubmitterID());
    }


    /*
    *****                                           *****
    *****     PROCESS APPLICATION HEADER RECORD     *****
    *****                                           *****
    */
    private void processApplicationHeaderRecord(ApplicationHeader applicationHeader)
                                                throws Exception
    {
        final String METHOD_NAME = "processApplicationHeaderRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (validateCostReportRecord(applicationHeader, applicationHeaderValidators))
        {
            insertFileStatus(FileStatus.FILE_STATUS_TYPE.START,
                             applicationHeader.getApplicationID());

            validApplicationHeader = true;
            applicationHeaderApplicationID = new String(applicationHeader.getApplicationID());
            costReportRecords = new LinkedList<CostReportRecord>();
            costReportRecords.add(applicationHeader);
        }
        else
        {
            validApplicationHeader = false;
            throw new ValidationException("INVALID COST REPORT APPLICATION HEADER - SubmitterID: " + fileHeaderSubmitterID + " ApplicationID: " + applicationHeader.getApplicationID());
        }
    }


    /*
    *****                                           *****
    *****     PROCESS APPLICATION DETAIL RECORD     *****
    *****                                           *****
    */
    private void processApplicationDetailRecord(ApplicationDetail applicationDetail)
                                                 throws Exception
    {
        final String METHOD_NAME = "processApplicationDetailRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (validateCostReportRecord(applicationDetail, applicationDetailValidators))
            costReportRecords.add(applicationDetail);
        else
            throw new ValidationException("INVALID COST REPORT APPLICATION DETAIL - SubmitterID: " + fileHeaderSubmitterID + " ApplicationID: " + applicationHeaderApplicationID + " Unique Benefit Option ID: " + applicationDetail.getUniqueBenefitOptionIdentifier());
    }


    /*
    *****                                            *****
    *****     PROCESS APPLICATION TRAILER RECORD     *****
    *****                                            *****
    */
    private List<CostReportRecord> processApplicationTrailerRecord(ApplicationTrailer applicationTrailer)
                                                                   throws Exception
    {
        final String METHOD_NAME = "processApplicationTrailerRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (validateCostReportRecord(applicationTrailer, applicationTrailerValidators))
        {
            costReportRecords.add(applicationTrailer);
            if (validateApplicationRecords(costReportRecords, applicationValidators))
            {
                insertFileStatus(FileStatus.FILE_STATUS_TYPE.END,
                                 applicationTrailer.getApplicationID());

                return costReportRecords;
            }
            else
            {
                insertFileStatus(FileStatus.FILE_STATUS_TYPE.BYPASS,
                                 applicationTrailer.getApplicationID());

                return null;
            }
        }
        else
        {
            throw new ValidationException("INVALID COST REPORT APPLICATION TRAILER - SubmitterID: " + fileHeaderSubmitterID + " ApplicationID: " + applicationTrailer.getApplicationID());
        }
    }


    /*
    *****                                     *****
    *****     PROCESS FILE TRAILER RECORD     *****
    *****                                     *****
    */
    private List<CostReportRecord> processFileTrailerRecord(FileTrailer fileTrailer)
                                                            throws Exception
    {
        final String METHOD_NAME = "processFileTrailerRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (fileHeaderCount == 0)
            throw new ValidationException("NO FILE HEADER EXISTS FOR FILE TRAILER - SubmitterID: " + fileTrailer.getSubmitterID());

        fileTrailerCount = fileTrailerCount + 1;
        if (fileTrailerCount > 1)
            throw new ValidationException("MULTIPLE COST REPORT FILE TRAILERS - SubmitterID: " + fileTrailer.getSubmitterID());

        if (validateCostReportRecord(fileTrailer, fileTrailerValidators))
        {
            costReportRecords = new LinkedList<CostReportRecord>();
            costReportRecords.add(fileTrailer);
            return costReportRecords;
        }
        else
            throw new ValidationException("INVALID COST REPORT FILE TRAILER - SubmitterID: " + fileTrailer.getSubmitterID());
    }


    private boolean validateCostReportRecord(CostReportRecord costReportRecord,
                                             List<Validator>  costReportValidators)
    {
        final String METHOD_NAME = "validateCostReportRecord";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (null != costReportValidators   &&
            !costReportValidators.isEmpty())
        {
            for (Validator costReportValidator : costReportValidators)
            {
                if (null != costReportValidator.validate(costReportRecord, new FileContext()))
                    return false;
            }
        }

        return true;
    }


    private boolean validateApplicationRecords(List<CostReportRecord> costReportRecords,
                                               List<Validator>        applicationValidators)
    {
        final String METHOD_NAME = "validateApplicationRecords";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        if (null != applicationValidators   &&
            !applicationValidators.isEmpty())
        {
            for (Validator applicationValidator : applicationValidators)
            {
                if (null != applicationValidator.validate(costReportRecords.get(0), new FileContext()))
                    return false;
            }
        }

        return true;
    }


    private void insertFileStatus(FileStatus.FILE_STATUS_TYPE fileStatusType,
                                  String                      fileStatusReason)
    {
        final String METHOD_NAME = "insertFileStatus";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        jdbcTemplate.update(INSERT_FILE_STATUS,
                            inputFilePath,
                            fileStatusType.name(),
                            fileStatusReason,
                            SIMPLE_NAME,
                            new Date(),
                            jobExecution.getJobInstance().getJobName(),
                            stepExecution.getStepName());

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


    public void setFileHeaderValidators(List<Validator> fileHeaderValidators)
    {
        final String METHOD_NAME = "setFileHeaderValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileHeaderValidators = fileHeaderValidators;
    }


    public void setApplicationHeaderValidators(List<Validator> applicationHeaderValidators)
    {
        final String METHOD_NAME = "setApplicationHeaderValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationHeaderValidators = applicationHeaderValidators;
    }


    public void setApplicationDetailValidators(List<Validator> applicationDetailValidators)
    {
        final String METHOD_NAME = "setApplicationDetailValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationDetailValidators = applicationDetailValidators;
    }


    public void setApplicationTrailerValidators(List<Validator> applicationTrailerValidators)
    {
        final String METHOD_NAME = "setApplicationTrailerValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationTrailerValidators = applicationTrailerValidators;
    }


    public void setApplicationValidators(List<Validator> applicationValidators)
    {
        final String METHOD_NAME = "setApplicationValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.applicationValidators = applicationValidators;
    }


    public void setFileTraileralidators(List<Validator> fileTrailerValidators)
    {
        final String METHOD_NAME = "setFileTrailerValidators";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        this.fileTrailerValidators = fileTrailerValidators;
    }
}
