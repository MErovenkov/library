package com.library.dao.interfaces;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Genre;

import java.util.List;

public interface IAuthorDao extends IGenericDao<Author>, ISearchingByFullName<Author> {

    Author addGenreToAuthor(Author author, Genre genre);

    Author deleteGenreToAuthor(Author author, Genre genre);

    List<Book> findBooksByAuthor(Integer idAuthor);
}
