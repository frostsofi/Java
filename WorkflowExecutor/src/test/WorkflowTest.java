package test;

import flow.Workflow;
import org.junit.Assert;
import org.junit.Test;
import settings.FactoryCustomizer;
import java.io.FileInputStream;

public class WorkflowTest
{

    @Test
    public void testFlow1() throws Exception {
        String expected, actual;
        Workflow pipe = new Workflow((new FactoryCustomizer("setting")).getFactory());
        pipe.run("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/test/Test1");
        FileInputStream fin = new FileInputStream("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/outfit/out1.txt");
        actual = new String(fin.readAllBytes());
        expected = "Twinkle, twinkle, big star,\r\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFlow2() throws Exception {
        String expected, actual;
        Workflow pipe = new Workflow((new FactoryCustomizer("setting")).getFactory());
        pipe.run("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/test/Test2");
        FileInputStream fin = new FileInputStream("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/outfit/out2.txt");
        actual = new String(fin.readAllBytes());
        expected = "And she has crept under\r\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFlow3() throws Exception {
        String expected, actual;
        Workflow pipe = new Workflow((new FactoryCustomizer("setting")).getFactory());
        pipe.run("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/test/Test3");
        FileInputStream fin = new FileInputStream("C:/Users/frost/IdeaProjects/WorkflowExecutor/src/outfit/out_file.txt");
        actual = new String(fin.readAllBytes());
        expected = "Math не относится к естественным наукам,\r\n" +
                "Math — наука об отношениях между объектами, о которых ничего не известно, кроме описывающих их некоторых свойств,\r\n" +
                "Math — фундаментальная наука, предоставляющая (общие) языковые средства другим наукам;\r\n";
        Assert.assertEquals(expected, actual);
    }

}