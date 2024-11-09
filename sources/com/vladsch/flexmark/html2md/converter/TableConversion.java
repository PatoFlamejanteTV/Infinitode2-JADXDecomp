package com.vladsch.flexmark.html2md.converter;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/TableConversion.class */
public enum TableConversion {
    NONE,
    MARKDOWN,
    MARKDOWN_NO_SINGLE_CELL,
    MARKDOWN_MACROS,
    MARKDOWN_MACROS_NO_SINGLE_CELL,
    TEXT,
    HTML;

    final boolean isParsed() {
        return this != HTML;
    }

    final boolean isTextOnly() {
        return this == TEXT;
    }

    final boolean isSuppressed() {
        return this == NONE;
    }

    final boolean isUnwrapSingleCell() {
        return this == MARKDOWN_NO_SINGLE_CELL || this == MARKDOWN_MACROS_NO_SINGLE_CELL;
    }

    final boolean isMacros() {
        return this == MARKDOWN_MACROS || this == MARKDOWN_MACROS_NO_SINGLE_CELL;
    }
}
