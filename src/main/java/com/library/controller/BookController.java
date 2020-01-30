package com.library.controller;

import com.library.model.Book;
import com.library.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @PostMapping
    public void createBook(@RequestBody Book book) {
        this.iBookService.createBook(book);
    }


    /// ?delete @RequestBody...?

    @DeleteMapping("/{idBook}")
    public void deleteBookById(@PathVariable Integer idBook) {
        this.iBookService.deleteBookById(idBook);
    }

    @GetMapping("/{idBook}")
    public Book getBookById(@PathVariable Integer idBook) {
        return this.iBookService.getBookById(idBook);
    }

    @GetMapping("/")
    public List<Book> getBookList() {
        return this.iBookService.getBookList();
    }

    /*   @PostMapping
    public void addMaster(@RequestBody Master master,
                          @RequestParam(name = "garage") Integer numberGarage) {
        this.iMasterService.addMaster(master, numberGarage);
    }

    @GetMapping("/sort")
    public List<Master> getSortMastersList(@RequestParam(name = "comparator",
                                                         defaultValue = "surname")
                                                       String nameComparator) {
        return this.iMasterService.getSortMastersList(nameComparator);
    }*/


    /// сортировки + update
}
