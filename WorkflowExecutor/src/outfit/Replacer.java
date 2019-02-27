package outfit;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class Replacer implements Block
{
    private static final Logger logger = Logger.getLogger(Replacer.class);

    @Override
    public String execute(String text, ArrayList<String> args) throws IOException
    {
        logger.info("Replacer execution");

        return text.replaceAll(args.get(1), args.get(2));
    }
}
