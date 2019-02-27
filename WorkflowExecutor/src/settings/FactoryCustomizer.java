package settings;

import java.io.IOException;
import java.util.*;
import org.apache.log4j.*;
import outfit.Factory;

public class FactoryCustomizer implements Customizer
{
    private static final Logger logger = Logger.getLogger(FactoryCustomizer.class);

    private Properties config;
    private Factory blockFactory;

    public FactoryCustomizer(String fileName) throws IOException
    {
        config = new Properties();
        config.load(FactoryCustomizer.class.getResourceAsStream(fileName));
    }

    public Factory getFactory() throws ClassNotFoundException
    {
        make_configurations();

        logger.info("Factory was customized succesfully");
        return blockFactory;
    }

    @Override
    public void make_configurations() throws ClassNotFoundException {
        Set <String> cur = config.stringPropertyNames();
        blockFactory = new Factory();
        for (String s : cur)
            blockFactory.add(s, config.getProperty(s));
        }
}
