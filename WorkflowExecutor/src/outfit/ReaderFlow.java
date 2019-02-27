package outfit;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderFlow implements Block
{
    private static final Logger logger = Logger.getLogger(ReaderFlow.class);

    @Override
    public String execute(String text, ArrayList<String> args) throws IOException
    {
        logger.info("ReaderFlow execution");

        FileInputStream fin = new FileInputStream(args.get(1));
        return new String(fin.readAllBytes());
    }
}
