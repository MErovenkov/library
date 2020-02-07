package com.library.service;

import com.library.dao.interfaces.IAuthorityDao;
import com.library.dao.interfaces.IReaderCardDao;
import com.library.dao.interfaces.IUserDao;
import com.library.model.Author;
import com.library.model.Authority;
import com.library.model.Entry;
import com.library.model.User;
import com.library.service.interfaces.IUserService;
import com.library.utils.NamingFormatter;
import com.library.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority authority : user.getAuthorityList()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getNameAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public User createUser(User user) {
        try {
            if (user != null) {
                if (user.getReaderCard() != null) {
                    if (Validator.getInstance().validationFullNameCheck(
                            user.getReaderCard().getSurname(),
                            user.getReaderCard().getName(),
                            user.getReaderCard().getPatronymic())) {
                        user.getReaderCard().setPenalty(0);
                        user.getReaderCard().setMaxBooksTaken(3);

                        this.readerCardDao.create(user.getReaderCard());

                        return this.userDao.create(user);
                    }
                } else {
                    log.warn("");
                }
            } else {
                log.warn("");
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
            log.warn("Жанр с таким id невозможно удалить, т.к его не существует");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    @Override
    public User findUserById(Integer idUser) {
        User user = this.userDao.findOneById(idUser);

        if (user != null) {
            return this.userDao.findOneById(idUser);
        } else {
            log.warn("Автора с таким id невозможно удалить, т.к его не существует");
            //TODO: 02.02.2020 выбросить фронт exception
        }

        return null;
    }

    @Override
    public List<User> findUsersList() {
        return this.userDao.findAll();
    }
}
