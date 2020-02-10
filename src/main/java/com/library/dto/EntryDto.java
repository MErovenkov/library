package com.library.dto;

import com.library.model.enums.EntryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EntryDto {

    private Integer id;

    private ReaderCardDto readerCard;

    private BookDto book;

    private LocalDate takeDate;

    private LocalDate returnDatePlanned;

    private LocalDate returnDate;

    private EntryStatus entryStatus;
}
