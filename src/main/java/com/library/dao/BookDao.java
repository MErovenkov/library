package com.library.dao;

import com.library.dao.interfaces.IBookDao;
import com.library.model.Book;
import com.library.model.Entry;
import com.library.model.enums.SortingComparator;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BookDao extends AbstractJpaDao<Book> implements IBookDao {

    private BookDao() {
        setClazz(Book.class);
    }

    @Override
    public Book findIsStockBookByName(String name) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        Predicate nameBook = criteriaBuilder.like(root.get("name"), name);
        Predicate inStock = criteriaBuilder.equal(root.get("inStock"), true);

        criteriaQuery.select(root).where(criteriaBuilder.and(nameBook, inStock));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Book> findBooksByName(String name) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        Predicate nameBook = criteriaBuilder.like(root.get("name"), name);

        return entityManager.createQuery(criteriaQuery.select(root).where(nameBook)).getResultList();
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
