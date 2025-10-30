package com.joaoglmartins.breakfactsapi.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "digests")
public class Digest {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "run_at", nullable = false)
    private OffsetDateTime runAt;

    @Column(name = "content_json", nullable = false, columnDefinition = "jsonb")
    private String contentJson;

    // Constructors
    public Digest() {}

    public Digest(User user, OffsetDateTime runAt, String contentJson) {
        this.user = user;
        this.runAt = runAt;
        this.contentJson = contentJson;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public OffsetDateTime getRunAt() { return runAt; }
    public void setRunAt(OffsetDateTime runAt) { this.runAt = runAt; }
    public String getContentJson() { return contentJson; }
    public void setContentJson(String contentJson) { this.contentJson = contentJson; }
}
