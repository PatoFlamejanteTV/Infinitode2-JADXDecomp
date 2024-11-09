package com.vladsch.flexmark.ext.tables;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableVisitor.class */
public interface TableVisitor {
    void visit(TableBlock tableBlock);

    void visit(TableHead tableHead);

    void visit(TableSeparator tableSeparator);

    void visit(TableBody tableBody);

    void visit(TableRow tableRow);

    void visit(TableCell tableCell);

    void visit(TableCaption tableCaption);
}
