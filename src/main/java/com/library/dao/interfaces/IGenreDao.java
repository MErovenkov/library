package com.library.dao.interfaces;

import com.library.model.Genre;

public interface IGenreDao extends IGenericDao<Genre> {

    Genre findGenreByName(String nameGenre);
}
