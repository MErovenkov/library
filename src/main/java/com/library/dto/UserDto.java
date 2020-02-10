package com.library.dto;

import com.library.model.Authority;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String username;

    private String password;

    private ReaderCardDto readerCardDto;

    private List<AuthorityDto> authorityDtoList;
}
