package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ext.macros.MacroReference;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosInlineParserExtension.class */
public class MacrosInlineParserExtension implements InlineParserExtension {
    static Pattern MACRO_REFERENCE = Pattern.compile("<<<([\\w_-]+)>>>");
    static Pattern MACRO_REFERENCE_INTELLIJ = Pattern.compile("<<<([\u001f\\w_-]+)>>>");

    public MacrosInlineParserExtension(LightInlineParser lightInlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        BasedSequence match = lightInlineParser.match(lightInlineParser.getParsing().intellijDummyIdentifier ? MACRO_REFERENCE_INTELLIJ : MACRO_REFERENCE);
        if (match != null) {
            MacroReference macroReference = new MacroReference(match.subSequence(0, 3), match.midSequence(3, -3), match.midSequence(-3));
            lightInlineParser.flushTextNode();
            lightInlineParser.getBlock().appendChild(macroReference);
            return true;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosInlineParserExtension$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return "<";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new MacrosInlineParserExtension(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
