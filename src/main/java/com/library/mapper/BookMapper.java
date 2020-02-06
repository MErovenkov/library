package com.library.mapper;

import com.library.dto.BookDto;
import com.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto convertToDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book convertToEntity(BookDto bookDto);

    List<BookDto> convertToListDto(List<Book> bookList);
}
