package com.dion.stekkieoverflow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name="plant_id")
    private Plant plant;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answer> answers;
}
