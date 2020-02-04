package com.library.service.interfaces;

import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IReaderCardService {

    ReaderCard createReaderCard(ReaderCard readerCard);

    ReaderCard updateReaderCard(Integer idReaderCard, ReaderCard newDataReaderCard);

    ReaderCard deleteReaderCardById(Integer idReaderCard);

    ReaderCard findReaderCardById(Integer idReaderCard);

    ReaderCard findReaderCardByFullName(ReaderCard readerCard);

    List<ReaderCard> findReadersCardsList();

    List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator);
}
