package com.library.controller;

import com.library.model.Author;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//todo:User     изменять данные/найти список записей!!!
//todo:Admin    добавлять картточку/удаляеться только при удалении User/выводит карточки/найти по id?имени/ найти список записей!!! у User

@RestController
@RequestMapping("/readers-cards")
public class ReaderCardController {

    @Autowired
    private IReaderCardService readerCardService;

    public ReaderCard updateReaderCard(Integer idReaderCard, ReaderCard newDataReaderCard) {
        return this.readerCardService.updateReaderCard(idReaderCard, newDataReaderCard);
    }

    public ReaderCard deleteReaderCardById(Integer idReaderCard) {
        return this.readerCardService.deleteReaderCardById(idReaderCard);
    }


    public ReaderCard findReaderCardById(Integer idReaderCard) {
        return this.readerCardService.findReaderCardById(idReaderCard);
    }

    //todo: подумать над тем, как лучше назвать запрос
    @GetMapping("/FullName")
    public ReaderCard findReaderCardByFullName(@RequestBody ReaderCard readerCard) {
        return this.readerCardService.findReaderCardByFullName(readerCard);
    }

    public List<ReaderCard> findReadersCardsList() {
        return this.readerCardService.findReadersCardsList();
    }

    //todo: подумать как реализовать по String? enum compor? принимать готовый список и compor?
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return this.readerCardService.findSortReadersCardsList(sortingComparator);
    }
}
