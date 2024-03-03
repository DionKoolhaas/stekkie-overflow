package com.dion.stekkieoverflow.domain.crawler;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String cssQuery;

    @ManyToMany(mappedBy = "texts")
    @JsonIgnore
    @ToString.Exclude
    private List<Document> documents;

}
