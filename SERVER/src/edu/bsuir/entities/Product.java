package edu.bsuir.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    @JsonIgnore
    private int idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private int count;

    @Column(name = "state")
    private String state;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_stock")
    @JsonIgnore
    private Stock stock;

    public Product() {
    }

    public Product(int idProduct,String name,int count,String state ) {
        this.idProduct = idProduct;
        this.name = name;
        this.count = count;
        this.state = state;
    }

    public Product(int idProduct, String name, int count, String state, Stock stock) {
        this.idProduct = idProduct;
        this.name = name;
        this.count = count;
        this.state = state;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", state='" + state + '\'' +
                ", stock=" + stock +
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
