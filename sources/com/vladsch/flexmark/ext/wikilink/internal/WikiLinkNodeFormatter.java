package com.vladsch.flexmark.ext.wikilink.internal;

import com.vladsch.flexmark.ext.wikilink.WikiImage;
import com.vladsch.flexmark.ext.wikilink.WikiLink;
import com.vladsch.flexmark.ext.wikilink.WikiNode;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.formatter.FormattingPhase;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.PhasedNodeFormatter;
import com.vladsch.flexmark.formatter.RenderPurpose;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpaceMapper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkNodeFormatter.class */
public class WikiLinkNodeFormatter implements PhasedNodeFormatter {
    public static final HashSet<FormattingPhase> FORMATTING_PHASES = new HashSet<>(Arrays.asList(FormattingPhase.COLLECT, FormattingPhase.DOCUMENT_TOP));
    private Map<String, String> attributeUniquificationIdMap;
    private WikiLinkOptions options;

    public WikiLinkNodeFormatter(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(WikiLink.class, this::render), new NodeFormattingHandler(WikiImage.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return new HashSet(Arrays.asList(WikiLink.class, WikiImage.class));
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public Set<FormattingPhase> getFormattingPhases() {
        return FORMATTING_PHASES;
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase) {
        this.attributeUniquificationIdMap = Formatter.ATTRIBUTE_UNIQUIFICATION_ID_MAP.get(nodeFormatterContext.getTranslationStore());
        this.options = new WikiLinkOptions(document);
    }

    private void render(WikiLink wikiLink, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append((CharSequence) wikiLink.getOpeningMarker());
        if (wikiLink.isLinkIsFirst()) {
            renderLink(wikiLink, nodeFormatterContext, markdownWriter);
            renderText(wikiLink, nodeFormatterContext, markdownWriter);
        } else {
            renderText(wikiLink, nodeFormatterContext, markdownWriter);
            renderLink(wikiLink, nodeFormatterContext, markdownWriter);
        }
        markdownWriter.append((CharSequence) wikiLink.getClosingMarker());
    }

    private void render(WikiImage wikiImage, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append((CharSequence) wikiImage.getOpeningMarker());
        if (wikiImage.isLinkIsFirst()) {
            renderLink(wikiImage, nodeFormatterContext, markdownWriter);
            renderText(wikiImage, nodeFormatterContext, markdownWriter);
        } else {
            renderText(wikiImage, nodeFormatterContext, markdownWriter);
            renderLink(wikiImage, nodeFormatterContext, markdownWriter);
        }
        markdownWriter.append((CharSequence) wikiImage.getClosingMarker());
    }

    private void renderText(WikiNode wikiNode, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!nodeFormatterContext.isTransformingText()) {
            if (wikiNode.getText().isNotNull()) {
                if (wikiNode.isLinkIsFirst()) {
                    markdownWriter.append((CharSequence) wikiNode.getTextSeparatorMarker());
                }
                if (nodeFormatterContext.getFormatterOptions().rightMargin > 0) {
                    markdownWriter.append((CharSequence) wikiNode.getText().toMapped(SpaceMapper.toNonBreakSpace));
                } else {
                    nodeFormatterContext.renderChildren(wikiNode);
                }
                if (!wikiNode.isLinkIsFirst()) {
                    markdownWriter.append((CharSequence) wikiNode.getTextSeparatorMarker());
                    return;
                }
                return;
            }
            return;
        }
        switch (nodeFormatterContext.getRenderPurpose()) {
            case TRANSLATION_SPANS:
            case TRANSLATED_SPANS:
                if (wikiNode.isLinkIsFirst()) {
                    markdownWriter.append('|');
                }
                BasedSequence pageRef = wikiNode.getText().isNull() ? wikiNode.getPageRef() : wikiNode.getText();
                if (this.options.allowInlines) {
                    nodeFormatterContext.renderChildren(wikiNode);
                } else {
                    markdownWriter.appendTranslating(pageRef.unescape());
                }
                if (!wikiNode.isLinkIsFirst()) {
                    markdownWriter.append('|');
                    return;
                }
                return;
            case TRANSLATED:
                if (wikiNode.isLinkIsFirst()) {
                    markdownWriter.append((CharSequence) wikiNode.getTextSeparatorMarker());
                }
                if (this.options.allowInlines) {
                    nodeFormatterContext.renderChildren(wikiNode);
                } else {
                    markdownWriter.append(escapePipeAnchors(nodeFormatterContext.transformTranslating(null, wikiNode.getText(), null, null)));
                }
                if (!wikiNode.isLinkIsFirst()) {
                    markdownWriter.append((CharSequence) wikiNode.getTextSeparatorMarker());
                    return;
                }
                return;
            default:
                throw new IllegalStateException("Unexpected renderer purpose");
        }
    }

    private CharSequence escapeUnescapedPipeAnchors(CharSequence charSequence) {
        boolean z = false;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            switch (charAt) {
                case '#':
                    if (!z && this.options.allowAnchors && this.options.allowAnchorEscape) {
                        sb.append('\\');
                        break;
                    }
                    break;
                case '\\':
                    z = !z;
                    break;
                case '|':
                    if (!z && this.options.allowPipeEscape) {
                        sb.append('\\');
                        break;
                    }
                    break;
                default:
                    z = false;
                    break;
            }
            sb.append(charAt);
        }
        if (z) {
            sb.append('\\');
        }
        return sb;
    }

    private CharSequence escapePipeAnchors(CharSequence charSequence) {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            switch (charAt) {
                case '#':
                    if (this.options.allowAnchors && this.options.allowAnchorEscape) {
                        sb.append('\\');
                        break;
                    }
                    break;
                case '\\':
                    sb.append('\\');
                    break;
                case '|':
                    if (this.options.allowPipeEscape) {
                        sb.append('\\');
                        break;
                    } else {
                        break;
                    }
            }
            sb.append(charAt);
        }
        return sb;
    }

    private void renderLink(WikiNode wikiNode, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!nodeFormatterContext.isTransformingText()) {
            if (nodeFormatterContext.getFormatterOptions().rightMargin > 0) {
                markdownWriter.append((CharSequence) wikiNode.getLink().toMapped(SpaceMapper.toNonBreakSpace));
                return;
            } else {
                markdownWriter.append((CharSequence) wikiNode.getLink());
                return;
            }
        }
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS) {
            markdownWriter.appendNonTranslating(wikiNode.getPageRef());
            markdownWriter.append((CharSequence) wikiNode.getAnchorMarker());
            if (wikiNode.getAnchorRef().isNotNull()) {
                markdownWriter.append(nodeFormatterContext.transformAnchorRef(wikiNode.getPageRef(), wikiNode.getAnchorRef()));
                return;
            }
            return;
        }
        CharSequence transformNonTranslating = nodeFormatterContext.transformNonTranslating(null, wikiNode.getPageRef(), null, null);
        markdownWriter.append(escapeUnescapedPipeAnchors(transformNonTranslating));
        markdownWriter.append((CharSequence) wikiNode.getAnchorMarker());
        if (wikiNode.getAnchorRef().isNotNull()) {
            CharSequence transformAnchorRef = nodeFormatterContext.transformAnchorRef(wikiNode.getPageRef(), wikiNode.getAnchorRef());
            if (this.attributeUniquificationIdMap != null && nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED && nodeFormatterContext.getMergeContext() != null) {
                String valueOf = String.valueOf(transformAnchorRef);
                if (transformNonTranslating.length() == 0) {
                    valueOf = this.attributeUniquificationIdMap.getOrDefault(valueOf, valueOf);
                }
                markdownWriter.append((CharSequence) valueOf);
                return;
            }
            markdownWriter.append(transformAnchorRef);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new WikiLinkNodeFormatter(dataHolder);
        }
    }
}
