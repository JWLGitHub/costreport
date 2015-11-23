package jwl.prp.retiree.costreport.writer;

import jwl.prp.retiree.costreport.entity.*;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jwleader on 10/2/15.
 */
public class CostReportItemWriter implements ItemWriter<List<CostReportRecord>>
{
    private static String CLASS_NAME  = CostReportItemWriter.class.getName();
    private static String SIMPLE_NAME = CostReportItemWriter.class.getSimpleName();

    public List<CostReportRecord> costReportRecords = new LinkedList<CostReportRecord>();


    @Override
    public void write(List<? extends List<CostReportRecord>> items)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - BEGIN ");

        System.out.println("items = " + items.size());

        for (List<CostReportRecord> item : items)
        {
            System.out.println("item = " + item.size());

            for (CostReportRecord costReportRecord : item)
            {
                System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + costReportRecord.toString());
            }

            costReportRecords.addAll(item);
        }

        System.out.println("costReportRecords = " + costReportRecords.size());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " -  END ");
    }


    public List<CostReportRecord> getCostReportRecords()
    {
        return costReportRecords;
    }
}

