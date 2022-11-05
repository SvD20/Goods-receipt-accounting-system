package edu.bsuir.entities;

import javafx.beans.property.*;

public class StockProperty {

    private StringProperty name;
    private IntegerProperty size;

    public StockProperty(Stock stock) {
        name = new SimpleStringProperty(stock.getName());
        size = new SimpleIntegerProperty(stock.getSize());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getSize() {
        return size.get();
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size.set(size);
    }
}
