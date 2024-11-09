package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.MarkdownTable;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableExtractingVisitor.class */
public class TableExtractingVisitor {
    private final TableFormatOptions options;
    private MarkdownTable myTable;
    private final NodeVisitor myVisitor = new NodeVisitor(new VisitHandler(TableBlock.class, this::visit), new VisitHandler(TableHead.class, this::visit), new VisitHandler(TableSeparator.class, this::visit), new VisitHandler(TableBody.class, this::visit), new VisitHandler(TableRow.class, this::visit), new VisitHandler(TableCell.class, this::visit), new VisitHandler(TableCaption.class, this::visit));
    private final List<MarkdownTable> myTables = new ArrayList();

    public TableExtractingVisitor(DataHolder dataHolder) {
        this.options = new TableFormatOptions(dataHolder);
    }

    public MarkdownTable[] getTables(Node node) {
        this.myTable = null;
        this.myVisitor.visit(node);
        return (MarkdownTable[]) this.myTables.toArray(new MarkdownTable[0]);
    }

    private void visit(TableBlock tableBlock) {
        this.myTable = new MarkdownTable(tableBlock.getChars(), this.options);
        this.myVisitor.visitChildren(tableBlock);
        this.myTables.add(this.myTable);
        this.myTable = null;
    }

    private void visit(TableHead tableHead) {
        this.myTable.setSeparator(false);
        this.myTable.setHeader(true);
        this.myVisitor.visitChildren(tableHead);
    }

    private void visit(TableSeparator tableSeparator) {
        this.myTable.setSeparator(true);
        this.myVisitor.visitChildren(tableSeparator);
    }

    private void visit(TableBody tableBody) {
        this.myTable.setSeparator(false);
        this.myTable.setHeader(false);
        this.myVisitor.visitChildren(tableBody);
    }

    private void visit(TableRow tableRow) {
        this.myVisitor.visitChildren(tableRow);
        if (!this.myTable.isSeparator()) {
            this.myTable.nextRow();
        }
    }

    private void visit(TableCaption tableCaption) {
        this.myTable.setCaptionWithMarkers(tableCaption, tableCaption.getOpeningMarker(), tableCaption.getText(), tableCaption.getClosingMarker());
    }

    private void visit(TableCell tableCell) {
        BasedSequence text = tableCell.getText();
        if (this.options.trimCellWhitespace) {
            if (text.isBlank() && !text.isEmpty()) {
                text = text.subSequence(0, 1);
            } else {
                text = text.trim();
            }
        }
        this.myTable.addCell(new com.vladsch.flexmark.util.format.TableCell(tableCell, tableCell.getOpeningMarker(), text, tableCell.getClosingMarker(), 1, tableCell.getSpan(), tableCell.getAlignment() == null ? CellAlignment.NONE : tableCell.getAlignment().cellAlignment()));
    }
}
