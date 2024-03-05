package com.dion.stekkieoverflow.processor;

import com.dion.stekkieoverflow.domain.crawler.Link;
import com.dion.stekkieoverflow.repository.crawler.DocumentRepository;
import com.dion.stekkieoverflow.repository.crawler.LinkRepository;
import com.dion.stekkieoverflow.repository.crawler.TextRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentItemProcessorTest {

    private LinkRepository linkRepository;
    private TextRepository textRepository;

    private DocumentFetcher documentFetcher;

    private DocumentItemProcessor documentItemProcessor;

    @BeforeEach
    public void setUp() {
        this.linkRepository = mock(LinkRepository.class);
        this.textRepository = mock(TextRepository.class);
        this.documentFetcher = mock(DocumentFetcher.class);
        this.documentItemProcessor = new DocumentItemProcessor(linkRepository, textRepository, documentFetcher);
    }

    @Test
    void test() throws IOException {
        String url = "testurl";
        Document doc = Jsoup.parse("<html><title>Test</title></html>");
        when(documentFetcher.getDocument(url)).thenReturn(doc);
        when(linkRepository.findByUrlIn(HashSet.newHashSet(0))).thenReturn(new ArrayList<>());
        when(textRepository.findByTextInAndCssQuery(HashSet.newHashSet(0), "b"))
                .thenReturn(HashSet.newHashSet(0));

        Link link = new Link();
        link.setUrl(url);
        assertEquals("Test", documentItemProcessor.process(link).getTitle());
    }
}
