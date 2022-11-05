package edu.bsuir.services;

import edu.bsuir.dao.DAO;
import edu.bsuir.dao.ProductDAOImpl;
import edu.bsuir.entities.Product;
import java.util.List;

public class ProductServiceImpl implements Service<Product>{

    DAO daoService = new ProductDAOImpl();

    @Override
    public Product findEntity(int id) {
        Product product = (Product) daoService.findByid(id);
        return product;
    }

    @Override
    public void saveEntity(Product product) {
        daoService.save(product);
    }

    @Override
    public void deleteEntity(Product product) {
        daoService.delete(product);
    }

    @Override
    public void updateEntity(Product product) {
        daoService.update(product);
    }

    @Override
    public List<Product> findAllEntities() {
       return daoService.findAll();
    }
}
