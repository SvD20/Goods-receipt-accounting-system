package edu.bsuir.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Product {

    @JsonIgnore
    private int idProduct;

    private String name;

    private int count;

    private String state;

    @JsonIgnore
    private Stock stock;

    public Product() {
    }

    public Product(String name, int count, String state ) {
        this.name = name;
        this.count = count;
        this.state = state;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", state='" + state + '\'' +
                '}';
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
