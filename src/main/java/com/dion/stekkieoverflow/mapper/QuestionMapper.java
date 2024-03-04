package com.dion.stekkieoverflow.mapper;

import com.dion.stekkieoverflow.domain.Question;
import com.dion.stekkieoverflow.dto.QuestionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper( QuestionMapper.class);
    QuestionDTO questionToQuestionDTO(Question question);

    List<QuestionDTO> questionToQuestionDtos(List<Question> answers);
}
