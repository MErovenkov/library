package com.library.service.interfaces;

import com.library.dto.EntryDto;
import com.library.model.Entry;
import com.library.model.enums.SortingComparator;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IEntryService {
    Entry createEntry(Entry entry);

    Entry closedEntryById(Integer idEntry);

    Entry deleteEntryById(Integer idEntry);

    Entry findEntryById(Integer idEntry);

    List<Entry> findEntriesByBook(Integer idBook);

    List<Entry> findEntriesByReaderCard(Integer idReaderCard);

    List<Entry> findExpiredEntriesList();

    List<Entry> findEntriesList();

    List<Entry> findSortEntriesList(SortingComparator sortingComparator);
}
