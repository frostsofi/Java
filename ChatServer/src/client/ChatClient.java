package client;

import messages.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

class ChatClient
{
    private ObjectOutputStream out;
    private Socket curSocket;

    private JTextField name = new JTextField(20);
    private JTextArea chatWindow = new JTextArea(15, 30);
    private JTextField myInput = new JTextField(30);
    private ArrayList<String> members = new ArrayList<String>(30);
    private JFrame frame = new JFrame();

    private JPanel membersPanel = new JPanel();
    private JButton close = new JButton("close");
    private JTextArea membersArea = new JTextArea();
    private JButton membersButton = new JButton("membersButton");

    private JPanel curPanel = new JPanel();

    class readOther implements Runnable {

        private ObjectInputStream in;

        readOther(ObjectInputStream newIn)
        {
           in =  newIn;
        }

        @Override
        public void run()
        {
            Message m = new Message();

            while (frame.isVisible())
            {
                try
               {
                   curSocket.setSoTimeout(20000);
                   m = (Message)in.readObject();

                   if (!m.getMembers().isEmpty())
                   {
                       members.addAll(0, m.getMembers());

                       if (membersButton.getActionListeners().length == 0)
                       {
                           membersButton.addActionListener(new MemberListener());
                           load();

                           ArrayList <Message> last = new ArrayList<Message>();
                           last.addAll(0, m.getLast().giveMes());
                           for (Message mc : last)
                           {
                               chatWindow.append(mc.getName() + " " +
                                       mc.getText() + "\n");
                           }
                       }
                       else
                           update();
                   }

                   chatWindow.append(m.getName() + " " +
                            m.getText() + "\n");

                }

               catch (IOException | ClassNotFoundException e)
               {
                   e.printStackTrace();
                   break;
               }
            }
            try
            {
                frame.setVisible(false);
                curSocket.close();
                out.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    void start() throws IOException
    {

        frame.add(curPanel);

        chatWindow.setLineWrap(true);
        chatWindow.setWrapStyleWord(true);
        chatWindow.setEnabled(false);
        membersArea.setEnabled(false);

        JScrollPane scroller = new JScrollPane(chatWindow);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton sendButton = new JButton("send");
        sendButton.addActionListener(new SendListener());

        curPanel.add(chatWindow);
        curPanel.add(myInput);
        curPanel.add(sendButton);
        curPanel.add(membersButton);

        frame.getContentPane().add(BorderLayout.CENTER, curPanel);

        JOptionPane.showConfirmDialog(frame, name, "Enter name",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (!name.getText().isEmpty())
            frame.setVisible(true);

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        curSocket = new Socket("127.0.0.1", 2000);
        out = new ObjectOutputStream(curSocket.getOutputStream());

        Thread readingThread = new Thread(new readOther(new ObjectInputStream(curSocket.getInputStream())));
        readingThread.start();
    }

    class SendListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (!myInput.getText().isEmpty())

                   out.writeObject(new Message(name.getText(), myInput.getText()));
                   myInput.setText(null);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    void load()
    {
        membersArea.setText("");
        for (String s : members)
        {
            membersArea.append(s + '\n');
        }

       if (close.getActionListeners().length == 0)
        close.addActionListener(new CloseListener());

        membersPanel.add(membersArea);
        membersPanel.add(close);
        frame.add(membersPanel);
        membersPanel.setVisible(false);

    }

    void update()
    {
        membersArea.setText("");
        for (String s : members)
        {
            membersArea.append(s + '\n');
        }
    }

    class CloseListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            membersPanel.setVisible(false);
            membersArea.setVisible(false);
            close.setVisible(false);
            chatWindow.setVisible(true);
            membersButton.setVisible(true);
            chatWindow.remove(membersPanel);
            frame.add(curPanel).setLocation(0,0);
        }
    }
        class MemberListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                chatWindow.setVisible(false);
                frame.remove(curPanel);
                frame.add(membersPanel).setLocation(0,0);
                membersPanel.setVisible(true);
                membersArea.setVisible(true);
                close.setVisible(true);
                membersButton.setVisible(false);

            }
        }
}
