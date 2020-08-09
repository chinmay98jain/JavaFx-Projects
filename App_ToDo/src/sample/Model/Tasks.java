package sample.Model;

import java.sql.Timestamp;

public class    Tasks {
    private int userId;
    private int taskId;
    private Timestamp datecreated;
    private String description;
    private String task_work;


    public Tasks() {
    }

    public Tasks(Timestamp datecreated, String description, String task_work, int userId) {
        this.datecreated = datecreated;
        this.description = description;
        this.task_work = task_work;
        this.userId = userId;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask_work() {
        return task_work;
    }

    public void setTask_work(String task_work) {
        this.task_work = task_work;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
