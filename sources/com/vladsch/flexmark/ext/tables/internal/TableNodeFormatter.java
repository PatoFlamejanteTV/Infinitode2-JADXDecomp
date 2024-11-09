package com.vladsch.flexmark.ext.tables.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ext.tables.TableBlock;
import com.vladsch.flexmark.ext.tables.TableBody;
import com.vladsch.flexmark.ext.tables.TableCaption;
import com.vladsch.flexmark.ext.tables.TableCell;
import com.vladsch.flexmark.ext.tables.TableHead;
import com.vladsch.flexmark.ext.tables.TableRow;
import com.vladsch.flexmark.ext.tables.TableSeparator;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.RenderPurpose;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.MarkdownTable;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.format.TableManipulator;
import com.vladsch.flexmark.util.format.TrackedOffset;
import com.vladsch.flexmark.util.format.TrackedOffsetList;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableNodeFormatter.class */
public class TableNodeFormatter implements NodeFormatter {
    private final TableFormatOptions options;
    private final boolean parserTrimCellWhiteSpace;
    private MarkdownTable myTable;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !TableNodeFormatter.class.desiredAssertionStatus();
    }

    public TableNodeFormatter(DataHolder dataHolder) {
        this.options = new TableFormatOptions(dataHolder);
        this.parserTrimCellWhiteSpace = TablesExtension.TRIM_CELL_WHITESPACE.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(TableBlock.class, this::render), new NodeFormattingHandler(TableHead.class, this::render), new NodeFormattingHandler(TableSeparator.class, this::render), new NodeFormattingHandler(TableBody.class, this::render), new NodeFormattingHandler(TableRow.class, this::render), new NodeFormattingHandler(TableCell.class, this::render), new NodeFormattingHandler(TableCaption.class, this::render), new NodeFormattingHandler(Text.class, this::render)));
    }

    private void render(TableBlock tableBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        this.myTable = new MarkdownTable(tableBlock.getChars(), this.options);
        switch (nodeFormatterContext.getRenderPurpose()) {
            case TRANSLATION_SPANS:
            case TRANSLATED_SPANS:
            case TRANSLATED:
                markdownWriter.blankLine();
                nodeFormatterContext.renderChildren(tableBlock);
                markdownWriter.tailBlankLine();
                break;
            default:
                nodeFormatterContext.renderChildren(tableBlock);
                TrackedOffsetList trackedOffsets = nodeFormatterContext.getTrackedOffsets();
                TrackedOffsetList trackedOffsets2 = trackedOffsets.getTrackedOffsets(tableBlock.getStartOffset(), tableBlock.getEndOffset());
                if (!trackedOffsets.isEmpty()) {
                    for (TrackedOffset trackedOffset : trackedOffsets2) {
                        if (!$assertionsDisabled && (trackedOffset.getOffset() < tableBlock.getStartOffset() || trackedOffset.getOffset() > tableBlock.getEndOffset())) {
                            throw new AssertionError();
                        }
                        this.myTable.addTrackedOffset(trackedOffset);
                    }
                }
                if (this.options.tableManipulator != TableManipulator.NULL) {
                    this.myTable.normalize();
                    this.options.tableManipulator.apply(this.myTable, tableBlock);
                }
                if (this.myTable.getMaxColumns() > 0) {
                    markdownWriter.blankLine();
                    this.myTable.setFormatTableIndentPrefix(markdownWriter.getPrefix());
                    MarkdownWriter markdownWriter2 = new MarkdownWriter(markdownWriter.getOptions());
                    this.myTable.appendTable(markdownWriter2);
                    List<TrackedOffset> trackedOffsets3 = this.myTable.getTrackedOffsets();
                    int offsetWithPending = markdownWriter.offsetWithPending();
                    if (!trackedOffsets2.isEmpty()) {
                        if (!$assertionsDisabled && trackedOffsets2.size() != trackedOffsets3.size()) {
                            throw new AssertionError();
                        }
                        for (TrackedOffset trackedOffset2 : trackedOffsets2) {
                            if (!$assertionsDisabled && (trackedOffset2.getOffset() < tableBlock.getStartOffset() || trackedOffset2.getOffset() > tableBlock.getEndOffset())) {
                                throw new AssertionError();
                            }
                            if (trackedOffset2.isResolved()) {
                                trackedOffset2.setIndex(trackedOffset2.getIndex() + offsetWithPending);
                            }
                        }
                    }
                    markdownWriter.pushPrefix().setPrefix((CharSequence) "", false).pushOptions().removeOptions(LineAppendable.F_WHITESPACE_REMOVAL).append(markdownWriter2).popOptions().popPrefix(false);
                    markdownWriter.tailBlankLine();
                    if (this.myTable.getMaxColumns() > 0 && !trackedOffsets2.isEmpty() && this.options.dumpIntellijOffsets) {
                        markdownWriter.append("\nTracked Offsets").line();
                        CharSequence charSequence = "  ";
                        int i = 0;
                        for (TrackedOffset trackedOffset3 : trackedOffsets3) {
                            i++;
                            markdownWriter.append(charSequence).append((CharSequence) String.format(Locale.US, "%d:[%d,%d] was:[%d,%d]", Integer.valueOf(i), Integer.valueOf(trackedOffset3.getIndex()), Integer.valueOf(trackedOffset3.getIndex() + 1), Integer.valueOf(trackedOffset3.getOffset()), Integer.valueOf(trackedOffset3.getOffset() + 1)));
                            charSequence = SequenceUtils.SPACE;
                        }
                        markdownWriter.append(SequenceUtils.EOL);
                        break;
                    }
                }
                break;
        }
        this.myTable = null;
    }

    private void render(TableHead tableHead, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        this.myTable.setSeparator(false);
        this.myTable.setHeader(true);
        nodeFormatterContext.renderChildren(tableHead);
    }

    private void render(TableSeparator tableSeparator, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        this.myTable.setSeparator(true);
        nodeFormatterContext.renderChildren(tableSeparator);
    }

    private void render(TableBody tableBody, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        this.myTable.setSeparator(false);
        this.myTable.setHeader(false);
        nodeFormatterContext.renderChildren(tableBody);
    }

    private void render(TableRow tableRow, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        nodeFormatterContext.renderChildren(tableRow);
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.FORMAT) {
            if (this.myTable.isSeparator()) {
                return;
            }
            this.myTable.nextRow();
            return;
        }
        markdownWriter.line();
    }

    private void render(TableCaption tableCaption, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.FORMAT) {
            this.myTable.setCaptionWithMarkers(tableCaption, tableCaption.getOpeningMarker(), tableCaption.getText(), tableCaption.getClosingMarker());
            return;
        }
        String str = tableCaption.hasChildren() ? "dummy" : "";
        if (MarkdownTable.formattedCaption(BasedSequence.of(str).subSequence(0, str.length()), this.options) != null) {
            markdownWriter.line().append((CharSequence) tableCaption.getOpeningMarker());
            nodeFormatterContext.renderChildren(tableCaption);
            markdownWriter.append((CharSequence) tableCaption.getClosingMarker()).line();
        }
    }

    private void render(TableCell tableCell, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.FORMAT) {
            BasedSequence text = tableCell.getText();
            if (this.options.trimCellWhitespace) {
                if (text.isBlank() && !text.isEmpty()) {
                    text = text.subSequence(0, 1);
                } else {
                    text = text.trim();
                }
            }
            this.myTable.addCell(new com.vladsch.flexmark.util.format.TableCell(tableCell, tableCell.getOpeningMarker(), text, tableCell.getClosingMarker(), 1, tableCell.getSpan(), tableCell.getAlignment() == null ? CellAlignment.NONE : tableCell.getAlignment().cellAlignment()));
            return;
        }
        if (tableCell.getPrevious() == null) {
            if (this.options.leadTrailPipes && tableCell.getOpeningMarker().isEmpty()) {
                markdownWriter.append('|');
            } else {
                markdownWriter.append((CharSequence) tableCell.getOpeningMarker());
            }
        } else {
            markdownWriter.append((CharSequence) tableCell.getOpeningMarker());
        }
        if (!this.myTable.isSeparator() && this.options.spaceAroundPipes && (!tableCell.getText().startsWith(SequenceUtils.SPACE) || this.parserTrimCellWhiteSpace)) {
            markdownWriter.append(' ');
        }
        String[] strArr = {""};
        nodeFormatterContext.translatingSpan((nodeFormatterContext2, markdownWriter2) -> {
            nodeFormatterContext2.renderChildren(tableCell);
            strArr[0] = markdownWriter2.toString(-1, -1);
        });
        if (!this.myTable.isSeparator() && this.options.spaceAroundPipes && (!strArr[0].endsWith(SequenceUtils.SPACE) || this.parserTrimCellWhiteSpace)) {
            markdownWriter.append(' ');
        }
        if (tableCell.getNext() == null) {
            if (this.options.leadTrailPipes && tableCell.getClosingMarker().isEmpty()) {
                markdownWriter.append('|');
                return;
            } else {
                markdownWriter.append((CharSequence) tableCell.getClosingMarker());
                return;
            }
        }
        markdownWriter.append((CharSequence) tableCell.getClosingMarker());
    }

    private void render(Text text, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (this.myTable != null && this.myTable.isSeparator()) {
            Node ancestorOfType = text.getAncestorOfType(Paragraph.class);
            if ((ancestorOfType instanceof Paragraph) && ((Paragraph) ancestorOfType).hasTableSeparator()) {
                markdownWriter.pushPrefix().addPrefix((CharSequence) SequenceUtils.SPACE).append((CharSequence) text.getChars()).popPrefix();
                return;
            } else {
                markdownWriter.append((CharSequence) text.getChars());
                return;
            }
        }
        markdownWriter.append((CharSequence) text.getChars());
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new TableNodeFormatter(dataHolder);
        }
    }
}
