package com.vladsch.flexmark.ext.gitlab.internal;

import com.vladsch.flexmark.ext.gitlab.GitLabBlockQuote;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabNodeFormatter.class */
public class GitLabNodeFormatter implements NodeFormatter {
    public GitLabNodeFormatter(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Collections.singletonList(new NodeFormattingHandler(GitLabBlockQuote.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return new HashSet(Collections.singletonList(GitLabBlockQuote.class));
    }

    private void render(GitLabBlockQuote gitLabBlockQuote, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append(">>>").line();
        nodeFormatterContext.renderChildren(gitLabBlockQuote);
        markdownWriter.append(">>>").line();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new GitLabNodeFormatter(dataHolder);
        }
    }
}
