package com.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private Integer id;

    private String name;

    private AuthorDto author;

    private GenreDto genre;

    private PublisherDto publisher;

    private String shortSpecification;

    private Integer numberPages;

    private boolean inStock;
}
