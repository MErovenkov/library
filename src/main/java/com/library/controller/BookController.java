package com.library.controller;

import com.library.dto.BookDto;
import com.library.mapper.BookMapper;
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

    @Autowired
    private BookMapper bookMapper;

    //todo: admin
    @PostMapping("/")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return this.bookMapper.convertToDto(
                this.bookService.createBook(
                        this.bookMapper.convertToEntity(bookDto)));
    }

    //todo: admin
    @PutMapping("/{idBook}")
    public BookDto updateBook(@PathVariable Integer idBook,
                             @RequestBody BookDto bookDto) {
        return this.bookMapper.convertToDto(
                this.bookService.updateBook(idBook, this.bookMapper.convertToEntity(bookDto)));
    }

    //todo: admin / user
    @DeleteMapping("/{idBook}")
    public BookDto deleteBookById(@PathVariable Integer idBook) {
        return this.bookMapper.convertToDto(bookService.deleteBookById(idBook));
    }

    //todo: admin / user
    @GetMapping("/{idBook}")
    public Book findBookById(@PathVariable Integer idBook) {
        return bookService.findBookById(idBook);
        //return this.bookMapper.convertToDto(bookService.findBookById(idBook));
    }

    //todo: admin / user
    //todo: getList? книг может быть несколько с идентичным названием
    @GetMapping("/name={nameBook}")
    public List<BookDto> findBookByName(@PathVariable String nameBook) {
        return this.bookMapper.convertToListDto(this.bookService.findBooksByName(nameBook));
    }

    //todo: admin / user
    //todo:
    //////
    @GetMapping("/author")
    public List<BookDto> findBooksByAuthor(@RequestParam(name = "id") Integer idAuthor) {
        return this.bookMapper.convertToListDto(
                this.bookService.findBooksByAuthor(idAuthor));
    }

    //todo: admin / user
    //todo:
    //////////
    @GetMapping("/genre")
    public List<BookDto> findBooksByGenre(@RequestParam(name = "id") Integer idGenre) {
        return this.bookMapper.convertToListDto(
                this.bookService.findBooksByGenre(idGenre));
    }

    //todo: admin / user
    @GetMapping("/")
    public List<BookDto> findBooksList() {
        return this.bookMapper.convertToListDto(bookService.findBooksList());
    }



    /*
    //todo: admin / user
    //todo: имя компоратора
    public List<BookDto> findSortBooksList(SortingComparator sortingComparator) {
        return null;
        //return this.bookService.findSortBooksList(sortingComparator);
    }
*/
    //todo: сортировка книг по алфавиту/автору/жанру по enum? или String?
    //////

    /*
    @GetMapping("/sort")
@RequestParam(name = "comparator",  defaultValue = "name") String nameComparator) {
    }*/

}
