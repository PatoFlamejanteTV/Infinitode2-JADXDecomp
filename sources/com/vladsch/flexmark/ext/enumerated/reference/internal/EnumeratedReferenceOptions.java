package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceOptions.class */
public class EnumeratedReferenceOptions {
    final int contentIndent;

    public EnumeratedReferenceOptions(DataHolder dataHolder) {
        this.contentIndent = Parser.LISTS_ITEM_INDENT.get(dataHolder).intValue();
    }
}
