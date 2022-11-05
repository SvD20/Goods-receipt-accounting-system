package edu.bsuir.services;

import edu.bsuir.dao.DAO;
import edu.bsuir.dao.StockDAOImpl;
import edu.bsuir.entities.Stock;

import java.util.List;

public class StockServiceImpl implements Service<Stock>{

    DAO daoService = new StockDAOImpl();

    @Override
    public Stock findEntity(int id) {
        Stock stock = (Stock) daoService.findByid(id);
        return stock;
    }

    @Override
    public void saveEntity(Stock stock) {
        daoService.save(stock);
    }

    @Override
    public void deleteEntity(Stock stock) {
        daoService.delete(stock);
    }

    @Override
    public void updateEntity(Stock stock) {
        daoService.update(stock);
    }

    @Override
    public List<Stock> findAllEntities() {
        return daoService.findAll();
    }
}
