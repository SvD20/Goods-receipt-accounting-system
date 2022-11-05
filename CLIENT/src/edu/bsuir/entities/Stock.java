package edu.bsuir.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;


public class Stock {

    @JsonIgnore
    private int idStock;

    private String name;
    private int size;

    @JsonIgnore
    private List<Product> productList;

    public Stock() {
    }

    public Stock( String name, int size) {
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
