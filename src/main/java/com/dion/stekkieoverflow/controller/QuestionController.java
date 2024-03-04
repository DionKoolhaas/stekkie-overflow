package com.dion.stekkieoverflow.controller;

import com.dion.stekkieoverflow.domain.Difficulty;
import com.dion.stekkieoverflow.dto.PlantDTO;
import com.dion.stekkieoverflow.dto.QuestionDTO;
import com.dion.stekkieoverflow.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/search/find-a-question-to-answer")
    public List<QuestionDTO> findAQuestion(@RequestParam Difficulty difficulty) {
        return questionService.findAQuestion(difficulty);
    }
}
