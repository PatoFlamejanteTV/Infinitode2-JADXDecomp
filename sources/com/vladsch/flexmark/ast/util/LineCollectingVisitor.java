package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.HtmlInline;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.sequence.Range;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/LineCollectingVisitor.class */
public class LineCollectingVisitor {
    private final NodeVisitor myVisitor = new NodeVisitor(new VisitHandler(Text.class, this::visit), new VisitHandler(TextBase.class, this::visit), new VisitHandler(HtmlEntity.class, this::visit), new VisitHandler(HtmlInline.class, this::visit), new VisitHandler(SoftLineBreak.class, this::visit), new VisitHandler(HardLineBreak.class, this::visit));
    private List<Range> myLines = Collections.EMPTY_LIST;
    private List<Integer> myEOLs;
    private int myStartOffset;
    private int myEndOffset;

    private void finalizeLines() {
        if (this.myStartOffset < this.myEndOffset) {
            this.myLines.add(Range.of(this.myStartOffset, this.myEndOffset));
            this.myEOLs.add(0);
            this.myStartOffset = this.myEndOffset;
        }
    }

    public List<Range> getLines() {
        finalizeLines();
        return this.myLines;
    }

    public List<Integer> getEOLs() {
        finalizeLines();
        return this.myEOLs;
    }

    public void collect(Node node) {
        this.myLines = new ArrayList();
        this.myEOLs = new ArrayList();
        this.myStartOffset = node.getStartOffset();
        this.myEndOffset = node.getEndOffset();
        this.myVisitor.visit(node);
    }

    public List<Range> collectAndGetRanges(Node node) {
        collect(node);
        return getLines();
    }

    private void visit(SoftLineBreak softLineBreak) {
        this.myLines.add(Range.of(this.myStartOffset, softLineBreak.getEndOffset()));
        this.myEOLs.add(Integer.valueOf(softLineBreak.getTextLength()));
        this.myStartOffset = softLineBreak.getEndOffset();
    }

    private void visit(HardLineBreak hardLineBreak) {
        this.myLines.add(Range.of(this.myStartOffset, hardLineBreak.getEndOffset()));
        this.myEOLs.add(Integer.valueOf(hardLineBreak.getTextLength()));
        this.myStartOffset = hardLineBreak.getEndOffset();
    }

    private void visit(HtmlEntity htmlEntity) {
        this.myEndOffset = htmlEntity.getEndOffset();
    }

    private void visit(HtmlInline htmlInline) {
        this.myEndOffset = htmlInline.getEndOffset();
    }

    private void visit(Text text) {
        this.myEndOffset = text.getEndOffset();
    }

    private void visit(TextBase textBase) {
        this.myEndOffset = textBase.getEndOffset();
    }
}
