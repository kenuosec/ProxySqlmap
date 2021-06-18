package proxy.module;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskTable {

    private final StringProperty TaskID;
    private final StringProperty Url;
    private final StringProperty TaskStatus;
    private final StringProperty IsSql;
    private final StringProperty StartTime;

    public TaskTable(String TaskID,String Url,String TaskStatus,String IsSql,String StartTime){

        this.TaskID = new SimpleStringProperty(TaskID);
        this.Url = new SimpleStringProperty(Url);
        this.TaskStatus = new SimpleStringProperty(TaskStatus);
        this.IsSql = new SimpleStringProperty(IsSql);
        this.StartTime = new SimpleStringProperty(StartTime);

    }

    public String getIsSql() {
        return IsSql.get();
    }

    public String getTaskID(){
        return TaskID.get();
    }

    public String getUrl(){
        return Url.get();
    }

    public String getTaskStatus(){
        return TaskStatus.get();
    }

    public String getStartTime(){
        return StartTime.get();
    }

    public void setTaskID(String TaskID){
        this.TaskID.set(TaskID);
    }

    public void setUrl(String Url){
        this.Url.set(Url);
    }

    public void setTaskStatus(String TaskStatus){
        this.TaskStatus.set(TaskStatus);
    }

    public void setIsSql(String IsSql){
        this.IsSql.set(IsSql);
    }

    public void setStartTime(String StartTime){
        this.StartTime.set(StartTime);
    }

    public StringProperty TaskIdProperty(){
        return TaskID;
    }

    public StringProperty UrlProperty(){
        return Url;
    }

    public StringProperty IsSqlProperty(){
        return IsSql;
    }

    public StringProperty TaskStatusProperty(){
        return TaskStatus;
    }

    public StringProperty StartTimeProperty(){
        return StartTime;
    }

}
