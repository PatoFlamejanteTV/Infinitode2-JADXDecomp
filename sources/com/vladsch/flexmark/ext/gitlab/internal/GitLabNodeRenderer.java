package com.vladsch.flexmark.ext.gitlab.internal;

import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.ImageRef;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.ext.gitlab.GitLabBlockQuote;
import com.vladsch.flexmark.ext.gitlab.GitLabDel;
import com.vladsch.flexmark.ext.gitlab.GitLabInlineMath;
import com.vladsch.flexmark.ext.gitlab.GitLabIns;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.HtmlRendererOptions;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.CoreNodeRenderer;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabNodeRenderer.class */
public class GitLabNodeRenderer implements NodeRenderer {
    public static final AttributablePart VIDEO = new AttributablePart("VIDEO");
    public static final AttributablePart VIDEO_LINK = new AttributablePart("VIDEO_LINK");
    final GitLabOptions options;
    private final boolean codeContentBlock;
    private final ReferenceRepository referenceRepository;
    private final boolean recheckUndefinedReferences;

    public GitLabNodeRenderer(DataHolder dataHolder) {
        this.options = new GitLabOptions(dataHolder);
        this.codeContentBlock = Parser.FENCED_CODE_CONTENT_BLOCK.get(dataHolder).booleanValue();
        this.referenceRepository = Parser.REFERENCES.get(dataHolder);
        this.recheckUndefinedReferences = HtmlRenderer.RECHECK_UNDEFINED_REFERENCES.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(GitLabIns.class, this::render));
        hashSet.add(new NodeRenderingHandler(GitLabDel.class, this::render));
        hashSet.add(new NodeRenderingHandler(GitLabInlineMath.class, this::render));
        hashSet.add(new NodeRenderingHandler(GitLabBlockQuote.class, this::render));
        if (this.options.renderBlockMath || this.options.renderBlockMermaid) {
            hashSet.add(new NodeRenderingHandler(FencedCodeBlock.class, this::render));
        }
        if (this.options.renderVideoImages) {
            hashSet.add(new NodeRenderingHandler(Image.class, this::render));
            hashSet.add(new NodeRenderingHandler(ImageRef.class, this::render));
        }
        return hashSet;
    }

    private void render(GitLabIns gitLabIns, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().tag(FlexmarkHtmlConverter.INS_NODE, false, false, () -> {
            nodeRendererContext.renderChildren(gitLabIns);
        });
    }

    private void render(GitLabDel gitLabDel, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().tag(FlexmarkHtmlConverter.DEL_NODE, false, false, () -> {
            nodeRendererContext.renderChildren(gitLabDel);
        });
    }

    private void render(GitLabInlineMath gitLabInlineMath, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().attr(Attribute.CLASS_ATTR, (CharSequence) this.options.inlineMathClass).withAttr().tag((CharSequence) FlexmarkHtmlConverter.SPAN_NODE);
        htmlWriter.text((CharSequence) gitLabInlineMath.getText());
        htmlWriter.tag("/span");
    }

    private void render(GitLabBlockQuote gitLabBlockQuote, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().tagLineIndent(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, () -> {
            nodeRendererContext.renderChildren(gitLabBlockQuote);
        });
    }

    private void render(FencedCodeBlock fencedCodeBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        HtmlRendererOptions htmlOptions = nodeRendererContext.getHtmlOptions();
        BasedSequence infoDelimitedByAny = fencedCodeBlock.getInfoDelimitedByAny(htmlOptions.languageDelimiterSet);
        if (this.options.renderBlockMath && infoDelimitedByAny.isIn(this.options.mathLanguages)) {
            htmlWriter.line();
            htmlWriter.srcPosWithTrailingEOL(fencedCodeBlock.getChars()).attr(Attribute.CLASS_ATTR, (CharSequence) this.options.blockMathClass).withAttr().tag((CharSequence) FlexmarkHtmlConverter.DIV_NODE).line().openPre();
            if (this.codeContentBlock) {
                nodeRendererContext.renderChildren(fencedCodeBlock);
            } else {
                htmlWriter.text((CharSequence) fencedCodeBlock.getContentChars().normalizeEOL());
            }
            htmlWriter.closePre().tag((CharSequence) "/div");
            htmlWriter.lineIf(htmlOptions.htmlBlockCloseTagEol);
            return;
        }
        if (this.options.renderBlockMermaid && infoDelimitedByAny.isIn(this.options.mermaidLanguages)) {
            htmlWriter.line();
            htmlWriter.srcPosWithTrailingEOL(fencedCodeBlock.getChars()).attr(Attribute.CLASS_ATTR, (CharSequence) this.options.blockMermaidClass).withAttr().tag((CharSequence) FlexmarkHtmlConverter.DIV_NODE).line().openPre();
            if (this.codeContentBlock) {
                nodeRendererContext.renderChildren(fencedCodeBlock);
            } else {
                htmlWriter.text((CharSequence) fencedCodeBlock.getContentChars().normalizeEOL());
            }
            htmlWriter.closePre().tag((CharSequence) "/div");
            htmlWriter.lineIf(htmlOptions.htmlBlockCloseTagEol);
            return;
        }
        nodeRendererContext.delegateRender();
    }

    private boolean renderVideoImage(Node node, String str, String str2, Attributes attributes, HtmlWriter htmlWriter) {
        String str3 = str;
        int indexOf = str.indexOf(63);
        if (indexOf != -1) {
            str3 = str.substring(0, indexOf);
        }
        int lastIndexOf = str3.lastIndexOf(46);
        if (lastIndexOf != -1) {
            if (this.options.videoImageExtensionSet.contains(str3.substring(lastIndexOf + 1))) {
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.options.videoImageClass).attr(attributes).withAttr().tagLineIndent((CharSequence) FlexmarkHtmlConverter.DIV_NODE, () -> {
                    htmlWriter.srcPos(node.getChars()).attr("src", (CharSequence) str).attr((CharSequence) "width", (CharSequence) "400").attr((CharSequence) "controls", (CharSequence) "true").withAttr(VIDEO).tag((CharSequence) "video").tag((CharSequence) "/video").line();
                    if (this.options.renderVideoLink) {
                        htmlWriter.tag(FlexmarkHtmlConverter.P_NODE).attr((CharSequence) "href", (CharSequence) str).attr((CharSequence) "target", (CharSequence) "_blank").attr((CharSequence) "rel", (CharSequence) "noopener noreferrer").attr((CharSequence) Attribute.TITLE_ATTR, (CharSequence) String.format(this.options.videoImageLinkTextFormat, str2)).withAttr(VIDEO_LINK).tag((CharSequence) FlexmarkHtmlConverter.A_NODE).text((CharSequence) str2).tag((CharSequence) "/a").tag((CharSequence) "/p").line();
                    }
                });
                return true;
            }
            return false;
        }
        return false;
    }

    private void render(Image image, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!nodeRendererContext.isDoNotRenderLinks() && !CoreNodeRenderer.isSuppressedLinkPrefix(image.getUrl(), nodeRendererContext)) {
            String collectAndGetText = new TextCollectingVisitor().collectAndGetText(image);
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.IMAGE, image.getUrl().unescape(), null, null);
            String url = resolveLink.getUrl();
            if (image.getUrlContent().isEmpty()) {
                if (renderVideoImage(image, url, collectAndGetText, nodeRendererContext.extendRenderingNodeAttributes(image, AttributablePart.NODE, resolveLink.getNonNullAttributes()), htmlWriter)) {
                    return;
                }
            }
            nodeRendererContext.delegateRender();
        }
    }

    private void render(ImageRef imageRef, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        ResolvedLink resolvedLink;
        boolean z = false;
        if (!imageRef.isDefined() && this.recheckUndefinedReferences && imageRef.getReferenceNode(this.referenceRepository) != null) {
            imageRef.setDefined(true);
        }
        if (imageRef.isDefined()) {
            Reference referenceNode = imageRef.getReferenceNode(this.referenceRepository);
            String unescape = referenceNode.getUrl().unescape();
            z = CoreNodeRenderer.isSuppressedLinkPrefix(unescape, nodeRendererContext);
            resolvedLink = nodeRendererContext.resolveLink(LinkType.IMAGE, unescape, null, null);
            if (referenceNode.getTitle().isNotNull()) {
                resolvedLink = resolvedLink.withTitle(referenceNode.getTitle().unescape());
            }
        } else {
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.IMAGE_REF, this.referenceRepository.normalizeKey(imageRef.getReference()), null, null);
            resolvedLink = resolveLink;
            if (resolveLink.getStatus() == LinkStatus.UNKNOWN) {
                resolvedLink = null;
            }
        }
        if (resolvedLink != null && !nodeRendererContext.isDoNotRenderLinks() && !z) {
            if (renderVideoImage(imageRef, resolvedLink.getUrl(), new TextCollectingVisitor().collectAndGetText(imageRef), resolvedLink.getNonNullAttributes(), htmlWriter)) {
                return;
            }
        }
        nodeRendererContext.delegateRender();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new GitLabNodeRenderer(dataHolder);
        }
    }
}
