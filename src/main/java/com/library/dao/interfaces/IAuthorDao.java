package com.library.dao.interfaces;

import com.library.model.Author;
import com.library.model.Genre;

import java.util.List;

public interface IAuthorDao extends IGenericDao<Author>, ISearchingByFullName<Author> {

    List<Author> findAuthorsListByGenre(Genre genre);

    List<Genre> findGenreListByAuthor(Author author);
}
