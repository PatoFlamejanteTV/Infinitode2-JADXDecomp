package com.vladsch.flexmark.ext.gfm.issues;

import com.vladsch.flexmark.ext.gfm.issues.internal.GfmIssuesInlineParserExtension;
import com.vladsch.flexmark.ext.gfm.issues.internal.GfmIssuesJiraRenderer;
import com.vladsch.flexmark.ext.gfm.issues.internal.GfmIssuesNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/GfmIssuesExtension.class */
public class GfmIssuesExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<String> GIT_HUB_ISSUES_URL_ROOT = new DataKey<>("GIT_HUB_ISSUES_URL_ROOT", "issues");
    public static final DataKey<String> GIT_HUB_ISSUE_URL_PREFIX = new DataKey<>("GIT_HUB_ISSUE_URL_PREFIX", "/");
    public static final DataKey<String> GIT_HUB_ISSUE_URL_SUFFIX = new DataKey<>("GIT_HUB_ISSUE_URL_SUFFIX", "");
    public static final DataKey<String> GIT_HUB_ISSUE_HTML_PREFIX = new DataKey<>("GIT_HUB_ISSUE_HTML_PREFIX", "");
    public static final DataKey<String> GIT_HUB_ISSUE_HTML_SUFFIX = new DataKey<>("GIT_HUB_ISSUE_HTML_SUFFIX", "");

    private GfmIssuesExtension() {
    }

    public static GfmIssuesExtension create() {
        return new GfmIssuesExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customInlineParserExtensionFactory(new GfmIssuesInlineParserExtension.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new GfmIssuesNodeRenderer.Factory());
        } else if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new GfmIssuesJiraRenderer.Factory());
        }
    }
}
