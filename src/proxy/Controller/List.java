package proxy.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class List extends Application {

    @Override
    public void start(Stage stage){
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("List.fxml"));
            stage.setTitle("ๅๅๅ่กจ");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
