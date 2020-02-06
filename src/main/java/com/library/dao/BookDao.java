package com.library.dao;

import com.library.dao.interfaces.IBookDao;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.enums.SortingComparator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao extends AbstractJpaDao<Book> implements IBookDao {

    private BookDao() {
        setClazz(Book.class);
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> findBooksByGenre(Integer idGenre) {
        return null;
    }

    public List<Book> findSortBooksList(SortingComparator sortingComparator) {
        return null;
    }
}
