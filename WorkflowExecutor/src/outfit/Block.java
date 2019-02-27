package outfit;

import java.io.IOException;
import java.util.ArrayList;

public interface Block
{
   String execute(String text, ArrayList<String> args) throws IOException;
}
