package edu.bsuir.dao;

import edu.bsuir.entities.Stock;
import edu.bsuir.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import java.util.List;

public class StockDAOImpl implements DAO<Stock>{

    @Override
    public void save(Stock stock) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Stock stock) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Stock stock) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Stock findByid(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Stock stock = session.get(Stock.class,id);
        session.close();
        return stock;
    }

    @Override
    public List<Stock> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Stock> stocks = (List<Stock>)session.createQuery("from Stock ").getResultList();
        session.close();
        return stocks;
    }
}
