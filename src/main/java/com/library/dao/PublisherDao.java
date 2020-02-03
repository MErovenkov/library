package com.library.dao;

import com.library.dao.interfaces.IPublisherDao;
import com.library.model.Publisher;
import com.library.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class PublisherDao extends AbstractJpaDao<Publisher> implements IPublisherDao {

    private PublisherDao(){
        setClazz(Publisher.class);
    }
}
