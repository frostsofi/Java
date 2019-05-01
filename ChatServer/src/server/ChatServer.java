package server;

import messages.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

class ChatServer
{
    private HashMap<Integer, ObjectOutputStream> members = new HashMap<>();
    private PreviousM listM = new PreviousM();
    private ArrayList<String> names = new ArrayList<>();

    public class readingSocket implements Runnable
    {
        private Socket socket;
        private ObjectInputStream in;

        readingSocket(Socket cur) throws IOException
        {
            socket = cur;
            in = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run()
        {
            String name = "";
            Message M = null;

            while (true)
            {
                try
                {
                    M = (Message) in.readObject();
                    listM.add(M);

                    if (!names.contains(M.getName()))
                    {
                        names.add(M.getName());

                        Message m = new Message(M.getName(), "new user in chat", names, listM);

                        mailing(m);
                    }
                    mailing(M);
                }
                catch
                (IOException | ClassNotFoundException e)
                {
                    try
                    {
                        members.get(socket.getPort()).close();
                        Message m = new Message(M.getName(), "go out from chat");
                        mailing(m);
                    }
                    catch (IOException exc)
                    { exc.printStackTrace(); }

                    members.remove(socket.getPort());
                    names.remove(name);
                    break;
                }
            }
        }
    }

    private void mailing(Message curMassage) throws IOException
    {
       for (Integer m : members.keySet())
       {
           members.get(m).writeObject(curMassage);
       }
    }

    void addMembers() throws IOException
    {
        ServerSocket server = new ServerSocket(2000);

        while (true)
        {
            Socket memberSocket = server.accept();
            members.put(memberSocket.getPort(), new ObjectOutputStream(memberSocket.getOutputStream()));

            Thread receivingThread = new Thread(new readingSocket(memberSocket));
            receivingThread.start();

        }
    }

}
