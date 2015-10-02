package jwl.prp.retiree.costreport.writer;

import jwl.prp.retiree.costreport.entity.*;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwleader on 10/2/15.
 */
public class CostReportItemWriter implements ItemWriter<CostReportRecord>
{
    private static String CLASS_NAME = CostReportItemWriter.class.getName();

    public List<CostReportRecord> costReportRecords = new ArrayList<CostReportRecord>();


    @Override
    public void write(List<? extends CostReportRecord> items)
            throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(CLASS_NAME + " " + METHOD_NAME + " - BEGIN ");

        System.out.println("items = " + items.size());

        for (CostReportRecord costReportRecord : items)
        {
            System.out.println(CLASS_NAME + " " + METHOD_NAME + " - " + costReportRecord.toString());
        }

        costReportRecords.addAll(items);

        System.out.println(CLASS_NAME + " " + METHOD_NAME + " -  END ");
    }


    public List<CostReportRecord> getCostReportRecords()
    {
        return costReportRecords;
    }
}

