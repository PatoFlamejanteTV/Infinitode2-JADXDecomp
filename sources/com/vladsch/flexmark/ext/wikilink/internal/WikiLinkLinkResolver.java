package com.vladsch.flexmark.ext.wikilink.internal;

import com.vladsch.flexmark.ext.wikilink.WikiImage;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.html.LinkResolver;
import com.vladsch.flexmark.html.LinkResolverFactory;
import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.ast.Node;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkLinkResolver.class */
public class WikiLinkLinkResolver implements LinkResolver {
    private final WikiLinkOptions options;

    public WikiLinkLinkResolver(LinkResolverBasicContext linkResolverBasicContext) {
        this.options = new WikiLinkOptions(linkResolverBasicContext.getOptions());
    }

    @Override // com.vladsch.flexmark.html.LinkResolver
    public ResolvedLink resolveLink(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedLink resolvedLink) {
        if (resolvedLink.getLinkType() == WikiLinkExtension.WIKI_LINK) {
            StringBuilder sb = new StringBuilder();
            boolean z = node instanceof WikiImage;
            String url = resolvedLink.getUrl();
            int length = url.length();
            boolean z2 = length > 0 && url.charAt(0) == '/';
            sb.append(z ? this.options.getImagePrefix(z2) : this.options.getLinkPrefix(z2));
            boolean z3 = false;
            boolean z4 = false;
            String str = this.options.linkEscapeChars;
            String str2 = this.options.linkReplaceChars;
            for (int i = z2 ? 1 : 0; i < length; i++) {
                char charAt = url.charAt(i);
                if (charAt == '#' && !z3 && this.options.allowAnchors && (!z4 || !this.options.allowAnchorEscape)) {
                    sb.append(z ? this.options.imageFileExtension : this.options.linkFileExtension);
                    sb.append(charAt);
                    z3 = true;
                    z4 = false;
                } else if (charAt == '\\') {
                    if (z4) {
                        sb.append("%5C");
                    }
                    z4 = !z4;
                } else {
                    z4 = false;
                    if (charAt == '#' && !z3) {
                        sb.append("%23");
                    } else {
                        int indexOf = str.indexOf(charAt);
                        if (indexOf < 0) {
                            sb.append(charAt);
                        } else {
                            sb.append(str2.charAt(indexOf));
                        }
                    }
                }
            }
            if (z4) {
                sb.append("%5C");
            }
            if (!z3) {
                sb.append(z ? this.options.imageFileExtension : this.options.linkFileExtension);
            }
            if (z) {
                return new ResolvedLink(LinkType.IMAGE, sb.toString(), null, LinkStatus.UNCHECKED);
            }
            return new ResolvedLink(LinkType.LINK, sb.toString(), null, LinkStatus.UNCHECKED);
        }
        return resolvedLink;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkLinkResolver$Factory.class */
    public static class Factory implements LinkResolverFactory {
        @Override // com.vladsch.flexmark.html.LinkResolverFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.html.LinkResolverFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.html.LinkResolverFactory, com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }

        @Override // com.vladsch.flexmark.html.LinkResolverFactory, java.util.function.Function
        public LinkResolver apply(LinkResolverBasicContext linkResolverBasicContext) {
            return new WikiLinkLinkResolver(linkResolverBasicContext);
        }
    }
}
