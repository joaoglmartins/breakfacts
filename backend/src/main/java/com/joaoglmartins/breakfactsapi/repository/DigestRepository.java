package com.joaoglmartins.breakfactsapi.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoglmartins.breakfactsapi.model.Digest;
import com.joaoglmartins.breakfactsapi.model.User;

public interface DigestRepository extends JpaRepository<Digest, UUID> {
    List<Digest> findByUser(User user);
    List<Digest> findByRunAtBetween(OffsetDateTime start, OffsetDateTime end);
}

