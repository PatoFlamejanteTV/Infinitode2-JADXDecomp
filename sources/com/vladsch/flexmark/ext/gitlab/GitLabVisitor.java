package com.vladsch.flexmark.ext.gitlab;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/GitLabVisitor.class */
public interface GitLabVisitor {
    void visit(GitLabIns gitLabIns);

    void visit(GitLabDel gitLabDel);

    void visit(GitLabInlineMath gitLabInlineMath);

    void visit(GitLabBlockQuote gitLabBlockQuote);
}
