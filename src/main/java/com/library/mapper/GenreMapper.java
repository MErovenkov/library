package com.library.mapper;

import com.library.dto.GenreDto;
import com.library.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto convertToDto(Genre genre);

    @Mapping(target = "id", ignore = true)
    Genre convertToEntity(GenreDto genreDto);

    List<GenreDto> convertToListDto(List<Genre> genreList);
}
