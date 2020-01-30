package com.library.dao.interfaces;

import com.library.model.Authority;
import com.library.model.User;

public interface IUsersDao extends IGenericDao<User> {
    User findByUsername(String login);
    Authority findByUser(User user);
}
