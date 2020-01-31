package com.library.service;

import com.library.dao.interfaces.IAuthorityDao;
import com.library.dao.interfaces.IUserDao;
import com.library.model.Authority;
import com.library.model.User;
import com.library.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IAuthorityDao authorityDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    ///
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Authority> authorities = new HashSet<>();

        authorities.add(authorityDao.findOneById(1));
        user.setAuthority(authorities);

        userDao.create(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        System.out.println(user);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority authority : user.getAuthority()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getNameAuthority()));
        }

        System.out.println(user.getUsername() + "      " + user.getPassword() + "        " + grantedAuthorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
