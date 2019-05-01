package messages;

import server.PreviousM;
import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
    private String sender;
    private String text;
    private ArrayList<String> listMember = new ArrayList<>();
    private PreviousM last;

    public Message() {}

    public Message(String senderNew, String textNew, ArrayList<String> list, PreviousM m)
    {
        sender = senderNew;
        text = textNew;
        listMember = new ArrayList<String>(list);
        last = new PreviousM(m);
    }

    public Message(String name, String mes)
    {
      sender = name;
      text = mes;
    }

    public ArrayList <String> getMembers()
    {
        return listMember;
    }

    public String getName()
    {
        return sender;
    }

    public String getText()
    {
        return text;
    }

    public PreviousM getLast()
    {
        return last;
    }
}
