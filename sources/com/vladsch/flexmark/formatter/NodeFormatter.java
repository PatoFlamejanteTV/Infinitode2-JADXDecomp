package com.vladsch.flexmark.formatter;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/NodeFormatter.class */
public interface NodeFormatter {
    Set<NodeFormattingHandler<?>> getNodeFormattingHandlers();

    Set<Class<?>> getNodeClasses();

    default char getBlockQuoteLikePrefixChar() {
        return (char) 0;
    }
}
