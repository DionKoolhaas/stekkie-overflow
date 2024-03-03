package com.dion.stekkieoverflow.repository;

import com.dion.stekkieoverflow.domain.Answer;
import com.dion.stekkieoverflow.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}