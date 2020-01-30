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
        this.iPublisherDao.create(publisher);
    }

    @Override
    public void deletePublisherById(Integer idPublisher) {
        this.iPublisherDao.deleteById(idPublisher);
    }

    @Override
    public Publisher getPublisherById(Integer idPublisher) {
        return this.iPublisherDao.findOneById(idPublisher);
    }

    @Override
    public List<Publisher> getPublisherList() {
        return this.iPublisherDao.findAll();
    }
}
