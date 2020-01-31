package com.library.service.interfaces;

import com.library.model.Book;

import java.util.List;

public interface IBookService {

    void createBook(Book book);

    Book updateBook(Integer idBook, Book newDataBook);

    void deleteBookById(Integer idBook);

    Book findBookById(Integer idBook);

    Book findBookByName(String nameBook);

    List<Book> findBookList();

}
