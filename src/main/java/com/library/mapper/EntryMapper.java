package com.library.mapper;

import com.library.dto.EntryDto;
import com.library.model.Entry;
import org.mapstruct.Mapper;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    EntryDto convertToDto(Entry entry);

    Entry convertToEntity(EntryDto entryDto);

    List<EntryDto> convertToListDto(List<Entry> entryList);
}
