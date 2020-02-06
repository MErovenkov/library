package com.library.mapper;

import com.library.dto.PublisherDto;
import com.library.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDto convertToDto(Publisher publisher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookList", ignore = true)
    Publisher convertToEntity(PublisherDto publisherDto);

    List<PublisherDto> convertToListDto(List<Publisher> publisherList);
}
