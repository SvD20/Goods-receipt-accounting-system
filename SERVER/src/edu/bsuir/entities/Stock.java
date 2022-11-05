package edu.bsuir.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStock")
    @JsonIgnore
    private int idStock;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private int size;

    @OneToMany(mappedBy = "stock")
    @JsonIgnore
    private List<Product> productList;

    public Stock() {
    }

    public Stock(int idStock, String name, int size) {
        this.idStock = idStock;
        this.name = name;
        this.size = size;
    }

    public void addProductToStock(Product product){
        if(productList == null){
            productList = new ArrayList<>();
        }
        productList.add(product);
        product.setStock(this);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "idStock=" + idStock +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
