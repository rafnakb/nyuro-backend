package com.nyuro.taskservice.services;

import com.nyuro.taskservice.exceptions.ObjectNotFoundException;
import com.nyuro.taskservice.repositories.TaskRepository;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task findById(Integer id) {
        Optional<Task> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Task not found!"));
    }

    public Task create(Task obj) {
        return repository.save(obj);
    }

    public Task update(Integer id, Task updateObj) {
        Task currentObj = findById(id);
        currentObj = updateObj;
        return repository.save(currentObj);
    }
}
