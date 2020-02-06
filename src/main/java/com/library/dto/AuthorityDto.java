package com.library.dto;


import com.library.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthorityDto {

    private Integer id;

    private String nameAuthority;

    private Set<User> users;
}
