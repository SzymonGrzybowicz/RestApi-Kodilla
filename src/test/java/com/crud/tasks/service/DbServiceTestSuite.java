package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(0L, "test1", "content1"));
        tasks.add(new Task(1L, "test2", "content2"));
        tasks.add(new Task(2L, "test3", "content3"));
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(new Long(0), result.get(0).getId());
        Assert.assertEquals("test2", result.get(1).getTitle());
        Assert.assertEquals("content3", result.get(2).getContent());
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(0L, "test", "content");
        Mockito.when(taskRepository.save(task)).thenReturn(new Task(99L, "ok", "good"));
        //When
        Task result = dbService.saveTask(task);
        //Then
        Assert.assertEquals(new Long(99), result.getId());
        Assert.assertEquals("ok", result.getTitle());
        Assert.assertEquals("good", result.getContent());
    }

    @Test
    public void getTask() {
        //Given
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L, "title", "content")));
        //When
        Optional<Task> result = dbService.getTask(1L);
        //Then
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("title", result.get().getTitle());
        Assert.assertEquals("content", result.get().getContent());
    }

    @Test
    public void deleteTask() {
        //Given
        //When
        dbService.deleteTask(1L);
        //Then
        Mockito.verify(taskRepository).deleteById(1L);
    }
}