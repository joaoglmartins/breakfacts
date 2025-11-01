package com.joaoglmartins.breakfactsapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoglmartins.breakfactsapi.dto.AiRequest;
import com.joaoglmartins.breakfactsapi.service.AiService;

@RestController
@RequestMapping("/api/ai")
public class AiController {
	
	private final AiService service;
	
	public AiController(AiService service) {
		this.service = service;
	}
    
	@PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generate(@RequestBody AiRequest request) {
        String result = service.generateText(request.getTopicId(), request.getPrompt());
        return ResponseEntity.ok(Map.of("text", result));
    }
}
