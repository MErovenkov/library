package com.library.mapper;

import com.library.dto.AuthorDto;
import com.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "genreDtoList", source = "genreList")
    AuthorDto convertToDto(Author author);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genreList", ignore = true)
    @Mapping(target = "bookList", ignore = true)
    Author convertToEntity(AuthorDto authorDto);

    List<AuthorDto> convertToListDto(List<Author> authorList);
}
