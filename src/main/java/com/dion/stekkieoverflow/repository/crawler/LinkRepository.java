package com.dion.stekkieoverflow.repository.crawler;

import com.dion.stekkieoverflow.domain.crawler.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LinkRepository extends JpaRepository<Link, Long> {

    List<Link> findByUrlIn(Set<String> url);

}