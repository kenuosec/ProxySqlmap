package proxy.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proxy.Main;

import java.io.IOException;

public class Seting extends Application {

    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Seting.fxml"));
            primaryStage.setTitle("配置");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
