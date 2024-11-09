package com.vladsch.flexmark.ext.tables.internal;

import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ext.tables.TableBlock;
import com.vladsch.flexmark.ext.tables.TableBody;
import com.vladsch.flexmark.ext.tables.TableCell;
import com.vladsch.flexmark.ext.tables.TableHead;
import com.vladsch.flexmark.ext.tables.TableRow;
import com.vladsch.flexmark.ext.tables.TableSeparator;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableJiraRenderer.class */
public class TableJiraRenderer implements NodeRenderer {
    public TableJiraRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(TableBlock.class, this::render), new NodeRenderingHandler(TableHead.class, this::render), new NodeRenderingHandler(TableSeparator.class, this::render), new NodeRenderingHandler(TableBody.class, this::render), new NodeRenderingHandler(TableRow.class, this::render), new NodeRenderingHandler(TableCell.class, this::render)));
    }

    private HtmlWriter tailBlankLine(Node node, HtmlWriter htmlWriter) {
        return tailBlankLine(node, 1, htmlWriter);
    }

    public boolean isLastBlockQuoteChild(Node node) {
        Node parent = node.getParent();
        return (parent instanceof BlockQuote) && parent.getLastChild() == node;
    }

    public HtmlWriter tailBlankLine(Node node, int i, HtmlWriter htmlWriter) {
        if (isLastBlockQuoteChild(node)) {
            BasedSequence prefix = htmlWriter.getPrefix();
            htmlWriter.popPrefix();
            htmlWriter.blankLine(i);
            htmlWriter.pushPrefix();
            htmlWriter.setPrefix((CharSequence) prefix, false);
        } else {
            htmlWriter.blankLine(i);
        }
        return htmlWriter;
    }

    private void render(TableBlock tableBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(tableBlock);
        tailBlankLine(tableBlock, htmlWriter);
    }

    private void render(TableHead tableHead, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(tableHead);
    }

    private void render(TableSeparator tableSeparator, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(TableBody tableBody, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(tableBody);
    }

    private void render(TableRow tableRow, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (tableRow.getParent() instanceof TableHead) {
            htmlWriter.line().raw((CharSequence) "||");
        } else if (tableRow.getParent() instanceof TableBody) {
            htmlWriter.line().raw((CharSequence) "|");
        }
        nodeRendererContext.renderChildren(tableRow);
        htmlWriter.line();
    }

    private void render(TableCell tableCell, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(tableCell);
        if (tableCell.getGrandParent() instanceof TableHead) {
            htmlWriter.raw("||");
        } else if (tableCell.getGrandParent() instanceof TableBody) {
            htmlWriter.raw("|");
        }
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableJiraRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new TableJiraRenderer(dataHolder);
        }
    }
}
