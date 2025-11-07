package com.joaoglmartins.breakfactsapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoglmartins.breakfactsapi.model.User;
import com.joaoglmartins.breakfactsapi.security.JwtUtil;
import com.joaoglmartins.breakfactsapi.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        if (userService.getUserByEmail("email") != null ) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }

        User user = userService.createUser(email, password);
        return ResponseEntity.ok(Map.of("message", "User created successfully", "username", user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        if (userService.validateCredentials(email, password)) {
            String token = jwtUtil.generateToken(email);
            return ResponseEntity.ok(Map.of("token", token, "email", email));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}
