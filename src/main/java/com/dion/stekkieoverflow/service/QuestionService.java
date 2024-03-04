package com.dion.stekkieoverflow.service;

import com.dion.stekkieoverflow.domain.Difficulty;
import com.dion.stekkieoverflow.domain.Question;
import com.dion.stekkieoverflow.dto.QuestionDTO;
import com.dion.stekkieoverflow.mapper.QuestionMapper;
import com.dion.stekkieoverflow.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> findAQuestion(Difficulty difficulty) {
        List<Question> plants = switch (difficulty) {
            // So far we don't have hard questions
            case HARD -> new ArrayList<>();
            default -> questionRepository.findAll();
        };
        return QuestionMapper.INSTANCE.questionToQuestionDtos(plants);
    }

}
