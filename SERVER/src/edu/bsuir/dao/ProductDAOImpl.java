package edu.bsuir.dao;

import edu.bsuir.entities.Product;
import edu.bsuir.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import java.util.List;

public class ProductDAOImpl implements DAO<Product>{

    @Override
    public void save(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Product findByid(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class,id);
        session.close();
        return product;
    }

    @Override
    public List<Product> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Product> products = (List<Product>)session.createQuery("from Product ").getResultList();
        session.close();
        return products;
    }
}
