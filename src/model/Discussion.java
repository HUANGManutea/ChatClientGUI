package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Manutea on 07/05/2016.
 */
public class Discussion {
    private ObservableList<Message> messages;

    public Discussion(){
        messages = FXCollections.observableArrayList();
    }

    public ObservableList<Message> getMessages(){
        return messages;
    }
}
