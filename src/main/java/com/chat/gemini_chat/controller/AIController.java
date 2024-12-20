package com.chat.gemini_chat.controller;


import com.chat.gemini_chat.service.QnaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai/qna")
public class AIController {

    private QnaService qnaService;

    public AIController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping
    public ResponseEntity<String> getAnswer(@RequestBody Map<String, String> prompt) {
        String question = prompt.get("question");
        String answer = qnaService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }
}
