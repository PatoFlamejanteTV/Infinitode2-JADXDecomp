package com.vladsch.flexmark.ext.gfm.users.internal;

import com.vladsch.flexmark.ext.gfm.users.GfmUser;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersInlineParserExtension.class */
public class GfmUsersInlineParserExtension implements InlineParserExtension {
    public static final Pattern GITHUB_USER = Pattern.compile("^(@)([a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38})\\b", 2);

    public GfmUsersInlineParserExtension(LightInlineParser lightInlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        BasedSequence[] matchWithGroups;
        int index = lightInlineParser.getIndex();
        boolean z = index == 0;
        boolean z2 = z;
        if (!z) {
            char charAt = lightInlineParser.getInput().charAt(index - 1);
            if (!Character.isUnicodeIdentifierPart(charAt) && charAt != '-' && charAt != '.') {
                z2 = true;
            }
        }
        if (z2 && (matchWithGroups = lightInlineParser.matchWithGroups(GITHUB_USER)) != null) {
            lightInlineParser.flushTextNode();
            lightInlineParser.getBlock().appendChild(new GfmUser(matchWithGroups[1], matchWithGroups[2]));
            return true;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/internal/GfmUsersInlineParserExtension$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return "@";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new GfmUsersInlineParserExtension(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
