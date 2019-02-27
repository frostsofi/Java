package outfit;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sorter implements Block
{
    private static final Logger logger = Logger.getLogger(Sorter.class);

    @Override
    public String execute(String text, ArrayList<String> args) throws IOException
    {
        logger.info("Sorter execution");

        ArrayList<String> list = new ArrayList<>();
        String [] setStrings = text.split("\n");
        list.addAll(Arrays.asList(setStrings));
        Collections.sort(list);
        int i = 0;
        for (String s : list) {
            setStrings[i] = s;
            i++;
        }
        return String.join("\n", setStrings);
    }
}
