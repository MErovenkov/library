package com.library.dto;

import com.library.model.Authority;
import com.library.model.ReaderCard;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String userName;

    private String password;

    private ReaderCard readerCard;

    private Set<Authority> authority;
}
