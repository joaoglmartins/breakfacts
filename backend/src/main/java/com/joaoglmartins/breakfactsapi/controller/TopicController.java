package com.joaoglmartins.breakfactsapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoglmartins.breakfactsapi.model.Topic;
import com.joaoglmartins.breakfactsapi.service.TopicService;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "http://localhost:4200")
public class TopicController {
	private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(service.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable UUID id) {
        return service.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
