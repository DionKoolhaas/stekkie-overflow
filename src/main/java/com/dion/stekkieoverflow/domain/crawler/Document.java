package com.dion.stekkieoverflow.domain.crawler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    private Link link;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<DocumentLink> linksInDocument;

    public List<DocumentLink> getLinksInDocument(){
        if (this.linksInDocument == null) {
            this.linksInDocument = new ArrayList<>();
        }
        return linksInDocument;
    }
}