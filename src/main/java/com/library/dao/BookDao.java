package com.library.dao;

import com.library.dao.interfaces.IBookDao;
import com.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends AbstractJpaDao<Book> implements IBookDao {

    private BookDao() {
        setClazz(Book.class);
    }
}
