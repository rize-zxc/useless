package com.example.postproject.controllers;

import com.example.postproject.models.Post;
import com.example.postproject.models.User;
import com.example.postproject.services.PostService;
import com.example.postproject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final StatusController statusController;

    public PostController(PostService postService, UserService userService, StatusController statusController) {
        this.postService = postService;
        this.userService = userService;
        this.statusController = statusController;
    }

    // Создание поста
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Post post, @RequestParam Long userId) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return ResponseEntity.ok(postService.createPost(post, user));
    }

    // Получение всех постов
    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // Получение поста по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Обновление поста
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        return ResponseEntity.ok(postService.updatePost(id, postDetails));
    }

    // Удаление поста
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}