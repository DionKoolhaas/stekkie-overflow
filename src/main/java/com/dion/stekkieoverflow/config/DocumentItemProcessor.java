package com.dion.stekkieoverflow.config;

import com.dion.stekkieoverflow.domain.crawler.Document;
import com.dion.stekkieoverflow.domain.crawler.DocumentLink;
import com.dion.stekkieoverflow.domain.crawler.Link;
import com.dion.stekkieoverflow.domain.crawler.Text;
import com.dion.stekkieoverflow.repository.crawler.LinkRepository;
import com.dion.stekkieoverflow.repository.crawler.TextRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DocumentItemProcessor implements ItemProcessor<Link, Document> {
    private final LinkRepository linkRepository;
    private final TextRepository textRepository;

    public DocumentItemProcessor(LinkRepository linkRepository,
                                 TextRepository textRepository) {
        this.linkRepository = linkRepository;
        this.textRepository = textRepository;
    }


    @Override
    public Document process(final Link link) throws IOException {
        File input = new File(link.getUrl());

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

        Set<String> texts = doc.select("b").stream().map(Element::text).collect(Collectors.toSet());
        Set<Text> textsInDb = textRepository.findByTextInAndCssQuery(texts, "b");
        texts.stream().forEach(text -> {
            Text foundText = textsInDb.stream().findFirst().orElse(Text.builder().text(text).cssQuery("b").build());
            document.getTexts().add(foundText);
        });

        return document;

    }
}