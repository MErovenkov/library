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
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
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
    public Entry createEntry(Integer idReaderCard, String nameBook, LocalDate returnDatePlanned) {
        try {
            ReaderCard readerCard = this.readerCardDao.findOneById(idReaderCard);
            if (readerCard != null) {
                if (readerCard.getMaxBooksTaken() > 0) {
                    Book book = this.bookDao.findIsStockBookByName(nameBook);

                    if (returnDatePlanned != null && LocalDate.now().isAfter(returnDatePlanned)) {
                        readerCard.setMaxBooksTaken(readerCard.getMaxBooksTaken() - 1);
                        Entry newEntry = new Entry(readerCard, book, returnDatePlanned);

                        return this.entryDao.create(newEntry);
                    } else {
                        log.warn("дата планир возврата, должа наступить позже чем дата создания запроса" +
                                "и не должна быть null");
                    }
                } else {
                    log.warn("лимит книг достигнут");
                }
            } else {
                log.warn("каточки нет с таким ид");
            }
        } catch (NoResultException e) {
            log.error("нет свободных книг");
        }

        return null;
    }

    @Override
    public Entry closedEntryById(Integer idEntry) {
        Entry entry = this.entryDao.findOneById(idEntry);

        if (entry != null) {
            entry.setEntryStatus(EntryStatus.CLOSE);
            entry.setReturnDate(LocalDate.now());

            stateChangingBook(entry);
            stateChangingReaderCard(entry);

            return this.entryDao.update(entry);
        }

        return null;
    }

    @Override
    public Entry deleteEntryById(Integer idEntry) {
        Entry entry = this.entryDao.findOneById(idEntry);

        if (entry != null) {

            Book book = this.bookDao.findOneById(entry.getBook().getId());
            book.setInStock(true);

            stateChangingBook(entry);
            stateChangingReaderCard(entry);

            return this.entryDao.deleteById(idEntry);
        } else {
            log.warn("Жанр с таким id невозможно удалить, т.к его не существует");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    private void stateChangingReaderCard(Entry entry) {
        ReaderCard readerCard = this.readerCardDao.findOneById(entry.getReaderCard().getId());
        readerCard.setMaxBooksTaken(readerCard.getMaxBooksTaken() + 1);

        if(entry.getEntryStatus().equals(EntryStatus.EXPIRED)) {
            readerCard.setPenalty(readerCard.getPenalty() - 1);
        }

        this.readerCardDao.update(readerCard);
    }

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

    //todo: метод
    //todo: поток проверяющий на своевременный возврат книг,
    //todo: если false ставит пенальти, делает список единовременно взятых книг меньше
    //todo: штраф действует месяц, если книга возвращена; если возврата нет, то бесрочно.
}
