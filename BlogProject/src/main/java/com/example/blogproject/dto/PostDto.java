package com.example.blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishedAt;
    private Long userId;
    private Long categoryId;


}
