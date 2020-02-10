package com.library.dao;

import com.library.dao.interfaces.IAuthorDao;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class AuthorDao extends AbstractJpaDao<Author> implements IAuthorDao {

    private AuthorDao() {
        setClazz(Author.class);
    }

    @Override
    public Author addGenreToAuthor(Author author, Genre genre) {
        Hibernate.initialize(author.getGenreList().add(genre));
        return this.update(author);
    }

    @Override
    public Author deleteGenreToAuthor(Author author, Genre genre) {
        Hibernate.initialize(author.getGenreList().removeIf(genreSearch -> genreSearch.getName().equals(genre.getName())));
        return this.update(author);
    }

    @Override
    public Author findOneById(Integer idAuthor) {
        Author author = entityManager.find(Author.class, idAuthor);

        if (author != null) {
            Hibernate.initialize(author.getGenreList());
        }

        return author;
    }

    @Override
    public List<Author> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

        criteriaQuery.select(criteriaQuery.from(Author.class));

        List<Author> authorList = entityManager.createQuery(criteriaQuery)
                .getResultList();

        for (Author author: authorList) {
            Hibernate.initialize(author.getGenreList());
        }

        return authorList;
    }

    @Override
    public List<Book> findBooksByAuthor(Integer idGenre) {
        Author author = this.findOneById(idGenre);

        if (author != null) {
            List<Book> bookList = author.getBookList();

            for (Book book : bookList) {
                Hibernate.initialize(book.getGenre());
            }

            return bookList;
        }

        return null;
    }
}
