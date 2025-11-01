package com.joaoglmartins.breakfactsapi.dto;

public class AiRequest {
    private String topicId;
    private String prompt;

    public String getTopicId() { return topicId; }
    public void setTopicId(String topicId) { this.topicId = topicId; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}
