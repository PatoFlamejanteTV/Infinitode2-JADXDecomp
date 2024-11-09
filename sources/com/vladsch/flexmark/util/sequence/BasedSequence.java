package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedSequence.class */
public interface BasedSequence extends BasedOptionsHolder, IRichSequence<BasedSequence> {
    public static final BasedSequence NULL = new EmptyBasedSequence();
    public static final BasedSequence EMPTY = new EmptyBasedSequence();
    public static final BasedSequence EOL = CharSubSequence.of((CharSequence) SequenceUtils.EOL);
    public static final BasedSequence SPACE = CharSubSequence.of((CharSequence) SequenceUtils.SPACE);
    public static final List<BasedSequence> EMPTY_LIST = new ArrayList();
    public static final BasedSequence[] EMPTY_ARRAY = new BasedSequence[0];
    public static final BasedSequence[] EMPTY_SEGMENTS = new BasedSequence[0];
    public static final BasedSequence LINE_SEP = CharSubSequence.of((CharSequence) SequenceUtils.LINE_SEP);

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    SequenceBuilder getBuilder();

    Object getBase();

    BasedSequence getBaseSequence();

    int getStartOffset();

    int getEndOffset();

    int getIndexOffset(int i);

    void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder);

    SegmentTree getSegmentTree();

    Range getSourceRange();

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
    BasedSequence subSequence(int i, int i2);

    BasedSequence baseSubSequence(int i, int i2);

    BasedSequence baseSubSequence(int i);

    char safeBaseCharAt(int i);

    boolean isBaseCharAt(int i, CharPredicate charPredicate);

    BasedSequence getEmptyPrefix();

    BasedSequence getEmptySuffix();

    String unescape();

    String unescapeNoEntities();

    BasedSequence unescape(ReplacedTextMapper replacedTextMapper);

    BasedSequence normalizeEOL(ReplacedTextMapper replacedTextMapper);

    BasedSequence normalizeEndWithEOL(ReplacedTextMapper replacedTextMapper);

    boolean isContinuedBy(BasedSequence basedSequence);

    boolean isContinuationOf(BasedSequence basedSequence);

    BasedSequence spliceAtEnd(BasedSequence basedSequence);

    boolean containsAllOf(BasedSequence basedSequence);

    boolean containsSomeOf(BasedSequence basedSequence);

    BasedSequence prefixOf(BasedSequence basedSequence);

    BasedSequence suffixOf(BasedSequence basedSequence);

    BasedSequence intersect(BasedSequence basedSequence);

    BasedSequence extendByAny(CharPredicate charPredicate, int i);

    BasedSequence extendByAny(CharPredicate charPredicate);

    BasedSequence extendByOneOfAny(CharPredicate charPredicate);

    BasedSequence extendByAnyNot(CharPredicate charPredicate, int i);

    BasedSequence extendByAnyNot(CharPredicate charPredicate);

    BasedSequence extendByOneOfAnyNot(CharPredicate charPredicate);

    BasedSequence extendToEndOfLine(CharPredicate charPredicate, boolean z);

    BasedSequence extendToEndOfLine(CharPredicate charPredicate);

    BasedSequence extendToEndOfLine(boolean z);

    BasedSequence extendToEndOfLine();

    BasedSequence extendToStartOfLine(CharPredicate charPredicate, boolean z);

    BasedSequence extendToStartOfLine(CharPredicate charPredicate);

    BasedSequence extendToStartOfLine(boolean z);

    BasedSequence extendToStartOfLine();

    BasedSequence prefixWithIndent(int i);

    BasedSequence prefixWithIndent();

    Pair<Integer, Integer> baseLineColumnAtIndex(int i);

    Range baseLineRangeAtIndex(int i);

    int baseEndOfLine(int i);

    int baseEndOfLineAnyEOL(int i);

    int baseStartOfLine(int i);

    int baseStartOfLineAnyEOL(int i);

    int baseColumnAtIndex(int i);

    Pair<Integer, Integer> baseLineColumnAtStart();

    Pair<Integer, Integer> baseLineColumnAtEnd();

    int baseEndOfLine();

    int baseEndOfLineAnyEOL();

    int baseStartOfLine();

    int baseStartOfLineAnyEOL();

    Range baseLineRangeAtStart();

    Range baseLineRangeAtEnd();

    int baseColumnAtEnd();

    int baseColumnAtStart();

    static BasedSequence of(CharSequence charSequence) {
        return BasedSequenceImpl.create(charSequence);
    }

    @Deprecated
    static BasedSequence of(CharSequence charSequence, int i) {
        return of(charSequence).subSequence(i);
    }

    @Deprecated
    static BasedSequence of(CharSequence charSequence, int i, int i2) {
        return of(charSequence).subSequence(i, i2);
    }

    static BasedSequence ofSpaces(int i) {
        return of(RepeatedSequence.ofSpaces(i));
    }

    static BasedSequence repeatOf(char c, int i) {
        return of(RepeatedSequence.repeatOf(String.valueOf(c), 0, i));
    }

    static BasedSequence repeatOf(CharSequence charSequence, int i) {
        return of(RepeatedSequence.repeatOf(charSequence, 0, charSequence.length() * i));
    }

    static BasedSequence repeatOf(CharSequence charSequence, int i, int i2) {
        return of(RepeatedSequence.repeatOf(charSequence, i, i2));
    }

    @Deprecated
    default BasedSequence extendToAny(CharPredicate charPredicate, int i) {
        return extendByAnyNot(charPredicate, i);
    }

    @Deprecated
    default BasedSequence extendToAny(CharPredicate charPredicate) {
        return extendByAnyNot(charPredicate);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedSequence$EmptyBasedSequence.class */
    public static class EmptyBasedSequence extends BasedSequenceImpl {
        public EmptyBasedSequence() {
            super(0);
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
        public int getOptionFlags() {
            return 0;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
        public boolean allOptions(int i) {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
        public boolean anyOptions(int i) {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
        public <T> T getOption(DataKeyBase<T> dataKeyBase) {
            return dataKeyBase.get(null);
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedOptionsHolder
        public DataHolder getOptions() {
            return null;
        }

        @Override // java.lang.CharSequence
        public int length() {
            return 0;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            throw new StringIndexOutOfBoundsException("EMPTY sequence has no characters");
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequence
        public int getIndexOffset(int i) {
            SequenceUtils.validateIndexInclusiveEnd(i, length());
            return 0;
        }

        @Override // com.vladsch.flexmark.util.sequence.IRichSequence, java.lang.CharSequence
        public BasedSequence subSequence(int i, int i2) {
            SequenceUtils.validateStartEnd(i, i2, length());
            return this;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequenceImpl, com.vladsch.flexmark.util.sequence.BasedSequence
        public BasedSequence baseSubSequence(int i, int i2) {
            return subSequence(i, i2);
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequence
        public BasedSequence getBaseSequence() {
            return this;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequence
        public BasedSequence getBase() {
            return this;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequence
        public int getStartOffset() {
            return 0;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequence
        public int getEndOffset() {
            return 0;
        }

        @Override // com.vladsch.flexmark.util.sequence.BasedSequence
        public Range getSourceRange() {
            return Range.NULL;
        }
    }
}
