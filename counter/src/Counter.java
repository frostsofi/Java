import java.io.IOException;
import java.util.*;
public class Counter {

    private String [] arg;
    private HashMap<String, Integer> map;

    Counter(String[] argv)
    {
       arg = argv;
       map = new HashMap<>();
    }

    private void SortWords() throws IOException {
        List<Map.Entry<String, Integer>> cur = new ArrayList<>(map.entrySet());
        cur.sort(Map.Entry.comparingByValue());

        Collections.reverse(cur);

        FileWriter CurWriter = new FileWriter(arg[1]);
        int emount = cur.size(), k = 0;
        String[] columns = new String[3];
        while (k != emount) {
                columns[0] = cur.get(k).getKey();
                columns[1] = cur.get(k).getValue().toString();
                columns[2] = String.valueOf(((float)cur.get(k).getValue()/emount)*100);
                k ++;
            CurWriter.write(columns);
        }
        CurWriter.close();
    }

    public void countWords() throws IOException {
        FileReader CurReaderF = new FileReader(arg[0]);
        String str = "str";

        while (!(str = (String)CurReaderF.read()).isEmpty()) {
            if (!map.containsKey(str))
                map.put(str, 1);
            else
                map.put(str, map.get(str)+ 1);
        }
        CurReaderF.close();
        SortWords();
    }
}
