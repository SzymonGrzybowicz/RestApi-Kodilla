package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTestSuite {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> list = new ArrayList<>();
        list.add(new TrelloBoard("0", "test", new ArrayList<>()));
        list.add(new TrelloBoard("1", "name 1", new ArrayList<>()));
        list.add(new TrelloBoard("2", "test", new ArrayList<>()));
        list.add(new TrelloBoard("3", "name 2", new ArrayList<>()));
        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(list);
        //Then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("name 2", result.get(1).getName());
    }
}
