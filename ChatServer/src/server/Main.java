package server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        try {
            server.addMembers();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
