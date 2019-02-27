package outfit;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Greper implements Block
{
    private static final Logger logger = Logger.getLogger(Greper.class);

    @Override
    public String execute(String text, ArrayList<String> args) throws IOException
    {
        logger.info("Greper execution");

        String [] setStrings = text.split("\n");
        StringBuilder textBuilder = new StringBuilder();

        for (String s : setStrings)
         {
             Matcher checker = Pattern.compile(args.get(1)).matcher(s);
              if (checker.find())
                  textBuilder.append(s).append("\n");
         }
        return  textBuilder.toString();
    }

}
