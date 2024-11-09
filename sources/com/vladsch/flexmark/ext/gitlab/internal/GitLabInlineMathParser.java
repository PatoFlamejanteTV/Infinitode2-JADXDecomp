package com.vladsch.flexmark.ext.gitlab.internal;

import com.vladsch.flexmark.ext.gitlab.GitLabInlineMath;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabInlineMathParser.class */
public class GitLabInlineMathParser implements InlineParserExtension {
    Pattern MATH_PATTERN = Pattern.compile("\\$`((?:.|\n)*?)`\\$");

    public GitLabInlineMathParser(LightInlineParser lightInlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        if (lightInlineParser.peek(1) == '`') {
            BasedSequence input = lightInlineParser.getInput();
            Matcher matcher = lightInlineParser.matcher(this.MATH_PATTERN);
            if (matcher != null) {
                lightInlineParser.flushTextNode();
                BasedSequence subSequence = input.subSequence(matcher.start(), matcher.start(1));
                BasedSequence subSequence2 = input.subSequence(matcher.end(1), matcher.end());
                lightInlineParser.getBlock().appendChild(new GitLabInlineMath(subSequence, subSequence.baseSubSequence(subSequence.getEndOffset(), subSequence2.getStartOffset()), subSequence2));
                return true;
            }
            return false;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabInlineMathParser$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return "$";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new GitLabInlineMathParser(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
