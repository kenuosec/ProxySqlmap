package proxy.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import proxy.module.Config;
import proxy.module.DomainList;

public class AddDomainController {

    @FXML
    private TextField Domain;

    public void Add(){
        if (Domain.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("错误提示:");
            alert.setHeaderText("表单不完整");
            alert.setContentText("请将表单填写完整!");
            alert.showAndWait();
        }
        Config.Domain.add(new DomainList(Domain.getText()));
        Config.AddStage.close();
    }

}
