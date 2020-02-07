package com.library.service;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.model.Author;
import com.library.model.Entry;
import com.library.model.ReaderCard;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IReaderCardService;
import com.library.utils.NamingFormatter;
import com.library.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReaderCardService implements IReaderCardService {

    @Autowired
    private IReaderCardDao readerCardDao;

    @Override
    public ReaderCard updateReaderCard(Integer idReaderCard, ReaderCard newDataReaderCard) {
        try {
            if (Validator.getInstance().validationFullNameCheck(
                    newDataReaderCard.getSurname(),
                    newDataReaderCard.getName(),
                    newDataReaderCard.getPatronymic())) {
                ReaderCard readerCard = this.readerCardDao.findOneById(idReaderCard);

                if (readerCard != null) {
                    newDataReaderCard = (ReaderCard) NamingFormatter.getInstance().formatFullName(newDataReaderCard);

                    readerCard.setSurname(newDataReaderCard.getSurname());
                    readerCard.setName(newDataReaderCard.getName());
                    readerCard.setPatronymic(newDataReaderCard.getPatronymic());

                    return this.readerCardDao.update(readerCard);
                } else {
                    log.warn("Карточку с таким id не возможно изменить т.к. её не существует");
                    //TODO: 03.02.2020 выбросить фронт exception
                }
            }
        } catch (DataIntegrityViolationException e) {
            //TODO: 03.02.2020 ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности
        }

        return null;
    }

    @Override
    public ReaderCard findReaderCardById(Integer idReaderCard) {
        ReaderCard readerCard = this.readerCardDao.findOneById(idReaderCard);

        if (readerCard != null) {
            return this.readerCardDao.deleteById(idReaderCard);
        } else {
            log.warn("Карточку с таким id невозможно удалить, т.к её не существует");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    @Override
    public ReaderCard findReaderCardByFullName(String surnameSearch, String nameSearch, String patronymicSearch) {
            return null;
    }

    @Override
    public List<ReaderCard> findReadersCardsList() {
        return null;
    }

    @Override
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }

    @Override
    public List<Entry> findEntriesByReaderCard(Integer idReaderCard) {
        return null;
    }
}
