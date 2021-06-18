package proxy.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import proxy.module.Config;

public class SetController {

    @FXML
    private TextField SqlmapApi;
    @FXML
    private TextField AdminToken;
    @FXML
    private TextField Port;

    @FXML
    private void initialize(){

    }

    @FXML
    public void Action(){
        if(SqlmapApi.getText().isEmpty() | AdminToken.getText().isEmpty() | Port.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("错误提示:");
            alert.setHeaderText("表单不完整");
            alert.setContentText("请将表单填写完整!");
            alert.showAndWait();
            return;
        }

        Config.SqlmapApi.setText(this.SqlmapApi.getText());
        Config.SqlmapToken.setText(this.AdminToken.getText());
        Config.BindPort.setText(this.Port.getText());
        Config.SetStage.close();
    }

}
