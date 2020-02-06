package com.library.mapper;

import com.library.dto.UserDto;
import com.library.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto convertToDto(User user);

    @Mapping(target = "id", ignore = true)
    User convertToEntity(UserDto userDto);

    List<UserDto> convertToListDto(List<User> UsersList);
}
