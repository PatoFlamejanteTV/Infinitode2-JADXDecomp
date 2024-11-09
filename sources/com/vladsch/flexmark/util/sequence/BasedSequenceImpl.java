package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.builder.BasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.IBasedSegmentBuilder;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;
import com.vladsch.flexmark.util.sequence.mappers.CharMapper;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/BasedSequenceImpl.class */
public abstract class BasedSequenceImpl extends IRichSequenceBase<BasedSequence> implements BasedSequence {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !BasedSequenceImpl.class.desiredAssertionStatus();
    }

    public static BasedSequence firstNonNull(BasedSequence... basedSequenceArr) {
        for (BasedSequence basedSequence : basedSequenceArr) {
            if (basedSequence != null && basedSequence != NULL) {
                return basedSequence;
            }
        }
        return NULL;
    }

    public BasedSequenceImpl(int i) {
        super(i);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public BasedSequence[] emptyArray() {
        return EMPTY_ARRAY;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public BasedSequence nullSequence() {
        return NULL;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public BasedSequence sequenceOf(CharSequence charSequence, int i, int i2) {
        return of(charSequence).subSequence(i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public SequenceBuilder getBuilder() {
        return SequenceBuilder.emptyBuilder(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public void addSegments(IBasedSegmentBuilder<?> iBasedSegmentBuilder) {
        iBasedSegmentBuilder.append(getStartOffset(), getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public SegmentTree getSegmentTree() {
        BasedSegmentBuilder emptyBuilder = BasedSegmentBuilder.emptyBuilder(getBaseSequence());
        addSegments(emptyBuilder);
        return SegmentTree.build(emptyBuilder.getSegments(), emptyBuilder.getText());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequence
    public BasedSequence toMapped(CharMapper charMapper) {
        return MappedBasedSequence.mappedOf(this, charMapper);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence baseSubSequence(int i) {
        return baseSubSequence(i, getBaseSequence().getEndOffset());
    }

    public BasedSequence baseSubSequence(int i, int i2) {
        return getBaseSequence().subSequence(i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequenceBase, com.vladsch.flexmark.util.sequence.IRichSequence
    public char safeCharAt(int i) {
        if (i < 0 || i >= length()) {
            return (char) 0;
        }
        return charAt(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public char safeBaseCharAt(int i) {
        int startOffset = getStartOffset();
        return (i < startOffset || i >= startOffset + length()) ? getBaseSequence().safeCharAt(i) : charAt(i - startOffset);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public boolean isBaseCharAt(int i, CharPredicate charPredicate) {
        return charPredicate.test(safeBaseCharAt(i));
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence getEmptyPrefix() {
        return subSequence(0, 0);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence getEmptySuffix() {
        return subSequence(length());
    }

    @Override // com.vladsch.flexmark.util.sequence.IRichSequenceBase, com.vladsch.flexmark.util.sequence.IRichSequence
    public String toStringOrNull() {
        if (isNull()) {
            return null;
        }
        return toString();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public String unescape() {
        return Escaping.unescapeString(this);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public String unescapeNoEntities() {
        return Escaping.unescapeString(this, false);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence unescape(ReplacedTextMapper replacedTextMapper) {
        return Escaping.unescape(this, replacedTextMapper);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence normalizeEOL(ReplacedTextMapper replacedTextMapper) {
        return Escaping.normalizeEOL(this, replacedTextMapper);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence normalizeEndWithEOL(ReplacedTextMapper replacedTextMapper) {
        return Escaping.normalizeEndWithEOL(this, replacedTextMapper);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public boolean isContinuedBy(BasedSequence basedSequence) {
        return basedSequence.length() > 0 && length() > 0 && basedSequence.getBase() == getBase() && basedSequence.getStartOffset() == getEndOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public boolean isContinuationOf(BasedSequence basedSequence) {
        return basedSequence.length() > 0 && length() > 0 && basedSequence.getBase() == getBase() && basedSequence.getEndOffset() == getStartOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence spliceAtEnd(BasedSequence basedSequence) {
        if (basedSequence.isEmpty()) {
            return this;
        }
        if (isEmpty()) {
            return basedSequence;
        }
        if ($assertionsDisabled || isContinuedBy(basedSequence)) {
            return baseSubSequence(getStartOffset(), basedSequence.getEndOffset());
        }
        throw new AssertionError("sequence[" + getStartOffset() + ", " + getEndOffset() + "] is not continued by other[" + basedSequence.getStartOffset() + ", " + basedSequence.getEndOffset() + "]");
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public boolean containsAllOf(BasedSequence basedSequence) {
        return getBase() == basedSequence.getBase() && basedSequence.getStartOffset() >= getStartOffset() && basedSequence.getEndOffset() <= getEndOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public boolean containsSomeOf(BasedSequence basedSequence) {
        return getBase() == basedSequence.getBase() && getStartOffset() < basedSequence.getEndOffset() && getEndOffset() > basedSequence.getStartOffset();
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence intersect(BasedSequence basedSequence) {
        return getBase() != basedSequence.getBase() ? BasedSequence.NULL : basedSequence.getEndOffset() <= getStartOffset() ? subSequence(0, 0) : basedSequence.getStartOffset() >= getEndOffset() ? subSequence(length(), length()) : baseSubSequence(Utils.max(getStartOffset(), basedSequence.getStartOffset()), Utils.min(getEndOffset(), basedSequence.getEndOffset()));
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendByAny(CharPredicate charPredicate) {
        return extendByAny(charPredicate, Integer.MAX_VALUE - getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendByOneOfAny(CharPredicate charPredicate) {
        return extendByAny(charPredicate, 1);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendByAny(CharPredicate charPredicate, int i) {
        int countLeading = getBaseSequence().countLeading(charPredicate, getEndOffset(), getEndOffset() + i);
        return countLeading == 0 ? this : baseSubSequence(getStartOffset(), getEndOffset() + countLeading);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendByAnyNot(CharPredicate charPredicate) {
        return extendByAnyNot(charPredicate, Integer.MAX_VALUE - getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendByOneOfAnyNot(CharPredicate charPredicate) {
        return extendByAnyNot(charPredicate, 1);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendByAnyNot(CharPredicate charPredicate, int i) {
        int countLeadingNot = getBaseSequence().countLeadingNot(charPredicate, getEndOffset(), getEndOffset() + i);
        return countLeadingNot == getBaseSequence().length() - getEndOffset() ? this : baseSubSequence(getStartOffset(), getEndOffset() + countLeadingNot + 1);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToEndOfLine(CharPredicate charPredicate) {
        return extendToEndOfLine(charPredicate, false);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToEndOfLine(boolean z) {
        return extendToEndOfLine(CharPredicate.EOL, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToEndOfLine() {
        return extendToEndOfLine(CharPredicate.EOL, false);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToStartOfLine(CharPredicate charPredicate) {
        return extendToStartOfLine(charPredicate, false);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToStartOfLine(boolean z) {
        return extendToStartOfLine(CharPredicate.EOL, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToStartOfLine() {
        return extendToStartOfLine(CharPredicate.EOL, false);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence extendToEndOfLine(CharPredicate charPredicate, boolean z) {
        int endOffset = getEndOffset();
        if (charPredicate.test(lastChar())) {
            return this;
        }
        BasedSequence baseSequence = getBaseSequence();
        int endOfLine = baseSequence.endOfLine(endOffset);
        if (z) {
            endOfLine = Math.min(baseSequence.length(), endOfLine + Math.min(baseSequence.eolStartLength(endOfLine), 1));
        }
        if (endOfLine != endOffset) {
            return baseSequence.subSequence(getStartOffset(), endOfLine);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence extendToStartOfLine(CharPredicate charPredicate, boolean z) {
        int startOffset = getStartOffset();
        if (charPredicate.test(firstChar())) {
            return this;
        }
        BasedSequence baseSequence = getBaseSequence();
        int startOfLine = baseSequence.startOfLine(startOffset);
        if (z) {
            startOfLine = Math.max(0, startOfLine - Math.min(baseSequence.eolEndLength(startOfLine), 1));
        }
        if (startOfLine != startOffset) {
            return baseSequence.subSequence(startOfLine, getEndOffset());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.sequence.IRichSequenceBase, com.vladsch.flexmark.util.sequence.IRichSequence
    public BasedSequence prefixWith(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0) ? this : PrefixedSubSequence.prefixOf(charSequence.toString(), this);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public final BasedSequence prefixWithIndent() {
        return prefixWithIndent(Integer.MAX_VALUE);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence prefixWithIndent(int i) {
        int i2;
        int i3;
        int startOffset = getStartOffset();
        int startOffset2 = getStartOffset();
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        while (true) {
            if (startOffset2 < 0) {
                break;
            }
            char charAt = getBaseSequence().charAt(startOffset2);
            if (charAt != '\t') {
                if (charAt == '\n') {
                    startOffset2++;
                    break;
                }
            } else {
                z = true;
            }
            startOffset2--;
        }
        if (startOffset2 < 0) {
            startOffset2 = 0;
        }
        if (startOffset2 < startOffset) {
            if (z) {
                int[] iArr = new int[startOffset - startOffset2];
                for (int i6 = startOffset2; i6 < startOffset; i6++) {
                    if (getBaseSequence().charAt(i6) == '\t') {
                        i2 = i5;
                        int i7 = 4 - (i5 % 4);
                        i3 = i7;
                        iArr[i6 - startOffset2] = i7;
                    } else {
                        i2 = i5;
                        i3 = 1;
                        iArr[i6 - startOffset2] = 1;
                    }
                    i5 = i2 + i3;
                }
                while (i4 < i && startOffset > 0 && (getBaseSequence().charAt(startOffset - 1) == ' ' || getBaseSequence().charAt(startOffset - 1) == '\t')) {
                    int i8 = i4 + iArr[(startOffset - 1) - startOffset2];
                    i4 = i8;
                    if (i8 > i) {
                        break;
                    }
                    startOffset--;
                }
            } else {
                while (i4 < i && startOffset > 0 && (getBaseSequence().charAt(startOffset - 1) == ' ' || getBaseSequence().charAt(startOffset - 1) == '\t')) {
                    i4++;
                    startOffset--;
                }
            }
        }
        return startOffset == getStartOffset() ? this : baseSubSequence(startOffset, getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence prefixOf(BasedSequence basedSequence) {
        return getBase() != basedSequence.getBase() ? BasedSequence.NULL : basedSequence.getStartOffset() <= getStartOffset() ? subSequence(0, 0) : basedSequence.getStartOffset() >= getEndOffset() ? this : baseSubSequence(getStartOffset(), basedSequence.getStartOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public BasedSequence suffixOf(BasedSequence basedSequence) {
        return getBase() != basedSequence.getBase() ? BasedSequence.NULL : basedSequence.getEndOffset() >= getEndOffset() ? subSequence(length(), length()) : basedSequence.getEndOffset() <= getStartOffset() ? this : baseSubSequence(basedSequence.getEndOffset(), getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public Range baseLineRangeAtIndex(int i) {
        return getBaseSequence().lineRangeAt(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public Pair<Integer, Integer> baseLineColumnAtIndex(int i) {
        return getBaseSequence().lineColumnAtIndex(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseEndOfLine(int i) {
        return getBaseSequence().endOfLine(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseEndOfLineAnyEOL(int i) {
        return getBaseSequence().endOfLineAnyEOL(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseStartOfLine(int i) {
        return getBaseSequence().startOfLine(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseStartOfLineAnyEOL(int i) {
        return getBaseSequence().startOfLineAnyEOL(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseColumnAtIndex(int i) {
        return getBaseSequence().columnAtIndex(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseEndOfLine() {
        return baseEndOfLine(getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseEndOfLineAnyEOL() {
        return baseEndOfLineAnyEOL(getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseColumnAtEnd() {
        return baseColumnAtIndex(getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public Range baseLineRangeAtEnd() {
        return baseLineRangeAtIndex(getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public Pair<Integer, Integer> baseLineColumnAtEnd() {
        return baseLineColumnAtIndex(getEndOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseStartOfLine() {
        return baseStartOfLine(getStartOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseStartOfLineAnyEOL() {
        return baseStartOfLineAnyEOL(getStartOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public int baseColumnAtStart() {
        return baseColumnAtIndex(getStartOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public Range baseLineRangeAtStart() {
        return baseLineRangeAtIndex(getStartOffset());
    }

    @Override // com.vladsch.flexmark.util.sequence.BasedSequence
    public Pair<Integer, Integer> baseLineColumnAtStart() {
        return baseLineColumnAtIndex(getStartOffset());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BasedSequence create(CharSequence charSequence) {
        if (charSequence == null) {
            return BasedSequence.NULL;
        }
        if (charSequence instanceof BasedSequence) {
            return (BasedSequence) charSequence;
        }
        return SubSequence.create(charSequence);
    }

    @Deprecated
    public static BasedSequence of(CharSequence charSequence) {
        return BasedSequence.of(charSequence);
    }

    @Deprecated
    public static BasedSequence of(CharSequence charSequence, int i) {
        return BasedSequence.of(charSequence).subSequence(i);
    }

    @Deprecated
    public static BasedSequence of(CharSequence charSequence, int i, int i2) {
        return BasedSequence.of(charSequence).subSequence(i, i2);
    }
}
