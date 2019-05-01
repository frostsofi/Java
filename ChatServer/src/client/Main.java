package client;

import java.io.IOException;

public class Main {

        public static void main(String[] args) throws IOException {
            ChatClient client = new ChatClient();
            client.start();
            ChatClient client1 = new ChatClient();
            client1.start();
            ChatClient client3 = new ChatClient();
            client3.start();
        }
}
