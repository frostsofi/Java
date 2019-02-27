package outfit;

import org.apache.log4j.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriterFlow implements Block
{
    private static final Logger logger = Logger.getLogger(WriterFlow.class);

    @Override
    public String execute(String text, ArrayList<String> args) throws IOException
    {
        logger.info("WriterFlow execution");

         FileOutputStream fout = new FileOutputStream(args.get(1));
         fout.write(text.getBytes());
         fout.close();

        logger.trace("out file closed successfully");
        return "";
    }
}
