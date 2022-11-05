package edu.bsuir.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskProperty {

    private StringProperty date;
    private StringProperty type;
    private StringProperty state;

    public TaskProperty(Task task) {
        date = new SimpleStringProperty(task.getDate());
        type = new SimpleStringProperty(task.getType());
        state = new SimpleStringProperty(task.getState());
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }
}
