package controller;

import Main.Main;
import javafx.application.Platform;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Manutea on 06/05/2016.
 */
public class WorkerReader implements Runnable{
    Socket s;
    BufferedReader b;
    ChatClient chatClient;

    public WorkerReader(ChatClient cc) throws IOException {
        chatClient = cc;
        s = cc.socket;
        b = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        String line;
        try{
            while (!chatClient.stop&&((line = b.readLine())!=null)){
                String[] received = line.split(";");
                if(!received[0].equals(Main.name)){
                    Message m = new Message(received[0],received[1]);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ViewController.discussion.getMessages().add(m);
                        }
                    });
                }
            }
        }catch(IOException e){
            System.out.println("socket closed");
            chatClient.stop = true;
        }
    }
}
