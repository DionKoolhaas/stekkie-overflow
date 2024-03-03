package com.dion.stekkieoverflow.repository.crawler;

import com.dion.stekkieoverflow.domain.crawler.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TextRepository extends JpaRepository<Text, Long> {

    Set<Text> findByTextInAndCssQuery(Set<String> texts, String cssQuery);

    @Query("SELECT t FROM Text t WHERE UPPER(t.text) LIKE UPPER(concat('%', :text, '%'))")
    Set<Text> findByText(String text);
}