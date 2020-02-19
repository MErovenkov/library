package com.library.service;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Genre;
import com.library.service.interfaces.IGenreService;
import com.library.utils.NamingFormatter;
import com.library.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

@Slf4j
@Service
public class GenreService implements IGenreService {

    @Autowired
    private IGenreDao genreDao;

    /**
     *  Функция, добавления жанра,
     *  проверяет полученные данные на значение null,
     *  пооверка на валидность имени, задаёт её формат,
     *  и исключает добовление дубликата, отлавливая исключение
     * */
    @Override
    public Genre createGenre(Genre genre) {
        try {
            if (genre != null) {
                if (Validator.getInstance().validationCheckName(genre.getName())) {
                    genre.setName(NamingFormatter.getInstance().formatForName(genre.getName()));

                    return this.genreDao.create(genre);
                }
            } else {
                log.warn("Попытка добавть null объект в жанры");
            }
        } catch (PersistenceException e) {
            log.error("Попытка добавть жанр, который уже существует");
        }

        return null;
    }

    /**
     *  Функция, изменяющая существующий жанр     *
     *  Находит целевой жанр по ид,
     *  проверяет новые данные на валидность,
     *  меняет старые данные на новые, задавая определённый формат имени
     * */
    @Override
    public Genre updateGenre(Integer idGenre, Genre newDataGenre) {
        try {
            if (Validator.getInstance().validationCheckName(newDataGenre.getName())) {
                Genre genre = this.genreDao.findOneById(idGenre);

                if (genre != null) {
                    genre.setName(NamingFormatter.getInstance().formatForName(newDataGenre.getName()));

                    return this.genreDao.update(genre);
                } else {
                    log.warn("Жанр с таким id не возможно изменить т.к. его не существует");
                }
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Жанр с такими данными уже существует" + e);
        }

        return null;
    }


    @Override
    public Genre deleteGenreById(Integer idGenre) {
        Genre genre = this.genreDao.findOneById(idGenre);

        if (genre != null) {
            return this.genreDao.deleteById(idGenre);
        } else {
            log.warn("Жанр с таким id невозможно удалить, т.к его не существует");
        }

        return null;
    }

    @Override
    public Genre findGenreById(Integer idGenre) {
        return this.genreDao.findOneById(idGenre);
    }

    @Override
    public Genre findGenreByName(String genreName) {
        try {
            return this.genreDao.findByName(genreName);
        } catch (NoResultException e) {
            log.error("Запрос на поиск по имени: " + genreName
                    + " не выполнен, т.к.такого жанра не существует", e);

            return null;
        }
    }

    @Override
    public List<Genre> findGenresList() {
        return this.genreDao.findAll();
    }
}
