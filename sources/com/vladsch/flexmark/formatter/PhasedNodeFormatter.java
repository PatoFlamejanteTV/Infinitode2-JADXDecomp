package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Document;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/PhasedNodeFormatter.class */
public interface PhasedNodeFormatter extends NodeFormatter {
    Set<FormattingPhase> getFormattingPhases();

    void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase);
}
