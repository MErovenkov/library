package com.library.dao;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Genre;

import org.springframework.stereotype.Repository;

@Repository
public class GenreDao extends AbstractJpaDao<Genre> implements IGenreDao {

    private GenreDao(){
        setClazz(Genre.class);
    }
}
