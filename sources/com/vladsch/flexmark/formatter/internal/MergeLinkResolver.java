package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.LinkResolver;
import com.vladsch.flexmark.html.LinkResolverFactory;
import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.misc.Utils;
import java.util.Set;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/MergeLinkResolver.class */
public class MergeLinkResolver implements LinkResolver {
    private final String docRelativeURL;
    private final String docRootURL;
    private final String[] relativeParts;

    public MergeLinkResolver(LinkResolverBasicContext linkResolverBasicContext) {
        String str = Formatter.DOC_RELATIVE_URL.get(linkResolverBasicContext.getOptions());
        String str2 = Formatter.DOC_ROOT_URL.get(linkResolverBasicContext.getOptions());
        this.docRelativeURL = str;
        this.docRootURL = str2;
        this.relativeParts = Utils.removePrefix(str, '/').split("/");
    }

    @Override // com.vladsch.flexmark.html.LinkResolver
    public ResolvedLink resolveLink(Node node, LinkResolverBasicContext linkResolverBasicContext, ResolvedLink resolvedLink) {
        if ((node instanceof Image) || (node instanceof Link) || (node instanceof Reference)) {
            String url = resolvedLink.getUrl();
            if (this.docRelativeURL.isEmpty() && this.docRootURL.isEmpty()) {
                return resolvedLink.withStatus(LinkStatus.VALID).withUrl(url);
            }
            if (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("ftp://") || url.startsWith("sftp://")) {
                return resolvedLink.withStatus(LinkStatus.VALID).withUrl(url);
            }
            if (url.startsWith("file:/")) {
                return resolvedLink.withStatus(LinkStatus.VALID).withUrl(url);
            }
            if (url.startsWith("/")) {
                if (this.docRootURL != null && !this.docRootURL.isEmpty()) {
                    return resolvedLink.withStatus(LinkStatus.VALID).withUrl((!this.docRootURL.startsWith("/") ? "/" : "") + this.docRootURL + url);
                }
            } else if (!url.matches("^(?:[a-z]+:|#|\\?)")) {
                String str = url;
                String str2 = "";
                int indexOf = url.indexOf(35);
                if (indexOf == 0) {
                    return resolvedLink.withStatus(LinkStatus.VALID);
                }
                if (indexOf > 0) {
                    str2 = url.substring(indexOf);
                    str = url.substring(0, indexOf);
                } else if (url.contains(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
                    int indexOf2 = url.indexOf(TypeDescription.Generic.OfWildcardType.SYMBOL);
                    str2 = url.substring(indexOf2);
                    str = url.substring(0, indexOf2);
                }
                String[] split = str.split("/");
                int length = this.relativeParts.length;
                StringBuilder sb = new StringBuilder();
                String str3 = "";
                for (String str4 : split) {
                    if (!str4.equals(".")) {
                        if (str4.equals("..")) {
                            if (length == 0) {
                                return resolvedLink;
                            }
                            length--;
                        } else {
                            sb.append(str3);
                            sb.append(str4);
                            str3 = "/";
                        }
                    }
                }
                String str5 = this.docRelativeURL.startsWith("/") ? "/" : "";
                StringBuilder sb2 = new StringBuilder();
                int i = length;
                for (int i2 = 0; i2 < i; i2++) {
                    sb2.append(str5);
                    sb2.append(this.relativeParts[i2]);
                    str5 = "/";
                }
                sb2.append('/').append((CharSequence) sb).append(str2);
                return resolvedLink.withStatus(LinkStatus.VALID).withUrl(sb2.toString());
            }
        }
        return resolvedLink;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/MergeLinkResolver$Factory.class */
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
            return new MergeLinkResolver(linkResolverBasicContext);
        }
    }
}
