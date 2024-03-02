package com.dion.stekkieoverflow.dto;

public record AnswerDTO(Long id, String answerText, String userId, QuestionDTO question) {
}
