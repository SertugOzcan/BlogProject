package com.example.blogproject.mapper;

import com.example.blogproject.dto.UserDto;
import com.example.blogproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "posts", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "posts", ignore = true)
    List<Long> postsToIds(List<User> users);

    default Long map(User value) {
        return value.getId();
    }

    @Mapping(target = "posts", ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "posts", ignore = true)
    List<UserDto> toDtoList(List<User> users);


}
