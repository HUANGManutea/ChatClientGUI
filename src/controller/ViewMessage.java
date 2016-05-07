package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

/**
 * Created by Manutea on 07/05/2016.
 */
public class ViewMessage extends VBox{
    @FXML Label viewMessageName;
    @FXML TextFlow viewMessageContent;

    public ViewMessage(String n, String c){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewMessage.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();

            viewMessageName.setText(n);
            Text text = new Text(c);
            viewMessageContent.getChildren().add(text);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
