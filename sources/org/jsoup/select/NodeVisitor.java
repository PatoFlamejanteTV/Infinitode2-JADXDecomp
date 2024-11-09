package org.jsoup.select;

import org.jsoup.nodes.Node;

@FunctionalInterface
/* loaded from: infinitode-2.jar:org/jsoup/select/NodeVisitor.class */
public interface NodeVisitor {
    void head(Node node, int i);

    default void tail(Node node, int i) {
    }
}
