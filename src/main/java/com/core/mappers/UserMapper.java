package com.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.core.dto.SignUpDto;
import com.core.dto.UserDto;
import com.core.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
