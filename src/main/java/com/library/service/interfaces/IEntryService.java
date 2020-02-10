package com.library.service.interfaces;

import com.library.model.Entry;
import com.library.model.enums.SortingComparator;

import java.time.LocalDate;
import java.util.List;

public interface IEntryService {

    Entry createEntry(Integer idReaderCard, String nameBook, LocalDate returnDatePlanned);

    Entry closedEntryById(Integer idEntry);

    Entry deleteEntryById(Integer idEntry);

    Entry findEntryById(Integer idEntry);

    List<Entry> findEntriesByBookId(Integer idBook);

    List<Entry> findEntriesByReaderCardId(Integer idReaderCard);

    List<Entry> findExpiredEntriesListByReaderCardId(Integer idReaderCard);

    List<Entry> findOpenedEntriesListByReaderCardId(Integer idReaderCard);

    List<Entry> findExpiredEntriesList();

    List<Entry> findEntriesList();

    List<Entry> findSortEntriesList(SortingComparator sortingComparator);
}
