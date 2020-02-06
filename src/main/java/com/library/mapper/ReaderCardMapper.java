package com.library.mapper;

import com.library.dao.ReaderCardDao;
import com.library.dto.ReaderCardDto;
import com.library.model.ReaderCard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReaderCardMapper {

    ReaderCardDto convertToDto(ReaderCard readerCard);

    ReaderCard convertToEntity(ReaderCardDto readerCardDto);

    List<ReaderCardDto> convertToListDto(List<ReaderCard> readerCardList);
}
