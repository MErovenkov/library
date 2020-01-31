package com.library.dao.interfaces;

import com.library.model.User;

public interface IUserDao extends IGenericDao<User> {
    User findByUsername(String login);
}
