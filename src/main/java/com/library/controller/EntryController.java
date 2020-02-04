package com.library.controller;

import com.library.model.Entry;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    /*todo: добавление/?удаление по ид?/апдейт/гет/сортировка по дате открытия записи
    todo:

     */

    @Autowired
    private IEntryService entryService;

    public Entry createEntry(Entry entry) {
        return this.entryService.createEntry(entry);
    }

    public Entry updateEntry(Integer idEntry, Entry newDataEntry) {
        return this.entryService.updateEntry(idEntry, newDataEntry);
    }

    public Entry deleteEntryById(Integer idEntry) {
        return this.entryService.deleteEntryById(idEntry);
    }

    public Entry findEntryById(Integer idEntry) {
        return this.entryService.findEntryById(idEntry);
    }

    public List<Entry> findEntriesByReaderCard(Integer idReaderCard) {
        return this.entryService.findEntriesByReaderCard(idReaderCard);
    }

    public List<Entry> findEntriesList() {
        return this.entryService.findEntriesList();
    }

    public List<Entry> findSortEntriesList(SortingComparator sortingComparator) {
        return this.entryService.findSortEntriesList(sortingComparator);
    }
}
