package com.library.dao;

import com.library.dao.interfaces.IBookDao;
import com.library.dto.EntryDto;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Entry;
import com.library.model.enums.SortingComparator;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao extends AbstractJpaDao<Book> implements IBookDao {

    private BookDao() {
        setClazz(Book.class);
    }

    @Override
    public List<Entry> findEntriesByBook(Integer idBook) {
        Book book = this.findOneById(idBook);

        if (book != null) {
            Hibernate.initialize(book.getEntryList());
            return book.getEntryList();
        }

        return null;
    }

    @Override
    public List<Book> findSortBooksList(SortingComparator sortingComparator) {
        return null;
    }
}
