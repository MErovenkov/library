package com.library.dao.interfaces;

import com.library.model.Author;
import com.library.model.Genre;

import java.util.List;

public interface IGenreDao extends IGenericDao<Genre>, ISearchingByName<Genre> {

    List<Genre> findGenresByAuthor(Author author);
}
