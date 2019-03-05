package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import outfit.Sorter;
import java.io.IOException;
import java.util.ArrayList;

public class SorterTest
{
    private String text;
    private ArrayList<String> args;
    private Sorter sort = new Sorter();

    @Before
    public void setUp() {
        args = new ArrayList<>();
        args.add(0,"");
    }

    @Test
    public void testExecute1() throws IOException {
        String expected, actual;
        text = "Winter, winter.\nLet’s go skate.\nWinter,winter.\nDon’t be late.Winter, winter.\n"+
                "Let’s go roll.\nWinter, winter.\nIn the snow.\nWinter, winter!\nWinter, winter!\n";
        actual = sort.execute(text, args);
        expected = "Don’t be late.Winter, winter.\nIn the snow.\nLet’s go roll.\n"
        + "Let’s go skate.\nWinter, winter!\nWinter, winter!\nWinter, winter.\nWinter, winter.\nWinter,winter.";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecute2() throws IOException {
        String expected, actual;
        text = "a\nf\ncat\nroot\nza\nzoo\nart\n";
        actual = sort.execute(text, args);
        expected = "a\nart\ncat\nf\nroot\nza\nzoo";
        Assert.assertEquals(actual, expected);
    }

}