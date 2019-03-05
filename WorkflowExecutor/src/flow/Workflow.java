package flow;

import org.apache.log4j.Logger;
import outfit.Block;
import outfit.Factory;
import java.io.IOException;
import java.util.*;

public class Workflow
{
    private static final Logger logger = Logger.getLogger(Workflow.class);

    private HashMap <Integer, ArrayList<String>> flowIntroduction = new HashMap<>();
    private ArrayList<String> flow;
    private Factory blockFactory;

    public Workflow(Factory newFactory)
    {
        blockFactory = newFactory;
    }

    private void analiseFile(String path) throws IOException {

        FileReader reader = new FileReader(Workflow.class.getResourceAsStream(path));
        ArrayList<String> curWords;
        int number;

        if (!(reader.read().get(0).equals("desc")))
        {
            logger.error("error in function alaliseFile, wrong parametr at first line");
            throw new IOException("wrong parametr at firstline");
        }

        while (!(curWords = reader.read()).isEmpty()) {
            if (!curWords.get(0).equals("csed"))
            {
                   number = Integer.parseInt(curWords.get(0));
                    curWords.remove(0);
                   flowIntroduction.put(number, curWords);
            }
            else
               flow = reader.read();
        }
        reader.close();

        logger.trace("File was processed and closed successfully");
    }

    public void run(String path) throws Exception{

        logger.info("Start processing");

        analiseFile(path);

        Iterator<String> node = flow.listIterator();
         String text = "";

         while (node.hasNext())
        {
            ArrayList<String> blockAtributes = flowIntroduction.get(Integer.parseInt(node.next()));
            Block operation = (Block)blockFactory.getBlock(blockAtributes.get(0));
            text = operation.execute(text, blockAtributes);
        }

         logger.info("All flow processed");
    }
}
