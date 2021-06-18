package proxy.module;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlMap {

    private static String GetToken(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Config.SqlmapApi.getText()+"/task/new").build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String body = response.body().string();
                JSONObject jb = JSONObject.parseObject(body);
                if (jb.getBoolean("success")){
                    return jb.getString("taskid");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Null";
    }

    public static Boolean StartScanGet(String url,String cookie,String referer){
        String Token = GetToken();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("url",url);
        json.put("cookie",cookie);
        json.put("referer",referer);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
        Request request = new Request.Builder().url(Config.SqlmapApi.getText()+"/scan/"+Token+"/start").post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            JSONObject jb = JSONObject.parseObject(body);
            if (jb.getBoolean("success")){
                Config.TaskList.add(new TaskTable(Token,url,"正在扫描","正在扫描",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean StartScanPost(String url,String data,String cookie,String referer){
        String Token = GetToken();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("url",url);
        json.put("data",data);
        json.put("cookie",cookie);
        json.put("referer",referer);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
        Request request = new Request.Builder().url(Config.SqlmapApi.getText()+"/scan/"+Token+"/start").post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            JSONObject jb = JSONObject.parseObject(body);
            if (jb.getBoolean("success")){
                Config.TaskList.add(new TaskTable(Token,url,"正在扫描","正在扫描",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
