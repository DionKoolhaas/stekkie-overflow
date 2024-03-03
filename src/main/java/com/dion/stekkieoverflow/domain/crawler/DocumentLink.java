package com.dion.stekkieoverflow.domain.crawler;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    @ToString.Exclude
    private Document document;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id")
    private Link link;

}