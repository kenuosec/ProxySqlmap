package proxy.module;

import com.github.monkeywie.proxyee.server.HttpProxyServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Config {

    public static Stage AddStage;
    public static Stage SetStage;
    public static HttpProxyServer httpProxyServer;
    public static ObservableList<DomainList> Domain = FXCollections.observableArrayList();
    public static ObservableList<TaskTable> TaskList = FXCollections.observableArrayList();

    public static Label SqlmapApi;
    public static Label SqlmapToken;
    public static Label BindPort;
}
