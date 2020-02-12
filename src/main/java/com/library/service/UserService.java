package com.library.service;

import com.library.dao.interfaces.IAuthorityDao;
import com.library.dao.interfaces.IReaderCardDao;
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
    private IReaderCardDao readerCardDao;

    @Autowired
    private IAuthorityDao authorityDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByUsername(username);

        System.out.println(username);
        System.out.println(user.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
/*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByUsername(username);

        System.out.println(user);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority authority : user.getAuthorityList()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getNameAuthority()));
        }

        System.out.println(user.getUsername() + "      " + user.getPassword() + "        " + grantedAuthorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }*/

    @Override
    public User createUser(User user) {
        try {
            if (user != null) {
                return this.userDao.create(user);
            }
        } catch (PersistenceException e) {
            //TODO: 03.02.2020 повторяющееся значение ключа нарушает ограничение уникальности
        }

        return null;
    }

    @Override
    public User updateUser(Integer idUser, User newDataUser) {
        try {
            User user = this.userDao.findOneById(idUser);

            if (user != null) {
                if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
                    if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
                        user.setUserName(newDataUser.getUsername());
                        user.setPassword(newDataUser.getPassword());

                        return this.userDao.update(user);
                    } else {
                        log.warn("узернейм нул или пуст");
                    }
                } else {
                    log.warn("Пароль пользоватебя нул или пуст");
                }
            } else {
                log.warn("Пользователя с таким id не возможно изменить т.к. его не существует");
                //TODO: 03.02.2020 выбросить фронт exception
            }

        } catch (DataIntegrityViolationException e) {
            //TODO: 03.02.2020 ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности
        }

        return null;
    }

    @Override
    public User deleteUserById(Integer idUser) {
        User user = this.userDao.findOneById(idUser);

        if (user != null) {
            return this.userDao.deleteById(idUser);
        } else {
            log.warn("Пользователя с таким id невозможно удалить, т.к его не существует");
            //TODO: 02.02.2020 выбросить фронт exception
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
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    @Override
    public List<User> findUsersList() {
        return this.userDao.findAll();
    }

}
