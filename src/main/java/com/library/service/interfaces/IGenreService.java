package com.library.service.interfaces;

import com.library.model.Genre;

import java.util.List;

public interface IGenreService {
    void createGenre(Genre genre);

    void deleteGenreById(Integer idGenre);

    Genre getGenreById(Integer idGenre);



    List<Genre> getGenreList();
}
