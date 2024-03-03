package com.dion.stekkieoverflow.config;

import com.dion.stekkieoverflow.domain.crawler.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class DocumentItemProcessor implements ItemProcessor<Document, Document> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentItemProcessor.class);

    @Override
    public Document process(final Document document) throws Exception {
        String brand = document.getBrand().toUpperCase();
        String origin = document.getOrigin().toUpperCase();
        String chracteristics = document.getCharacteristics().toUpperCase();

        Document transformedDocument = Document.builder()
                .brand(brand)
                .origin(origin)
                .characteristics(chracteristics)
                .build();
        LOGGER.info("Converting ( {} ) into ( {} )", document, transformedDocument);

        return transformedDocument;
    }
}