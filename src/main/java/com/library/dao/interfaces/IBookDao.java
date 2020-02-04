package com.library.dao.interfaces;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IBookDao extends IGenericDao<Book> {

    Book findBookByName(String nameBook);

    List<Book> findBooksByAuthor(Author author);

    List<Book> findSortBooksList(SortingComparator sortingComparator);

}
