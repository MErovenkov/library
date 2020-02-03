package com.library.dao;

import com.library.dao.interfaces.IGenericDao;
import com.library.model.Genre;
import com.library.model.Publisher;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public T findByName(String name) throws NoResultException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        Predicate predicate = criteriaBuilder.like(root.get("name"), name);

        return entityManager.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
    }

    public List<T> findAll(){
        CriteriaQuery<T> criteriaQuery = entityManager
                .getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    public T create(T entity){
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity){
        entityManager.merge(entity);
        return entity;
    }

    public T delete(T entity) {
        entityManager.remove(entity);
        return entity;
    }

    public T deleteById(Integer entityId) throws NullPointerException {
        T entity = findOneById(entityId);

        if(entity != null) {
            delete(entity);
        } else throw new NullPointerException();
        return entity;
    }
}
