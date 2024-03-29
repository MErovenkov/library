package com.library.service;

import com.library.dao.interfaces.IPublisherDao;
import com.library.model.Publisher;
import com.library.service.interfaces.IPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

@Slf4j
@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private IPublisherDao publisherDao;

    @Override
    public Publisher createPublisher(Publisher publisher) {
        try {
            if (publisher != null) {
                if (validationCheck(publisher)) {
                    return this.publisherDao.create(publisher);
                }
            } else {
                log.warn("Попытка добавть объект равный null в издатели");
            }
        } catch (PersistenceException e) {
            log.error("Издатель с таким данными уже существует" + e);
        }

        return null;
    }

    @Override
    public Publisher updatePublisher(Integer idPublisher, Publisher newDataPublisher) {
        try {
            if (validationCheck(newDataPublisher)) {
                Publisher publisher = this.publisherDao.findOneById(idPublisher);

                if (publisher != null) {
                    publisher.setName(newDataPublisher.getName());
                    publisher.setAddress(newDataPublisher.getAddress());

                    return this.publisherDao.update(publisher);
                } else {
                    log.warn("Издателя с таким id не возможно изменить т.к. его не существует");
                }
            }
        } catch (PersistenceException  e) {
            log.error("Издатель с таким данными уже существует" + e);
        }

        return null;
    }

    /**
     * Функция, проверяющая данные издателя на валидность
     * */
    private boolean validationCheck(Publisher publisher) {
        boolean validate = false;

        if(publisher.getName() != null && publisher.getAddress() != null) {
            if (!publisher.getName().trim().equals("") && !publisher.getAddress().trim().equals("")) {
                validate = true;
            } else {
                log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                        +  " с пустыми полем(ями)"
                        + new Throwable().getStackTrace()[1]);
            }
        } else {
            log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName()
                    + " с полем(ями) равными null"
                    + new Throwable().getStackTrace()[1]);
        }

        return validate;
    }

    @Override
    public Publisher deletePublisherById(Integer idPublisher) {
        Publisher publisher = this.publisherDao.findOneById(idPublisher);

        if (publisher != null) {
            return this.publisherDao.delete(publisher);
        } else {
            log.warn("Издателя с таким id не существует");
        }

        return null;
    }

    @Override
    public Publisher findPublisherById(Integer idPublisher) {
        return this.publisherDao.findOneById(idPublisher);
    }

    @Override
    public Publisher findPublisherByName(String namePublisher) {
        try {
            return this.publisherDao.findByName(namePublisher);
        } catch (NoResultException e) {
            log.error("Запрос на поиск по имени: " + namePublisher
                    + " не выполнен, т.к. такого издателя не существует", e);

            return null;
        }
    }

    @Override
    public List<Publisher> findPublishersList() {
        return this.publisherDao.findAll();
    }
}
