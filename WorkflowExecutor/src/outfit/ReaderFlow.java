package outfit;
import flow.Workflow;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class ReaderFlow implements Block
{
    private static final Logger logger = Logger.getLogger(ReaderFlow.class);

    @Override
    public String execute(String text, ArrayList<String> args) throws IOException
    {
        logger.info("ReaderFlow execution");

        return new String(Workflow.class.getResourceAsStream(args.get(1)).readAllBytes());
    }
}
