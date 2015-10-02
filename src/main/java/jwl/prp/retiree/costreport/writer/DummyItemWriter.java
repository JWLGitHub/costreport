package jwl.prp.retiree.costreport.writer;

import jwl.prp.retiree.costreport.entity.FileHeader;
import jwl.prp.retiree.costreport.entity.ApplicationHeader;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwleader on 9/30/15.
 */
public class DummyItemWriter implements ItemWriter<ApplicationHeader>
{
    private static String CLASS_NAME = DummyItemWriter.class.getName();

    public List<FileHeader> fileHeaders = new ArrayList<FileHeader>();

    public List<ApplicationHeader> applicationHeaders = new ArrayList<ApplicationHeader>();


    /*
    @Override
    public void write(List<? extends FileHeader> items)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(CLASS_NAME + " " + METHOD_NAME + " - BEGIN ");

        System.out.println("items = " + items.size());

        for (FileHeader fileHeader : items)
            System.out.println(CLASS_NAME + " " + METHOD_NAME + " - " + fileHeader.toString());


        fileHeaders.addAll(items);

        System.out.println(CLASS_NAME + " " + METHOD_NAME + " -  END ");
    }
    */


    @Override
    public void write(List<? extends ApplicationHeader> items)
                      throws Exception
    {
        final String METHOD_NAME = "write";
        System.out.println(CLASS_NAME + " " + METHOD_NAME + " - BEGIN ");

        System.out.println("items = " + items.size());

        for (ApplicationHeader applicationHeader : items)
            System.out.println(CLASS_NAME + " " + METHOD_NAME + " - " + applicationHeader.toString());


        applicationHeaders.addAll(items);

        System.out.println(CLASS_NAME + " " + METHOD_NAME + " -  END ");
    }


    public List<FileHeader> getFileHeaders() {
        return fileHeaders;
    }

    public List<ApplicationHeader> getApplicationHeaders() {
        return applicationHeaders;
    }
}
