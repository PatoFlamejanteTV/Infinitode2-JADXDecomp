package com.vladsch.flexmark.ext.typographic.internal;

import com.vladsch.flexmark.ext.typographic.TypographicSmarts;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/SmartsInlineParser.class */
public class SmartsInlineParser implements InlineParserExtension {
    private final SmartsParsing parsing;

    public SmartsInlineParser(LightInlineParser lightInlineParser) {
        this.parsing = new SmartsParsing(lightInlineParser.getParsing());
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        BasedSequence input = lightInlineParser.getInput();
        String str = null;
        BasedSequence basedSequence = null;
        int index = lightInlineParser.getIndex();
        char charAt = input.charAt(index);
        if (charAt == '.') {
            if (input.matchChars(this.parsing.ELIPSIS, index)) {
                basedSequence = input.subSequence(index, index + this.parsing.ELIPSIS.length());
                str = "&hellip;";
            } else if (input.matchChars(this.parsing.ELIPSIS_SPACED, index)) {
                basedSequence = input.subSequence(index, index + this.parsing.ELIPSIS_SPACED.length());
                str = "&hellip;";
            }
        } else if (charAt == '-') {
            if (input.matchChars(this.parsing.EM_DASH, index)) {
                basedSequence = input.subSequence(index, index + this.parsing.EM_DASH.length());
                str = "&mdash;";
            } else if (input.matchChars(this.parsing.EN_DASH, index)) {
                basedSequence = input.subSequence(index, index + this.parsing.EN_DASH.length());
                str = "&ndash;";
            }
        }
        if (basedSequence != null) {
            lightInlineParser.flushTextNode();
            lightInlineParser.setIndex(index + basedSequence.length());
            lightInlineParser.getBlock().appendChild(new TypographicSmarts(basedSequence, str));
            return true;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/SmartsInlineParser$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return ".-";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new SmartsInlineParser(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
