package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ext.attributes.AttributeNode;
import com.vladsch.flexmark.ext.attributes.AttributesDelimiter;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Matcher;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesInlineParserExtension.class */
public class AttributesInlineParserExtension implements InlineParserExtension {
    private final AttributeParsing parsing;

    public AttributesInlineParserExtension(LightInlineParser lightInlineParser) {
        this.parsing = new AttributeParsing(lightInlineParser.getParsing());
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        AttributeNode attributeNode;
        if (lightInlineParser.peek(1) != '{') {
            int index = lightInlineParser.getIndex();
            BasedSequence input = lightInlineParser.getInput();
            Matcher matcher = lightInlineParser.matcher(this.parsing.ATTRIBUTES_TAG);
            if (matcher != null) {
                BasedSequence subSequence = input.subSequence(matcher.start(), matcher.end());
                BasedSequence subSequence2 = input.subSequence(matcher.start(1), matcher.end(1));
                Node attributesDelimiter = (subSequence2.equals("#") || subSequence2.equals(".")) ? new AttributesDelimiter(subSequence.subSequence(0, 1), subSequence2, subSequence.endSequence(1)) : new AttributesNode(subSequence.subSequence(0, 1), subSequence2, subSequence.endSequence(1));
                Node node = attributesDelimiter;
                attributesDelimiter.setCharsFromContent();
                lightInlineParser.flushTextNode();
                lightInlineParser.getBlock().appendChild(node);
                BasedSequence trim = subSequence2.trim();
                if (!trim.isEmpty()) {
                    Matcher matcher2 = this.parsing.ATTRIBUTE.matcher(trim);
                    while (matcher2.find()) {
                        BasedSequence subSequence3 = trim.subSequence(matcher2.start(1), matcher2.end(1));
                        BasedSequence trim2 = (matcher2.groupCount() == 1 || matcher2.start(2) == -1) ? BasedSequence.NULL : trim.subSequence(matcher2.end(1), matcher2.start(2)).trim();
                        BasedSequence subSequence4 = (matcher2.groupCount() == 1 || matcher2.start(2) == -1) ? BasedSequence.NULL : trim.subSequence(matcher2.start(2), matcher2.end(2));
                        BasedSequence basedSequence = subSequence4;
                        boolean z = subSequence4.length() >= 2 && ((basedSequence.charAt(0) == '\"' && basedSequence.endCharAt(1) == '\"') || (basedSequence.charAt(0) == '\'' && basedSequence.endCharAt(1) == '\''));
                        boolean z2 = z;
                        BasedSequence subSequence5 = !z ? BasedSequence.NULL : basedSequence.subSequence(0, 1);
                        BasedSequence endSequence = !z2 ? BasedSequence.NULL : basedSequence.endSequence(1, 0);
                        if (z2) {
                            basedSequence = basedSequence.midSequence(1, -1);
                        }
                        if (trim2.isNull() && basedSequence.isNull() && AttributeNode.isImplicitName(subSequence3)) {
                            attributeNode = new AttributeNode(subSequence3.subSequence(0, 1), trim2, subSequence5, subSequence3.subSequence(1), endSequence);
                        } else {
                            attributeNode = new AttributeNode(subSequence3, trim2, subSequence5, basedSequence, endSequence);
                        }
                        node.appendChild(attributeNode);
                    }
                    return true;
                }
                lightInlineParser.setIndex(index);
                return false;
            }
            return false;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesInlineParserExtension$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return "{";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new AttributesInlineParserExtension(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
