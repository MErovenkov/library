package com.library.service;

import com.library.dao.interfaces.IAuthorDao;
import com.library.dao.interfaces.IBookDao;
import com.library.dao.interfaces.IGenreDao;
import com.library.dao.interfaces.IPublisherDao;
import com.library.model.*;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

@Slf4j
@Service
public class BookService implements IBookService {

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IAuthorDao authorDao;

    @Autowired
    private IGenreDao genreDao;

    @Autowired
    private IPublisherDao publisherDao;

    /**
     * Функция, проверяющая значение полученной книги на null, валидность,
     * Определение статуса книги как true ("в наличие")
     * */
    @Override
    public Book createBook(Book book) {
        try {
            if (book != null) {
                if (validationCheck(book)) {
                    book.setInStock(true);

                    return this.bookDao.create(book);
                }
            } else {
                log.warn("Попытка добавить книгу равную null");
            }
        } catch (PersistenceException e) {
            log.error("Книга с таким именем уже существует" + e);
        }

        return null;
    }

    /**
     * Функция, добавляющая уже существующую книгу.
     * */
    @Override
    public Book addExistBook(Integer idBook) {
        Book book = this.bookDao.findOneById(idBook);

        if (book != null) {
            Book newBook = new Book(book.getName(), book.getAuthor(), book.getGenre(),
                    book.getPublisher(), book.getShortSpecification(), book.getNumberPages());

            return this.bookDao.create(newBook);
        } else {
            log.warn("Добавить ещё одну такуюже книгу не выйдет, т.к. исходной книги не существует");
        }

        return null;
    }


    @Override
    public Book updateBook(Integer idBook, Book newDataBook) {
        if (validationCheck(newDataBook)) {
            Book book = this.bookDao.findOneById(idBook);

            if (book != null) {
                book.setName(newDataBook.getName());
                book.setAuthor(newDataBook.getAuthor());
                book.setGenre(newDataBook.getGenre());
                book.setNumberPages(newDataBook.getNumberPages());
                book.setShortSpecification(newDataBook.getShortSpecification());

                return this.bookDao.update(book);
            } else {
                log.warn("Попытка изменить книгу, которой нет");
            }
        }

        return null;
    }


    /**
     * Функция, проверяющая поля книги на валидность
     * */
    private boolean validationCheck(Book book) {

        boolean validate = false;

        if (!book.getName().trim().equals("") || book.getName() != null) {
            if (book.getAuthor() != null
                    && this.authorDao.findOneById(book.getAuthor().getId()) != null) {
                if (book.getGenre() != null
                        && this.genreDao.findOneById(book.getGenre().getId()) != null) {
                    if (book.getPublisher() != null
                            && this.publisherDao.findOneById(book.getPublisher().getId()) != null) {
                        if (book.getShortSpecification() != null) {
                            if (book.getNumberPages() != null
                                    && book.getNumberPages() > 0) {
                                validate = true;
                            } else {
                                log.warn("Число страниц не должно быть null и должно быть больше 0"
                                        + new Throwable().getStackTrace()[1]);
                            }
                        } else {
                            log.warn("Описание книги недолжно быть null"
                                    + new Throwable().getStackTrace()[1]);
                        }
                    } else {
                        log.warn("Издатель у книги равен null или его нет в бд"
                                + new Throwable().getStackTrace()[1]);
                    }
                } else {
                    log.warn("Жанр у книги равен null или его нет в бд"
                            + new Throwable().getStackTrace()[1]);
                }
            } else {
                log.warn("Автор у книги равен null или его нет в бд"
                        + new Throwable().getStackTrace()[1]);
            }
        } else {
            log.warn("Имя книги не должно быть нул или пустым"
                    + new Throwable().getStackTrace()[1]);
        }

        return validate;
    }


    @Override
    public Book deleteBookById(Integer idBook) {
        Book book = this.bookDao.findOneById(idBook);

        if (book != null) {
            return this.bookDao.deleteById(idBook);
        } else  {
            log.warn("Книга с таким id не существует");
        }

        return null;
    }


    @Override
    public Book findBookById(Integer idBook) {
        return this.bookDao.findOneById(idBook);
    }

    @Override
    public List<Book> findBooksByName(String nameBook) {
        try {
            return this.bookDao.findBooksByName(nameBook);
        } catch (NoResultException e) {
            log.error("Запрос на поиск по имени: " + nameBook
                    + " не выполнен, т.к.такого книги не существует", e);

            return null;
        }
    }

    public List<Book> findBooksByAuthor(Integer idAuthor) {
        return this.authorDao.findBooksByAuthor(idAuthor);
    }

    public List<Book> findBooksByGenre(Integer idGenre) {
        return this.genreDao.findBooksByGenre(idGenre);
    }

    @Override
    public List<Book> findBooksList() {
        return this.bookDao.findAll();
    }

    //todo:
    public List<Book> findSortBooksList(SortingComparator sortingComparator) {
        return this.bookDao.findSortAll(sortingComparator);
    }
}
