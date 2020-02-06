package com.library.controller;

import com.library.dao.EntryDao;
import com.library.dto.EntryDto;
import com.library.mapper.EntryMapper;
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

    @Autowired
    private IEntryService entryService;

    @Autowired
    private EntryMapper entryMapper;

    //todo: admin
    public EntryDto createEntry(EntryDto entryDto) {
        return this.entryMapper.convertToDto(
                this.entryService.createEntry(
                        this.entryMapper.convertToEntity(entryDto)));
    }

    //todo: admin
    public EntryDto updateEntry(Integer idEntry, EntryDto entryDto) {
        return this.entryMapper.convertToDto(
                this.entryService.updateEntry(idEntry, this.entryMapper.convertToEntity(entryDto)));
    }

    //todo: admin
    public EntryDto deleteEntryById(Integer idEntry) {
        return this.entryMapper.convertToDto(entryService.deleteEntryById(idEntry));
    }

    //todo: admin
    public EntryDto findEntryById(Integer idEntry) {
        return this.entryMapper.convertToDto(entryService.findEntryById(idEntry));
    }

    //todo: admin
    //todo: закрытие записи
    public EntryDto closedEntryById(Integer idEntry) {
        //return this.entryService.closedEntryById(idEntry);
        return null;
    }

    //todo: admin
    //todo: вывод просроченных
    public List<EntryDto> findExpiredEntriesList() {
        //return this.entryService.findExpiredEntriesList();
        return null;
    }

    //todo: admin
    public List<EntryDto> findEntriesList() {
        return this.entryMapper.convertToListDto(entryService.findEntriesList());
    }

    //todo: admin
    public List<EntryDto> findSortEntriesList(SortingComparator sortingComparator) {
      //  return this.entryService.findSortEntriesList(sortingComparator);
        return null;
    }

        /*todo: сортировка по дате открытия записи?
    todo:

     */

}
