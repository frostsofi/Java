package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import outfit.Replacer;
import java.io.IOException;
import java.util.ArrayList;

public class ReplacerTest
{
    private String text;
    private ArrayList<String> args;
    private Replacer replace = new Replacer();

    @Before
    public void setUp() throws Exception {
        args = new ArrayList<>();
        args.add(0,"");
    }

    @Test
    public void testExecute1() throws IOException {
        String expected, actual;
        args.add(1, "Grandpa");
        args.add(2, "Father");
        text = "Grandpa planted a turnip.\n The turnip grew bigger and bigger.\n" +
                "Grandpa came to pick the turnip, pulled and pulled but couldn't pull it up!\n";
        actual = replace.execute(text, args);
        expected = "Father planted a turnip.\n The turnip grew bigger and bigger.\n" +
                "Father came to pick the turnip, pulled and pulled but couldn't pull it up!\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecute2() throws IOException {
        String expected, actual;
        args.add(1, "Winter");
        args.add(2, "Snow");
        text = "Winter, winter.\nLet’s go skate.\nWinter,winter.\nDon’t be late.Winter, winter.\n"+
                "Let’s go roll.\nWinter, winter.\nIn the snow.\nWinter, winter!\nWinter, winter!\n";
        actual = replace.execute(text, args);
        expected = "Snow, winter.\nLet’s go skate.\nSnow,winter.\nDon’t be late.Snow, winter.\n"+
                "Let’s go roll.\nSnow, winter.\nIn the snow.\nSnow, winter!\nSnow, winter!\n";
        Assert.assertEquals(actual, expected);
    }
}