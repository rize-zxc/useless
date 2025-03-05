//package com.example.postproject.controllers;
//
//import com.example.postproject.models.Post;
//import com.example.postproject.services.PostService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/posts")
//public class PostController {
//    private final PostService postService;
//
//    public PostController(PostService postService){
//        this.postService = postService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Post>> getAllPosts() {
//        return ResponseEntity.ok(postService.getAllPosts());
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam Long userId) {
//        return ResponseEntity.ok(postService.createPost(post, userId));
//    }
//}