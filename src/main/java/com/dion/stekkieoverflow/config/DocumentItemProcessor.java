package com.dion.stekkieoverflow.config;

import com.dion.stekkieoverflow.domain.crawler.Document;
import com.dion.stekkieoverflow.domain.crawler.DocumentLink;
import com.dion.stekkieoverflow.domain.crawler.Link;
import com.dion.stekkieoverflow.repository.crawler.LinkRepository;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DocumentItemProcessor implements ItemProcessor<Link, Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentItemProcessor.class);

    private final LinkRepository linkRepository;

    public DocumentItemProcessor(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    @Override
    public Document process(final Link link) throws Exception {
        File input = new File(link.getUrl());
        try {
            org.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8", "");
            Document document = Document.builder().title(doc.title()).link(link).build();
            Set<String> links = doc.select("a[href]").stream()
                    .map(linkInDocument -> linkInDocument.attr("abs:href"))
                    .collect(Collectors.toSet());

            List<Link> linksInDb = linkRepository.findByUrlIn(links);
            links.stream().forEach(linkInDocument -> {
                Link foundLink = linksInDb.stream().findFirst().orElse(Link.builder().url(linkInDocument).build());
                document.getLinksInDocument().add(DocumentLink.builder().document(document).link(foundLink).build());

            });
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}