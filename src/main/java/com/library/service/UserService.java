package com.library.service;

import com.library.dao.interfaces.IUserDao;
import com.library.model.*;
import com.library.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        try {
            if (user != null) {
                if (validationCheck(user)) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return this.userDao.create(user);
                }
            } else {
                log.warn("Пользователя равеного null добваить невозможно");
            }
        } catch (PersistenceException e) {
            log.error("Пользователь с таким username уже сужествует" + e);
        }

        return null;
    }

    @Override
    public User updateUser(Integer idUser, User newDataUser) {
        try {
            User user = this.userDao.findOneById(idUser);

            if (user != null) {
                if (validationCheck(user)) {
                        user.setUserName(newDataUser.getUsername());
                        user.setPassword(newDataUser.getPassword());

                        return this.userDao.update(user);
                    }
            } else {
                log.warn("Пользователя с таким id не возможно изменить т.к. его не существует");
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Пользователь с таким username уже сужествует" + e);
        }

        return null;
    }

    private boolean validationCheck(User user) {
        boolean validate = false;

        if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
            if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
                validate = true;
            } else {
                log.warn("Username равен null или пуст" + new Throwable().getStackTrace()[1]);
            }
        } else {
            log.warn("Пароль пользователя null или пуст" + new Throwable().getStackTrace()[1]);
        }

        return validate;
    }

    @Override
    public User deleteUserById(Integer idUser) {
        User user = this.userDao.findOneById(idUser);

        if (user != null) {
            return this.userDao.deleteById(idUser);
        } else {
            log.warn("Пользователя с таким id невозможно удалить, т.к его не существует");
        }

        return null;
    }

    @Override
    public User findUserById(Integer idUser) {
        User user = this.userDao.findOneById(idUser);

        if (user != null) {
            return user;
        } else {
            log.warn("Пользователя с таким id невозможно найти, т.к его не существует");
        }

        return null;
    }

    @Override
    public List<User> findUsersList() {
        return this.userDao.findAll();
    }

}
