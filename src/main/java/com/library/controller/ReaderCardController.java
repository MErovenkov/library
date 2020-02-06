package com.library.controller;

import com.library.dao.ReaderCardDao;
import com.library.dto.EntryDto;
import com.library.dto.ReaderCardDto;
import com.library.mapper.ReaderCardMapper;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//todo:User     изменять данные/найти список записей!!!
//todo:Admin    добавлять картточку/выводит карточки/найти по id?имени/ найти список записей!!! у User

@RestController
@RequestMapping("/readers-cards")
public class ReaderCardController {

    @Autowired
    private IReaderCardService readerCardService;

    @Autowired
    private ReaderCardMapper readerCardMapper;

    //todo: user?id привязка
    public ReaderCardDto updateReaderCard(Integer idReaderCard, ReaderCardDto readerCardDto) {
        return this.readerCardMapper.convertToDto(
                this.readerCardService.updateReaderCard(idReaderCard,
                        this.readerCardMapper.convertToEntity(readerCardDto)));
    }

    //todo: admin/ user?id привязка
    public ReaderCardDto findReaderCardById(Integer idReaderCard) {
        return this.readerCardMapper.convertToDto(
                readerCardService.findReaderCardById(idReaderCard));
    }

    //todo: admin
    //todo: подумать над тем, как лучше назвать запрос
    public ReaderCardDto findReaderCardByFullName(String surnameSearch, String nameSearch, String patronymicSearch) {
        return this.readerCardMapper.convertToDto(
                this.readerCardService.findReaderCardByFullName(surnameSearch, nameSearch, patronymicSearch));
    }

    //todo: admin
    public List<ReaderCardDto> findReadersCardsList() {
        return this.readerCardMapper.convertToListDto(
                readerCardService.findReadersCardsList());
    }

    /////////////
    //todo: admin
    //todo: подумать как реализовать по String? enum compor? принимать готовый список и compor?
    public List<ReaderCardDto> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }

    ///////////////
    //todo: admin/user?id привязка
    public List<EntryDto> findEntriesByReaderCard(Integer idReaderCard) {
        return null;
    }
}
