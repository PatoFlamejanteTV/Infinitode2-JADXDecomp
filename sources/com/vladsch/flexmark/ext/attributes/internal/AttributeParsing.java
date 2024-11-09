package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributeParsing.class */
public class AttributeParsing {
    final Parsing myParsing;
    final Pattern ATTRIBUTES_TAG;
    final Pattern ATTRIBUTE;

    public AttributeParsing(Parsing parsing) {
        this.myParsing = parsing;
        String str = this.myParsing.UNQUOTEDVALUE;
        this.ATTRIBUTE = Pattern.compile("\\s*([#.]" + str + "|" + this.myParsing.ATTRIBUTENAME + ")\\s*(?:=\\s*(" + this.myParsing.ATTRIBUTEVALUE + ")?)?");
        if (AttributesExtension.USE_EMPTY_IMPLICIT_AS_SPAN_DELIMITER.get(parsing.options).booleanValue()) {
            this.ATTRIBUTES_TAG = Pattern.compile("^\\{((?:[#.])|(?:\\s*([#.]" + str + "|" + this.myParsing.ATTRIBUTENAME + ")\\s*(?:=\\s*(" + this.myParsing.ATTRIBUTEVALUE + ")?)?)(?:\\s+([#.]" + str + "|" + this.myParsing.ATTRIBUTENAME + ")\\s*(?:=\\s*(" + this.myParsing.ATTRIBUTEVALUE + ")?)?)*\\s*)\\}");
        } else {
            this.ATTRIBUTES_TAG = Pattern.compile("^\\{((?:\\s*([#.]" + str + "|" + this.myParsing.ATTRIBUTENAME + ")\\s*(?:=\\s*(" + this.myParsing.ATTRIBUTEVALUE + ")?)?)(?:\\s+([#.]" + str + "|" + this.myParsing.ATTRIBUTENAME + ")\\s*(?:=\\s*(" + this.myParsing.ATTRIBUTEVALUE + ")?)?)*\\s*)\\}");
        }
    }
}
