package com.library.dao.interfaces;

import com.library.model.Book;
import com.library.model.Genre;

import java.util.List;

public interface IGenreDao extends IGenericDao<Genre> {
    List<Book> findBooksByGenre(Integer idGenre);
}
