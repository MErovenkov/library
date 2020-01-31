package com.library.service.interfaces;

import com.library.model.Genre;

import java.util.List;

public interface IGenreService {

    void createGenre(Genre genre);

    Genre updateGenre(Integer idGenre, Genre newDataGenre);

    void deleteGenreById(Integer idGenre);

    Genre findGenreById(Integer idGenre);

    Genre findGenreByName(String nameGenre);

    List<Genre> findGenreList();
}
