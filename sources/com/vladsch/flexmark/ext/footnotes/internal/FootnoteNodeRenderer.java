package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.Footnote;
import com.vladsch.flexmark.ext.footnotes.FootnoteBlock;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.PhasedNodeRenderer;
import com.vladsch.flexmark.html.renderer.RenderingPhase;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteNodeRenderer.class */
public class FootnoteNodeRenderer implements PhasedNodeRenderer {
    private final FootnoteRepository footnoteRepository;
    private final FootnoteOptions options;
    private final boolean recheckUndefinedReferences;

    public FootnoteNodeRenderer(DataHolder dataHolder) {
        this.options = new FootnoteOptions(dataHolder);
        this.footnoteRepository = FootnoteExtension.FOOTNOTES.get(dataHolder);
        this.recheckUndefinedReferences = HtmlRenderer.RECHECK_UNDEFINED_REFERENCES.get(dataHolder).booleanValue();
        this.footnoteRepository.resolveFootnoteOrdinals();
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(Footnote.class, this::render), new NodeRenderingHandler(FootnoteBlock.class, this::render)));
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public Set<RenderingPhase> getRenderingPhases() {
        HashSet hashSet = new HashSet();
        hashSet.add(RenderingPhase.BODY_TOP);
        hashSet.add(RenderingPhase.BODY_BOTTOM);
        return hashSet;
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public void renderDocument(NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, Document document, RenderingPhase renderingPhase) {
        if (renderingPhase == RenderingPhase.BODY_TOP && this.recheckUndefinedReferences) {
            boolean[] zArr = {false};
            new NodeVisitor(new VisitHandler(Footnote.class, footnote -> {
                FootnoteBlock footnoteBlock;
                if (!footnote.isDefined() && (footnoteBlock = footnote.getFootnoteBlock(this.footnoteRepository)) != null) {
                    this.footnoteRepository.addFootnoteReference(footnoteBlock, footnote);
                    footnote.setFootnoteBlock(footnoteBlock);
                    zArr[0] = true;
                }
            })).visit(document);
            if (zArr[0]) {
                this.footnoteRepository.resolveFootnoteOrdinals();
            }
        }
        if (renderingPhase == RenderingPhase.BODY_BOTTOM && this.footnoteRepository.getReferencedFootnoteBlocks().size() > 0) {
            htmlWriter.attr(Attribute.CLASS_ATTR, "footnotes").withAttr().tagIndent((CharSequence) FlexmarkHtmlConverter.DIV_NODE, () -> {
                htmlWriter.tagVoidLine(FlexmarkHtmlConverter.HR_NODE);
                htmlWriter.tagIndent(FlexmarkHtmlConverter.OL_NODE, () -> {
                    for (FootnoteBlock footnoteBlock : this.footnoteRepository.getReferencedFootnoteBlocks()) {
                        int footnoteOrdinal = footnoteBlock.getFootnoteOrdinal();
                        htmlWriter.attr(Attribute.ID_ATTR, (CharSequence) ("fn-" + footnoteOrdinal));
                        htmlWriter.withAttr().tagIndent(FlexmarkHtmlConverter.LI_NODE, () -> {
                            nodeRendererContext.renderChildren(footnoteBlock);
                            int footnoteReferences = footnoteBlock.getFootnoteReferences();
                            int i = 0;
                            while (i < footnoteReferences) {
                                htmlWriter.attr("href", (CharSequence) ("#fnref-" + footnoteOrdinal + (i == 0 ? "" : String.format(Locale.US, "-%d", Integer.valueOf(i)))));
                                if (!this.options.footnoteBackLinkRefClass.isEmpty()) {
                                    htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.options.footnoteBackLinkRefClass);
                                }
                                htmlWriter.line().withAttr().tag((CharSequence) FlexmarkHtmlConverter.A_NODE);
                                htmlWriter.raw((CharSequence) this.options.footnoteBackRefString);
                                htmlWriter.tag("/a");
                                i++;
                            }
                        });
                    }
                });
            });
        }
    }

    private void render(FootnoteBlock footnoteBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(Footnote footnote, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        FootnoteBlock footnoteBlock = footnote.getFootnoteBlock();
        if (footnoteBlock == null) {
            htmlWriter.raw("[^");
            nodeRendererContext.renderChildren(footnote);
            htmlWriter.raw("]");
        } else {
            int footnoteOrdinal = footnoteBlock.getFootnoteOrdinal();
            int referenceOrdinal = footnote.getReferenceOrdinal();
            htmlWriter.attr(Attribute.ID_ATTR, (CharSequence) ("fnref-" + footnoteOrdinal + (referenceOrdinal == 0 ? "" : String.format(Locale.US, "-%d", Integer.valueOf(referenceOrdinal)))));
            htmlWriter.srcPos(footnote.getChars()).withAttr().tag(FlexmarkHtmlConverter.SUP_NODE, false, false, () -> {
                if (!this.options.footnoteLinkRefClass.isEmpty()) {
                    htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.options.footnoteLinkRefClass);
                }
                htmlWriter.attr("href", (CharSequence) ("#fn-" + footnoteOrdinal));
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.A_NODE);
                htmlWriter.raw((CharSequence) (this.options.footnoteRefPrefix + footnoteOrdinal + this.options.footnoteRefSuffix));
                htmlWriter.tag("/a");
            });
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new FootnoteNodeRenderer(dataHolder);
        }
    }
}
