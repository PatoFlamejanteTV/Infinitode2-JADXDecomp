package org.jsoup.select;

import java.util.stream.Collectors;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:org/jsoup/select/Collector.class */
public class Collector {
    private Collector() {
    }

    public static Elements collect(Evaluator evaluator, Element element) {
        evaluator.reset();
        return (Elements) element.stream().filter(evaluator.asPredicate(element)).collect(Collectors.toCollection(Elements::new));
    }

    public static Element findFirst(Evaluator evaluator, Element element) {
        evaluator.reset();
        return element.stream().filter(evaluator.asPredicate(element)).findFirst().orElse(null);
    }
}
