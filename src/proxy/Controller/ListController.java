package proxy.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proxy.module.Config;
import proxy.module.DomainList;

import java.io.IOException;

public class ListController {

    @FXML
    private TableView<DomainList> DomainTab;

    @FXML
    private TableColumn<DomainList,String> DomainName;

    @FXML
    private void initialize() {
        DomainName.setCellValueFactory(cellData -> cellData.getValue().domainProperty());
        DomainTab.setItems(Config.Domain);
    }

    @FXML
    public void add(){
        Config.AddStage = new Stage();
        Config.AddStage.setTitle("域名添加,请使用正则表达式:");
        Config.AddStage.initModality(Modality.APPLICATION_MODAL);
        try {
            Config.AddStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("AddDomain.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config.AddStage.show();
    }

    @FXML
    public void delete(){
        if(DomainTab.getSelectionModel().getSelectedIndex() >= 0){
            DomainTab.getItems().remove(DomainTab.getSelectionModel().getSelectedIndex());
        }
    }

}
