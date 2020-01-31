package com.library.dao.interfaces;

import com.library.model.Author;

public interface IAuthorDao extends IGenericDao<Author> {

    boolean duplicateCheck(Author author);

    Author findAuthorByFullName(Author author);
}
