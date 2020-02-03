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
    private IBookService bookService;

    @PostMapping("/")
    public void createBook(@RequestBody Book book) {
        this.bookService.createBook(book);
    }

    @PutMapping("/{idBook}")
    public Book updateBook(@PathVariable Integer idBook,
                             @RequestBody Book book) {
        return this.bookService.updateBook(idBook, book);
    }

    @DeleteMapping("/{idBook}")
    public void deleteBookById(@PathVariable Integer idBook) {
        this.bookService.deleteBookById(idBook);
    }

    @GetMapping("/{idBook}")
    public Book getBookById(@PathVariable Integer idBook) {
        return this.bookService.findBookById(idBook);
    }

    @GetMapping("/{nameBook}")
    public Book findBookByName(@PathVariable String nameBook) {
        return this.bookService.findBookByName(nameBook);
    }

    @GetMapping("/")
    public List<Book> getBookList() {
        return this.bookService.findBookList();
    }

    //todo: Вывод книг оперделённого автора.
    //todo: Вывод книг определённого жанра
    //todo: сортировка книг по алфавиту/автору/жанру




    /*
    @GetMapping("/sort")
@RequestParam(name = "comparator",
                     defaultValue = "surname")
                                String nameComparator) {

    }*/


    /// сортировки + update
}
