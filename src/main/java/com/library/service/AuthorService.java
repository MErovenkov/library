package com.library.service;

import com.library.dao.interfaces.IAuthorDao;
import com.library.dao.interfaces.IGenreDao;
import com.library.model.Author;

import com.library.model.Genre;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IAuthorService;
import com.library.utils.NamingFormatter;
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
                if (validationFullNameCheck(author)) {
                    return this.authorDao.create(author);
                }
            }
        } catch (PersistenceException e) {
            //TODO: 03.02.2020 повторяющееся значение ключа нарушает ограничение уникальности
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
            if (validationFullNameCheck(newDataAuthor)) {
                Author author = this.authorDao.findOneById(idAuthor);

                if (author != null) {
                    newDataAuthor = (Author) NamingFormatter.getInstance().formatFullName(newDataAuthor);

                    author.setSurname(newDataAuthor.getSurname());
                    author.setName(newDataAuthor.getName());
                    author.setPatronymic(newDataAuthor.getPatronymic());

                    return this.authorDao.update(author);
                } else {
                    log.warn("Автора с таким id не возможно изменить т.к. его не существует");
                    //TODO: 03.02.2020 выбросить фронт exception
                }
            }
        } catch (DataIntegrityViolationException e) {
            //TODO: 03.02.2020 ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности
        }

        return null;
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    private boolean validationFullNameCheck(Author author) {
        boolean validate = false;

        if (author.getSurname() != null
                || author.getName() != null
                || author.getPatronymic() != null) {
            if (!(author.getSurname().trim().equals("")
                    || author.getName().trim().equals("")
                    || author.getPatronymic().trim().equals(""))) {
                if (author.getSurname().chars().allMatch(Character::isLetter)
                        ||author.getName().chars().allMatch(Character::isLetter)
                        || author.getPatronymic().chars().allMatch(Character::isLetter)) {
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

    //TODO:
    /**
     *  Функция
     *
     * */
    public Author addGenreToAuthor(Integer idAuthor, Integer idGenre) {
        Author author = this.authorDao.findOneById(idAuthor);
        this.authorDao.findGenreListByAuthor(author);


        Genre genre = this.genreDao.findOneById(idGenre);

        if (genre != null && author != null) {


            return this.authorDao.update(author);
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
        Author author = this.authorDao.findOneById(idAuthor);

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


    public List<Author> findAuthorsByGenre(Genre genre){


        return this.authorDao.findAuthorsListByGenre(genre);
    }

    public List<Author> findSortAuthorsList(SortingComparator sortingComparator) {
        return this.authorDao.findSortAll(sortingComparator);
    }

}
