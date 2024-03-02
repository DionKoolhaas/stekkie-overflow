package com.dion.stekkieoverflow.repository;

import com.dion.stekkieoverflow.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
