package edu.bsuir.services;

import java.util.List;


public interface Service <T>{

    T findEntity(int id);

    void saveEntity(T obj);

    void deleteEntity(T obj);

    void updateEntity(T obj);

    List<T> findAllEntities();

}
