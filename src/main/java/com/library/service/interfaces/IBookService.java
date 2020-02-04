package com.library.service.interfaces;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IBookService {

    Book createBook(Book book);

    Book updateBook(Integer idBook, Book newDataBook);

    Book deleteBookById(Integer idBook);

    Book findBookById(Integer idBook);

    Book findBookByName(String nameBook);

    List<Book> findBooksByAuthor(Integer idAuthor);

    List<Book> findBooksByGenre(Integer idGenre);

    List<Book> findBooksList();

    List<Book> findSortBooksList(SortingComparator sortingComparator);

}
