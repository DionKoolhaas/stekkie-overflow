package com.dion.stekkieoverflow.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.dion.stekkieoverflow.util.ProcesDocumentHelper.selectElementsInDocByCssQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcesDocumentHelperTest {

    @Test
    void selectElementsInDocByCssQueryTest() {
        Document doc = Jsoup.parse("");

        Element p = doc.appendElement("p");
        p.text("testElement");
        p.attr("testAttribute", "testAttributeValue");
        doc.appendElement("p-with-appendix").text("with appendix should not be selected");


        // element a is not in doc
        assertEquals( 0, selectElementsInDocByCssQuery(doc, "a", object -> true).size());

        // p element is doc with size 1 and the content is p
        Set<String> selectedTextFromPElement = selectElementsInDocByCssQuery(doc, "p", Element::text);
        assertEquals(1, selectedTextFromPElement.size());
        assertEquals("testElement", selectedTextFromPElement.stream().findFirst().get());


        Set<String> selectAttributeFromPElement = selectElementsInDocByCssQuery(doc, "p", element -> element.attr("testAttribute"));
        assertEquals(1, selectAttributeFromPElement.size());
        assertEquals("testAttributeValue", selectAttributeFromPElement.stream().findFirst().get());

        assertEquals( 0, selectElementsInDocByCssQuery(doc, "p[testAttributeValue]", Element::text).size());
    }


}
