package com.library.service;

import com.library.dao.interfaces.IReaderCardDao;
import com.library.dao.interfaces.IUserDao;
import com.library.model.ReaderCard;
import com.library.model.User;
import com.library.model.enums.SortingComparator;
import com.library.service.interfaces.IReaderCardService;
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
public class ReaderCardService implements IReaderCardService {

    @Autowired
    private IReaderCardDao readerCardDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public ReaderCard createReaderCard(Integer idUser, ReaderCard readerCard) {
        try {
            User user = this.userDao.findOneById(idUser);

            if (user != null) {
                if (user.getReaderCard() == null) {
                    if (readerCard != null) {
                        if (Validator.getInstance().validationFullNameCheck(readerCard)) {
                            readerCard = (ReaderCard) NamingFormatter.getInstance().formatFullName(readerCard);

                            ReaderCard createdReaderCard = this.readerCardDao.create(readerCard);

                            user.setReaderCard(createdReaderCard);
                            this.userDao.update(user);

                            return createdReaderCard;
                        }
                    } else {
                        log.warn("");
                    }
                } else {
                    log.warn("Карточка читателя равна null");
                }
            } else {
                log.warn("Пользователь равен null т.е. не существует, невозможно добавть ему карточку читателя");
            }
        } catch (PersistenceException e) {
            log.error("Читательская карточка с такими данными уже существует" + e);
        }

        return null;
    }


    @Override
    public ReaderCard updateReaderCard(Integer idReaderCard, ReaderCard newDataReaderCard) {
        try {
            if (newDataReaderCard != null || Validator.getInstance().validationFullNameCheck(newDataReaderCard)) {
                ReaderCard readerCard = this.readerCardDao.findOneById(idReaderCard);

                if (readerCard != null) {
                    newDataReaderCard = (ReaderCard) NamingFormatter.getInstance().formatFullName(newDataReaderCard);

                    readerCard.setSurname(newDataReaderCard.getSurname());
                    readerCard.setName(newDataReaderCard.getName());
                    readerCard.setPatronymic(newDataReaderCard.getPatronymic());

                    return this.readerCardDao.update(readerCard);
                } else {
                    log.warn("Карточку с таким id не возможно изменить т.к. её не существует");
                }
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Карточка с такими данными уже существует" + e);
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
        }

        return null;
    }

    @Override
    public ReaderCard findReaderCardByFullName(String surnameSearch, String nameSearch, String patronymicSearch) {
        try {
            return this.readerCardDao.findByFullName(surnameSearch, nameSearch, patronymicSearch);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ReaderCard> findReadersCardsList() {
        return this.readerCardDao.findAll();
    }



////////////////////
    @Override
    public List<ReaderCard> findSortReadersCardsList(SortingComparator sortingComparator) {
        return null;
    }
}
