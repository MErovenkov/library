package com.library.dao;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;

import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GenreDao extends AbstractJpaDao<Genre> implements IGenreDao {

    private GenreDao(){
        setClazz(Genre.class);
    }

    @Override
    public List<Genre> findGenresByAuthor(Author author) {
        return null;
    }
}
