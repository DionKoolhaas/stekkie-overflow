package com.dion.stekkieoverflow.controller;

import com.dion.stekkieoverflow.dto.AnswerDTO;
import com.dion.stekkieoverflow.service.AnswerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for the Answer resource
 */
@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * Get all answers, no pagination yet.
     * @return
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }
}
