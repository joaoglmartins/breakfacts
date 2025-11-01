package com.joaoglmartins.breakfactsapi.service;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joaoglmartins.breakfactsapi.repository.TopicRepository;

import jakarta.transaction.Transactional;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Transactional
public class AiService {
	
	private final TopicRepository topicRepository;
	
	@Value("${ai.api.key}")
	private String AiApiKey;
	private String model = "katanemo/Arch-Router-1.5B:hf-inference";
	
	public AiService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
	
	public String generateText(String topicId, String prompt){
		
		String topicName = getTopicNameById(topicId);
		
		OkHttpClient client = new OkHttpClient();
		
		new JSONObject()
        .put("messages", new JSONArray()
            .put(new JSONObject()
                .put("role", "user")
                .put("content", "What is the capital of France?")
            )
        )
        .put("model", "katanemo/Arch-Router-1.5B:hf-inference")
        .put("stream", false);

        JSONObject body = new JSONObject()
            .put("messages", new JSONArray()
            		.put(new JSONObject()
	            		.put("role", "user")
	            		.put("content", (prompt != null && !prompt.isBlank()) ? prompt : "Tell me an interesting and random fact about " + topicName + ". Be verbose about it, present various lines of thought if possible and give me the sources of said fact.")))
            .put("model", model)
        	.put("stream", false);

        Request request = new Request.Builder()
            .url("https://router.huggingface.co/v1/chat/completions")
            .header("Authorization", "Bearer " + AiApiKey)
            .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
            .build();
        
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Request failed: " + response);
            }

            String responseBody = response.body().string();
            JSONObject jsonObj = new JSONObject(responseBody);
            return (jsonObj.getJSONArray("choices")).getJSONObject(0).getJSONObject("message").getString("content");
        } catch (IOException e) {
        	return "Something went wrong!";
        }
	}
	
	private String getTopicNameById(String id) {
		try {
			return topicRepository.findById(UUID.fromString(id)).get().getName();			
		} catch (Exception e) {
			return "Interesting facts";
		}
	}
	
}
