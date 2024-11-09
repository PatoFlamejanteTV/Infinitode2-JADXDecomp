package com.vladsch.flexmark.ext.jekyll.tag.internal;

import com.vladsch.flexmark.ast.util.Parsing;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagParsing.class */
class JekyllTagParsing {
    final Parsing myParsing;
    final String OPEN_MACROTAG;
    final Pattern MACRO_OPEN;
    final Pattern MACRO_TAG;

    public JekyllTagParsing(Parsing parsing) {
        this.myParsing = parsing;
        this.OPEN_MACROTAG = "\\{%\\s+(" + this.myParsing.TAGNAME + ")(?:\\s+.+)?\\s+%\\}";
        this.MACRO_OPEN = Pattern.compile("^" + this.OPEN_MACROTAG + "\\s*$", 2);
        this.MACRO_TAG = Pattern.compile(this.OPEN_MACROTAG);
    }
}
