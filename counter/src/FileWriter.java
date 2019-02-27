import java.io.IOException;
import java.io.*;
import java.util.*;

public class FileWriter implements Writer{

    private OutputStreamWriter out;

    FileWriter(String path) throws FileNotFoundException
    {
        out = new OutputStreamWriter(new FileOutputStream(path));
    }

    @Override
    public void write(String... columns) throws IOException {
        out.write(String.join(",  ", columns));
        out.write("\n");
    }

    public void close()
    {
        try {
            out.close();
        }
        catch (java.io.IOException exp)
        {
            System.err.println("Can't close output file");
        }
    }
}

