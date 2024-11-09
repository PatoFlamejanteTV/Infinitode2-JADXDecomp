package com.vladsch.flexmark.ext.gfm.users;

import com.vladsch.flexmark.ext.gfm.users.internal.GfmUsersInlineParserExtension;
import com.vladsch.flexmark.ext.gfm.users.internal.GfmUsersJiraRenderer;
import com.vladsch.flexmark.ext.gfm.users.internal.GfmUsersNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/GfmUsersExtension.class */
public class GfmUsersExtension implements HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<String> GIT_HUB_USERS_URL_ROOT = new DataKey<>("GIT_HUB_USERS_URL_ROOT", "https://github.com");
    public static final DataKey<String> GIT_HUB_USER_URL_PREFIX = new DataKey<>("GIT_HUB_USER_URL_PREFIX", "/");
    public static final DataKey<String> GIT_HUB_USER_URL_SUFFIX = new DataKey<>("GIT_HUB_USER_URL_SUFFIX", "");
    public static final DataKey<String> GIT_HUB_USER_HTML_PREFIX = new DataKey<>("GIT_HUB_USER_HTML_PREFIX", "<strong>");
    public static final DataKey<String> GIT_HUB_USER_HTML_SUFFIX = new DataKey<>("GIT_HUB_USER_HTML_SUFFIX", "</strong>");

    private GfmUsersExtension() {
    }

    public static GfmUsersExtension create() {
        return new GfmUsersExtension();
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customInlineParserExtensionFactory(new GfmUsersInlineParserExtension.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new GfmUsersNodeRenderer.Factory());
        } else if (builder.isRendererType("JIRA")) {
            builder.nodeRendererFactory(new GfmUsersJiraRenderer.Factory());
        }
    }
}
