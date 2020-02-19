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

@Slf4j
@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorDao authorDao;

    @Autowired
    private IGenreDao genreDao;

    /**
     *  Функция, в которой полученные данные
     *  проверяються на валидность,
     *  задаёться определённый формат для фио,
     *  после чего новый автор передаёться дальше в dao layer,
     *  в случае удалного добавления возвращает объект.
     * */
    @Override
    public Author createAuthor(Author author) {
        try {
            if (author != null) {
                if (Validator.getInstance().validationFullNameCheck(author)) {
                    author = (Author) NamingFormatter.getInstance().formatFullName(author);

                    return this.authorDao.create(author);
                }
            } else {
                log.warn("Попытка добавить автора равного null");
            }
        } catch (PersistenceException e) {
            log.error("Попытка добваить автора, который уже существует" + e);
        }

        return null;
    }

    /**
     *  Функция, служит для обновления данных автора(фио)
     *  Проверяет новые данные на валидность
     *  Получает нужного автора по id из bd
     *  Меняет старые значения на новые
     *  Передаёт на другой слой для мержа в бд
     * */
    @Override
    public Author updateAuthor(Integer idAuthor, Author newDataAuthor) {
        try {
            if (newDataAuthor != null || Validator.getInstance().validationFullNameCheck(newDataAuthor)) {
                Author author = this.authorDao.findOneById(idAuthor);

                if (author != null) {
                    newDataAuthor = (Author) NamingFormatter.getInstance().formatFullName(newDataAuthor);

                    author.setSurname(newDataAuthor.getSurname());
                    author.setName(newDataAuthor.getName());
                    author.setPatronymic(newDataAuthor.getPatronymic());

                    return this.authorDao.update(author);
                } else {
                    log.warn("Автора с таким id не возможно изменить т.к. его не существует");
                }
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Автор с таким данными уже существует " + e);
        }

        return null;
    }

    /**
     *  Функция, добваление существующему автору
     *  Новый жанр, который уже занесён в бд.
     * */
    public Author addGenreToAuthor(Integer idAuthor, Integer idGenre) {
        try {
            Author author = this.authorDao.findOneById(idAuthor);
            Genre genre = this.genreDao.findOneById(idGenre);

            if (genre != null && author != null) {
                return this.authorDao.addGenreToAuthor(author, genre);
            } else {
                log.warn("Жанра или автора с таким id не сужествует"
                        + new Throwable().getStackTrace()[1].getMethodName());
            }

            return null;
        } catch (DataIntegrityViolationException e) {
            log.error("Попытка добавить автору жанр, которуй уже присутствует у него " + e);

            return null;
        }
    }

    /**
     * Функуия, позволяющаяя убрать у автора жанр
     * */
    public Author deleteGenreToAuthor(Integer idAuthor, Integer idGenre) {
        Author author = this.authorDao.findOneById(idAuthor);
        Genre genre = this.genreDao.findOneById(idGenre);

        if (genre != null && author != null) {
            return this.authorDao.deleteGenreToAuthor(author, genre);
        } else {
            log.warn("Жанра или автора с таким id не сужествует"
                    + new Throwable().getStackTrace()[1].getMethodName());
        }

        return null;
    }

    @Override
    public Author deleteAuthorById(Integer idAuthor) {
        Author author = this.authorDao.findOneById(idAuthor);

        if (author != null) {
            return this.authorDao.deleteById(idAuthor);
        } else {
            log.warn("Автора с таким id невозможно удалить, т.к его не существует");
        }

        return null;
    }

    @Override
    public Author findAuthorById(Integer idAuthor) {
        return this.authorDao.findOneById(idAuthor);
    }

    @Override
    public Author findAuthorByFullName(String surnameSearch, String nameSearch, String patronymicSearch) {
        try {
            return this.authorDao.findByFullName(surnameSearch, nameSearch, patronymicSearch);
        } catch (NoResultException e) {
            log.error("Автора с таким фио в bd нет " + e);
            return null;
        }
    }

    @Override
    public List<Author> findAuthorsList() {
        return this.authorDao.findAll();
    }

    /**
     * Функция, возвращающая список авторов по id жанра
     * */
    @Override
    public List<Author> findAuthorsByGenre(Integer idGenre){
        return this.genreDao.findAuthorsByGenre(idGenre);
    }

    public List<Author> findSortAuthorsList(SortingComparator sortingComparator) {
        return this.authorDao.findSortAll(sortingComparator);
    }

}
