package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {
    @FXML private TextField firstName;

    public void handleSaySup(ActionEvent actionEvent) {
        System.out.println("Sup " + firstName.getText() + " !!!");
    }
}
