package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.builder.BasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.SegmentOptimizer;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/SpaceInsertingSequenceBuilder.class */
public class SpaceInsertingSequenceBuilder implements ISequenceBuilder<SpaceInsertingSequenceBuilder, BasedSequence> {
    final SequenceBuilder out;
    Node lastNode;
    boolean needEol;
    final boolean addSpacesBetweenNodes;
    boolean addSpaces;

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public /* bridge */ /* synthetic */ SpaceInsertingSequenceBuilder append(Iterable iterable) {
        return append((Iterable<? extends CharSequence>) iterable);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public /* bridge */ /* synthetic */ SpaceInsertingSequenceBuilder addAll(Iterable iterable) {
        return addAll((Iterable<? extends CharSequence>) iterable);
    }

    public static SpaceInsertingSequenceBuilder emptyBuilder(BasedSequence basedSequence) {
        return new SpaceInsertingSequenceBuilder(SequenceBuilder.emptyBuilder(basedSequence), false);
    }

    public static SpaceInsertingSequenceBuilder emptyBuilder(BasedSequence basedSequence, SegmentOptimizer segmentOptimizer) {
        return new SpaceInsertingSequenceBuilder(SequenceBuilder.emptyBuilder(basedSequence, segmentOptimizer), false);
    }

    public static SpaceInsertingSequenceBuilder emptyBuilder(BasedSequence basedSequence, int i) {
        return new SpaceInsertingSequenceBuilder(SequenceBuilder.emptyBuilder(basedSequence, i), BitFieldSet.any(i, TextContainer.F_ADD_SPACES_BETWEEN_NODES));
    }

    public static SpaceInsertingSequenceBuilder emptyBuilder(BasedSequence basedSequence, int i, SegmentOptimizer segmentOptimizer) {
        return new SpaceInsertingSequenceBuilder(SequenceBuilder.emptyBuilder(basedSequence, i, segmentOptimizer), BitFieldSet.any(i, TextContainer.F_ADD_SPACES_BETWEEN_NODES));
    }

    public static SpaceInsertingSequenceBuilder emptyBuilder(SequenceBuilder sequenceBuilder) {
        return new SpaceInsertingSequenceBuilder(sequenceBuilder, false);
    }

    private SpaceInsertingSequenceBuilder(SequenceBuilder sequenceBuilder, boolean z) {
        this.out = sequenceBuilder;
        this.addSpacesBetweenNodes = z;
    }

    public SequenceBuilder getOut() {
        return this.out;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public char charAt(int i) {
        return this.out.charAt(i);
    }

    public boolean isNeedEol() {
        return this.needEol;
    }

    public void setNeedEol(boolean z) {
        this.needEol = z;
    }

    public Node getLastNode() {
        return this.lastNode;
    }

    public void setLastNode(Node node) {
        if (node instanceof Document) {
            return;
        }
        if (this.lastNode != null && this.lastNode.getEndOffset() < node.getStartOffset()) {
            BasedSequence subSequence = getBaseSequence().subSequence(this.lastNode.getEndOffset(), node.getStartOffset());
            this.needEol = subSequence.trim(CharPredicate.SPACE_TAB).length() > 0 && subSequence.trim(CharPredicate.WHITESPACE).isEmpty();
        }
        this.addSpaces = this.addSpacesBetweenNodes;
        this.lastNode = node;
    }

    public boolean needSpace() {
        for (int size = this.out.getSegmentBuilder().size(); size >= 0; size--) {
            Object part = this.out.getSegmentBuilder().getPart(size);
            if (part instanceof Range) {
                if (((Range) part).isNotNull()) {
                    BasedSequence subSequence = getBaseSequence().subSequence(((Range) part).getStart(), ((Range) part).getEnd());
                    if (subSequence.length() > 0) {
                        return !CharPredicate.WHITESPACE.test(subSequence.charAt(subSequence.length() - 1));
                    }
                } else {
                    continue;
                }
            } else if (part instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) part;
                if (charSequence.length() > 0) {
                    return !CharPredicate.WHITESPACE.test(charSequence.charAt(charSequence.length() - 1));
                }
            } else {
                throw new IllegalStateException("Invalid part type " + part.getClass().getSimpleName());
            }
        }
        return false;
    }

    public void appendEol() {
        append('\n');
        this.needEol = false;
    }

    public boolean needEol() {
        if (this.needEol) {
            return true;
        }
        for (int size = this.out.getSegmentBuilder().size(); size >= 0; size--) {
            Object part = this.out.getSegmentBuilder().getPart(size);
            if (part instanceof Range) {
                if (((Range) part).isNotNull()) {
                    BasedSequence subSequence = getBaseSequence().subSequence(((Range) part).getStart(), ((Range) part).getEnd());
                    if (subSequence.length() > 0) {
                        return !CharPredicate.EOL.test(subSequence.charAt(subSequence.length() - 1));
                    }
                } else {
                    continue;
                }
            } else if (part instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) part;
                if (charSequence.length() > 0) {
                    return !CharPredicate.EOL.test(charSequence.charAt(charSequence.length() - 1));
                }
            } else {
                throw new IllegalStateException("Invalid part type " + part.getClass().getSimpleName());
            }
        }
        return false;
    }

    public BasedSequence getBaseSequence() {
        return this.out.getBaseSequence();
    }

    public BasedSegmentBuilder getSegmentBuilder() {
        return this.out.getSegmentBuilder();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public BasedSequence getSingleBasedSequence() {
        return this.out.getSingleBasedSequence();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SpaceInsertingSequenceBuilder getBuilder() {
        return new SpaceInsertingSequenceBuilder(this.out.getBuilder(), this.addSpacesBetweenNodes);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public SpaceInsertingSequenceBuilder append(CharSequence charSequence, int i, int i2) {
        if (this.addSpaces && charSequence != null && i < i2 && !CharPredicate.WHITESPACE.test(charSequence.charAt(i)) && needSpace()) {
            this.out.append(' ');
            this.addSpaces = false;
        }
        this.out.append(charSequence, i, i2);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public SpaceInsertingSequenceBuilder append(char c) {
        if (this.addSpaces && !CharPredicate.WHITESPACE.test(c) && needSpace()) {
            this.out.append(' ');
            this.addSpaces = false;
        }
        this.out.append(c);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SpaceInsertingSequenceBuilder append(char c, int i) {
        if (this.addSpaces && !CharPredicate.WHITESPACE.test(c) && needSpace()) {
            this.out.append(' ');
            this.addSpaces = false;
        }
        this.out.append(c, i);
        return this;
    }

    public SpaceInsertingSequenceBuilder append(int i, int i2) {
        if (this.addSpaces && i < i2 && !CharPredicate.WHITESPACE.test(this.out.getBaseSequence().charAt(i)) && needSpace()) {
            this.out.append(' ');
            this.addSpaces = false;
        }
        this.out.append(i, i2);
        return this;
    }

    public SpaceInsertingSequenceBuilder append(Range range) {
        return append(range.getStart(), range.getEnd());
    }

    public SpaceInsertingSequenceBuilder addRange(Range range) {
        return append(range.getStart(), range.getEnd());
    }

    public SpaceInsertingSequenceBuilder addByOffsets(int i, int i2) {
        return append(i, i2);
    }

    public SpaceInsertingSequenceBuilder addByLength(int i, int i2) {
        return append(i, i + i2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public BasedSequence toSequence() {
        return this.out.toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public int length() {
        return this.out.length();
    }

    public String toStringWithRanges() {
        return this.out.toStringWithRanges();
    }

    public String toString() {
        return this.out.toString();
    }

    public String toStringNoAddedSpaces() {
        return this.out.toStringNoAddedSpaces();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SpaceInsertingSequenceBuilder addAll(Iterable<? extends CharSequence> iterable) {
        return append(iterable);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SpaceInsertingSequenceBuilder append(Iterable<? extends CharSequence> iterable) {
        Iterator<? extends CharSequence> it = iterable.iterator();
        while (it.hasNext()) {
            append(it.next());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SpaceInsertingSequenceBuilder add(CharSequence charSequence) {
        return append(charSequence);
    }

    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, java.lang.Appendable
    public SpaceInsertingSequenceBuilder append(CharSequence charSequence) {
        return charSequence == null ? this : append(charSequence, 0, charSequence.length());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder
    public SpaceInsertingSequenceBuilder append(CharSequence charSequence, int i) {
        return charSequence == null ? this : append(charSequence, i, charSequence.length());
    }
}
