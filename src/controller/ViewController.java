package controller;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.Discussion;
import model.Message;
import Main.Main;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Manutea on 07/05/2016.
 */
public class ViewController implements Initializable{
    @FXML TabPane tabPane;
    @FXML Tab discussTab;
    @FXML TextArea textArea;
    @FXML VBox discussionGUI;
    public static Discussion discussion;
    public static ChatClient cc;
    public static WorkerReader wr;
    public static WorkerWriter ww;

    public ViewMessage createMessageGUI(Message m){
        ViewMessage vm = new ViewMessage(m.getOwnerName().get(),m.getMessage().get());
        return vm;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cc = null;
        discussion = new Discussion();

        //network
        try {
            cc = new ChatClient(Main.name);
            wr = new WorkerReader(cc);
            Thread thr = new Thread(wr);
            thr.start();
            ww = new WorkerWriter(cc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //managing message input
        discussion.getMessages().addListener(new ListChangeListener<Message>() {
            @Override
            public void onChanged(Change<? extends Message> c) {
                if(c.next()){
                    if(c.wasAdded()){
                        //instantiate new message in GUI and OUT if mine
                        for(Message m : c.getAddedSubList()){
                            //GUI
                            ViewMessage vm = createMessageGUI(m);
                            discussionGUI.getChildren().add(vm);
                            //OUT
                            if(m.getOwnerName().get().equals(Main.name)){
                                try {
                                    ww.out(m.getMessage().get());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        });

        //filter keyboard
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.isShiftDown() && e.getCode() == KeyCode.ENTER){
                    textArea.setText(textArea.getText()+"\n");
                    textArea.positionCaret(textArea.getText().length());
                }else if(e.getCode() == KeyCode.ENTER){
                    if(!textArea.getText().equals("")){
                        Message m = new Message(Main.name, textArea.getText());
                        discussion.getMessages().add(m);
                        textArea.clear();
                    }
                }else if(e.getCode() == KeyCode.BACK_SPACE){
                    textArea.deletePreviousChar();
                }
                e.consume();
            }
        });


    }
}
