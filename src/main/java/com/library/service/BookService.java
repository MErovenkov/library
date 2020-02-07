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

//todo: описание, логирование исключений, сообщения на сторону клиента. сортировка
/**
 *
 * */
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


    @Override
    public Book createBook(Book book) {
        try {
            if (book != null) {
                if (validationCheck(book)) {
                    book.setInStock(true);
                    return this.bookDao.create(book);
                }
            } else {
                log.warn("");
            }
        } catch (PersistenceException e) {
            //TODO: 03.02.2020 повторяющееся значение ключа нарушает ограничение уникальности
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
                log.warn("");
                //todo:http....
            }
        }

        return null;
    }


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
                            if (book.getNumberPages() != null) {
                                validate = true;
                            } else {
                                log.warn("сило страниц не должно быть нул");
                                //todo: http,,,,
                            }
                        } else {
                            log.warn("описание книги недолжно быть нул");
                            //todo: http,,,,
                        }
                    } else {
                        log.warn("издатель нул или нет в бд");
                        //todo: http,,,,
                    }
                } else {
                    log.warn("жанр нул или нет в бд");
                    //todo: http,,,,
                }
            } else {
                log.warn("Автор null или нет в бд");
                //todo: http,,,,
            }
        } else {
            log.warn("имя книги не должно быть нул или пустым");
            //todo: http,,,,
        }

        return validate;
    }


    @Override
    public Book deleteBookById(Integer idBook) {
        Book book = this.bookDao.findOneById(idBook);

        if (book != null) {
            return this.bookDao.deleteById(idBook);
        } else  {
            log.warn("Книги с таким id не существует");
            //TODO: http...
        }

        return null;
    }


    @Override
    public Book findBookById(Integer idBook) {
        return this.bookDao.findOneById(idBook);
    }

    @Override
    public Book findBookByName(String nameBook) {
        try {
            return this.bookDao.findByName(nameBook);
        } catch (NoResultException e) {
            log.error("Запрос на поиск по имени: " + nameBook
                    + " не выполнен, т.к.такого книги не существует", e);

            // TODO: 02.02.2020 выбросить фронт exception
            return null;
        }
    }

    public List<Book> findBooksByAuthor(Integer idAuthor) {
        return this.authorDao.findBooksByAuthor(idAuthor);
    }

    //todo:
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
