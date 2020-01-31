package com.library.service.interfaces;

import com.library.model.Author;

import java.util.List;

public interface IAuthorService {
    void createAuthor(Author author);

    Author updateAuthor(Integer idAuthor, Author newDataAuthor);

    void deleteAuthorById(Integer idAuthor);

    Author findAuthorById(Integer idAuthor);

    Author findAuthorByFullName(Author author);

    List<Author> findAuthorList();
}
