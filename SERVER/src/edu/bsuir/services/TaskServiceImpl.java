package edu.bsuir.services;

import edu.bsuir.dao.DAO;
import edu.bsuir.dao.TaskDAOImpl;
import edu.bsuir.entities.Task;
import java.util.List;

public class TaskServiceImpl implements Service<Task>{

    DAO daoService = new TaskDAOImpl();

    @Override
    public Task findEntity(int id) {
        Task task = (Task) daoService.findByid(id);
        return task;
    }

    @Override
    public void saveEntity(Task task) {
        daoService.save(task);
    }

    @Override
    public void deleteEntity(Task task) {
        daoService.delete(task);
    }

    @Override
    public void updateEntity(Task task) {
        daoService.update(task);
    }

    @Override
    public List<Task> findAllEntities() {
        return daoService.findAll();
    }
}
