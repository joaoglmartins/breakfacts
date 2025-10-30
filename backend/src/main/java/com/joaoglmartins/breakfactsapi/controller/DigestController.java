package com.joaoglmartins.breakfactsapi.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaoglmartins.breakfactsapi.model.Digest;
import com.joaoglmartins.breakfactsapi.service.DigestService;

@RestController
@RequestMapping("/api/digests")
public class DigestController {

    private final DigestService service;

    public DigestController(DigestService digestService) {
        this.service = digestService;
    }

    @PostMapping
    public ResponseEntity<Digest> createDigest(@RequestBody Map<String, Object> request) {
        UUID userId = UUID.fromString(request.get("userId").toString());
        OffsetDateTime runAt = OffsetDateTime.parse(request.get("runAt").toString());
        String contentJson = request.get("contentJson").toString();

        Digest created = service.createDigest(userId, runAt, contentJson);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Digest>> getUserDigests(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getDigestsForUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Digest> getDigestById(@PathVariable UUID id) {
        return service.getDigestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/range")
    public ResponseEntity<List<Digest>> getDigestsInRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) {
        OffsetDateTime startTime = OffsetDateTime.parse(start);
        OffsetDateTime endTime = OffsetDateTime.parse(end);
        return ResponseEntity.ok(service.getDigestsInRange(startTime, endTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDigest(@PathVariable UUID id) {
        service.deleteDigest(id);
        return ResponseEntity.noContent().build();
    }
}
