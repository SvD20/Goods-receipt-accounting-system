package edu.bsuir.dao;

import java.util.List;

public interface DAO <T>{

    void save(T obj);
    void update(T obj);
    void delete(T obj);
    T findByid(int id);
    List<T> findAll();

}
