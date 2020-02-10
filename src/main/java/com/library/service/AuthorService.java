package com.library.service;

import com.library.dao.interfaces.IAuthorDao;
import com.library.dao.interfaces.IGenreDao;
import com.library.model.Author;

import com.library.model.Genre;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IAuthorService;
import com.library.utils.NamingFormatter;
import com.library.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

//todo: описание, логирование исключений, сообщения на сторону клиента, транзакция
//todo: не работает добавление новых жанров автору
//TODO: 05.02.2020
/**
 *
 * */

@Slf4j
@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorDao authorDao;

    @Autowired
    private IGenreDao genreDao;


    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Author createAuthor(Author author) {
        try {
            if (author != null) {
                if (Validator.getInstance().validationFullNameCheck(author)) {
                    author = (Author) NamingFormatter.getInstance().formatFullName(author);
                    if (author != null) {
                        return this.authorDao.create(author);
                    }
                }
            }
        } catch (PersistenceException e) {
            //TODO: 03.02.2020 повторяющееся значение ключа нарушает ограничение уникальности
        } catch (StringIndexOutOfBoundsException e) {
            log.error( "" + e);
        }

        return null;
    }



    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Author updateAuthor(Integer idAuthor, Author newDataAuthor) {
        try {
            if (newDataAuthor != null || Validator.getInstance().validationFullNameCheck(newDataAuthor)) {
                Author author = this.authorDao.findOneById(idAuthor);

                if (author != null) {
                    newDataAuthor = (Author) NamingFormatter.getInstance().formatFullName(newDataAuthor);

                    if (newDataAuthor != null) {
                        author.setSurname(newDataAuthor.getSurname());
                        author.setName(newDataAuthor.getName());
                        author.setPatronymic(newDataAuthor.getPatronymic());

                        return this.authorDao.update(author);
                    } else {
                        log.warn("StringIndexOutOfBoundsException..... ");
                    }
                } else {
                    log.warn("Автора с таким id не возможно изменить т.к. его не существует");
                    //TODO: 03.02.2020 выбросить фронт exception
                }
            }
        } catch (DataIntegrityViolationException e) {
            //TODO: 03.02.2020 ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности
        } catch (StringIndexOutOfBoundsException e) {
            log.error( "" + e);
        }

        return null;
    }


    //TODO:
    /**
     *  Функция
     *
     * */
    public Author addGenreToAuthor(Integer idAuthor, Integer idGenre) {
        try {
            Author author = this.authorDao.findOneById(idAuthor);
            Genre genre = this.genreDao.findOneById(idGenre);

            if (genre != null && author != null) {
                return this.authorDao.addGenreToAuthor(author, genre);
            } else {
                log.warn("!");
                //TODO: 03.02.2020 выбросить фронт exception
            }

            return null;
        } catch (DataIntegrityViolationException e) {
            log.error("" + e);
            return null;
        }
    }

    public Author deleteGenreToAuthor(Integer idAuthor, Integer idGenre) {
        Author author = this.authorDao.findOneById(idAuthor);
        Genre genre = this.genreDao.findOneById(idGenre);

        if (genre != null && author != null) {
            return this.authorDao.deleteGenreToAuthor(author, genre);
        } else {
            log.warn("!");
            //TODO: 03.02.2020 выбросить фронт exception
        }

        return null;
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Author deleteAuthorById(Integer idAuthor) {
        Author author = this.authorDao.findOneById(idAuthor);

        if (author != null) {
            return this.authorDao.deleteById(idAuthor);
        } else {
            log.warn("Автора с таким id невозможно удалить, т.к его не существует");
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
    public Author findAuthorById(Integer idAuthor) {
        return this.authorDao.findOneById(idAuthor);
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    // todo: need test
    @Override
    public Author findAuthorByFullName(String surnameSearch, String nameSearch, String patronymicSearch) {
        try {
            return this.authorDao.findByFullName(surnameSearch, nameSearch, patronymicSearch);
        } catch (NoResultException e) {
            return null;
        }
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public List<Author> findAuthorsList() {
        return this.authorDao.findAll();
    }

    @Override
    public List<Author> findAuthorsByGenre(Integer idGenre){
        return this.genreDao.findAuthorsByGenre(idGenre);
    }

    public List<Author> findSortAuthorsList(SortingComparator sortingComparator) {
        return this.authorDao.findSortAll(sortingComparator);
    }

}
