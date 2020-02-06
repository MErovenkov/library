package com.library.dao.interfaces;

import java.io.Serializable;

public interface ISearchingByFullName <T extends Serializable> {
    T findByFullName(String surnameSearch, String nameSearch, String patronymicSearch);
}
