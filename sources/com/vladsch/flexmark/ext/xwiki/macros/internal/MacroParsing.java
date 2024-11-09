package com.vladsch.flexmark.ext.xwiki.macros.internal;

import com.vladsch.flexmark.ast.util.Parsing;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroParsing.class */
class MacroParsing {
    final Parsing myParsing;
    final String OPEN_MACROTAG;
    final String CLOSE_MACROTAG;
    final String MACROTAG;
    final Pattern MACRO_OPEN;
    final Pattern MACRO_CLOSE;
    final Pattern MACRO_CLOSE_END;
    final Pattern MACRO_ATTRIBUTE;
    final Pattern MACRO_TAG;

    public MacroParsing(Parsing parsing) {
        this.myParsing = parsing;
        this.OPEN_MACROTAG = "\\{\\{(" + this.myParsing.TAGNAME + ")" + this.myParsing.ATTRIBUTE + "*\\s*/?\\}\\}";
        this.CLOSE_MACROTAG = "\\{\\{/(" + this.myParsing.TAGNAME + ")\\s*\\}\\}";
        this.MACRO_OPEN = Pattern.compile("^" + this.OPEN_MACROTAG, 2);
        this.MACRO_CLOSE = Pattern.compile("^" + this.CLOSE_MACROTAG + "\\s*$", 2);
        this.MACRO_CLOSE_END = Pattern.compile(this.CLOSE_MACROTAG + "\\s*$", 2);
        this.MACRO_ATTRIBUTE = Pattern.compile("\\s*(" + this.myParsing.ATTRIBUTENAME + ")\\s*(?:=\\s*(" + this.myParsing.ATTRIBUTEVALUE + ")?)?");
        this.MACROTAG = "(?:" + this.OPEN_MACROTAG + ")|(?:" + this.CLOSE_MACROTAG + ")";
        this.MACRO_TAG = Pattern.compile(this.MACROTAG);
    }
}
