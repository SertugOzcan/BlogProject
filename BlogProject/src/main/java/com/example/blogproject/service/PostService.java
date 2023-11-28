package com.example.blogproject.service;

import com.example.blogproject.dto.PostDto;
import com.example.blogproject.entity.Post;
import com.example.blogproject.mapper.PostMapper;
import com.example.blogproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.toDtoList(posts);
    }

    public PostDto getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.map(postMapper::toDto).orElse(null);
    }

    public PostDto createPost(PostDto postDto) {
        Post postToCreate = postMapper.toEntity(postDto);
        Post createdPost = postRepository.save(postToCreate);
        return postMapper.toDto(createdPost);
    }

    public PostDto updatePost(Long postId, PostDto postDto) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post existingPost = postOptional.get();
            postRepository.save(existingPost);
            return postMapper.toDto(existingPost);
        } else {
            return null;
        }
    }
    public List<PostDto> getPostsByUser(Long userId) {
        List<Post> userPosts = postRepository.findByUser_Id(userId);
        return postMapper.toDtoList(userPosts);
    }

    public List<PostDto> getPostsByCategory(Long categoryId) {
        List<Post> categoryPosts = postRepository.findByCategory_Id(categoryId);
        return postMapper.toDtoList(categoryPosts);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }


    public List<PostDto> getAllPostsOrderByPublishedDate() {
        List<Post> posts = postRepository.findAllByOrderByPublishedAtDesc();
        return postMapper.toDtoList(posts);
    }


}
