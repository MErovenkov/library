package com.library.service.interfaces;

import com.library.model.User;

public interface IUserService {

    void addUser(User user);

    User findByUsername(String username);
}
