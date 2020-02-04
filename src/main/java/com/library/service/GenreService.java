package com.library.service;

import com.library.dao.interfaces.IGenreDao;
import com.library.model.Genre;
import com.library.service.interfaces.IGenreService;
import com.library.utils.NamingFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

//todo: описание, логирование исключений, сообщения на сторону клиента
//TODO: 03.02.2020
/**
 *
 * */
@Slf4j
@Service
public class GenreService implements IGenreService {

    @Autowired
    private IGenreDao genreDao;

    //todo:изменить
    /**
     *  Функция
     *  @see GenreService#createGenre(Genre), получает
     *  @param genre
     *
     *  проверяет его на значение null,
     *  пооверка на валидность,
     *  исключает использование в имени жанра цифр,
     *  задаёт формат имени,
     *  и исключает добовление дубликата, отлавливая исключение
     *
     *  @return возвращая результат операции ввиде объекта
     *  или null в случае не соответствия условиям
     * */
    @Override
    public Genre createGenre(Genre genre) {
        try {
            if (genre != null) {
                if (validationCheck(genre)) {
                    genre.setName(NamingFormatter.getInstance().formatForName(genre.getName()));

                    return this.genreDao.create(genre);
                }
            } else {
                log.warn("Попытка добавть null объект в жанры");
                //TODO: 02.02.2020 выбросить фронт exception
            }
        } catch (PersistenceException e) {
            //TODO: 02.02.2020 повторяющееся значение ключа нарушает ограничение уникальности "genre_name_key",
            // жанр уже существует с таким именем.
        }

        return null;
    }

    //todo:изменить
    /**
     *  Функция
     *  @see GenreService#updateGenre(Integer, Genre), получает
     *  @param idGenre - ид жанра, для нахождения нужного жанра для update
     *  @param newDataGenre - новые данные, которые будут записаны в старый объект, полученный из бд
     *
     *  находит по {@param idGenre} нужный объект,
     *  после проверки на null,
     *  проверяет {@param newDataGenre} на валидность,
     *  задаёт формат имени новых данных и
     *  записывает их,
     *  передаёт данные для записи в бд

     *  @return возвращая результат операции ввиде перезописанного объекта
     *  или значение null, если объект не был найден по {@param idGenre}
     *  или {@param newDataGenre} не прошли валидность
     * */
    @Override
    public Genre updateGenre(Integer idGenre, Genre newDataGenre) {
        try {
            if(validationCheck(newDataGenre)) {
                Genre genre = this.genreDao.findOneById(idGenre);

                if (genre != null) {
                    genre.setName(NamingFormatter.getInstance().formatForName(newDataGenre.getName()));

                    return this.genreDao.update(genre);
                } else {
                    log.warn("Жанр с таким id не возможно изменить т.к. его не существует");
                    //TODO: 02.02.2020 выбросить фронт exception
                }
            }
        } catch (DataIntegrityViolationException e) {
            //TODO: 02.02.2020 ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности "genre_name_key"
        }

        return null;
    }

    //todo:изменить
    /**
     *  Функция
     *  @see GenreService#validationCheck(Genre)
     *  @param genre
     *
     *  проверяет полученный {@param genre} на корректность имени {@link Genre#getName()}
     *
     *  @return возвращяет результат проверки
     * */
    private boolean validationCheck(Genre genre) {
        boolean validate = false;

        if (genre.getName() != null) {
            if (!genre.getName().trim().equals("")) {
                if (genre.getName().chars().allMatch(Character::isLetter)) {
                    validate = true;

                } else {
                    log.warn("Имя жанра должна состоять только из букв");
                    //TODO: 02.02.2020 выбросить фронт exception
                }
            } else {
                log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                        +  " с пустым полем name");
                //TODO: 02.02.2020 выбросить фронт exception
            }
        } else {
            log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                    + " с name равным null");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return validate;
    }


    /**
     *  Функция
     *  @see GenreService#deleteGenreById(Integer)
     *  @param idGenre
     *
     *  находит жанр по {@param idGenre},
     *  проверяет полученное значение на null,
     *  в случае присутстия такой записи в бд, удаляет её
     *
     *  @return в случае успешного удаления возвращает искомый жанр
     *  или же значение null
     */
    @Override
    public Genre deleteGenreById(Integer idGenre) {
        Genre genre = this.genreDao.findOneById(idGenre);

        if (genre != null) {
            return this.genreDao.deleteById(idGenre);
        } else {
            log.warn("Жанр с таким id невозможно удалить, т.к его не существует");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Genre findGenreById(Integer idGenre) {
        return this.genreDao.findOneById(idGenre);
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Genre findGenreByName(String nameGenre) {
        try {
            return this.genreDao.findByName(nameGenre);
        } catch (NoResultException e) {
            log.error("Запрос на поиск по имени: " + nameGenre
                    + " не выполнен, т.к.такого жанра не существует", e);

            // TODO: 02.02.2020 выбросить фронт exception
            return null;
        }
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public List<Genre> findGenresList() {
        return this.genreDao.findAll();
    }
}
