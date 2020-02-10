package com.library.dao.interfaces;

import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IReaderCardDao extends IGenericDao<ReaderCard>, ISearchingByFullName<ReaderCard> {

    List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator);

    List<Entry> findEntriesByReaderCardId(Integer idReaderCard);

    List<Entry> findExpiredEntriesListByReaderCardId(Integer idReaderCard);

    List<Entry> findOpenedEntriesListByReaderCardId(Integer idReaderCard);
}
