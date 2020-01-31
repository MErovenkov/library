package com.library.dao;

import com.library.dao.interfaces.IBookDao;
import com.library.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class BookDao extends AbstractJpaDao<Book> implements IBookDao {

    private BookDao() {
        setClazz(Book.class);
    }

    @Override
    public Book findBookByName(String nameBook) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        //todo: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return null;
    }
}
