package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.ast.util.TextCollectingVisitor;

@Deprecated
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableTextCollectingVisitor.class */
public class TableTextCollectingVisitor extends TextCollectingVisitor {
    public static final Class<?>[] TABLE_LINE_BREAK_CLASSES = {TableBlock.class, TableRow.class, TableCaption.class};

    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Class[], java.lang.Class[][]] */
    public TableTextCollectingVisitor(Class<?>... clsArr) {
        super(clsArr.length == 0 ? TABLE_LINE_BREAK_CLASSES : concatArrays(new Class[]{TABLE_LINE_BREAK_CLASSES, clsArr}));
    }
}
