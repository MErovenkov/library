package com.library.controller;

import com.library.dto.UserDto;
import com.library.mapper.UserMapper;
import com.library.model.User;
import com.library.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    //todo: admin
    //todo: добавлять пользователя вместе с карточкой читателя
    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return this.userMapper.convertToDto(
                this.userService.createUser(
                        this.userMapper.convertToEntity(userDto)));
    }

    //todo: admin
    //todo:
    @PutMapping("/{idUser}")
    public UserDto updateUser(@PathVariable Integer idUser,
                                     @RequestBody UserDto userDto) {
        return this.userMapper.convertToDto(
                this.userService.updateUser(
                        idUser, this.userMapper.convertToEntity(userDto)));
    }

    //todo: admin
    @DeleteMapping("/{idUser}")
    public UserDto deleteUserById(@PathVariable Integer idUser) {
        return this.userMapper.convertToDto(
                this.userService.deleteUserById(idUser));
    }

    //todo: admin
    @GetMapping("/{idUser}")
    public UserDto findUserById(@PathVariable Integer idUser) {
        return this.userMapper.convertToDto(this.userService.findUserById(idUser));
    }

    //todo: admin
    @GetMapping("/")
    public List<UserDto> findUsersList() {
        return this.userMapper.convertToListDto(this.userService.findUsersList());
    }
}
