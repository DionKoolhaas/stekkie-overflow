package com.dion.stekkieoverflow.util;

import org.jsoup.nodes.Element;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProcesDocumentHelper {

    /**
     *
     * @param setFromDb
     * @param filter
     * @param newObject
     * @return
     * @param <R>
     */
    public static <R> R selectFirstElementOrElseSelectNew(Collection<R> setFromDb, Predicate<R> filter, R newObject) {
        return setFromDb.stream().filter(filter).findFirst().orElse(newObject);
    }

    /**
     * Method to select al the elements in a JSoup doc, given a cssquery.
     * @param doc, the subject (a Jsoup document) for querying
     * @param cssQuery
     * @param map, mapping function for the select result
     * @return
     * @param <R>
     */
    public static <R> Set<R> selectElementsInDocByCssQuery(org.jsoup.nodes.Document doc, String cssQuery, Function<? super Element, ? extends R> map) {
        return doc.select(cssQuery).stream().map(map).collect(Collectors.toSet());
    }
}
