package com.library.dto;

import com.library.model.Entry;
import com.library.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReaderCardDto {

    private Integer id;

    private Integer numberPhone;

    private String email;

    private Integer penalty;

    private Integer maxBooksTaken;

    private User user;
}
