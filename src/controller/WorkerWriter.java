package controller;

import Main.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Manutea on 06/05/2016.
 */
public class WorkerWriter{
    Socket s;
    PrintWriter out;
    ChatClient chatClient;

    public WorkerWriter(ChatClient cc) throws IOException {
        s= null;
        out = null;
        chatClient = cc;
        s = cc.socket;
        out = new PrintWriter(s.getOutputStream(), true);
    }

    public void out(String s) throws IOException{
        out.println(Main.name+";"+s);
        out.flush();
    }
}
