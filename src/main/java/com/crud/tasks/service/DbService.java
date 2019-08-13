package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(final Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> getTask(final Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(final Long id) {
            taskRepository.deleteById(id);
    }
}
