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

//TODO: 03.02.2020
/**
 *
 * */
@Slf4j
@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private IPublisherDao publisherDao;

    //TODO: 03.02.2020
    /**
     *
     * */
    @Override
    public Publisher createPublisher(Publisher publisher) {
        try {
            if (publisher != null) {
                if (validationCheck(publisher)) {
                    return this.publisherDao.create(publisher);
                }
            } else {
                log.warn("Попытка добавть объект равный null в издатели");
                //TODO: 02.02.2020 выбросить фронт exception
            }
        } catch (PersistenceException e) {
            //TODO: 02.02.2020 повторяющееся значение ключа нарушает ограничение уникальности ,
            // уже существует с таким именем.
        }

        return null;
    }

    //TODO: 03.02.2020
    /**
     *
     *
     * */
    @Override
    public Publisher updatePublisher(Integer idPublisher, Publisher newDataPublisher) {
        try {
            Publisher publisher = this.publisherDao.findOneById(idPublisher);

            if(publisher != null) {
                if (validationCheck(newDataPublisher)) {
                    publisher.setName(newDataPublisher.getName());
                    publisher.setAddress(newDataPublisher.getAddress());

                    return this.publisherDao.update(newDataPublisher);
                } else {
                    log.warn("Издателя с таким id не возможно изменить т.к. его не существует");
                    //TODO: 02.02.2020 выбросить фронт exception
                }
            }
        } catch (PersistenceException  e) {
            //TODO: 02.02.2020 ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности
        }
        return null;
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    private boolean validationCheck(Publisher publisher) {
        boolean validate = false;

        if(publisher.getName() != null && publisher.getAddress() != null) {
            if (!publisher.getName().trim().equals("") && !publisher.getAddress().trim().equals("")) {
                validate = true;
            } else {
                log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName() +  " с пустыми полем(ями)");
                //TODO: 02.02.2020 выбросить фронт exception
            }
        } else {
            log.warn("Попытка " + new Throwable().getStackTrace()[1].getMethodName() + " с полем(ями) равными null");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return validate;
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Publisher deletePublisherById(Integer idPublisher) {
        Publisher publisher = this.publisherDao.findOneById(idPublisher);

        if (publisher != null) {
            return this.publisherDao.deleteById(idPublisher);
        } else {
            log.warn("Издателя с таким id не существует");
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
    public Publisher findPublisherById(Integer idPublisher) {
        return this.publisherDao.findOneById(idPublisher);
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public Publisher findPublisherByName(String namePublisher) {
        try {
            return this.publisherDao.findByName(namePublisher);
        } catch (NoResultException e) {
            log.error("Запрос на поиск по имени: " + namePublisher
                    + " не выполнен, т.к. такого издателя не существует", e);

            // TODO: 02.02.2020 выбросить фронт exception
            return null;
        }
    }

    //TODO: 03.02.2020
    /**
     *  Функция
     *
     * */
    @Override
    public List<Publisher> findPublisherList() {
        return this.publisherDao.findAll();
    }
}
