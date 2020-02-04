package com.library.service.interfaces;

import com.library.model.Author;

import java.util.List;

public interface IAuthorService {
    Author createAuthor(Author author);

    Author updateAuthor(Integer idAuthor, Author newDataAuthor);

    Author deleteAuthorById(Integer idAuthor);

    Author findAuthorById(Integer idAuthor);

    Author findAuthorByFullName(Author author);

    List<Author> findAuthorsList();

    Author addGenreToAuthor(Integer idAuthor, Integer idGenre);
}
