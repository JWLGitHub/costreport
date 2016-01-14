package jwl.prp.retiree.costreport.writer;


import jwl.prp.retiree.costreport.dao.RetFileHistDAO;
import jwl.prp.retiree.costreport.entity.RetFileHist;
import org.springframework.batch.item.ItemWriter;

import java.util.Calendar;
import java.util.List;


public class DriverQueryItemWriter implements ItemWriter<RetFileHist>
{
    private static String CLASS_NAME  = DriverQueryItemWriter.class.getName();
    private static String SIMPLE_NAME = DriverQueryItemWriter.class.getSimpleName();

    private RetFileHistDAO retFileHistDAO;


    @Override
    public void write(List<? extends RetFileHist> retFileHists)
            throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        for (RetFileHist retFileHist : retFileHists)
            updateRetFileHist(retFileHist);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    private void updateRetFileHist(RetFileHist retFileHist)
    {
        final String METHOD_NAME = "updateRetFileHist";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        retFileHist.setTimeStamp(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        retFileHist.setUpdtPgm(SIMPLE_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + retFileHist.toString());

        retFileHistDAO.updateRetFileHist(retFileHist);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
    public void setRetFileHistDAO(RetFileHistDAO retFileHistDAO)
    {
        this.retFileHistDAO = retFileHistDAO;
    }
}
