package com.example.blogproject.controller;

import com.example.blogproject.dto.PostDto;
import com.example.blogproject.entity.Post;
import com.example.blogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        @GetMapping("/user/{userId}")
        public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
            List<PostDto> userPosts = postService.getPostsByUser(userId);
            return ResponseEntity.ok(userPosts);
        }

        @GetMapping("/category/{categoryId}")
        public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId) {
            List<PostDto> categoryPosts = postService.getPostsByCategory(categoryId);
            return ResponseEntity.ok(categoryPosts);
        }

        @GetMapping("/posts")
        public ResponseEntity<List<PostDto>> getAllPostsOrderByPublishedDate() {
            List<PostDto> posts = postService.getAllPostsOrderByPublishedDate();
            return ResponseEntity.ok(posts);
        }
    }

