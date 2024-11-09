package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableManipulator.class */
public interface TableManipulator {
    public static final TableManipulator NULL = (markdownTable, node) -> {
    };

    void apply(MarkdownTable markdownTable, Node node);
}
