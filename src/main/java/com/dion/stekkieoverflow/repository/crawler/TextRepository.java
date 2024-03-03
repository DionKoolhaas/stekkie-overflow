package com.dion.stekkieoverflow.repository.crawler;

import com.dion.stekkieoverflow.domain.crawler.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TextRepository extends JpaRepository<Text, Long> {

    Set<Text> findByTextInAndCssQuery(Set<String> texts, String cssQuery);

}