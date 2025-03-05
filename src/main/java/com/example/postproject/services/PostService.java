//package com.example.postproject.services;
//
//import com.example.postproject.models.Post;
//import com.example.postproject.models.User;
//import com.example.postproject.repository.PostRepository;
//import org.springframework.stereotype.Service;
//import com.example.postproject.repository.UserRepository;
//
//import java.util.List;
//
//@Service
//public class PostService {
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//    public PostService(PostRepository postRepository, UserRepository userRepository) {
//        this.postRepository = postRepository;
//        this.userRepository = userRepository;
//    }
//
//    public List<Post> getAllPosts() {
//        return postRepository.findAll();
//    }
//
//    public Post createPost(Post post) {
//
//        return postRepository.save(post);
//    }public Post createPost(Post post, Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        post.setUser(user);
//        return postRepository.save(post);
//    }
//}