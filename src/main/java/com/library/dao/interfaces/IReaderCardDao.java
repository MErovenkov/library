package com.library.dao.interfaces;

import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IReaderCardDao extends IGenericDao<ReaderCard> {

    ReaderCard findReaderCardByFullName(ReaderCard readerCard);

    List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator);
}
