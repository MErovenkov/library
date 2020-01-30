package com.library.service.interfaces;

import com.library.model.Book;

import java.util.List;

public interface IBookService {

    void createBook(Book book);

    void deleteBookById(Integer idBook);

    Book getBookById(Integer idBook);



    List<Book> getBookList();

}
