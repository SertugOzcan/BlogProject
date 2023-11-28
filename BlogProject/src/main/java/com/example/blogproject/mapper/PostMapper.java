package com.example.blogproject.mapper;

import com.example.blogproject.dto.PostDto;
import com.example.blogproject.entity.Post;
import com.example.blogproject.entity.User;
import com.example.blogproject.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "category", source = "categoryId")
    Post toEntity(PostDto postDto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    PostDto toDto(Post post);

    List<PostDto> toDtoList(List<Post> posts);

    default User map(Long value) {
        User user = new User();
        user.setId(value);
        return user;
    }

    default Category mapCategory(Long value) {
        Category category = new Category();
        category.setId(value);
        return category;
    }

}
