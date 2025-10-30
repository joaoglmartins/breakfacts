package com.joaoglmartins.breakfactsapi.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoglmartins.breakfactsapi.model.User;
import com.joaoglmartins.breakfactsapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String passwordHash = request.get("passwordHash");
        User created = service.createUser(email, passwordHash);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody Map<String, String> request) {
        String email = request.get("email");
        String passwordHash = request.get("passwordHash");
        User updated = service.updateUser(id, email, passwordHash);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{userId}/topics/{topicId}")
    public ResponseEntity<Void> addTopic(@PathVariable UUID userId, @PathVariable UUID topicId) {
        service.addTopicToUser(userId, topicId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/topics/{topicId}")
    public ResponseEntity<Void> removeTopic(@PathVariable UUID userId, @PathVariable UUID topicId) {
        service.removeTopicFromUser(userId, topicId);
        return ResponseEntity.noContent().build();
    }
}
