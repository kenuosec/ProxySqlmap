package proxy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.CertDownIntercept;
import com.github.monkeywie.proxyee.intercept.common.FullRequestIntercept;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import proxy.module.Config;
import proxy.module.SqlMap;
import proxy.module.TaskTable;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;


public class Layout {
    @FXML
    private Label Port;
    @FXML
    private Label Api;
    @FXML
    private Label Token;

    @FXML
    private TableView<TaskTable> taskTable;
    @FXML
    private TableColumn<TaskTable,String> TaskID;

    @FXML
    private TableColumn<TaskTable,String> Url;
    @FXML
    private TableColumn<TaskTable,String> TaskStatus;
    @FXML
    private TableColumn<TaskTable,String> IsSql;
    @FXML
    private TableColumn<TaskTable,String> StartTime;

    @FXML
    private void initialize(){
        Config.SqlmapApi = this.Api;
        Config.BindPort = this.Port;
        Config.SqlmapToken = this.Token;
        TaskID.setCellValueFactory(cell -> cell.getValue().TaskIdProperty());
        Url.setCellValueFactory(cell->cell.getValue().UrlProperty());
        TaskStatus.setCellValueFactory(cell->cell.getValue().TaskStatusProperty());
        IsSql.setCellValueFactory(cell->cell.getValue().IsSqlProperty());
        StartTime.setCellValueFactory(cell->cell.getValue().StartTimeProperty());
        taskTable.setItems(Config.TaskList);
        class ForThread extends Thread{

            public Boolean RequestStatus(String TaskId){
                String Url = Config.SqlmapApi.getText()+"/scan/"+TaskId+"/status";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(Url).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()){
                        String body = response.body().string();
                        JSONObject jb = JSONObject.parseObject(body);
                        if (jb.getString("status").equals("terminated")){
                            return true;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            public Boolean RequestIsSql(String TaskId){
                String Url = Config.SqlmapApi.getText()+"/scan/"+TaskId+"/data";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(Url).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()){
                        String body = response.body().string();
                        JSONObject jb = JSONObject.parseObject(body);
                        if (jb.getJSONArray("data").isEmpty()){
                            return true;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            public void run() {
                while (true){
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (Config.TaskList.size() <= 0){

                        continue;
                    }
                    for (int i = 0;i < Config.TaskList.size();i++){
                        if (Config.TaskList.get(i).getTaskStatus().equals("正在扫描")){

                            if (RequestStatus(Config.TaskList.get(i).getTaskID())){
                                Config.TaskList.get(i).setTaskStatus("扫描完成");
                            }else{
                                continue;
                            }
                            if (RequestIsSql(Config.TaskList.get(i).getTaskID())){
                                Config.TaskList.get(i).setIsSql("不存在");
                            }else{
                                Config.TaskList.get(i).setIsSql("存在");
                            }

                        }
                    }
                }
            }
        }
        new ForThread().start();
    }

    @FXML
    public void Setting(){
        Seting seting = new Seting();
        Config.SetStage = new Stage();
        Config.SetStage.initModality(Modality.APPLICATION_MODAL);
        seting.start(Config.SetStage);
    }

    @FXML
    public void List(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        List list = new List();
        list.start(stage);
    }

    @FXML
    public void AboutMe(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("AbotMe");
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("AbotMe.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    @FXML
    public void Action(){
        Config.httpProxyServer = new HttpProxyServer();
        class BindPortThread extends Thread{
            public void run(){
                HttpProxyServerConfig config =  new HttpProxyServerConfig();
                config.setHandleSsl(true);
                Config.httpProxyServer.serverConfig(config).proxyInterceptInitializer(
                        new HttpProxyInterceptInitializer(){
                            @Override
                            public void init(HttpProxyInterceptPipeline pipeline){
                                pipeline.addLast(new CertDownIntercept());
                                pipeline.addLast(new FullRequestIntercept() {
                                    @Override
                                    public boolean match(HttpRequest httpRequest, HttpProxyInterceptPipeline httpProxyInterceptPipeline) {
                                        for(int i = 0;i< Config.Domain.size();i++){
                                            if(Pattern.matches(Config.Domain.get(i).getDomain(),httpRequest.headers().get("Host"))){
                                                if (httpRequest.method().toString().equals("GET") && httpRequest.uri().indexOf(".js") > -1 && httpRequest.uri().indexOf(".css") > -1 && httpRequest.uri().indexOf(".pdf") > -1 && httpRequest.uri().indexOf(".xlsx") > -1){
                                                    return false;
                                                }
                                                if (httpRequest.method().toString().equals("GET") && httpRequest.uri().indexOf("?") > -1 && httpRequest.uri().indexOf("=") > -1){
                                                    return true;
                                                }
                                                if (httpRequest.method().toString().equals("POST")){
                                                    return true;
                                                }
                                            }
                                        }
                                        return false;
                                    }

                                    @Override
                                    public void handleRequest(FullHttpRequest httpRequest, HttpProxyInterceptPipeline pipeline) {
                                        String Url = pipeline.getRequestProto().getSsl() ? "https://"+pipeline.getRequestProto().getHost()+":"+pipeline.getRequestProto().getPort()+httpRequest.uri(): "http://"+pipeline.getRequestProto().getHost()+":"+ pipeline.getRequestProto().getPort()+httpRequest.uri();
                                        String Cookie = httpRequest.headers().get("Cookie");
                                        String Referer = httpRequest.headers().get("Referer");
                                        if (httpRequest.method().toString().equals("GET")){
                                            class SqlMapThread extends Thread{
                                                public void run(){
                                                    SqlMap.StartScanGet(Url,Cookie,Referer);
                                                }
                                            }
                                            new SqlMapThread().start();
                                        }
                                        if (httpRequest.method().toString().equals("POST")){
                                            class SqlMapThread extends Thread{
                                                public void run(){
                                                    SqlMap.StartScanPost(Url,httpRequest.content().toString(Charset.defaultCharset()),Cookie,Referer);
                                                }
                                            }
                                            new SqlMapThread().start();
                                        }
                                    }
                                });
                            }


                        }
                ).start(Integer.parseInt(Config.BindPort.getText()));
            }
        }
        new BindPortThread().start();
    }

    @FXML
    public void Stop(){
        Config.httpProxyServer.close();
    }

    public void OpenBro(){

        if (taskTable.getSelectionModel().getSelectedIndex()<0){
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = null;
            try {
                uri = new URI(Config.SqlmapApi.getText()+"/scan/"+taskTable.getSelectionModel().getSelectedItem().getTaskID()+"/data");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ClearBcz(){
        if (Config.TaskList.size() <= 0){
            return;
        }

        for (int i = 0;i < Config.TaskList.size();i++){
            if (Config.TaskList.get(i).getIsSql().equals("不存在")){


                String Url = Config.SqlmapApi.getText()+"/task/"+Config.TaskList.get(i).getTaskID()+"/delete";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(Url).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()){

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Config.TaskList.remove(i);

            }
        }
    }
}
