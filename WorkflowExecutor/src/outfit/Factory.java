package outfit;
import org.apache.log4j.Logger;

import java.lang.*;
import java.util.*;

public class Factory
{
    private static final Logger logger = Logger.getLogger(Factory.class);

    private HashMap<String, Class> names = new HashMap<>();

    public void add(String name, String nameClass) throws ClassNotFoundException
    {
        names.put(name, Class.forName(nameClass));

        logger.trace("Factory successfully included new Blockclass");
    }

    public Object getBlock(String s) throws Exception
    {
        logger.info("Create new blockObject");

        return names.get(s).getDeclaredConstructor().newInstance();
    }
}
