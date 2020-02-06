package com.library.service.interfaces;

import com.library.model.Author;
import com.library.model.Genre;
import com.library.model.enums.SortingComparator;

import java.util.List;

public interface IAuthorService {
    Author createAuthor(Author author);

    Author updateAuthor(Integer idAuthor, Author newDataAuthor);

    Author addGenreToAuthor(Integer idAuthor, Integer idGenre);

    Author deleteAuthorById(Integer idAuthor);

    Author findAuthorById(Integer idAuthor);

    Author findAuthorByFullName(String surnameSearch, String nameSearch, String patronymicSearch);

    List<Author> findAuthorsList();

    List<Author> findAuthorsByGenre(Genre genre);

    List<Author> findSortAuthorsList(SortingComparator sortingComparator);
}
