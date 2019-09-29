package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    AdminConfig adminConfig;

    @Mock
    SimpleEmailService simpleEmailService;

    @Test
    public void fetchTrelloBoards() {
        //Given
        TrelloBoardDto board1 = new TrelloBoardDto("name1", "0", new ArrayList<>());
        TrelloBoardDto board2 = new TrelloBoardDto("name2", "1", new ArrayList<>());
        List<TrelloBoardDto> list = new ArrayList<>();
        list.add(board1);
        list.add(board2);
        Mockito.when(trelloClient.getTrelloBoards()).thenReturn(list);
        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("name2", result.get(1).getName());
    }

    @Test
    public void createdTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1", "1", "0");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("0", "created name", "testURL");
        Mockito.when(trelloClient.createnewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        Mockito.when(adminConfig.getAdminMail()).thenReturn("test");
        Mockito.doNothing().when(simpleEmailService).send(Mockito.any());
        //When
        CreatedTrelloCardDto result = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals("0", result.getId());
        Assert.assertEquals("created name", result.getName());
        Assert.assertEquals("testURL", result.getShortUrl());
    }
}
