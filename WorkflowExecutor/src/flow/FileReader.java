package flow;

import java.io.*;
import java.util.ArrayList;

class FileReader implements Reader {

    private java.io.InputStreamReader reader;

    FileReader(InputStream path)
    {
        reader = new InputStreamReader(path);
    }

    public ArrayList<String> read() throws IOException {

        StringBuilder builder = new StringBuilder();
        ArrayList<String> cur = new ArrayList<>();
        int symbol = 0;

        while (symbol != '\n' && symbol != -1) {

            while (Character.isLetterOrDigit(symbol = reader.read()) || symbol == '_' || symbol == '.')
                builder.append((char)symbol);

            if (!builder.toString().equals(""))
               cur.add(builder.toString());

            builder.delete(0, builder.capacity());
        }

        return cur;
    }

    public void close() {
        try {
            reader.close();
        } catch (java.io.IOException exp) {
            System.err.println("Can't close output file");
        }
    }
}

