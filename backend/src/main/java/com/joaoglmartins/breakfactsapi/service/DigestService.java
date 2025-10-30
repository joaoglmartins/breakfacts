package com.joaoglmartins.breakfactsapi.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoglmartins.breakfactsapi.model.Digest;
import com.joaoglmartins.breakfactsapi.model.User;
import com.joaoglmartins.breakfactsapi.repository.DigestRepository;
import com.joaoglmartins.breakfactsapi.repository.UserRepository;

@Service
@Transactional
public class DigestService {

    private final DigestRepository digestRepository;
    private final UserRepository userRepository;

    public DigestService(DigestRepository digestRepository, UserRepository userRepository) {
        this.digestRepository = digestRepository;
        this.userRepository = userRepository;
    }

    public Digest createDigest(UUID userId, OffsetDateTime runAt, String contentJson) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
        Digest digest = new Digest(user, runAt, contentJson);
        return digestRepository.save(digest);
    }

    public Optional<Digest> getDigestById(UUID id) {
        return digestRepository.findById(id);
    }

    public List<Digest> getDigestsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
        return digestRepository.findByUser(user);
    }

    public List<Digest> getDigestsInRange(OffsetDateTime start, OffsetDateTime end) {
        return digestRepository.findByRunAtBetween(start, end);
    }

    public void deleteDigest(UUID id) {
        digestRepository.deleteById(id);
    }
}
