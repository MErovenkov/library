package com.library.service;

import com.library.dao.interfaces.IBookDao;
import com.library.dao.interfaces.IEntryDao;
import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.Book;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.EntryStatus;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@EnableScheduling
public class EntryService implements IEntryService {

    @Autowired
    private IEntryDao entryDao;

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IReaderCardDao readerCardDao;

    /**
     * Функция, добавления записи о том, какая книга и кем была взята.
     * Проверяет, существует ли карточка, книга, для которых создаёться запись,
     * проверяет не привышин ли лимит на взятие книг,
     * корректность дат.
     * */
    @Override
    public Entry createEntry(Integer idReaderCard, String nameBook, LocalDate returnDatePlanned) {
        try {
            ReaderCard readerCard = this.readerCardDao.findOneById(idReaderCard);
            if (readerCard != null) {
                if ((this.readerCardDao.findExpiredEntriesListByReaderCardId(idReaderCard).size()
                        + this.readerCardDao.findOpenedEntriesListByReaderCardId(idReaderCard).size())
                            <= readerCard.getMaxBooksTaken()) {
                    Book book = this.bookDao.findIsStockBookByName(nameBook);

                    if (returnDatePlanned != null && LocalDate.now().isBefore(returnDatePlanned)) {
                        Entry newEntry = new Entry(readerCard, book, returnDatePlanned);

                        return this.entryDao.create(newEntry);
                    } else {
                        log.warn("Дата планируемого возвращения, должа наступить позже чем дата создания записи" +
                                "и не должна быть null");
                    }
                } else {
                    log.warn("Лимит книг достигнут у пользователя достигнут, поэтому нельзя создать новую запись");
                }
            } else {
                log.warn("Карточки чиитателя с таким ид нет, запись создать невозможно");
            }
        } catch (NoResultException e) {
            log.error("Нет свободных книг, создать запись невозможно");
        }

        return null;
    }

    /**
     * Функция, служащая для закрытия записи при возврате книги.
     * */
    @Override
    public Entry closedEntryById(Integer idEntry) {
        Entry entry = this.entryDao.findOneById(idEntry);

        if (entry != null) {
            entry.setEntryStatus(EntryStatus.CLOSE);
            entry.setReturnDate(LocalDate.now());

            stateChangingBook(entry);

            return this.entryDao.update(entry);
        } else {
            log.warn("Закрыть запись нельзя, так как её не существует");
        }

        return null;
    }

    /**
     * Функция, удаляющая запись из бд
     * */
    @Override
    public Entry deleteEntryById(Integer idEntry) {
        Entry entry = this.entryDao.findOneById(idEntry);

        if (entry != null) {

            Book book = this.bookDao.findOneById(entry.getBook().getId());
            book.setInStock(true);

            stateChangingBook(entry);

            return this.entryDao.deleteById(idEntry);
        } else {
            log.warn("Запись с таким id невозможно удалить, т.к её не существует");
        }

        return null;
    }

    /**
     * Функция, изменяющая состояние книги
     * */
    private void stateChangingBook(Entry entry) {
        Book book = this.bookDao.findOneById(entry.getBook().getId());
        book.setInStock(true);

        this.bookDao.update(book);
    }

    @Override
    public Entry findEntryById(Integer idEntry) {
        return this.entryDao.findOneById(idEntry);
    }

    @Override
    public List<Entry> findEntriesByBookId(Integer idBook) {
        return this.bookDao.findEntriesByBook(idBook);
    }


    @Override
    public List<Entry> findEntriesByReaderCardId(Integer idReaderCard) {
        return this.readerCardDao.findEntriesByReaderCardId(idReaderCard);
    }

    @Override
    public List<Entry> findExpiredEntriesListByReaderCardId(Integer idReaderCard) {
        return this.readerCardDao.findExpiredEntriesListByReaderCardId(idReaderCard);
    }

    @Override
    public List<Entry> findOpenedEntriesListByReaderCardId(Integer idReaderCard) {
        return this.readerCardDao.findOpenedEntriesListByReaderCardId(idReaderCard);
    }

    @Override
    public List<Entry> findExpiredEntriesList() {
        return this.entryDao.findExpiredEntriesList();
    }

    @Override
    public List<Entry> findEntriesList() {
        return this.entryDao.findAll();
    }

    /////////////////
    @Override
    public List<Entry> findSortEntriesList(SortingComparator sortingComparator) {
        return null;
    }

    /**
     * Функция, по расписанию, каждый день в полночь, проверяющая все открытые записи на просроченность,
     * при необходимости отмечает запись как просроченную.
     * */
    @Scheduled(cron = "0 0 0 * * *")
    private void statusChangingEntry() {
        List<Entry> entryList = this.entryDao.findOpenEntriesList();

        if (entryList != null) {
            for (Entry entry : entryList) {
                if (entry.getReturnDatePlanned().isBefore(LocalDate.now())) {
                    entry.setEntryStatus(EntryStatus.EXPIRED);

                    this.entryDao.update(entry);
                }
            }
        }
    }
}
