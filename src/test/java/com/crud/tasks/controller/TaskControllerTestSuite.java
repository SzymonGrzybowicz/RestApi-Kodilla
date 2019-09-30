package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    private static final String CONTROLLER_ADDRESS = "/v1/task/";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Before
    public void init() {
        prepareData();
    }

    @Test
    public void getTasks() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(get(CONTROLLER_ADDRESS + "getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[1].title", is("t1")))
                .andExpect(jsonPath("$[2].content", is("c2")));
    }

    @Test
    public void getTask() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(
                get(CONTROLLER_ADDRESS + "getTask")
                        .param("taskId", "0")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void deleteTask() throws Exception{
        //Given
        //When
        mockMvc.perform(
                delete(CONTROLLER_ADDRESS + "deleteTask")
                .param("taskId", "0"));
        //Then
        Mockito.verify(dbService).deleteTask(0L);
    }

    @Test
    public void updateTask() throws Exception{
        //Given
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(
                put(CONTROLLER_ADDRESS + "updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void createTask() throws Exception{
        //Given
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When
        mockMvc.perform(
                post(CONTROLLER_ADDRESS + "createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
        );
        //Then
        Mockito.verify(dbService).saveTask(task);
    }

    private void prepareData() {
        tasks.add(new Task(0L, "t0", "c0"));
        tasks.add(new Task(1L, "t1", "c1"));
        tasks.add(new Task(2L, "t2", "c2"));

        Mockito.when(dbService.getAllTasks()).thenReturn(tasks);
        Mockito.when(dbService.getTask(0L)).thenReturn(Optional.of(task));
        Mockito.when(dbService.saveTask(Mockito.any())).thenReturn(task);

        Mockito.when(taskMapper.mapToTaskDtoList(Mockito.any()))
                .thenReturn(tasks.stream()
                        .map(t -> new TaskDto(t.getId(), t.getTitle(), t.getContent()))
                        .collect(Collectors.toList())
                );

        Mockito.when(taskMapper.mapToTaskDto(Mockito.any()))
                .thenReturn(taskDto);
        Mockito.when(taskMapper.mapToTask(Mockito.any()))
                .thenReturn(task);

    }

    private Task task = new Task(0L, "title", "content");
    private List<Task> tasks = new ArrayList<>();
    private TaskDto taskDto = new TaskDto(0L, "title", "content");
}