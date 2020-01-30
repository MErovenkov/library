package com.library.dao;

import com.library.dao.interfaces.IGenericDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractJpaDao<T extends Serializable> implements IGenericDao<T> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOneById(Integer id){
        return entityManager.find(clazz, id);
    }

    public List<T> findAll(){
        CriteriaQuery<T> criteriaQuery = entityManager
                .getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    public void create(T entity){
        entityManager.persist(entity);
    }

    public T update(T entity){
        entityManager.merge(entity);
        return entity;
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(Integer entityId) throws NullPointerException {
        T entity = findOneById(entityId);
        if(entity != null) {
            delete(entity);
        } else throw new NullPointerException();
    }
}
