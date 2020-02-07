package com.library.service;

import com.library.dao.interfaces.IBookDao;
import com.library.dao.interfaces.IEntryDao;
import com.library.dao.interfaces.IReaderCardDao;
import com.library.dto.EntryDto;
import com.library.model.Book;
import com.library.model.Entry;
import com.library.model.Genre;
import com.library.model.enums.EntryStatus;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IEntryService;
import com.library.utils.NamingFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EntryService implements IEntryService {

    @Autowired
    private IEntryDao entryDao;

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IReaderCardDao readerCardDao;

    @Override
    public Entry createEntry(Entry entry) {
        try {
            if (entry != null) {
                if (validationCheck(entry)) {
                    entry.setEntryStatus(EntryStatus.OPEN);
                    entry.setTakeDate(LocalDate.now());

                    Book book = this.bookDao.findOneById(entry.getBook().getId());
                    book.setInStock(false);
                    this.bookDao.update(book);

                    return this.entryDao.create(entry);
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

    private boolean validationCheck(Entry entry) {
        boolean validate = false;

        if (entry.getBook() != null
                && this.bookDao.findOneById(entry.getBook().getId()) != null) {
            if (entry.getReaderCard() != null
                    && this.readerCardDao.findOneById(entry.getReaderCard().getId()) != null) {
                if(entry.getReturnDatePlanned() != null) {
                    validate = true;
                } else {
                    log.warn("даты, к когда нужно вернуть = нул");
                }
            } else {
                log.warn("записаня карточка нул или не существует");
            }
        } else {
            log.warn("книга нул или нет такой книги");
            //todo:
        }

        return validate;
    }

    @Override
    public Entry closedEntryById(Integer idEntry) {
        Entry entry = this.entryDao.findOneById(idEntry);

        if (entry != null) {
            entry.setEntryStatus(EntryStatus.CLOSE);
            entry.setReturnDate(LocalDate.now());
            this.bookDao.findOneById(entry.getBook().getId()).setInStock(true);
            this.entryDao.update(entry);
        }

        return null;
    }

    @Override
    public Entry deleteEntryById(Integer idEntry) {
        Entry entry = this.entryDao.findOneById(idEntry);

        if (entry != null) {
            return this.entryDao.deleteById(idEntry);
        } else {
            log.warn("Жанр с таким id невозможно удалить, т.к его не существует");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    @Override
    public Entry findEntryById(Integer idEntry) {
        return this.entryDao.findOneById(idEntry);
    }

    @Override
    public List<Entry> findEntriesByBook(Integer idBook) {
        return this.bookDao.findEntriesByBook(idBook);
    }

    @Override
    public List<Entry> findEntriesByReaderCard(Integer idReaderCard) {
        return this.readerCardDao.findEntriesByReaderCard(idReaderCard);
    }

    @Override
    public List<Entry> findExpiredEntriesList() {
        return this.entryDao.findExpiredEntriesList();
    }

    @Override
    public List<Entry> findEntriesList() {
        return this.entryDao.findAll();
    }

    @Override
    public List<Entry> findSortEntriesList(SortingComparator sortingComparator) {
        return null;
    }

    //todo: метод
    //todo: поток проверяющий на своевременный возврат книг,
    //todo: если false ставит пенальти, делает список единовременно взятых книг меньше
    //todo: штраф действует месяц, если книга возвращена; если возврата нет, то бесрочно.
}
