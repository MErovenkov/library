package com.library.dto;

import com.library.model.Entry;
import com.library.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReaderCardDto {

    private Integer id;

    private String surname;

    private String name;

    private String patronymic;

    private Integer numberPhone;

    private String email;

    private Integer penalty;

    private Integer maxBooksTaken;
}
