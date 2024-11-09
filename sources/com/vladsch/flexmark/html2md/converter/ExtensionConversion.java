package com.vladsch.flexmark.html2md.converter;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/ExtensionConversion.class */
public enum ExtensionConversion {
    NONE,
    MARKDOWN,
    TEXT,
    HTML;

    public final boolean isParsed() {
        return this != HTML;
    }

    public final boolean isTextOnly() {
        return this == TEXT;
    }

    public final boolean isSuppressed() {
        return this == NONE;
    }
}
