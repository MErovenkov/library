package com.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDto {

    private Integer id;

    private String surname;

    private String name;

    private String patronymic;

    private List<GenreDto> genreDtoList;
}
