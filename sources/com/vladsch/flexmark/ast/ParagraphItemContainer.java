package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/ParagraphItemContainer.class */
public interface ParagraphItemContainer {
    boolean isParagraphInTightListItem(Paragraph paragraph);

    boolean isItemParagraph(Paragraph paragraph);

    boolean isParagraphWrappingDisabled(Paragraph paragraph, ListOptions listOptions, DataHolder dataHolder);
}
