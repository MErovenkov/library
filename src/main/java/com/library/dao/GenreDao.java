package com.library.dao;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class GenreDao extends AbstractJpaDao<Genre> implements IGenreDao {

    private GenreDao(){
        setClazz(Genre.class);
    }

    @Override
    public List<Author> findAuthorsByGenre(Integer idGenre) throws NoResultException {
        Genre genre = this.findOneById(idGenre);

        if (genre != null) {
            List<Author> authorList = genre.getAuthorList();

            for (Author author : authorList) {
                Hibernate.initialize(author.getGenreList());
            }

            return authorList;
        }

        return null;
    }

    @Override
    public List<Book> findBooksByGenre(Integer idGenre) {
        Genre genre = this.findOneById(idGenre);

        if (genre != null) {
                List<Book> bookList = this.findOneById(idGenre).getBookList();

            for (Book book : bookList) {
                Hibernate.initialize(book.getGenre());
            }

            return bookList;
        }

        return null;
    }
}
