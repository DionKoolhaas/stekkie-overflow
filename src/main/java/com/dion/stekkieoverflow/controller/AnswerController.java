package com.dion.stekkieoverflow.controller;

import com.dion.stekkieoverflow.dto.AnswerDTO;
import com.dion.stekkieoverflow.mappers.AnswerMapper;
import com.dion.stekkieoverflow.repository.AnswerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private AnswerRepository answerRepository;

    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping
    public List<AnswerDTO> getAllAnswers() {
        return AnswerMapper.INSTANCE.answersToAnswerDto(answerRepository.findAll());
    }
}
