package com.library.dao.interfaces;

import com.library.model.Author;

public interface IAuthorDao extends IGenericDao<Author> {

    Author findAuthorByFullName(Author author);
}
