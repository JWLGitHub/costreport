package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.dao.ApplicationDAO;
import jwl.prp.retiree.costreport.entity.Application;
import jwl.prp.retiree.costreport.entity.RetFileHist;
import org.springframework.batch.item.ItemProcessor;


public class CursorItemProcessor implements ItemProcessor<RetFileHist, RetFileHist>
{
    private static String CLASS_NAME  = CursorItemProcessor.class.getName();
    private static String SIMPLE_NAME = CursorItemProcessor.class.getSimpleName();

    private static String PROCESS_RETIREE_FILE       = "03";
    private static String DONT_PROCESS_RETIREE_FILE  = "23";

    private static String FILE_STATUS_PROCESSED      = "01";
    private static String FILE_STATUS_NEVER_PROCESS  = "02";

    private static String ERROR_REASON_04            = "04";

    private ApplicationDAO applicationDAO;


    @Override
    public RetFileHist process(RetFileHist retFileHist) throws Exception
    {
        final String METHOD_NAME = "process";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + retFileHist.toString());

        Application application = applicationDAO.findByApplicationId(retFileHist.getApplId());

        if (null == application)
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - APPLICATION NOT FOUND");
            return null;
        }

        if (application.getStatus().equalsIgnoreCase(DONT_PROCESS_RETIREE_FILE))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - DON'T PROCESS RETIREE FILE");
            retFileHist.setPrevStatus(retFileHist.getStatus());
            retFileHist.setStatus(FILE_STATUS_NEVER_PROCESS);
            retFileHist.setErrorReason(ERROR_REASON_04);
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - CREATE RLSR 'N' TABLE ENTRY");
            return retFileHist;
        }

        if (application.getStatus().equalsIgnoreCase(PROCESS_RETIREE_FILE))
        {
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - PROCESS RETIREE FILE");
            retFileHist.setPrevStatus(retFileHist.getStatus());
            retFileHist.setStatus(FILE_STATUS_PROCESSED);
            return retFileHist;
        }

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BYPASS RETIREE FILE");
        return null;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setApplicationDAO(ApplicationDAO applicationDAO)
    {
        this.applicationDAO = applicationDAO;
    }
}

