package jwl.prp.retiree.costreport.processor;


import jwl.prp.retiree.costreport.entity.RetFileHist;
import org.springframework.batch.item.ItemProcessor;


public class CursorItemProcessor implements ItemProcessor<RetFileHist, RetFileHist>
{
    private static String CLASS_NAME  = CursorItemProcessor.class.getName();
    private static String SIMPLE_NAME = CursorItemProcessor.class.getSimpleName();


    @Override
    public RetFileHist process(RetFileHist retFileHist) throws Exception
    {
        final String METHOD_NAME = "process";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + retFileHist.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
        return null;
    }


    /*
     *****                                       *****
     *****     -----     SETTER(s)     -----     *****
     *****                                       *****
     */
}

