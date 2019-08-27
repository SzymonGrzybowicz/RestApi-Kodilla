package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        printTrelloBoards(trelloBoards);
    }

    private void printTrelloBoards(List<TrelloBoardDto> list){
        list.stream()
                .filter(n -> n.getName().toLowerCase().contains("kodilla"))
                .filter(n -> !n.getId().isEmpty())
                .forEach(n-> System.out.println("Id: " + n.getId() + "name: " + n.getName()));
    }
}
