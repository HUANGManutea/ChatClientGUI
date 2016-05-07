package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Manutea on 07/05/2016.
 */
public class Message {
    private StringProperty message;
    private StringProperty ownerName;

    public Message(String name, String text){
        message = new SimpleStringProperty(text);
        ownerName = new SimpleStringProperty(name);
    }

    public StringProperty getMessage(){
        return message;
    }

    public StringProperty getOwnerName() {
        return ownerName;
    }
}
