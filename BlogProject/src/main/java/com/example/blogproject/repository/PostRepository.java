package com.example.blogproject.repository;

import com.example.blogproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_Id(Long userId);
    List<Post> findByCategory_Id(Long categoryId);
    List<Post> findAllByOrderByPublishedAtDesc();
}


