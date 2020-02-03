package com.library.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    T findOneById(Integer id);

    T findByName(String name);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    T delete(T entity);

    T deleteById(Integer entityId);

}
