package com.library.dao.interfaces;

import com.library.model.Entry;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IEntryDao extends IGenericDao<Entry> {
    List<Entry> findEntriesByReaderCard(Integer idReaderCard);

    List<Entry> findSortEntriesList(SortingComparator sortingComparator);
}
