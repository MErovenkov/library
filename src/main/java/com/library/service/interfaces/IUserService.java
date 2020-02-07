package com.library.service.interfaces;

import com.library.model.User;

import java.util.List;

public interface IUserService {

    User createUser(User user);

    User updateUser(Integer idUser, User user);

    User deleteUserById(Integer idUser);

    User findUserById(Integer idUser);

    List<User> findUsersList();
}
