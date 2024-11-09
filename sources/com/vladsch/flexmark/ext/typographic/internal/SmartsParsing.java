package com.vladsch.flexmark.ext.typographic.internal;

import com.vladsch.flexmark.ast.util.Parsing;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/SmartsParsing.class */
class SmartsParsing {
    final Parsing myParsing;
    final String ELIPSIS = "...";
    final String ELIPSIS_SPACED = ". . .";
    final String EN_DASH = "--";
    final String EM_DASH = NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR;

    public SmartsParsing(Parsing parsing) {
        this.myParsing = parsing;
    }
}
