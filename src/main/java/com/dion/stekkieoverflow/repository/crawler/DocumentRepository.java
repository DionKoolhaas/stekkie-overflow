package com.dion.stekkieoverflow.repository.crawler;

import com.dion.stekkieoverflow.domain.Answer;
import com.dion.stekkieoverflow.domain.crawler.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}