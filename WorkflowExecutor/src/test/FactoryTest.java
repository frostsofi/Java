package test;

import org.junit.Assert;
import org.junit.Test;
import outfit.Factory;
import outfit.Greper;
import static org.junit.Assert.*;

public class FactoryTest {

    @Test
    public void getBlockTest() throws Exception {
        Factory production = new Factory();
        production.add("grep", "outfit.Greper");
        production.add("sort", "outfit.Sorter");
        production.add("replace", "outfit.Replacer");
        production.add("readfile", "outfit.ReaderFlow");
        production.add("writefile", "outfit.WriterFlow");

        Object actual = production.getBlock("grep");
        Assert.assertNotNull(actual);
        actual = production.getBlock("sort");
        Assert.assertNotNull(actual);
        actual = production.getBlock("replace");
        Assert.assertNotNull(actual);
        actual = production.getBlock("readfile");
        Assert.assertNotNull(actual);
        actual = production.getBlock("writefile");
        Assert.assertNotNull(actual);
    }
}