package com.library.service;

import com.library.dao.interfaces.IPublisherDao;
import com.library.model.Publisher;
import com.library.service.interfaces.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private IPublisherDao iPublisherDao;

    @Override
    public void createPublisher(Publisher publisher) {

        /// todo: проверка на пустое имя и пустой адрес

        this.iPublisherDao.create(publisher);
    }

    @Override
    public Publisher updatePublisher(Integer idPublisher, Publisher newDataPublisher) {

        // todo: проверка на наличие publisher'а которого надо update

        Publisher publisher = this.iPublisherDao.findOneById(idPublisher);

        publisher.setName(newDataPublisher.getName());
        publisher.setName(newDataPublisher.getName());

        return this.iPublisherDao.update(publisher);
    }

    @Override
    public void deletePublisherById(Integer idPublisher) {

        // todo: проверка на наличие publisher'а

        this.iPublisherDao.deleteById(idPublisher);
    }

    @Override
    public Publisher findPublisherById(Integer idPublisher) {

        // todo: проверка на наличие publisher'а

        return this.iPublisherDao.findOneById(idPublisher);
    }


    // todo: возможно стоит убрать
    @Override
    public Publisher findPublisherByName(String namePublisher) {

        // todo: проверка на наличие publisher'а

        return this.iPublisherDao.findPublisherByName(namePublisher);
    }

    @Override
    public List<Publisher> findPublisherList() {

        // todo: проверка на наличие publisherList

        return this.iPublisherDao.findAll();
    }
}
