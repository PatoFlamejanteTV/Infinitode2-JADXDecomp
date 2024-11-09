package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attributes;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/LinkResolverContext.class */
public interface LinkResolverContext extends LinkResolverBasicContext {
    @Override // com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
    DataHolder getOptions();

    @Override // com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
    Document getDocument();

    String encodeUrl(CharSequence charSequence);

    void render(Node node);

    void renderChildren(Node node);

    Node getCurrentNode();

    ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool);

    default ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Boolean bool) {
        return resolveLink(linkType, charSequence, null, bool);
    }
}
