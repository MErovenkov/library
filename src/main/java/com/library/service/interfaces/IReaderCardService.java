package com.library.service.interfaces;

import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IReaderCardService {

    ReaderCard createReaderCard(Integer idUser, ReaderCard readerCard);

    ReaderCard updateReaderCard(Integer idReaderCard, ReaderCard newDataReaderCard);

    ReaderCard findReaderCardById(Integer idReaderCard);

    ReaderCard findReaderCardByFullName(String surnameSearch, String nameSearch, String patronymicSearch);

    List<ReaderCard> findReadersCardsList();

    List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator);

}
