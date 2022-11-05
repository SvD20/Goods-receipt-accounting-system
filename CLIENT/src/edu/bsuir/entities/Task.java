package edu.bsuir.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Task {

    @JsonIgnore
    private int idTask;
    private String date;
    private String type;
    private String state;

    public Task() {
    }

    public Task(String date, String type, String state) {
        this.date = date;
        this.type = type;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
