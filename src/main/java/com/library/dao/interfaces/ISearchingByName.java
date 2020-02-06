package com.library.dao.interfaces;

import java.io.Serializable;

public interface ISearchingByName <T extends Serializable> {
    public T findByName(String name);
}
