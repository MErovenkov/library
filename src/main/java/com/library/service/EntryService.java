package com.library.service;

import com.library.dao.interfaces.IEntryDao;
import com.library.model.Entry;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EntryService implements IEntryService {

    @Autowired
    private IEntryDao entryDao;


    @Override
    public Entry createEntry(Entry entry) {
        return null;
    }

    //todo: ?
    @Override
    public Entry updateEntry(Integer idEntry, Entry newDataEntry) {
        return null;
    }

    @Override
    public Entry closedEntryById(Integer idEntry) {
        return this.entryDao.closedEntryById(idEntry);
    }

    @Override
    public Entry deleteEntryById(Integer idEntry) {
        return null;
    }

    @Override
    public Entry findEntryById(Integer idEntry) {
        return null;
    }

    @Override
    public List<Entry> findExpiredEntriesList() {
        return this.entryDao.findExpiredEntriesList();
    }

    @Override
    public List<Entry> findEntriesList() {

        return null;
    }

    @Override
    public List<Entry> findSortEntriesList(SortingComparator sortingComparator) {
        return null;
    }

    //todo: метод
    //todo: поток проверяющий на своевременный возврат книг,
    //todo: если false ставит пенальти, делает список единовременно взятых книг меньше
    //todo: штраф действует месяц, если книга возвращена; если возврата нет, то бесрочно.
}
