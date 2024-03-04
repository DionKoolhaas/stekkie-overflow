package com.dion.stekkieoverflow.mapper;

import com.dion.stekkieoverflow.domain.Answer;
import com.dion.stekkieoverflow.dto.AnswerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper( AnswerMapper.class);

    AnswerDTO answerToAnswerDTO(Answer answer);

    List<AnswerDTO> answersToAnswerDto(List<Answer> answers);
}
