package org.jsoup.select;

import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:org/jsoup/select/NodeFilter.class */
public interface NodeFilter {

    /* loaded from: infinitode-2.jar:org/jsoup/select/NodeFilter$FilterResult.class */
    public enum FilterResult {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP
    }

    FilterResult head(Node node, int i);

    default FilterResult tail(Node node, int i) {
        return FilterResult.CONTINUE;
    }
}
