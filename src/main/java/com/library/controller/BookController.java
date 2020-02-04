package com.library.controller;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;
import com.library.model.enums.SortingComparator;
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
    public Book createBook(@RequestBody Book book) {
        return this.bookService.createBook(book);
    }

    @PutMapping("/{idBook}")
    public Book updateBook(@PathVariable Integer idBook,
                             @RequestBody Book book) {
        return this.bookService.updateBook(idBook, book);
    }

    @DeleteMapping("/{idBook}")
    public Book deleteBookById(@PathVariable Integer idBook) {
        return this.bookService.deleteBookById(idBook);
    }

    @GetMapping("/{idBook}")
    public Book findBookById(@PathVariable Integer idBook) {
        return this.bookService.findBookById(idBook);
    }

    @GetMapping("/{nameBook}")
    public Book findBookByName(@PathVariable String nameBook) {
        return this.bookService.findBookByName(nameBook);
    }

    //todo:
    @GetMapping("/findBooksByAuthor")
    public List<Book> findBooksByAuthor(@RequestParam Integer idAuthor) {
        return this.bookService.findBooksByAuthor(idAuthor);
    }

    //todo:
    @GetMapping("/findBooksByGenre/{idGenre}")
    public List<Book> findBooksByGenre(@PathVariable Integer idGenre) {
        return this.bookService.findBooksByGenre(idGenre);
    }

    @GetMapping("/")
    public List<Book> findBooksList() {
        return this.bookService.findBooksList();
    }

    //todo: имя компоратора
    public List<Book> findSortBooksList(SortingComparator sortingComparator) {
        return this.bookService.findSortBooksList(sortingComparator);
    }

    //todo: сортировка книг по алфавиту/автору/жанру




    /*
    @GetMapping("/sort")
@RequestParam(name = "comparator",
                     defaultValue = "surname")
                                String nameComparator) {

    }*/

}
