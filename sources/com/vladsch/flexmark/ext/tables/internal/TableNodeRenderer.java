package com.vladsch.flexmark.ext.tables.internal;

import com.vladsch.flexmark.ext.tables.TableBlock;
import com.vladsch.flexmark.ext.tables.TableBody;
import com.vladsch.flexmark.ext.tables.TableCaption;
import com.vladsch.flexmark.ext.tables.TableCell;
import com.vladsch.flexmark.ext.tables.TableHead;
import com.vladsch.flexmark.ext.tables.TableRow;
import com.vladsch.flexmark.ext.tables.TableSeparator;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableNodeRenderer.class */
public class TableNodeRenderer implements NodeRenderer {
    private final TableParserOptions options;

    public TableNodeRenderer(DataHolder dataHolder) {
        this.options = new TableParserOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(TableBlock.class, this::render), new NodeRenderingHandler(TableHead.class, this::render), new NodeRenderingHandler(TableSeparator.class, this::render), new NodeRenderingHandler(TableBody.class, this::render), new NodeRenderingHandler(TableRow.class, this::render), new NodeRenderingHandler(TableCell.class, this::render), new NodeRenderingHandler(TableCaption.class, this::render)));
    }

    private void render(TableBlock tableBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!this.options.className.isEmpty()) {
            htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.options.className);
        }
        htmlWriter.srcPosWithEOL(tableBlock.getChars()).withAttr().tagLineIndent(FlexmarkHtmlConverter.TABLE_NODE, () -> {
            nodeRendererContext.renderChildren(tableBlock);
        }).line();
    }

    private void render(TableHead tableHead, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.THEAD_NODE, () -> {
            nodeRendererContext.renderChildren(tableHead);
        });
    }

    private void render(TableSeparator tableSeparator, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(TableBody tableBody, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.TBODY_NODE, () -> {
            nodeRendererContext.renderChildren(tableBody);
        });
    }

    private void render(TableRow tableRow, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.srcPos(tableRow.getChars().trimStart()).withAttr().tagLine(FlexmarkHtmlConverter.TR_NODE, () -> {
            nodeRendererContext.renderChildren(tableRow);
        });
    }

    private void render(TableCaption tableCaption, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.srcPos(tableCaption.getChars().trimStart()).withAttr().tagLine(FlexmarkHtmlConverter.CAPTION_NODE, () -> {
            nodeRendererContext.renderChildren(tableCaption);
        });
    }

    private void render(TableCell tableCell, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String str = tableCell.isHeader() ? FlexmarkHtmlConverter.TH_NODE : FlexmarkHtmlConverter.TD_NODE;
        if (tableCell.getAlignment() != null) {
            htmlWriter.attr("align", (CharSequence) getAlignValue(tableCell.getAlignment()));
        }
        if (this.options.columnSpans && tableCell.getSpan() > 1) {
            htmlWriter.attr("colspan", (CharSequence) String.valueOf(tableCell.getSpan()));
        }
        htmlWriter.srcPos(tableCell.getText()).withAttr().tag((CharSequence) str);
        nodeRendererContext.renderChildren(tableCell);
        htmlWriter.tag((CharSequence) ("/" + str));
    }

    private static String getAlignValue(TableCell.Alignment alignment) {
        switch (alignment) {
            case LEFT:
                return "left";
            case CENTER:
                return "center";
            case RIGHT:
                return "right";
            default:
                throw new IllegalStateException("Unknown alignment: " + alignment);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new TableNodeRenderer(dataHolder);
        }
    }
}
