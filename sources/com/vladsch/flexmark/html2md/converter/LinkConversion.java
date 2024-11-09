package com.vladsch.flexmark.html2md.converter;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/LinkConversion.class */
public enum LinkConversion {
    NONE,
    MARKDOWN_EXPLICIT,
    MARKDOWN_REFERENCE,
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

    public final boolean isReference() {
        return this == MARKDOWN_REFERENCE;
    }
}
