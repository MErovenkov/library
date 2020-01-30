package com.library.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    T findOneById(Integer id);

    List<T> findAll();

    void create(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(Integer entityId);

}
