package com.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatingEntryDto {

    private Integer idReaderCard;

    private String nameBook;

    private LocalDate returnDatePlanned;
}
