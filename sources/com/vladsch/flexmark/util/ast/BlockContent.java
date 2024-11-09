package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/BlockContent.class */
public class BlockContent {
    private final ArrayList<BasedSequence> lines = new ArrayList<>();
    private final ArrayList<Integer> lineIndents = new ArrayList<>();
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !BlockContent.class.desiredAssertionStatus();
    }

    public BasedSequence getLine(int i) {
        return this.lines.get(i);
    }

    public BasedSequence getSpanningChars() {
        return this.lines.size() > 0 ? this.lines.get(0).baseSubSequence(this.lines.get(0).getStartOffset(), this.lines.get(this.lines.size() - 1).getEndOffset()) : BasedSequence.NULL;
    }

    public List<BasedSequence> getLines() {
        return this.lines;
    }

    public List<Integer> getLineIndents() {
        return this.lineIndents;
    }

    public int getLineCount() {
        return this.lines.size();
    }

    public BlockContent() {
    }

    public BlockContent(BlockContent blockContent, int i, int i2) {
        if (!$assertionsDisabled && blockContent.lines.size() != blockContent.lineIndents.size()) {
            throw new AssertionError("lines and eols should be of the same size");
        }
        if (blockContent.lines.size() > 0 && i < i2) {
            this.lines.addAll(blockContent.lines.subList(i, i2));
            this.lineIndents.addAll(blockContent.lineIndents.subList(i, i2));
        }
    }

    public int getStartOffset() {
        if (this.lines.size() > 0) {
            return this.lines.get(0).getStartOffset();
        }
        return -1;
    }

    public int getEndOffset() {
        if (this.lines.size() > 0) {
            return this.lines.get(this.lines.size() - 1).getEndOffset();
        }
        return -1;
    }

    public int getLineIndent() {
        if (this.lines.size() > 0) {
            return this.lineIndents.get(0).intValue();
        }
        return 0;
    }

    public int getSourceLength() {
        if (this.lines.size() > 0) {
            return this.lines.get(this.lines.size() - 1).getEndOffset() - this.lines.get(0).getStartOffset();
        }
        return -1;
    }

    public void add(BasedSequence basedSequence, int i) {
        this.lines.add(basedSequence);
        this.lineIndents.add(Integer.valueOf(i));
    }

    public void addAll(List<BasedSequence> list, List<Integer> list2) {
        if (!$assertionsDisabled && list.size() != list2.size()) {
            throw new AssertionError("lines and lineIndents should be of the same size");
        }
        this.lines.addAll(list);
        this.lineIndents.addAll(list2);
    }

    public boolean hasSingleLine() {
        return this.lines.size() == 1;
    }

    public BasedSequence getContents() {
        return this.lines.size() == 0 ? BasedSequence.NULL : getContents(0, this.lines.size());
    }

    public BlockContent subContents(int i, int i2) {
        return new BlockContent(this, i, i2);
    }

    public BasedSequence getContents(int i, int i2) {
        if (this.lines.size() == 0) {
            return BasedSequence.NULL;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("startLine must be at least 0");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("endLine must be at least 0");
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException("endLine must not be less than startLine");
        }
        if (i2 > this.lines.size()) {
            throw new IndexOutOfBoundsException("endLine must not be greater than line cardinality");
        }
        return SegmentedSequence.create(this.lines.get(0), this.lines.subList(i, i2));
    }

    public String getString() {
        if (this.lines.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<BasedSequence> it = this.lines.iterator();
        while (it.hasNext()) {
            sb.append((CharSequence) it.next().trimEOL());
            sb.append('\n');
        }
        return sb.toString();
    }
}
