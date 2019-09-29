package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Before
    public void init() {
        prepareData();
    }

    @Test
    public void mapToBoards() {
        //Given
        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("Board Dto Name 2", result.get(1).getName());
        Assert.assertEquals(3, result.get(2).getLists().size());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("Board Name 2", result.get(1).getName());
        Assert.assertEquals(3, result.get(2).getLists().size());
    }

    @Test
    public void mapToList() {
        //Given
        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("Test Dto List 2", result.get(1).getName());
        Assert.assertEquals("03", result.get(2).getId());
    }

    @Test
    public void mapToListDto() {
        //Given
        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("Test List 2", result.get(1).getName());
        Assert.assertEquals("03", result.get(2).getId());
    }

    @Test
    public void mapToCardDto() {
        //Given
        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard1);
        //Then
        Assert.assertEquals("Card 1", result.getName());
        Assert.assertEquals("Test description", result.getDescription());
        Assert.assertEquals("2",result.getPos());
        Assert.assertEquals("1", result.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto1);
        //Then
        Assert.assertEquals("Card Dto 1", result.getName());
        Assert.assertEquals("Test Dto description", result.getDescription());
        Assert.assertEquals("2",result.getPos());
        Assert.assertEquals("1", result.getListId());
    }


    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(0L, "test Title", "test content");
        //When
        Task result = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(new Long(0), result.getId());
        Assert.assertEquals("test Title", result.getTitle());
        Assert.assertEquals("test content", result.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(0L, "test Title", "test content");
        //When
        TaskDto result = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(new Long(0), result.getId());
        Assert.assertEquals("test Title", result.getTitle());
        Assert.assertEquals("test content", result.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> list = new ArrayList<>();
        list.add(new Task(0L, "test1", "content1"));
        list.add(new Task(1L, "test2", "content2"));
        list.add(new Task(2L, "test3", "content3"));
        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(list);
        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("test2", result.get(1).getTitle());
        Assert.assertEquals("content3", result.get(2).getContent());
    }

    private void prepareData() {
        trelloBoardDtos = new ArrayList<>();
        trelloListDtos = new ArrayList<>();
        trelloListDto1 = new TrelloListDto("01", "Test Dto List 1", true);
        trelloListDto2 = new TrelloListDto("02", "Test Dto List 2", false);
        trelloListDto3 = new TrelloListDto("03", "Test Dto List 3", true);
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        trelloListDtos.add(trelloListDto3);
        trelloBoardDto1 = new TrelloBoardDto("Board Dto Name 1", "1", trelloListDtos);
        trelloBoardDto2 = new TrelloBoardDto("Board Dto Name 2", "2", trelloListDtos);
        trelloBoardDto3 = new TrelloBoardDto("Board Dto Name 3", "3", trelloListDtos);
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        trelloBoardDtos.add(trelloBoardDto3);
        trelloCardDto1 = new TrelloCardDto("Card Dto 1", "Test Dto description", "2", "1");

        trelloBoards = new ArrayList<>();
        trelloLists = new ArrayList<>();
        trelloList1 = new TrelloList("01", "Test List 1", true);
        trelloList2 = new TrelloList("02", "Test List 2", false);
        trelloList3 = new TrelloList("03", "Test List 3", true);
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        trelloBoard1 = new TrelloBoard("Board Name 1", "1", trelloLists);
        trelloBoard2 = new TrelloBoard("Board Name 2", "2", trelloLists);
        trelloBoard3 = new TrelloBoard("Board Name 3", "3", trelloLists);
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        trelloBoards.add(trelloBoard3);
        trelloCard1 = new TrelloCard("Card 1", "Test description", "2", "1");
    }

    private ArrayList<TrelloBoardDto> trelloBoardDtos;
    private TrelloListDto trelloListDto1;
    private TrelloListDto trelloListDto2;
    private TrelloListDto trelloListDto3;
    private ArrayList<TrelloListDto> trelloListDtos;
    private TrelloBoardDto trelloBoardDto1;
    private TrelloBoardDto trelloBoardDto2;
    private TrelloBoardDto trelloBoardDto3;
    private TrelloCardDto trelloCardDto1;

    private ArrayList<TrelloBoard> trelloBoards;
    private TrelloList trelloList1;
    private TrelloList trelloList2;
    private TrelloList trelloList3;
    private ArrayList<TrelloList> trelloLists;
    private TrelloBoard trelloBoard1;
    private TrelloBoard trelloBoard2;
    private TrelloBoard trelloBoard3;
    private TrelloCard trelloCard1;
}
