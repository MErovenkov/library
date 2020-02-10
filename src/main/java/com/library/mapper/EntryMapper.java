package com.library.mapper;

import com.library.dto.EntryDto;
import com.library.model.Entry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntryMapper {

    EntryDto convertToDto(Entry entry);

    @Mapping(target = "takeDate", ignore = true)
    @Mapping(target = "returnDate", ignore = true)
    Entry convertToEntity(EntryDto entryDto);

    List<EntryDto> convertToListDto(List<Entry> entryList);
}
