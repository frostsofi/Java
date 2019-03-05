package outfit;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.*;
import java.util.*;

public class Factory
{
    private static final Logger logger = Logger.getLogger(Factory.class);

    private static volatile Factory factory;
    private static Properties config = new Properties();

    public static Factory getInstance(String fileName) throws IOException
    {
         if (factory == null)
             synchronized (Factory.class) {
                 if (factory == null)
                     factory = new Factory();
             }

         config.load(Factory.class.getResourceAsStream(fileName));
         return factory;
    }

    public Object getBlock(String s) throws Exception
    {
        logger.info("Create new blockObject");

        if (!config.containsKey(s))
        {
            logger.error("function getBlock was incorrect");
            throw new Exception("Such Block doesn't exist");
        }

        return Class.forName(config.getProperty(s)).getDeclaredConstructor().newInstance();
    }

}
