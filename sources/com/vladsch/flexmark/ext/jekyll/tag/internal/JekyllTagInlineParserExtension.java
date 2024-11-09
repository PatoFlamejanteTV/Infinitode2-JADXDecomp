package com.vladsch.flexmark.ext.jekyll.tag.internal;

import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Matcher;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagInlineParserExtension.class */
public class JekyllTagInlineParserExtension implements InlineParserExtension {
    private final JekyllTagParsing parsing;
    private final boolean listIncludesOnly;

    public JekyllTagInlineParserExtension(LightInlineParser lightInlineParser) {
        this.parsing = new JekyllTagParsing(lightInlineParser.getParsing());
        this.listIncludesOnly = JekyllTagExtension.LIST_INCLUDES_ONLY.get(lightInlineParser.getDocument()).booleanValue();
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        if (lightInlineParser.peek(1) != '%') {
            return false;
        }
        if (lightInlineParser.peek(2) == ' ' || lightInlineParser.peek(2) == '\t') {
            BasedSequence input = lightInlineParser.getInput();
            Matcher matcher = lightInlineParser.matcher(this.parsing.MACRO_TAG);
            if (matcher != null) {
                BasedSequence subSequence = input.subSequence(matcher.start(), matcher.end());
                BasedSequence subSequence2 = input.subSequence(matcher.start(1), matcher.end(1));
                JekyllTag jekyllTag = new JekyllTag(subSequence.subSequence(0, 2), subSequence2, input.subSequence(matcher.end(1), matcher.end() - 2).trim(), subSequence.endSequence(2));
                jekyllTag.setCharsFromContent();
                if (!this.listIncludesOnly || subSequence2.equals(JekyllTagBlockParser.INCLUDE_TAG)) {
                    JekyllTagExtension.TAG_LIST.get(lightInlineParser.getDocument()).add(jekyllTag);
                }
                lightInlineParser.flushTextNode();
                lightInlineParser.getBlock().appendChild(jekyllTag);
                return true;
            }
            return false;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagInlineParserExtension$Factory.class */
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
            return new JekyllTagInlineParserExtension(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
