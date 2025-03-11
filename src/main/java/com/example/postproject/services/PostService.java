package com.example.postproject.services;

import com.example.postproject.models.Post;
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

    // Создание пользователя
    public Post createPost(Post post){

        return postRepository.save(post);
    }

    // Получение всех пользователей
    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }

    // Получение пользователя по ID
    public Optional<Post> getPostById(Long id) {

        return postRepository.findById(id);
    }

    // Обновление пользователя
    public Post updatePost(Long id, Post userDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        post.setTitle(userDetails.getTitle());
        post.setText(userDetails.getText());
        post.setUserId(userDetails.getUserId());
        return postRepository.save(post);
    }

    // Удаление пользователя
    public void deletePost(Long id) {

        postRepository.deleteById(id);
    }

}
