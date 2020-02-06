package com.library.service.interfaces;

import com.library.model.User;

public interface IUserService {

    User createUser(User user);

    User updateUser(Integer idUser, User user);

    User deleteUserById(Integer idUser);
}
