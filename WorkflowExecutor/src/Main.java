import flow.Workflow;
import org.apache.log4j.PropertyConfigurator;
import settings.FactoryCustomizer;

public class Main {

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/log4j");
        try {
            if (args.length < 2)
                throw new Exception();

            String settings = args[0];
            String pipeFile = args[1];

            if (settings.isEmpty() || pipeFile.isEmpty())
                throw new Exception();

            Workflow pipe = new Workflow((new FactoryCustomizer(settings)).getFactory());
            pipe.run(pipeFile);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
