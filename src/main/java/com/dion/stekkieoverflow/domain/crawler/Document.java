package com.dion.stekkieoverflow.domain.crawler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<DocumentLink> linksInDocument;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "document_text",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "text_id")
    )
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    @ToString.Exclude
    private List<Text> texts;

    public List<DocumentLink> getLinksInDocument(){
        if (this.linksInDocument == null) {
            this.linksInDocument = new ArrayList<>();
        }
        return linksInDocument;
    }

    @JsonIgnore
    public List<Text> getTexts(){
        if (this.texts == null) {
            this.texts = new ArrayList<>();
        }
        return texts;
    }
}