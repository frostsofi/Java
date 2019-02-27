package flow;

import java.io.IOException;
import java.util.ArrayList;

interface Reader
{
    ArrayList<String> read() throws IOException;
    void close();
}
