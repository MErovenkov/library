package com.library.dto;

import com.library.model.AuthorGenre;
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

    //todo:
    private List<GenreDto> genreDtoList;
}
