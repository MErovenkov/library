package com.library.service.interfaces;

import com.library.model.Entry;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IEntryService {
    Entry createEntry(Entry entry);

    Entry updateEntry(Integer idEntry, Entry newDataEntry);

    Entry closedEntryById(Integer idEntry);

    Entry deleteEntryById(Integer idEntry);

    Entry findEntryById(Integer idEntry);

    List<Entry> findExpiredEntriesList();

    List<Entry> findEntriesList();

    List<Entry> findSortEntriesList(SortingComparator sortingComparator);
}
