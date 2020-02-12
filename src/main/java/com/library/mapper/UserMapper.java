package com.library.mapper;

import com.library.dto.UserDto;
import com.library.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "readerCardDto", source = "readerCard")
    @Mapping(target = "authorityDtoList", source = "authorityList")
    UserDto convertToDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "readerCard", ignore = true)
    @Mapping(target = "userName", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "authorityList", source = "authorityDtoList")
    User convertToEntity(UserDto userDto);

    List<UserDto> convertToListDto(List<User> UsersList);
}
