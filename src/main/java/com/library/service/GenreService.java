package com.library.service;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Genre;
import com.library.service.interfaces.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService {

    @Autowired
    private IGenreDao iGenreRepository;

    @Override
    public void createGenre(Genre genre) {
        this.iGenreRepository.create(genre);
    }

    @Override
    public void deleteGenreById(Integer idGenre) {
        this.iGenreRepository.deleteById(idGenre);
    }

    @Override
    public Genre getGenreById(Integer idGenre) {
        return this.iGenreRepository.findOneById(idGenre);
    }

    @Override
    public List<Genre> getGenreList() {
        return this.iGenreRepository.findAll();
    }
}
