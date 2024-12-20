package com.chat.gemini_chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnaService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;
    @Value("${gemini.api.url}")
    private String geminiUrl;

    private final WebClient.Builder webClient;

    public QnaService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    public String getAnswer(String question) {
        Map<String, Object> map = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                       Map.of("text", question)
                                }
                        )
                }
        );

       String response = webClient.build().post().uri(geminiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(map)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
