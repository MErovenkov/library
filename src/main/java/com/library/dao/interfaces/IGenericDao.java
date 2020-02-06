package com.library.dao.interfaces;

import com.library.model.enums.SortingComparator;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    T create(T entity);

    T update(T entity);

    T delete(T entity);

    T deleteById(Integer entityId);

    T findOneById(Integer id);

    List<T> findAll();

    List<T> findSortAll(SortingComparator sortingComparator);

}
