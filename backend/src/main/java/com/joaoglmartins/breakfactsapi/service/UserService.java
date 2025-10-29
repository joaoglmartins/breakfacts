package com.joaoglmartins.breakfactsapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.joaoglmartins.breakfactsapi.model.User;
import com.joaoglmartins.breakfactsapi.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}

	public User createUser(String email, String passwordHash) {
        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
        User user = new User(email, passwordHash);
        return repository.save(user);
    }
	
	public Optional<User> getUserById(UUID id) {
        return repository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
		return repository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User updateUser(UUID id, String newEmail, String newPasswordHash) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + id));
        if (newEmail != null) user.setEmail(newEmail);
        if (newPasswordHash != null) user.setPasswordHash(newPasswordHash);
        return repository.save(user);
    }

    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
