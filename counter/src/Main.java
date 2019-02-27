import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Counter curCounter = new Counter(args);
        try {
            curCounter.countWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
