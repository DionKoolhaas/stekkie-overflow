package com.dion.stekkieoverflow.service;

import com.dion.stekkieoverflow.dto.AnswerDTO;
import com.dion.stekkieoverflow.mapper.AnswerMapper;
import com.dion.stekkieoverflow.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<AnswerDTO> getAllAnswers() {
        return AnswerMapper.INSTANCE.answersToAnswerDto(answerRepository.findAll());
    }
}
