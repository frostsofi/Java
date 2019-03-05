package test;

import flow.Workflow;
import org.junit.Assert;
import org.junit.Test;
import outfit.Factory;
import java.io.FileInputStream;

public class WorkflowTest
{

    @Test
    public void testFlow1() throws Exception {
        String expected, actual;
        Workflow pipe = new Workflow(Factory.getInstance("setting"));
        pipe.run("Test1");

        actual = new String(Workflow.class.getResourceAsStream("out1.txt").readAllBytes());
        expected = "Twinkle, twinkle, big star,\r\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFlow2() throws Exception {
        String expected, actual;
        Workflow pipe = new Workflow(Factory.getInstance("setting"));
        pipe.run("Test2");
        actual = new String(Workflow.class.getResourceAsStream("out2.txt").readAllBytes());

        expected = "And she has crept under\r\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFlow3() throws Exception {
        String expected, actual;
        Workflow pipe = new Workflow(Factory.getInstance("setting"));
        pipe.run("Test3");

        actual = new String(Workflow.class.getResourceAsStream("out_file.txt").readAllBytes());
        expected = "Math не относится к естественным наукам,\r\n" +
                "Math — наука об отношениях между объектами, о которых ничего не известно, кроме описывающих их некоторых свойств,\r\n" +
                "Math — фундаментальная наука, предоставляющая (общие) языковые средства другим наукам;\r\n";
        Assert.assertEquals(expected, actual);
    }

}