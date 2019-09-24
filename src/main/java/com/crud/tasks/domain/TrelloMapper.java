package com.crud.tasks.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(trelloBoard ->
                        new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard -> new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToList(List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(t -> new TrelloList(t.getId(), t.getName(), t.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed))
                .collect(Collectors.toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}
