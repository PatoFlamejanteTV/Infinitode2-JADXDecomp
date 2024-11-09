package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.util.ast.BlockNodeVisitor;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import java.util.ArrayList;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/HeadingCollectingVisitor.class */
public class HeadingCollectingVisitor {
    private final ArrayList<Heading> headings = new ArrayList<>();
    private final NodeVisitor myVisitor;

    public HeadingCollectingVisitor() {
        ArrayList<Heading> arrayList = this.headings;
        arrayList.getClass();
        this.myVisitor = new BlockNodeVisitor(new VisitHandler(Heading.class, (v1) -> {
            r9.add(v1);
        }));
    }

    public void collect(Node node) {
        this.myVisitor.visit(node);
    }

    public ArrayList<Heading> collectAndGetHeadings(Node node) {
        this.myVisitor.visit(node);
        return this.headings;
    }

    public ArrayList<Heading> getHeadings() {
        return this.headings;
    }
}
