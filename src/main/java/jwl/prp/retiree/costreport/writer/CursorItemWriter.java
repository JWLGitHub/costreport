package jwl.prp.retiree.costreport.writer;


import jwl.prp.retiree.costreport.entity.RetFileHist;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


public class CursorItemWriter implements ItemWriter<RetFileHist>
{
    private static String CLASS_NAME  = CursorItemWriter.class.getName();
    private static String SIMPLE_NAME = CursorItemWriter.class.getSimpleName();


    @Override
    public void write(List<? extends RetFileHist> retFileHists)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        for (RetFileHist retFileHist : retFileHists)
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + retFileHist.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }
}
