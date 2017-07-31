package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;



import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group(); // grup de elemente

        Text txt = new Text("Sup?");
        txt.setFont(new Font("Papyrus", 80));

        TextField nameFld = new TextField();

        Label label = new Label();
        label.setText("Name: ");

        Button btn = new Button();
        btn.setText("Say Sup!");

        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(nameFld, 1, 0);
        gridPane.add(btn, 1,1);
        gridPane.setHgap(20); // gutter, locul liber dintre collumns
        //gridPane.setGridLinesVisible(true); // arata liniile tabelului

        btn.setOnAction(event -> System.out.println("Sup " + nameFld.getText() + " !!!")); //onAction:click prin lambda

        VBox box = new VBox();
        box.getChildren().addAll(txt, gridPane); // adaugarea in containter gen vertical layout

        root.getChildren().add(box); // atribuirea layoutului in root

        primaryStage.setTitle("Sup");
        primaryStage.setScene(new Scene(root, 300, 275)); // adaugarea grupului de elemente in scena
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
