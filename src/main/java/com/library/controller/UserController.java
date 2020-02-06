package com.library.controller;

import com.library.mapper.UserMapper;
import com.library.model.User;
import com.library.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    //todo: admin
    //todo: добавлять пользователя вместе с карточкой читателя
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    //todo: admin
    //todo:
    @PutMapping("/{idUser}")
    public User updateUser(@PathVariable Integer idUser,
                                     @RequestBody User user) {
        return this.userService.updateUser(idUser, user);
    }

    //todo: admin
    @DeleteMapping("/{idUser}")
    public User deleteUserById(@PathVariable Integer idUser) {
        return this.userService.deleteUserById(idUser);
    }

    //todo: admin
    //dropPassword??

    //todo: user
    //todo: setPassword


}
