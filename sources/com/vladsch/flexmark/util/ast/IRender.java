package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/IRender.class */
public interface IRender {
    void render(Node node, Appendable appendable);

    DataHolder getOptions();

    default String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        return sb.toString();
    }
}
