package com.library.controller;

import com.library.dto.EntryDto;
import com.library.mapper.EntryMapper;
import com.library.service.interfaces.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    private IEntryService entryService;

    @Autowired
    private EntryMapper entryMapper;

    //todo: admin
    @PostMapping("/")
    public EntryDto createEntry(@RequestBody EntryDto entryDto) {
        return this.entryMapper.convertToDto(
                this.entryService.createEntry(
                        this.entryMapper.convertToEntity(entryDto)));
    }

    //todo: admin
    //todo: закрытие записи
    @PutMapping("/closed")
    public EntryDto closedEntryById(@RequestParam(name = "id") Integer idEntry) {
        return this.entryMapper.convertToDto(
                this.entryService.closedEntryById(idEntry));
    }

    //todo: admin
    @DeleteMapping("/{idEntry}")
    public EntryDto deleteEntryById(@PathVariable Integer idEntry) {
        return this.entryMapper.convertToDto(this.entryService.deleteEntryById(idEntry));
    }

    //todo: admin
    @GetMapping("/{idEntry}")
    public EntryDto findEntryById(@PathVariable Integer idEntry) {
        return this.entryMapper.convertToDto(this.entryService.findEntryById(idEntry));
    }

    @GetMapping("/by-book/{idBook}")
    public List<EntryDto> findEntriesByBook(@PathVariable Integer idBook) {
        return this.entryMapper.convertToListDto(this.entryService.findEntriesByBook(idBook));
    }

    @GetMapping("/by-reader-card/{idReaderCard}")
    public List<EntryDto> findEntriesByReaderCard(@PathVariable Integer idReaderCard) {
        return this.entryMapper.convertToListDto(this.entryService.findEntriesByReaderCard(idReaderCard));
    }

    //todo: admin
    //todo: вывод просроченных
    @GetMapping("/expired")
    public List<EntryDto> findExpiredEntriesList() {
        return  this.entryMapper.convertToListDto(this.entryService.findExpiredEntriesList());
    }

    //todo: admin
    @GetMapping("/")
    public List<EntryDto> findEntriesList() {
        return this.entryMapper.convertToListDto(this.entryService.findEntriesList());
    }


    //todo: admin
    //todo: по дате
     /*
    public List<EntryDto> findSortEntriesList(SortingComparator sortingComparator) {
      //  return this.entryService.findSortEntriesList(sortingComparator);
        return null;
    }*/

}
