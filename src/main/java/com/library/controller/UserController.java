package com.library.controller;

import com.library.model.User;
import com.library.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    //todo: добавлять пользователя вместе с карточкой читателя
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    //todo:
    @PutMapping("/{idUser}")
    public User updateUser(@PathVariable Integer idUser,
                                     @RequestBody User user) {
        return this.userService.updateUser(idUser, user);
    }

    @DeleteMapping("/{idUser}")
    public User deleteUserById(@PathVariable Integer idUser) {
        return this.userService.deleteUserById(idUser);
    }

    //todo: setPassword, dropPassword??


}
