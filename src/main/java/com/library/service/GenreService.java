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
    private IGenreDao iGenreDao;

    @Override
    public void createGenre(Genre genre) {

        // todo: проверка

        this.iGenreDao.create(genre);
    }


    @Override
    public Genre updateGenre(Integer idGenre, Genre newDataGenre) {

        // todo: проверка на наличие

        Genre genre = this.iGenreDao.findOneById(idGenre);

        genre.setName(newDataGenre.getName());

        return this.iGenreDao.update(genre);
    }

    @Override
    public void deleteGenreById(Integer idGenre) {

        // todo: проверка

        this.iGenreDao.deleteById(idGenre);
    }

    @Override
    public Genre findGenreById(Integer idGenre) {

        // todo: проверка на наличие

        return this.iGenreDao.findOneById(idGenre);
    }

    // todo: возможно стоит убрать
    @Override
    public Genre findGenreByName(String nameGenre) {

        // todo: проверка на наличие

        return this.iGenreDao.findGenreByName(nameGenre);
    }

    @Override
    public List<Genre> findGenreList() {
        return this.iGenreDao.findAll();
    }
}
