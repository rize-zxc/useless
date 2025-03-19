package com.example.postproject.controllers;

import com.example.postproject.models.User;
import com.example.postproject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final StatusController statusController;

    public UserController(UserService userService, StatusController statusController) {
        this.userService = userService;
        this.statusController = statusController;
    }

    // Создание пользователя
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    // Получение всех пользователей
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Получение пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Обновление пользователя
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    // Удаление пользователя
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!statusController.isServerAvailable()) {
            return ResponseEntity.status(503).body("Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}