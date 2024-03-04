package com.dion.stekkieoverflow.service;

import com.dion.stekkieoverflow.domain.Difficulty;
import com.dion.stekkieoverflow.domain.Question;
import com.dion.stekkieoverflow.dto.QuestionDTO;
import com.dion.stekkieoverflow.repository.QuestionRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.mockito.Mockito.*;

class QuestionServiceTest {

    private QuestionRepository questionRepository;

    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        questionRepository = mock(QuestionRepository.class);
        questionService = new QuestionService(questionRepository);
    }

    @Test
    void findAHardQuestionTest() {
        List<QuestionDTO> questions = questionService.findAQuestion(Difficulty.HARD);
        assertEquals(questions.size(), 0);
    }

    @Test
    void findAnEasyQuestionTest() {
        when(questionRepository.findAll()).thenReturn(Lists.newArrayList(new Question()));
        List<QuestionDTO> questions = questionService.findAQuestion(Difficulty.EASY);
        assertEquals(questions.size(), 1);
    }
}
