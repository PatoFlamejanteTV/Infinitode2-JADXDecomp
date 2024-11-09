package com.vladsch.flexmark.ext.jekyll.tag.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagBlock;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.LinkResolver;
import com.vladsch.flexmark.html.LinkResolverFactory;
import com.vladsch.flexmark.html.UriContentResolver;
import com.vladsch.flexmark.html.UriContentResolverFactory;
import com.vladsch.flexmark.html.renderer.FileUriContentResolver;
import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.ResolvedContent;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.dependency.FirstDependent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/IncludeNodePostProcessor.class */
public class IncludeNodePostProcessor extends NodePostProcessor {
    final Parser parser;
    final List<LinkResolver> linkResolvers;
    final List<UriContentResolver> contentResolvers;
    final Document document;
    final LinkResolverBasicContext context;
    private final boolean embedIncludedContent;
    private final Map<String, String> includedHtml;
    final HashMap<JekyllTag, String> includedDocuments = new HashMap<>();
    final HashMap<String, ResolvedLink> resolvedLinks = new HashMap<>();
    final boolean isIncluding = false;

    public IncludeNodePostProcessor(final Document document) {
        this.document = document;
        this.parser = Parser.builder(document).build();
        this.context = new LinkResolverBasicContext() { // from class: com.vladsch.flexmark.ext.jekyll.tag.internal.IncludeNodePostProcessor.1
            @Override // com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
            public DataHolder getOptions() {
                return document;
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
            public Document getDocument() {
                return document;
            }
        };
        List resolveFlatDependencies = DependencyResolver.resolveFlatDependencies(JekyllTagExtension.LINK_RESOLVER_FACTORIES.get(document), null, null);
        this.linkResolvers = new ArrayList(resolveFlatDependencies.size());
        Iterator it = resolveFlatDependencies.iterator();
        while (it.hasNext()) {
            this.linkResolvers.add(((LinkResolverFactory) it.next()).apply(this.context));
        }
        List<UriContentResolverFactory> list = JekyllTagExtension.CONTENT_RESOLVER_FACTORIES.get(document);
        List resolveFlatDependencies2 = DependencyResolver.resolveFlatDependencies(list.isEmpty() ? Collections.singletonList(new FileUriContentResolver.Factory()) : list, null, null);
        this.contentResolvers = new ArrayList(resolveFlatDependencies2.size());
        Iterator it2 = resolveFlatDependencies2.iterator();
        while (it2.hasNext()) {
            this.contentResolvers.add(((UriContentResolverFactory) it2.next()).apply(this.context));
        }
        this.embedIncludedContent = JekyllTagExtension.EMBED_INCLUDED_CONTENT.get(document).booleanValue();
        this.includedHtml = JekyllTagExtension.INCLUDED_HTML.get(document);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v31, types: [com.vladsch.flexmark.html.renderer.LinkStatus] */
    /* JADX WARN: Type inference failed for: r0v33, types: [java.lang.String] */
    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        if ((node instanceof JekyllTag) && !this.includedDocuments.containsKey(node)) {
            JekyllTag jekyllTag = (JekyllTag) node;
            if (this.embedIncludedContent && jekyllTag.getTag().equals(JekyllTagBlockParser.INCLUDE_TAG)) {
                String unescape = jekyllTag.getParameters().unescape();
                String str = null;
                if (this.includedHtml != null && this.includedHtml.containsKey(unescape)) {
                    str = this.includedHtml.get(unescape);
                } else {
                    ResolvedLink resolvedLink = this.resolvedLinks.get(unescape);
                    ResolvedLink resolvedLink2 = resolvedLink;
                    if (resolvedLink == null) {
                        resolvedLink2 = new ResolvedLink(LinkType.LINK, unescape);
                        Iterator<LinkResolver> it = this.linkResolvers.iterator();
                        while (it.hasNext()) {
                            ResolvedLink resolveLink = it.next().resolveLink(node, this.context, resolvedLink2);
                            resolvedLink2 = resolveLink;
                            if (resolveLink.getStatus() != LinkStatus.UNKNOWN) {
                                break;
                            }
                        }
                        this.resolvedLinks.put(unescape, resolvedLink2);
                    }
                    if (resolvedLink2.getStatus() == LinkStatus.VALID) {
                        ResolvedContent resolvedContent = new ResolvedContent(resolvedLink2, LinkStatus.UNKNOWN, null);
                        Iterator<UriContentResolver> it2 = this.contentResolvers.iterator();
                        while (it2.hasNext()) {
                            ResolvedContent resolveContent = it2.next().resolveContent(node, this.context, resolvedContent);
                            resolvedContent = resolveContent;
                            if (resolveContent.getStatus() != LinkStatus.UNKNOWN) {
                                break;
                            }
                        }
                        UnsupportedEncodingException status = resolvedContent.getStatus();
                        if (status == LinkStatus.VALID) {
                            try {
                                status = new String(resolvedContent.getContent(), "UTF-8");
                                str = status;
                            } catch (UnsupportedEncodingException e) {
                                status.printStackTrace();
                            }
                        }
                    }
                }
                if (str != null && !str.isEmpty()) {
                    this.includedDocuments.put(jekyllTag, str);
                    Document parse = this.parser.parse(str);
                    this.parser.transferReferences(this.document, parse, (Boolean) null);
                    if (parse.contains(Parser.REFERENCES)) {
                        this.document.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RECHECK_UNDEFINED_REFERENCES, (DataKey<Boolean>) Boolean.TRUE);
                    }
                    Node firstChild = parse.getFirstChild();
                    if (!(jekyllTag.getParent() instanceof JekyllTagBlock) && (firstChild instanceof Paragraph) && firstChild.getNext() == null) {
                        firstChild = firstChild.getFirstChild();
                    }
                    while (firstChild != null) {
                        Node next = firstChild.getNext();
                        node.appendChild(firstChild);
                        nodeTracker.nodeAddedWithDescendants(firstChild);
                        firstChild = next;
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/IncludeNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory() {
            super(false);
            addNodes(JekyllTag.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return Collections.singleton(FirstDependent.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new IncludeNodePostProcessor(document);
        }
    }
}
