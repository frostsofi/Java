package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import outfit.Greper;
import java.io.IOException;
import java.util.ArrayList;

public class GreperTest {

    private String text;
    private ArrayList<String> args;
    private Greper grep = new Greper();

    @Before
    public void setUp() throws Exception {
         args = new ArrayList<>();
         args.add(0,"");
    }

    @Test
    public void testExecute1() throws IOException {
        String expected, actual;
        args.add(1, "pulled");
      text = "Grandpa planted a turnip.\n The turnip grew bigger and bigger.\n" +
              "Grandpa came to pick the turnip, pulled and pulled but couldn't pull it up!\n";
         actual = grep.execute(text, args);
         expected = "Grandpa came to pick the turnip, pulled and pulled but couldn't pull it up!\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecute2() throws IOException {
        String expected, actual;
        args.add(1, "Jack");
        text = "So Jack took the cow to market.\n On the way, Jack met a stranger.\n" +
                "I'll give you five beans for that cow, she said.\n  They're magic beans…\n";
        actual = grep.execute(text, args);
        expected = "So Jack took the cow to market.\n On the way, Jack met a stranger.\n";
        Assert.assertEquals(actual, expected);
    }

}