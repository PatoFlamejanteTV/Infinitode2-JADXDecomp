package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.collection.iteration.Indexed;
import com.vladsch.flexmark.util.collection.iteration.IndexedItemIterable;
import com.vladsch.flexmark.util.collection.iteration.IndexedItemIterator;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.LineInfo;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.StringSequenceBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineAppendableImpl.class */
public class LineAppendableImpl implements LineAppendable {
    private static final char EOL = '\n';
    private final boolean passThrough;
    private final BitFieldSet<LineAppendable.Options> options;
    private int preFormattedNesting;
    private int preFormattedFirstLine;
    private int preFormattedFirstLineOffset;
    private int preFormattedLastLine;
    private int preFormattedLastLineOffset;
    private ISequenceBuilder<?, ?> appendable;
    final ArrayList<LineInfo> lines;
    private CharSequence prefix;
    private CharSequence prefixAfterEol;
    private CharSequence indentPrefix;
    private final Stack<CharSequence> prefixStack;
    private final Stack<Boolean> indentPrefixStack;
    private boolean allWhitespace;
    private boolean lastWasWhitespace;
    private int eolOnFirstText;
    private final ArrayList<Runnable> indentsOnFirstEol;
    private final Stack<Integer> optionStack;
    int modificationCount;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !LineAppendableImpl.class.desiredAssertionStatus();
    }

    public LineAppendableImpl(int i) {
        this((Appendable) null, LineAppendable.toOptionSet(i));
    }

    public LineAppendableImpl(Appendable appendable, int i) {
        this(appendable, LineAppendable.toOptionSet(i));
    }

    public LineAppendableImpl(Appendable appendable, BitFieldSet<LineAppendable.Options> bitFieldSet) {
        ISequenceBuilder<?, ?> emptyBuilder;
        this.optionStack = new Stack<>();
        if (appendable instanceof ISequenceBuilder) {
            emptyBuilder = ((ISequenceBuilder) appendable).getBuilder();
        } else if (appendable instanceof LineAppendable) {
            emptyBuilder = ((LineAppendable) appendable).getBuilder();
        } else {
            emptyBuilder = StringSequenceBuilder.emptyBuilder();
        }
        this.appendable = emptyBuilder;
        this.options = bitFieldSet;
        this.passThrough = any(F_PASS_THROUGH);
        this.preFormattedNesting = 0;
        this.preFormattedFirstLine = -1;
        this.preFormattedLastLine = -1;
        this.allWhitespace = true;
        this.lastWasWhitespace = false;
        this.lines = new ArrayList<>();
        this.prefixStack = new Stack<>();
        this.indentPrefixStack = new Stack<>();
        this.prefix = BasedSequence.EMPTY;
        this.prefixAfterEol = BasedSequence.EMPTY;
        this.indentPrefix = BasedSequence.EMPTY;
        this.eolOnFirstText = 0;
        this.indentsOnFirstEol = new ArrayList<>();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable getEmptyAppendable() {
        return new LineAppendableImpl(this, getOptions());
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BitFieldSet<LineAppendable.Options> getOptionSet() {
        return this.options;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable setOptions(int i) {
        this.options.setAll(i);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable pushOptions() {
        this.optionStack.push(Integer.valueOf(this.options.toInt()));
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable popOptions() {
        if (this.optionStack.isEmpty()) {
            throw new IllegalStateException("Option stack is empty");
        }
        this.options.setAll(this.optionStack.pop().intValue());
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable changeOptions(int i, int i2) {
        if ((i & i2) != 0) {
            throw new IllegalStateException(String.format("Add flags:%d and remove flags:%d overlap:%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i & i2)));
        }
        this.options.orMask(i);
        this.options.andNotMask(i2);
        return this;
    }

    private boolean any(int i) {
        return this.options.any(i);
    }

    private boolean isConvertingTabs() {
        return any(F_CONVERT_TABS | F_COLLAPSE_WHITESPACE);
    }

    private boolean isTrimTrailingWhitespace() {
        return any(F_TRIM_TRAILING_WHITESPACE);
    }

    private boolean isTrimLeadingWhitespace() {
        return any(F_TRIM_LEADING_WHITESPACE);
    }

    private boolean isCollapseWhitespace() {
        return any(F_COLLAPSE_WHITESPACE);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getIndentPrefix() {
        return BasedSequence.of(this.indentPrefix);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable setIndentPrefix(CharSequence charSequence) {
        this.indentPrefix = charSequence == null ? BasedSequence.NULL : charSequence;
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getPrefix() {
        return BasedSequence.of(this.prefixAfterEol);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getBeforeEolPrefix() {
        return BasedSequence.of(this.prefix);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable addPrefix(CharSequence charSequence, boolean z) {
        if (!this.passThrough && charSequence.length() != 0) {
            if (z) {
                this.prefixAfterEol = LineAppendable.combinedPrefix(this.prefixAfterEol, charSequence);
            } else {
                this.prefix = LineAppendable.combinedPrefix(this.prefixAfterEol, charSequence);
                this.prefixAfterEol = this.prefix;
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getAfterEolPrefixDelta() {
        return this.prefixAfterEol.length() - this.prefix.length();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable setPrefix(CharSequence charSequence, boolean z) {
        if (!this.passThrough) {
            if (z) {
                this.prefixAfterEol = charSequence == null ? BasedSequence.NULL : charSequence;
            } else {
                this.prefix = charSequence == null ? BasedSequence.NULL : charSequence;
                this.prefixAfterEol = this.prefix;
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable indent() {
        if (!this.passThrough) {
            line();
            rawIndent();
        }
        return this;
    }

    private void rawIndent() {
        this.prefixStack.push(this.prefixAfterEol);
        this.prefix = LineAppendable.combinedPrefix(this.prefixAfterEol, this.indentPrefix);
        this.prefixAfterEol = this.prefix;
        this.indentPrefixStack.push(Boolean.TRUE);
    }

    private void rawUnIndent() {
        if (this.prefixStack.isEmpty()) {
            throw new IllegalStateException("unIndent with an empty stack");
        }
        if (!this.indentPrefixStack.peek().booleanValue()) {
            throw new IllegalStateException("unIndent for an element added by pushPrefix(), use popPrefix() instead");
        }
        this.prefix = this.prefixStack.pop();
        this.prefixAfterEol = this.prefix;
        this.indentPrefixStack.pop();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable unIndent() {
        if (!this.passThrough) {
            line();
            rawUnIndent();
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable unIndentNoEol() {
        if (!this.passThrough) {
            if (endsWithEOL()) {
                rawUnIndent();
            } else {
                CharSequence charSequence = this.prefix;
                rawUnIndent();
                this.prefixAfterEol = this.prefix;
                this.prefix = charSequence;
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable pushPrefix() {
        if (!this.passThrough) {
            this.prefixStack.push(this.prefixAfterEol);
            this.indentPrefixStack.push(Boolean.FALSE);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable popPrefix(boolean z) {
        if (!this.passThrough) {
            if (this.prefixStack.isEmpty()) {
                throw new IllegalStateException("popPrefix with an empty stack");
            }
            if (this.indentPrefixStack.peek().booleanValue()) {
                throw new IllegalStateException("popPrefix for element added by indent(), use unIndent() instead");
            }
            this.prefixAfterEol = this.prefixStack.pop();
            if (!z) {
                this.prefix = this.prefixAfterEol;
            }
            this.indentPrefixStack.pop();
        }
        return this;
    }

    LineInfo getLastLineInfo() {
        return this.lines.isEmpty() ? LineInfo.NULL : this.lines.get(this.lines.size() - 1);
    }

    private boolean isTrailingBlankLine() {
        return this.appendable.length() == 0 && getLastLineInfo().isBlankText();
    }

    int lastNonBlankLine(int i) {
        if (i > this.lines.size() && this.appendable.length() > 0 && !this.allWhitespace) {
            return this.lines.size();
        }
        int min = Math.min(this.lines.size(), i);
        do {
            int i2 = min;
            min--;
            if (i2 <= 0) {
                break;
            }
        } while (this.lines.get(min).isBlankText());
        return min;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getTrailingBlankLines(int i) {
        int min = Math.min(this.lines.size(), i);
        return (min - lastNonBlankLine(min)) - 1;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public boolean endsWithEOL() {
        return this.appendable.length() == 0 && getLastLineInfo().isNotNull();
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.CharSequence] */
    private LineInfo getLineRange(int i, int i2, CharSequence charSequence) {
        LineInfo.Preformatted preformatted;
        if (!$assertionsDisabled && i > i2) {
            throw new AssertionError();
        }
        ?? sequence = this.appendable.toSequence();
        CharSequence trimmedEOL = SequenceUtils.trimmedEOL(sequence);
        CharSequence charSequence2 = trimmedEOL;
        if (trimmedEOL == null || charSequence2.length() == 0) {
            charSequence2 = SequenceUtils.EOL;
        }
        CharSequence subSequence = (i == Range.NULL.getStart() && i2 == Range.NULL.getEnd()) ? BasedSequence.NULL : sequence.subSequence(i, Math.max(i, i2 - Math.max(0, charSequence2.length() - 1)));
        if (i >= i2) {
            charSequence = SequenceUtils.trimEnd(charSequence);
        }
        CharSequence sequence2 = this.appendable.getBuilder().append(charSequence).append(subSequence).append(charSequence2).toSequence();
        if (this.preFormattedNesting > 0) {
            preformatted = this.preFormattedFirstLine == this.lines.size() ? LineInfo.Preformatted.FIRST : LineInfo.Preformatted.BODY;
        } else {
            preformatted = this.preFormattedFirstLine == this.lines.size() ? LineInfo.Preformatted.LAST : LineInfo.Preformatted.NONE;
        }
        return LineInfo.create(sequence2, getLastLineInfo(), charSequence.length(), subSequence.length(), sequence2.length(), SequenceUtils.isBlank(charSequence), this.allWhitespace || subSequence.length() == 0, preformatted);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder<?, ?>] */
    private void resetBuilder() {
        this.appendable = this.appendable.getBuilder();
        this.allWhitespace = true;
        this.lastWasWhitespace = true;
    }

    private void addLineRange(int i, int i2, CharSequence charSequence) {
        this.lines.add(getLineRange(i, i2, charSequence));
        resetBuilder();
    }

    private void appendEol(CharSequence charSequence) {
        this.appendable.append(charSequence);
        addLineRange(0, this.appendable.length() - charSequence.length(), this.prefix);
        this.eolOnFirstText = 0;
        rawIndentsOnFirstEol();
    }

    private void rawIndentsOnFirstEol() {
        this.prefix = this.prefixAfterEol;
        while (!this.indentsOnFirstEol.isEmpty()) {
            Runnable remove = this.indentsOnFirstEol.remove(this.indentsOnFirstEol.size() - 1);
            rawIndent();
            remove.run();
        }
    }

    private void appendEol(int i) {
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                appendEol(BasedSequence.EOL);
            } else {
                return;
            }
        }
    }

    private boolean isPrefixed(int i) {
        if (any(F_PREFIX_PRE_FORMATTED) || this.preFormattedFirstLine == i) {
            return true;
        }
        return this.preFormattedNesting == 0 && this.preFormattedLastLine != i;
    }

    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.CharSequence] */
    private Pair<Range, CharSequence> getRangePrefixAfterEol() {
        int i = 0;
        int length = this.appendable.length() + 1;
        int size = this.lines.size();
        boolean isPrefixed = isPrefixed(size);
        if (this.passThrough) {
            return new Pair<>(Range.of(0, length - 1), isPrefixed ? this.prefix : BasedSequence.NULL);
        }
        if (this.allWhitespace && this.preFormattedNesting == 0 && this.preFormattedFirstLine != size && this.preFormattedLastLine != size) {
            if (!any(F_TRIM_LEADING_EOL) || !this.lines.isEmpty()) {
                return new Pair<>(Range.of(0, length - 1), this.prefix);
            }
            return new Pair<>(Range.NULL, BasedSequence.NULL);
        }
        if (isTrimTrailingWhitespace() && this.preFormattedNesting == 0) {
            if (this.allWhitespace) {
                i = length - 1;
            } else {
                length -= SequenceUtils.countTrailingSpaceTab(this.appendable.toSequence(), length - 1);
            }
        }
        if (this.preFormattedFirstLine == size && i > this.preFormattedFirstLineOffset) {
            i = this.preFormattedFirstLineOffset;
        }
        if (this.preFormattedLastLine == size && length < this.preFormattedLastLineOffset + 1) {
            length = this.preFormattedLastLineOffset + 1;
        }
        return new Pair<>(Range.of(i, length - 1), isPrefixed ? this.prefix : BasedSequence.NULL);
    }

    private int offsetAfterEol() {
        Pair<Range, CharSequence> rangePrefixAfterEol = getRangePrefixAfterEol();
        LineInfo lastLineInfo = getLastLineInfo();
        if (rangePrefixAfterEol.getFirst().isNull()) {
            return lastLineInfo.sumLength;
        }
        Range first = rangePrefixAfterEol.getFirst();
        CharSequence second = rangePrefixAfterEol.getSecond();
        if (first.isEmpty() && second.length() != 0) {
            second = SequenceUtils.trimEnd(second);
        }
        return lastLineInfo.sumLength + rangePrefixAfterEol.getFirst().getSpan() + second.length();
    }

    private void doEolOnFirstTest() {
        if (this.eolOnFirstText > 0) {
            this.eolOnFirstText = 0;
            appendEol(BasedSequence.EOL);
        }
    }

    private void appendImpl(CharSequence charSequence, int i) {
        char charAt = charSequence.charAt(i);
        if (this.passThrough) {
            if (charAt == '\n') {
                appendEol(BasedSequence.EOL);
                return;
            }
            if (this.eolOnFirstText > 0) {
                this.eolOnFirstText = 0;
                appendEol(BasedSequence.EOL);
            }
            if (charAt != '\t' && charAt != ' ') {
                this.allWhitespace = false;
            }
            this.appendable.append(charAt);
            return;
        }
        if (charAt == '\n') {
            Pair<Range, CharSequence> rangePrefixAfterEol = getRangePrefixAfterEol();
            Range first = rangePrefixAfterEol.getFirst();
            if (first.isNull()) {
                resetBuilder();
            } else {
                this.appendable.append(charAt);
                addLineRange(first.getStart(), first.getEnd(), rangePrefixAfterEol.getSecond());
            }
            rawIndentsOnFirstEol();
            return;
        }
        doEolOnFirstTest();
        if (charAt == '\t') {
            if (this.preFormattedNesting == 0 && any(F_COLLAPSE_WHITESPACE)) {
                if (!this.lastWasWhitespace) {
                    this.appendable.append(' ');
                    this.lastWasWhitespace = true;
                    return;
                }
                return;
            }
            if (any(F_CONVERT_TABS)) {
                this.appendable.append(' ', 4 - (this.appendable.length() % 4));
                return;
            } else {
                this.appendable.append(charSequence, i, i + 1);
                return;
            }
        }
        if (charAt == ' ') {
            if (this.preFormattedNesting == 0) {
                if (!any(F_TRIM_LEADING_WHITESPACE) || (this.appendable.length() != 0 && !this.allWhitespace)) {
                    if (any(F_COLLAPSE_WHITESPACE)) {
                        if (!this.lastWasWhitespace) {
                            this.appendable.append(' ');
                        }
                    } else {
                        this.appendable.append(' ');
                    }
                }
            } else {
                this.appendable.append(charSequence.subSequence(i, i + 1));
            }
            this.lastWasWhitespace = true;
            return;
        }
        this.allWhitespace = false;
        this.lastWasWhitespace = false;
        this.appendable.append(charSequence, i, i + 1);
    }

    private void appendImpl(CharSequence charSequence, int i, int i2) {
        int i3 = i;
        while (i3 < i2) {
            int i4 = i3;
            i3++;
            appendImpl(charSequence, i4);
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public LineAppendable append(CharSequence charSequence) {
        if (charSequence.length() > 0) {
            appendImpl(charSequence, 0, charSequence.length());
        } else {
            this.appendable.append(charSequence);
        }
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder, com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder<?, ?>] */
    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public ISequenceBuilder<?, ?> getBuilder() {
        return this.appendable.getBuilder();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public LineAppendable append(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            appendImpl(charSequence, i, i2);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public LineAppendable append(char c) {
        appendImpl(Character.toString(c), 0);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable append(char c, int i) {
        append(RepeatedSequence.repeatOf(c, i));
        return this;
    }

    public LineAppendable repeat(CharSequence charSequence, int i) {
        append(RepeatedSequence.repeatOf(charSequence, i));
        return this;
    }

    public LineAppendable repeat(CharSequence charSequence, int i, int i2, int i3) {
        append(RepeatedSequence.repeatOf(charSequence.subSequence(i, i2), i3));
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable line() {
        if (this.preFormattedNesting > 0 || this.appendable.length() != 0) {
            appendImpl(SequenceUtils.EOL, 0);
        } else {
            CharSequence charSequence = this.prefix;
            boolean z = !this.indentsOnFirstEol.isEmpty();
            rawIndentsOnFirstEol();
            if (z || (charSequence.length() > 0 && this.prefix.length() == 0)) {
                this.prefix = charSequence;
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable lineWithTrailingSpaces(int i) {
        if (this.preFormattedNesting > 0 || this.appendable.length() != 0) {
            int i2 = this.options.toInt();
            this.options.andNotMask(F_TRIM_TRAILING_WHITESPACE | F_COLLAPSE_WHITESPACE);
            if (i > 0) {
                append(' ', i);
            }
            appendImpl(SequenceUtils.EOL, 0);
            this.options.setAll(i2);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable lineIf(boolean z) {
        if (z) {
            line();
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable blankLine() {
        line();
        if ((!this.lines.isEmpty() && !isTrailingBlankLine()) || (this.lines.isEmpty() && !any(F_TRIM_LEADING_EOL))) {
            appendEol(BasedSequence.EOL);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable blankLineIf(boolean z) {
        if (z) {
            blankLine();
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable blankLine(int i) {
        line();
        if (!any(F_TRIM_LEADING_EOL) || !this.lines.isEmpty()) {
            appendEol(i - getTrailingBlankLines(this.lines.size()));
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable lineOnFirstText(boolean z) {
        if (z) {
            this.eolOnFirstText++;
        } else if (this.eolOnFirstText > 0) {
            this.eolOnFirstText--;
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable removeIndentOnFirstEOL(Runnable runnable) {
        this.indentsOnFirstEol.remove(runnable);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable addIndentOnFirstEOL(Runnable runnable) {
        this.indentsOnFirstEol.add(runnable);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getLineCount() {
        return this.lines.size();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getLineCountWithPending() {
        return this.appendable.length() == 0 ? this.lines.size() : this.lines.size() + 1;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int column() {
        return this.appendable.length();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineInfo getLineInfo(int i) {
        if (i == this.lines.size()) {
            if (this.appendable.length() == 0) {
                return LineInfo.NULL;
            }
            Pair<Range, CharSequence> rangePrefixAfterEol = getRangePrefixAfterEol();
            Range first = rangePrefixAfterEol.getFirst();
            if (first.isNull()) {
                return LineInfo.NULL;
            }
            return getLineRange(first.getStart(), first.getEnd(), rangePrefixAfterEol.getSecond());
        }
        return this.lines.get(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getLine(int i) {
        return getLineInfo(i).getLine();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int offset() {
        return getLastLineInfo().sumLength;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int offsetWithPending() {
        return offsetAfterEol();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public boolean isPendingSpace() {
        return this.appendable.length() > 0 && this.lastWasWhitespace;
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.CharSequence] */
    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getPendingSpace() {
        if (this.lastWasWhitespace && this.appendable.length() != 0) {
            return SequenceUtils.countTrailingSpaceTab(this.appendable.toSequence());
        }
        return 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getPendingEOL() {
        if (this.appendable.length() == 0) {
            return getTrailingBlankLines(this.lines.size()) + 1;
        }
        return 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public boolean isPreFormatted() {
        return this.preFormattedNesting > 0;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable openPreFormatted(boolean z) {
        if (this.preFormattedNesting == 0 && this.preFormattedFirstLine != this.lines.size()) {
            this.preFormattedFirstLine = this.lines.size();
            this.preFormattedFirstLineOffset = this.appendable.length();
        }
        this.preFormattedNesting++;
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable closePreFormatted() {
        if (this.preFormattedNesting <= 0) {
            throw new IllegalStateException("closePreFormatted called with nesting == 0");
        }
        this.preFormattedNesting--;
        if (this.preFormattedNesting == 0 && !endsWithEOL()) {
            this.preFormattedLastLine = this.lines.size();
            this.preFormattedLastLineOffset = this.appendable.length();
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            appendToNoLine(sb, true, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public String toString(int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        try {
            appendTo(sb, z, i, i2, 0, Integer.MAX_VALUE);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.CharSequence] */
    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public CharSequence toSequence(int i, int i2, boolean z) {
        ISequenceBuilder<?, ?> builder = getBuilder();
        try {
            appendTo(builder, z, i, i2, 0, Integer.MAX_VALUE);
        } catch (IOException unused) {
        }
        return builder.toSequence();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public <T extends Appendable> T appendTo(T t, boolean z, int i, int i2, int i3, int i4) {
        line();
        return (T) appendToNoLine(t, z, i, i2, i3, i4);
    }

    public <T extends Appendable> T appendToNoLine(T t, boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = i2 >= 0;
        int max = Math.max(0, i);
        int max2 = Math.max(0, i2);
        int size = this.lines.size();
        int min = Utils.min(getLineCountWithPending(), i4);
        int lastNonBlankLine = lastNonBlankLine(min);
        int i5 = 0;
        int i6 = i3;
        while (i6 < min) {
            LineInfo lineInfo = getLineInfo(i6);
            boolean z3 = i6 < size;
            if (lineInfo.textLength == 0 && !lineInfo.isPreformatted()) {
                if (i6 > lastNonBlankLine) {
                    if (i5 < max2) {
                        i5++;
                        if (z) {
                            t.append(isTrimTrailingWhitespace() ? SequenceUtils.trimEnd(lineInfo.getPrefix()) : lineInfo.getPrefix());
                        }
                        if (z3 && (z2 || i5 != max2)) {
                            t.append('\n');
                        }
                    }
                } else if (i5 < max) {
                    i5++;
                    if (z) {
                        t.append(isTrimTrailingWhitespace() ? SequenceUtils.trimEnd(lineInfo.getPrefix()) : lineInfo.getPrefix());
                    }
                    if (z3) {
                        t.append('\n');
                    }
                }
            } else {
                i5 = 0;
                if (z3 && (z2 || i6 < lastNonBlankLine || (lineInfo.isPreformatted() && lineInfo.getPreformatted() != LineInfo.Preformatted.LAST))) {
                    if (z) {
                        t.append(lineInfo.lineSeq);
                    } else {
                        t.append(lineInfo.getText());
                    }
                } else if (z) {
                    t.append(lineInfo.getLineNoEOL());
                } else {
                    t.append(lineInfo.getText());
                }
            }
            i6++;
        }
        return t;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable append(LineAppendable lineAppendable, int i, int i2, boolean z) {
        int min = Math.min(i2, lineAppendable.getLineCountWithPending());
        for (int max = Math.max(0, i); max < min; max++) {
            LineInfo lineInfo = lineAppendable.getLineInfo(max);
            BasedSequence textNoEOL = lineInfo.getTextNoEOL();
            BasedSequence prefix = z ? lineInfo.getPrefix() : BasedSequence.NULL;
            CharSequence combinedPrefix = (any(F_PREFIX_PRE_FORMATTED) || !lineInfo.isPreformatted() || lineInfo.getPreformatted() == LineInfo.Preformatted.FIRST) ? LineAppendable.combinedPrefix(this.prefix, prefix) : prefix;
            this.appendable.append((CharSequence) textNoEOL);
            this.allWhitespace = lineInfo.isBlankText();
            this.lastWasWhitespace = lineInfo.textLength == 0 || CharPredicate.SPACE_TAB.test(textNoEOL.safeCharAt(lineInfo.textLength - 1));
            if (max < lineAppendable.getLineCount()) {
                this.appendable.append('\n');
                this.allWhitespace = lineInfo.isBlankText();
                addLineRange(0, this.appendable.length() - 1, combinedPrefix);
            } else {
                this.prefix = combinedPrefix;
            }
        }
        return this;
    }

    private int removeLinesRaw(int i, int i2) {
        int minLimit = Utils.minLimit(i, 0);
        int maxLimit = Utils.maxLimit(i2, getLineCountWithPending());
        if (minLimit < maxLimit) {
            this.lines.subList(minLimit, maxLimit).clear();
            this.modificationCount++;
            return minLimit;
        }
        if (i2 >= getLineCountWithPending() && this.appendable.length() > 0) {
            resetBuilder();
        }
        return this.lines.size();
    }

    void recomputeLineInfo(int i) {
        int size = this.lines.size();
        int max = Math.max(0, i);
        if (max < size) {
            LineInfo lineInfo = max - 1 >= 0 ? this.lines.get(max - 1) : LineInfo.NULL;
            for (int i2 = max; i2 < size; i2++) {
                LineInfo lineInfo2 = this.lines.get(i2);
                lineInfo = LineInfo.create(lineInfo, lineInfo2);
                this.lines.set(i2, lineInfo);
                if (!lineInfo.needAggregateUpdate(lineInfo2)) {
                    return;
                }
            }
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable removeLines(int i, int i2) {
        recomputeLineInfo(removeLinesRaw(i, i2));
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineAppendable removeExtraBlankLines(int i, int i2, int i3, int i4) {
        int max = Math.max(0, i);
        int max2 = Math.max(0, i2);
        int min = Utils.min(i4, getLineCountWithPending());
        int i5 = 0;
        int i6 = max2;
        int lineCountWithPending = getLineCountWithPending();
        int i7 = min;
        while (true) {
            int i8 = i7;
            i7--;
            if (i8 > 0) {
                LineInfo lineInfo = getLineInfo(i7);
                if (lineInfo.isBlankText() && !lineInfo.isPreformatted()) {
                    if (i5 >= i6) {
                        lineCountWithPending = removeLinesRaw(i7 + i5, i7 + i5 + 1);
                    } else {
                        i5++;
                    }
                } else {
                    i5 = 0;
                    i6 = max;
                }
            } else {
                recomputeLineInfo(lineCountWithPending);
                return this;
            }
        }
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public void setPrefixLength(int i, int i2) {
        if (i == this.lines.size() && this.appendable.length() > 0) {
            line();
        }
        LineInfo lineInfo = this.lines.get(i);
        CharSequence charSequence = lineInfo.lineSeq;
        if (i2 < 0 || i2 > lineInfo.getTextEnd()) {
            throw new IllegalArgumentException(String.format("prefixLength %d is out of valid range [0, %d) for the line", Integer.valueOf(i2), Integer.valueOf(lineInfo.getTextEnd() + 1)));
        }
        if (i2 != lineInfo.prefixLength) {
            CharSequence subSequence = charSequence.subSequence(0, i2);
            this.lines.set(i, LineInfo.create(lineInfo.lineSeq, i == 0 ? LineInfo.NULL : this.lines.get(i - 1), subSequence.length(), (lineInfo.prefixLength + lineInfo.textLength) - i2, lineInfo.length, SequenceUtils.isBlank(subSequence), SequenceUtils.isBlank(charSequence.subSequence(i2, lineInfo.getTextEnd())), lineInfo.getPreformatted()));
            recomputeLineInfo(i + 1);
        }
    }

    /* JADX WARN: Type inference failed for: r0v22, types: [com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder] */
    private LineInfo createLineInfo(int i, CharSequence charSequence, CharSequence charSequence2) {
        LineInfo.Preformatted preformatted;
        LineInfo lineInfo = i == 0 ? LineInfo.NULL : this.lines.get(i - 1);
        LineInfo lineInfo2 = i == this.lines.size() ? LineInfo.NULL : this.lines.get(i);
        CharSequence charSequence3 = charSequence2;
        CharSequence trimmedEOL = SequenceUtils.trimmedEOL(charSequence2);
        CharSequence charSequence4 = trimmedEOL;
        if (trimmedEOL == null) {
            charSequence4 = SequenceUtils.EOL;
        } else {
            charSequence3 = charSequence3.subSequence(0, charSequence3.length() - charSequence4.length());
        }
        if (charSequence3.length() == 0) {
            charSequence = SequenceUtils.trimEnd(charSequence);
        }
        if (!$assertionsDisabled && SequenceUtils.containsAny(charSequence3, CharPredicate.ANY_EOL)) {
            throw new AssertionError(String.format("Line text should not contain any EOL, text: %s", SequenceUtils.toVisibleWhitespaceString(charSequence3)));
        }
        CharSequence sequence = this.appendable.getBuilder().append(charSequence).append(charSequence3).append(charSequence4).toSequence();
        if (lineInfo2.isNotNull()) {
            preformatted = lineInfo2.getPreformatted();
        } else {
            preformatted = (!lineInfo.isPreformatted() || lineInfo.getPreformatted() == LineInfo.Preformatted.LAST) ? LineInfo.Preformatted.NONE : LineInfo.Preformatted.BODY;
        }
        return LineInfo.create(sequence, lineInfo, charSequence.length(), charSequence3.length(), sequence.length(), SequenceUtils.isBlank(charSequence), SequenceUtils.isBlank(charSequence3), preformatted);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public void setLine(int i, CharSequence charSequence, CharSequence charSequence2) {
        if (i == this.lines.size() && this.appendable.length() > 0) {
            line();
        }
        this.lines.set(i, createLineInfo(i, charSequence, charSequence2));
        recomputeLineInfo(i + 1);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public void insertLine(int i, CharSequence charSequence, CharSequence charSequence2) {
        this.lines.add(i, createLineInfo(i, charSequence, charSequence2));
        recomputeLineInfo(i + 1);
    }

    int tailBlankLinesToRemove(int i, int i2) {
        return Utils.max(0, getTrailingBlankLines(i) - Utils.max(0, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineAppendableImpl$IndexedLineInfoProxy.class */
    public static class IndexedLineInfoProxy implements Indexed<LineInfo> {
        final LineAppendableImpl appendable;
        final int startLine;
        final int endLine;
        final int maxTrailingBlankLines;

        public IndexedLineInfoProxy(LineAppendableImpl lineAppendableImpl, int i, int i2, int i3) {
            this.appendable = lineAppendableImpl;
            this.startLine = i2;
            this.endLine = Math.min(i3, lineAppendableImpl.getLineCountWithPending());
            this.maxTrailingBlankLines = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public LineInfo get(int i) {
            if (i + this.startLine >= this.endLine) {
                throw new IndexOutOfBoundsException(String.format("index %d is out of valid range [%d, %d)", Integer.valueOf(i), Integer.valueOf(this.startLine), Integer.valueOf(this.endLine)));
            }
            return this.appendable.getLineInfo(i + this.startLine);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public void set(int i, LineInfo lineInfo) {
            if (i + this.startLine >= this.endLine) {
                throw new IndexOutOfBoundsException(String.format("index %d is out of valid range [%d, %d)", Integer.valueOf(i), Integer.valueOf(this.startLine), Integer.valueOf(this.endLine)));
            }
            this.appendable.lines.set(this.startLine + i, lineInfo);
            this.appendable.recomputeLineInfo(this.startLine + i + 1);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public void removeAt(int i) {
            if (i + this.startLine >= this.endLine) {
                throw new IndexOutOfBoundsException(String.format("index %d is out of valid range [%d, %d)", Integer.valueOf(i), Integer.valueOf(this.startLine), Integer.valueOf(this.endLine)));
            }
            this.appendable.removeLines(i + this.startLine, i + 1);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public int size() {
            return Math.max(0, (this.endLine - this.startLine) - this.appendable.tailBlankLinesToRemove(this.endLine, this.maxTrailingBlankLines));
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public int modificationCount() {
            return this.appendable.modificationCount;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/LineAppendableImpl$IndexedLineProxy.class */
    public static class IndexedLineProxy implements Indexed<BasedSequence> {
        final IndexedLineInfoProxy proxy;
        final boolean withPrefixes;

        public IndexedLineProxy(IndexedLineInfoProxy indexedLineInfoProxy, boolean z) {
            this.proxy = indexedLineInfoProxy;
            this.withPrefixes = z;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public BasedSequence get(int i) {
            return (this.proxy.maxTrailingBlankLines == -1 && i + 1 == this.proxy.size()) ? this.withPrefixes ? this.proxy.get(i).getLineNoEOL() : this.proxy.get(i).getTextNoEOL() : this.withPrefixes ? this.proxy.get(i).getLine() : this.proxy.get(i).getText();
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public void set(int i, BasedSequence basedSequence) {
            if (this.withPrefixes) {
                this.proxy.appendable.setLine(i + this.proxy.startLine, BasedSequence.NULL, basedSequence);
            } else {
                this.proxy.appendable.setLine(i + this.proxy.startLine, this.proxy.appendable.getLineInfo(i + this.proxy.startLine).getPrefix(), basedSequence);
            }
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public void removeAt(int i) {
            this.proxy.removeAt(i);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public int size() {
            return this.proxy.size();
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public int modificationCount() {
            return this.proxy.modificationCount();
        }
    }

    IndexedLineInfoProxy getIndexedLineInfoProxy(int i, int i2, int i3) {
        return new IndexedLineInfoProxy(this, i, i2, i3);
    }

    IndexedLineProxy getIndexedLineProxy(int i, int i2, int i3, boolean z) {
        return new IndexedLineProxy(getIndexedLineInfoProxy(i, i2, i3), z);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Iterable
    public Iterator<LineInfo> iterator() {
        return new IndexedItemIterator(getIndexedLineInfoProxy(Integer.MAX_VALUE, 0, getLineCount()));
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public Iterable<BasedSequence> getLines(int i, int i2, int i3, boolean z) {
        return new IndexedItemIterable(getIndexedLineProxy(i, i2, i3, z));
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public Iterable<LineInfo> getLinesInfo(int i, int i2, int i3) {
        return new IndexedItemIterable(getIndexedLineInfoProxy(i, i2, i3));
    }
}
