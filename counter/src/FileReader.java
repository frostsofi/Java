import java.io.*;

public class FileReader implements Reader {
    private java.io.InputStreamReader reader;

    FileReader(String path) throws FileNotFoundException {
        reader = new InputStreamReader(new FileInputStream(path));
    }

    public String read() throws IOException {
        StringBuilder builder = new StringBuilder();
        int symbol = 0;
            while ((builder.toString().isEmpty()&&symbol != -1)) {
                while (Character.isLetterOrDigit(symbol = reader.read())) {
                    builder.append((char)symbol);
                }
            }

        return builder.toString();
    }

    public void close() {
        try {
            reader.close();
        } catch (java.io.IOException exp) {
            System.err.println("Can't close output file");
        }
    }
}

