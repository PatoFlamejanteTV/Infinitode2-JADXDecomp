package com.vladsch.flexmark.ext.gitlab.internal;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ext.gitlab.GitLabDel;
import com.vladsch.flexmark.ext.gitlab.GitLabInline;
import com.vladsch.flexmark.ext.gitlab.GitLabIns;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabInlineParser.class */
public class GitLabInlineParser implements InlineParserExtension {
    private final List<GitLabInline> openInlines = new ArrayList();
    private final GitLabOptions options;

    public GitLabInlineParser(LightInlineParser lightInlineParser) {
        this.options = new GitLabOptions(lightInlineParser.getDocument());
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
        int size = this.openInlines.size();
        while (true) {
            int i = size;
            size--;
            if (i > 0) {
                GitLabInline gitLabInline = this.openInlines.get(size);
                gitLabInline.insertBefore(new Text(gitLabInline.getChars()));
                gitLabInline.unlink();
            } else {
                this.openInlines.clear();
                return;
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        GitLabInline gitLabInline;
        BasedSequence chars;
        char peek = lightInlineParser.peek();
        char peek2 = lightInlineParser.peek(1);
        if ((peek == '{' || peek == '[') && ((this.options.insParser && peek2 == '+') || (this.options.delParser && peek2 == '-'))) {
            BasedSequence subSequence = lightInlineParser.getInput().subSequence(lightInlineParser.getIndex());
            GitLabInline gitLabIns = peek2 == '+' ? new GitLabIns(subSequence.subSequence(0, 2)) : new GitLabDel(subSequence.subSequence(0, 2));
            lightInlineParser.flushTextNode();
            lightInlineParser.getBlock().appendChild(gitLabIns);
            this.openInlines.add(gitLabIns);
            lightInlineParser.setIndex(lightInlineParser.getIndex() + 2);
            return true;
        }
        if ((!this.options.insParser || peek != '+') && (!this.options.delParser || peek != '-')) {
            return false;
        }
        if (peek2 == ']' || peek2 == '}') {
            BasedSequence subSequence2 = lightInlineParser.getInput().subSequence(lightInlineParser.getIndex());
            BasedSequence of = BasedSequence.of(peek2 == ']' ? peek == '+' ? "[+" : "[-" : peek == '+' ? "{+" : "{-");
            int size = this.openInlines.size();
            do {
                int i = size;
                size--;
                if (i > 0) {
                    gitLabInline = this.openInlines.get(size);
                    chars = gitLabInline.getChars();
                } else {
                    return false;
                }
            } while (!chars.equals(of));
            lightInlineParser.setIndex(lightInlineParser.getIndex() + 2);
            BasedSequence subSequence3 = subSequence2.subSequence(0, 2);
            gitLabInline.setOpeningMarker(chars);
            gitLabInline.setClosingMarker(subSequence3);
            gitLabInline.setText(chars.baseSubSequence(chars.getEndOffset(), subSequence3.getStartOffset()));
            lightInlineParser.flushTextNode();
            lightInlineParser.moveNodes(gitLabInline, lightInlineParser.getBlock().getLastChild());
            if (size == 0) {
                this.openInlines.clear();
                return true;
            }
            List<GitLabInline> list = this.openInlines;
            list.subList(size, list.size()).clear();
            return true;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabInlineParser$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return "{[-+";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new GitLabInlineParser(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}
