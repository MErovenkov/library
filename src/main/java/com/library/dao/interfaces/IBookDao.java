package com.library.dao.interfaces;

import com.library.model.Book;
import com.library.model.Entry;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IBookDao extends IGenericDao<Book> {
    Book findIsStockBookByName(String name);

    List<Book> findBooksByName(String name);

    List<Entry> findEntriesByBook(Integer idBook);

    List<Book> findSortBooksList(SortingComparator sortingComparator);
}
