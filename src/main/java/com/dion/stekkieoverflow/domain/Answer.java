package com.dion.stekkieoverflow.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
}
