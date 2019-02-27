import java.io.IOException;

public interface Writer
{
    void write(String... columns) throws IOException;
    void close();
}
