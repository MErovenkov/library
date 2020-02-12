package com.library.service.interfaces;

import com.library.model.Book;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IBookService {

    Book createBook(Book book);

    Book addExistBook(Integer idBook);

    Book updateBook(Integer idBook, Book newDataBook);

    Book deleteBookById(Integer idBook);

    Book findBookById(Integer idBook);

    List<Book> findBooksByName(String nameBook);

    List<Book> findBooksByAuthor(Integer idAuthor);

    List<Book> findBooksByGenre(Integer idGenre);

    List<Book> findBooksList();

    List<Book> findSortBooksList(SortingComparator sortingComparator);

}
