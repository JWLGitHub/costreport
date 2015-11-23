package jwl.prp.retiree.costreport.writer;

import jwl.prp.retiree.costreport.entity.CostReportRecord;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwleader on 9/30/15.
 */
public class DummyItemWriter implements ItemWriter<CostReportRecord>
{
    private static String CLASS_NAME  = DummyItemWriter.class.getName();
    private static String SIMPLE_NAME = DummyItemWriter.class.getSimpleName();


    @Override
    public void write(List<? extends CostReportRecord> costReportRecords)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);

        for (CostReportRecord costReportRecord : costReportRecords)
            System.out.println(SIMPLE_NAME + " " + METHOD_NAME + " - " + costReportRecord.toString());

        System.out.println(SIMPLE_NAME + " " + METHOD_NAME);
    }
}
