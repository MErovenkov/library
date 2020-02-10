package com.library.dao;

import com.library.dao.interfaces.IPublisherDao;
import com.library.model.Publisher;

import org.springframework.stereotype.Repository;

@Repository
public class PublisherDao extends AbstractJpaDao<Publisher> implements IPublisherDao {

    private PublisherDao(){
        setClazz(Publisher.class);
    }
}
