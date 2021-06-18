package proxy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controller/Layout.fxml"));
        primaryStage.setTitle("ProxySqlmap  by:小透明");
        primaryStage.getIcons().add(new Image("/proxy/favicon.ico"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void init(){

        System.out.println("Hello Word!");

    }

    public void stop(){
       System.exit(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
