package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/IParse.class */
public interface IParse {
    Node parse(BasedSequence basedSequence);

    Node parse(String str);

    Node parseReader(Reader reader);

    DataHolder getOptions();

    boolean transferReferences(Document document, Document document2, Boolean bool);
}
