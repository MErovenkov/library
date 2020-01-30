package com.library.service.interfaces;

import com.library.model.Author;

import java.util.List;

public interface IAuthorService {

    void createAuthor(Author author);
    void deleteAuthor(Author author);
    void deleteAuthorById(Integer idAuthor);
    Author getAuthorById(Integer idAuthor);
    List<Author> getAuthorList();
}
