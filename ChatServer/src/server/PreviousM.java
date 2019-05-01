package server;

import messages.Message;

import java.io.Serializable;
import java.util.ArrayList;

public class PreviousM implements Serializable
{
    private ArrayList<Message> lastMessages = new ArrayList<>();

    public void add(Message m)
    {
       if (lastMessages.size() < 10)
           lastMessages.add(m);
       else {
           lastMessages.remove(0);
           lastMessages.add(m);
       }
    }

    public PreviousM(){};

    public PreviousM(PreviousM newObj)
    {
        lastMessages.addAll(0, newObj.lastMessages);
    }

    public ArrayList<Message> giveMes()
    {
        return lastMessages;
    }
}
