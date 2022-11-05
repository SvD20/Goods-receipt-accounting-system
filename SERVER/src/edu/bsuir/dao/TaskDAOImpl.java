package edu.bsuir.dao;

import edu.bsuir.entities.Task;
import edu.bsuir.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import java.util.List;

public class TaskDAOImpl implements DAO<Task>{
    @Override
    public void save(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Task findByid(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Task task = session.get(Task.class,id);
        session.close();
        return task;
    }

    @Override
    public List<Task> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> tasks = (List<Task>)session.createQuery("from Task ").getResultList();
        session.close();
        return tasks;
    }
}
