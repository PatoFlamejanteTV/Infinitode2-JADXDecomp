package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.Footnote;
import com.vladsch.flexmark.ext.footnotes.FootnoteBlock;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.NodeRepositoryFormatter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteNodeFormatter.class */
public class FootnoteNodeFormatter extends NodeRepositoryFormatter<FootnoteRepository, FootnoteBlock, Footnote> {
    public static final DataKey<Map<String, String>> FOOTNOTE_TRANSLATION_MAP = new DataKey<>("FOOTNOTE_TRANSLATION_MAP", new HashMap());
    public static final DataKey<Map<String, String>> FOOTNOTE_UNIQUIFICATION_MAP = new DataKey<>("FOOTNOTE_UNIQUIFICATION_MAP", new HashMap());
    private final FootnoteFormatOptions options;

    public FootnoteNodeFormatter(DataHolder dataHolder) {
        super(dataHolder, FOOTNOTE_TRANSLATION_MAP, FOOTNOTE_UNIQUIFICATION_MAP);
        this.options = new FootnoteFormatOptions(dataHolder);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public FootnoteRepository getRepository(DataHolder dataHolder) {
        return FootnoteExtension.FOOTNOTES.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacement getReferencePlacement() {
        return this.options.footnotePlacement;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacementSort getReferenceSort() {
        return this.options.footnoteSort;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public void renderReferenceBlock(FootnoteBlock footnoteBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine().append((CharSequence) "[^");
        markdownWriter.append((CharSequence) transformReferenceId(footnoteBlock.getText().toString(), nodeFormatterContext));
        markdownWriter.append("]: ");
        markdownWriter.pushPrefix().addPrefix((CharSequence) "    ");
        nodeFormatterContext.renderChildren(footnoteBlock);
        markdownWriter.popPrefix();
        markdownWriter.blankLine();
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(Footnote.class, this::render), new NodeFormattingHandler(FootnoteBlock.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        if (this.options.footnotePlacement.isNoChange() || !this.options.footnoteSort.isUnused()) {
            return null;
        }
        return new HashSet(Arrays.asList(Footnote.class));
    }

    private void render(FootnoteBlock footnoteBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderReference(footnoteBlock, nodeFormatterContext, markdownWriter);
    }

    private void render(Footnote footnote, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append("[^");
        if (nodeFormatterContext.isTransformingText()) {
            String transformReferenceId = transformReferenceId(footnote.getText().toString(), nodeFormatterContext);
            nodeFormatterContext.nonTranslatingSpan((nodeFormatterContext2, markdownWriter2) -> {
                markdownWriter2.append((CharSequence) transformReferenceId);
            });
        } else {
            markdownWriter.append((CharSequence) footnote.getText());
        }
        markdownWriter.append("]");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new FootnoteNodeFormatter(dataHolder);
        }
    }
}
