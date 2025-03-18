package com.example.postproject.services;

import com.example.postproject.models.Post;
import com.example.postproject.models.User;
import com.example.postproject.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    // Создание поста
    public Post createPost(Post post, User user){
        post.setUser(user); // Устанавливаем пользователя для поста
        return postRepository.save(post);
    }

    // Получение всех постов
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Получение поста по ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Обновление поста
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        post.setTitle(postDetails.getTitle());
        post.setText(postDetails.getText());
        post.setUser(postDetails.getUser()); // Обновляем пользователя
        return postRepository.save(post);
    }

    // Удаление поста
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}