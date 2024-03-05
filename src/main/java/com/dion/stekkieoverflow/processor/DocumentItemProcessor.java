package com.dion.stekkieoverflow.processor;

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
import java.net.URL;
import static com.dion.stekkieoverflow.util.ProcesDocumentHelper.selectFirstElementOrElseSelectNew;
import static com.dion.stekkieoverflow.util.ProcesDocumentHelper.selectElementsInDocByCssQuery;

@Component
public class DocumentItemProcessor implements ItemProcessor<Link, Document> {
    private final LinkRepository linkRepository;
    private final TextRepository textRepository;

    private final DocumentFetcher documentFetcher;

    public DocumentItemProcessor(LinkRepository linkRepository,
                                 TextRepository textRepository, DocumentFetcher documentFetcher) {
        this.linkRepository = linkRepository;
        this.textRepository = textRepository;
        this.documentFetcher = documentFetcher;
    }


    /**
     * The processor method used by the spring batch job. It:
     * - receives a Link form the db;
     * - downloads the document;
     * - processes it ( extracts links and text snippets );
     * - returns the document
     *
     * @param link,
     * @return Document, the constructed document to be stored in the database including the related entities
     * (e.g. links)
     * @throws IOException
     */
    @Override
    public Document process(final Link link) throws IOException {
        org.jsoup.nodes.Document jsoupDoc = documentFetcher.getDocument(link.getUrl());
        // Build initial document
        Document docToSave = Document.builder()
                .title(jsoupDoc.title())
                .link(link)
                .build();

        // find link elements (a[href]) and extract the absolute link (abs:href)
        Set<String> linksFromScrapedDoc = selectElementsInDocByCssQuery(
                jsoupDoc,
                "a[href]",
                linkInDocument -> linkInDocument.attr("abs:href")
        );
        addLinksToDoc(linksFromScrapedDoc, docToSave);

        // select the <b> elements extract the text
        Set<String> texts = selectElementsInDocByCssQuery(jsoupDoc, "b", Element::text);
        addTextsToDoc(texts, docToSave);

        return docToSave;
    }

    //TODO: reduce code duplication in addLinksToDoc & addTextsToDoc
    private void addLinksToDoc(Set<String> linksFromScrapedDoc, Document docToSave) {
        // check if links allready exist in db
        List<Link> linksInDb = linkRepository.findByUrlIn(linksFromScrapedDoc);

        linksFromScrapedDoc.forEach(linkInDocument -> {
            Link foundLink = selectFirstElementOrElseSelectNew(
                    linksInDb,
                    linkInDb -> linkInDb.getUrl().equals(linkInDocument),
                    Link.builder().url(linkInDocument).build());
            docToSave.getLinksInDocument().add(DocumentLink.builder().document(docToSave).link(foundLink).build());
        });
    }

    private void addTextsToDoc(Set<String> textsFromScrapedDoc, Document docToSave) {
        final String cssQuery = "b";
        // check if texts allready exist in db
        Set<Text> textsInDb = textRepository.findByTextInAndCssQuery(textsFromScrapedDoc, cssQuery);
        textsFromScrapedDoc.forEach(text -> {
            Text foundText = selectFirstElementOrElseSelectNew(
                    textsInDb,
                    textFromDb -> textFromDb.getText().equals(text),
                    Text.builder().text(text).cssQuery(cssQuery).build());
            docToSave.getTexts().add(foundText);
        });
    }

}
@Component
class DocumentFetcher {

    public org.jsoup.nodes.Document getDocument(String url) throws IOException {

        if (url.startsWith("http")) {
            return Jsoup.connect(url).get();
        } else {
            File input = new File(url);
            return Jsoup.parse(input, "UTF-8", "");
        }
    }
}

