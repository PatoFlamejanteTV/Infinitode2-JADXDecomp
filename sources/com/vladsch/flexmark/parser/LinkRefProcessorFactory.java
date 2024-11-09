package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/LinkRefProcessorFactory.class */
public interface LinkRefProcessorFactory extends Function<Document, LinkRefProcessor> {
    boolean getWantExclamationPrefix(DataHolder dataHolder);

    int getBracketNestingLevel(DataHolder dataHolder);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.function.Function
    LinkRefProcessor apply(Document document);
}
