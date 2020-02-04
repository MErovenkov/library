package com.library.dao;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Book;
import com.library.model.Genre;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDao extends AbstractJpaDao<Genre> implements IGenreDao {

    private GenreDao(){
        setClazz(Genre.class);
    }

    @Override
    public List<Book> findBooksByGenre(Integer idGenre) {
        Genre genre = findOneById(idGenre);
        return genre.getBookList();
    }
}
