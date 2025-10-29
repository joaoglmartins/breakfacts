package com.joaoglmartins.breakfactsapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoglmartins.breakfactsapi.model.Topic;
import com.joaoglmartins.breakfactsapi.repository.TopicRepository;

@Service
@Transactional
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository topicRepository) {
        this.repository = topicRepository;
    }

    public Optional<Topic> getTopicById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Topic> getTopicByName(String name) {
        return repository.findByName(name);
    }

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }
}
