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





    @PutMapping("/{idBook}")
    public Book updateGenre(@PathVariable Integer idBook,
                             @RequestBody Book book) {
        return this.iBookService.updateBook(idBook, book);
    }







    @DeleteMapping("/{idBook}")
    public void deleteBookById(@PathVariable Integer idBook) {
        this.iBookService.deleteBookById(idBook);
    }

    @GetMapping("/{idBook}")
    public Book getBookById(@PathVariable Integer idBook) {
        return this.iBookService.findBookById(idBook);
    }







    @GetMapping("/{nameBook}")
    public Book findBookByName(@PathVariable String nameBook) {
        return this.iBookService.findBookByName(nameBook);
    }






    @GetMapping("/")
    public List<Book> getBookList() {
        return this.iBookService.findBookList();
    }

    /*
    @GetMapping("/sort")
@RequestParam(name = "comparator",
                     defaultValue = "surname")
                                String nameComparator) {

    }*/


    /// сортировки + update
}
