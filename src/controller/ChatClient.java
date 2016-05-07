package controller;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Manutea on 06/05/2016.
 */
public class ChatClient {
    public Socket socket;
    String url = "localhost";
    public static boolean stop;
    private String name;
    int port = 8888;

    public ChatClient(String n) throws IOException {
        //nullify
        socket = null;
        stop = false;

        name = n;

        //init socket, in and out stream
        socket = new Socket(url,port);
    }

    public void close() throws IOException {
        socket.close();
    }
}
