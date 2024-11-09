package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.tree.BasedOffsetTracker;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownParagraph.class */
public class MarkdownParagraph {
    private static final char MARKDOWN_START_LINE_CHAR = 8232;
    public static final List<SpecialLeadInHandler> EMPTY_LEAD_IN_HANDLERS;
    public static final List<TrackedOffset> EMPTY_OFFSET_LIST;
    final BasedSequence baseSeq;
    final BasedSequence altSeq;
    final CharWidthProvider charWidthProvider;
    private BasedSequence firstIndent;
    private BasedSequence indent;
    private int firstWidthOffset;
    int width;
    boolean keepHardLineBreaks;
    boolean keepSoftLineBreaks;
    boolean unEscapeSpecialLeadInChars;
    boolean escapeSpecialLeadInChars;
    boolean restoreTrackedSpaces;
    DataHolder options;
    List<? extends SpecialLeadInHandler> leadInHandlers;
    private List<TrackedOffset> trackedOffsets;
    private boolean trackedOffsetsSorted;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownParagraph$TextType.class */
    public enum TextType {
        WORD,
        SPACE,
        BREAK,
        MARKDOWN_BREAK,
        MARKDOWN_START_LINE
    }

    static {
        $assertionsDisabled = !MarkdownParagraph.class.desiredAssertionStatus();
        EMPTY_LEAD_IN_HANDLERS = Collections.emptyList();
        EMPTY_OFFSET_LIST = Collections.emptyList();
    }

    public MarkdownParagraph(CharSequence charSequence) {
        this(BasedSequence.of(charSequence));
    }

    public MarkdownParagraph(BasedSequence basedSequence) {
        this(basedSequence, basedSequence, CharWidthProvider.NULL);
    }

    public MarkdownParagraph(BasedSequence basedSequence, CharWidthProvider charWidthProvider) {
        this(basedSequence, basedSequence, charWidthProvider);
    }

    public MarkdownParagraph(BasedSequence basedSequence, BasedSequence basedSequence2, CharWidthProvider charWidthProvider) {
        this.firstIndent = BasedSequence.NULL;
        this.indent = BasedSequence.NULL;
        this.firstWidthOffset = 0;
        this.width = 0;
        this.keepHardLineBreaks = true;
        this.keepSoftLineBreaks = false;
        this.unEscapeSpecialLeadInChars = true;
        this.escapeSpecialLeadInChars = true;
        this.restoreTrackedSpaces = false;
        this.options = null;
        this.leadInHandlers = EMPTY_LEAD_IN_HANDLERS;
        this.trackedOffsets = EMPTY_OFFSET_LIST;
        this.trackedOffsetsSorted = true;
        this.baseSeq = basedSequence;
        this.altSeq = basedSequence2;
        this.charWidthProvider = charWidthProvider;
    }

    public BasedSequence wrapTextNotTracked() {
        return getFirstWidth() <= 0 ? this.baseSeq : new LeftAlignedWrapping(this.baseSeq).wrapText();
    }

    public Range getContinuationStartSplice(int i, boolean z, boolean z2) {
        BasedSequence baseSequence = this.altSeq.getBaseSequence();
        if (!$assertionsDisabled && (i < 0 || i > baseSequence.length())) {
            throw new AssertionError();
        }
        if (z && z2) {
            BasedOffsetTracker create = BasedOffsetTracker.create(this.altSeq);
            int startOfLine = baseSequence.startOfLine(i);
            if (startOfLine > this.altSeq.getStartOffset() && !baseSequence.isCharAt(i, CharPredicate.SPACE_TAB_NBSP_LINE_SEP) && baseSequence.lastIndexOfAnyNot(CharPredicate.SPACE_TAB_NBSP_EOL, i - 1) < startOfLine) {
                int i2 = create.getOffsetInfo(i, true).endIndex;
                return Range.of(this.altSeq.lastIndexOfAnyNot(CharPredicate.SPACE_TAB_NBSP_EOL, i2 - 1) + 1, i2);
            }
        }
        return Range.NULL;
    }

    BasedSequence resolveTrackedOffsets(BasedSequence basedSequence, BasedSequence basedSequence2) {
        BasedOffsetTracker create = BasedOffsetTracker.create(basedSequence2);
        int size = this.trackedOffsets.size();
        while (true) {
            int i = size;
            size--;
            if (i > 0) {
                TrackedOffset trackedOffset = this.trackedOffsets.get(size);
                int offset = trackedOffset.getOffset();
                boolean isBaseCharAt = basedSequence.isBaseCharAt(offset, CharPredicate.WHITESPACE_NBSP);
                if (!isBaseCharAt || basedSequence.isBaseCharAt(offset - 1, CharPredicate.WHITESPACE_NBSP)) {
                    if (!isBaseCharAt && basedSequence.isBaseCharAt(offset + 1, CharPredicate.WHITESPACE_NBSP)) {
                        trackedOffset.setIndex(create.getOffsetInfo(offset, false).startIndex);
                    } else {
                        trackedOffset.setIndex(create.getOffsetInfo(offset, true).endIndex);
                    }
                } else {
                    trackedOffset.setIndex(create.getOffsetInfo(offset - 1, false).endIndex);
                }
            } else {
                return basedSequence2;
            }
        }
    }

    public BasedSequence wrapText() {
        BasedSequence resolveTrackedOffsets;
        if (getFirstWidth() <= 0) {
            return this.baseSeq;
        }
        if (this.trackedOffsets.isEmpty()) {
            return wrapTextNotTracked();
        }
        sortedTrackedOffsets();
        BasedSequence basedSequence = this.baseSeq;
        BasedSequence basedSequence2 = this.altSeq;
        Range range = Range.NULL;
        int size = this.trackedOffsets.size();
        while (true) {
            int i = size;
            size--;
            if (i <= 0) {
                break;
            }
            TrackedOffset trackedOffset = this.trackedOffsets.get(size);
            if (range.isEmpty() || !range.contains(trackedOffset.getOffset())) {
                Range continuationStartSplice = getContinuationStartSplice(trackedOffset.getOffset(), trackedOffset.isAfterSpaceEdit(), trackedOffset.isAfterDelete());
                range = continuationStartSplice;
                if (continuationStartSplice.isNotEmpty()) {
                    trackedOffset.setSpliced(true);
                    basedSequence = basedSequence.delete(range.getStart(), range.getEnd());
                    basedSequence2 = basedSequence2.delete(range.getStart(), range.getEnd());
                }
            }
        }
        if (!$assertionsDisabled && !basedSequence.equals(basedSequence2)) {
            throw new AssertionError();
        }
        BasedSequence wrapText = new LeftAlignedWrapping(basedSequence).wrapText();
        if (this.restoreTrackedSpaces) {
            if (this.indent.isNotEmpty() || this.firstIndent.isNotEmpty()) {
                throw new IllegalStateException("restoreTrackedSpaces is not supported with indentation applied by MarkdownParagraph");
            }
            resolveTrackedOffsets = resolveTrackedOffsetsEdit(basedSequence, basedSequence2, wrapText);
        } else {
            resolveTrackedOffsets = resolveTrackedOffsets(this.baseSeq, wrapText);
        }
        return resolveTrackedOffsets;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x03dc, code lost:            r8 = r27;     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0557, code lost:            throw new java.lang.AssertionError(java.lang.String.format("baseSeq.charAt(%d): '%s':0x%04x != altUnwrapped.charAt(%d=%d+%d): '%s':0x%04x, baseSequence anchor: '%s', altUnwrapped anchor: '%s', altWrapped anchor: '%s', wrapped anchor: '%s'", java.lang.Integer.valueOf(r27), java.lang.String.valueOf(r0.safeCharAt(r27)), java.lang.Integer.valueOf(r0.safeCharAt(r27)), java.lang.Integer.valueOf(r28), java.lang.Integer.valueOf(r28), 0, java.lang.String.valueOf(r0.safeCharAt(r28)), java.lang.Integer.valueOf(r0.safeCharAt(r28)), r0.safeSubSequence(r27 - 10, r27).toVisibleWhitespaceString() + "|" + r0.safeSubSequence(r8, r8 + 10).toVisibleWhitespaceString(), r0.safeSubSequence(r28 - 10, r28).toVisibleWhitespaceString() + "|" + r0.safeSubSequence(r28, r28 + 10).toVisibleWhitespaceString(), r0.safeSubSequence(r24 - 10, r24).toVisibleWhitespaceString() + "|" + r0.safeSubSequence(r24, r24 + 10).toVisibleWhitespaceString(), r15.safeSubSequence(r24 - 10, r24).toVisibleWhitespaceString() + "|" + r15.safeSubSequence(r24, r24 + 10).toVisibleWhitespaceString()));     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x076c, code lost:            throw new java.lang.AssertionError(java.lang.String.format("altUnwrapped.charAt: '%s'(%d) != wrapped.charAt: '%s'(%d) for width=%d, unwrapped: '%s', wrapped: '%s'", com.vladsch.flexmark.util.sequence.SequenceUtils.toVisibleWhitespaceString(java.lang.Character.toString(r0)), java.lang.Integer.valueOf(r0), com.vladsch.flexmark.util.sequence.SequenceUtils.toVisibleWhitespaceString(java.lang.Character.toString(r0)), java.lang.Integer.valueOf(r0), java.lang.Integer.valueOf(r12.width), com.vladsch.flexmark.util.sequence.SequenceUtils.toVisibleWhitespaceString(r0), com.vladsch.flexmark.util.sequence.SequenceUtils.toVisibleWhitespaceString(r0)));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.vladsch.flexmark.util.sequence.BasedSequence resolveTrackedOffsetsEdit(com.vladsch.flexmark.util.sequence.BasedSequence r13, com.vladsch.flexmark.util.sequence.BasedSequence r14, com.vladsch.flexmark.util.sequence.BasedSequence r15) {
        /*
            Method dump skipped, instructions count: 2494
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.format.MarkdownParagraph.resolveTrackedOffsetsEdit(com.vladsch.flexmark.util.sequence.BasedSequence, com.vladsch.flexmark.util.sequence.BasedSequence, com.vladsch.flexmark.util.sequence.BasedSequence):com.vladsch.flexmark.util.sequence.BasedSequence");
    }

    public void addTrackedOffset(TrackedOffset trackedOffset) {
        if (this.trackedOffsets == EMPTY_OFFSET_LIST) {
            this.trackedOffsets = new ArrayList();
        }
        if (!$assertionsDisabled && (trackedOffset.getOffset() < 0 || trackedOffset.getOffset() > this.altSeq.getBaseSequence().length())) {
            throw new AssertionError();
        }
        this.trackedOffsets.removeIf(trackedOffset2 -> {
            return trackedOffset2.getOffset() == trackedOffset.getOffset();
        });
        this.trackedOffsets.add(trackedOffset);
        this.trackedOffsetsSorted = false;
    }

    public List<TrackedOffset> getTrackedOffsets() {
        return sortedTrackedOffsets();
    }

    private List<TrackedOffset> sortedTrackedOffsets() {
        if (!this.trackedOffsetsSorted) {
            this.trackedOffsets.sort(Comparator.comparing((v0) -> {
                return v0.getOffset();
            }));
            this.trackedOffsetsSorted = true;
        }
        return this.trackedOffsets;
    }

    public TrackedOffset getTrackedOffset(int i) {
        sortedTrackedOffsets();
        for (TrackedOffset trackedOffset : this.trackedOffsets) {
            if (trackedOffset.getOffset() == i) {
                return trackedOffset;
            }
            if (trackedOffset.getOffset() > i) {
                return null;
            }
        }
        return null;
    }

    public List<? extends SpecialLeadInHandler> getLeadInHandlers() {
        return this.leadInHandlers;
    }

    public void setLeadInHandlers(List<? extends SpecialLeadInHandler> list) {
        this.leadInHandlers = list;
    }

    public DataHolder getOptions() {
        return this.options;
    }

    public void setOptions(DataHolder dataHolder) {
        this.options = dataHolder;
    }

    public boolean isRestoreTrackedSpaces() {
        return this.restoreTrackedSpaces;
    }

    public void setRestoreTrackedSpaces(boolean z) {
        this.restoreTrackedSpaces = z;
    }

    public BasedSequence getChars() {
        return this.baseSeq;
    }

    public CharSequence getFirstIndent() {
        return this.firstIndent;
    }

    public void setFirstIndent(CharSequence charSequence) {
        this.firstIndent = BasedSequence.of(charSequence);
    }

    public CharSequence getIndent() {
        return this.indent;
    }

    public void setIndent(CharSequence charSequence) {
        this.indent = BasedSequence.of(charSequence);
        if (this.firstIndent.isNull()) {
            this.firstIndent = this.indent;
        }
    }

    public int getFirstWidth() {
        if (this.width == 0) {
            return 0;
        }
        return Math.max(0, this.width + this.firstWidthOffset);
    }

    public int getFirstWidthOffset() {
        return this.firstWidthOffset;
    }

    public void setFirstWidthOffset(int i) {
        this.firstWidthOffset = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = Math.max(0, i);
    }

    public boolean getKeepHardBreaks() {
        return this.keepHardLineBreaks;
    }

    public void setKeepHardBreaks(boolean z) {
        this.keepHardLineBreaks = z;
    }

    public boolean getKeepSoftBreaks() {
        return this.keepSoftLineBreaks;
    }

    public boolean isUnEscapeSpecialLeadIn() {
        return this.unEscapeSpecialLeadInChars;
    }

    public void setUnEscapeSpecialLeadIn(boolean z) {
        this.unEscapeSpecialLeadInChars = z;
    }

    public boolean isEscapeSpecialLeadIn() {
        return this.escapeSpecialLeadInChars;
    }

    public void setEscapeSpecialLeadIn(boolean z) {
        this.escapeSpecialLeadInChars = z;
    }

    public void setKeepSoftBreaks(boolean z) {
        this.keepSoftLineBreaks = z;
    }

    public CharWidthProvider getCharWidthProvider() {
        return this.charWidthProvider;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownParagraph$Token.class */
    public static class Token {
        public final TextType type;
        public final Range range;
        public final boolean isFirstWord;

        private Token(TextType textType, Range range, boolean z) {
            this.type = textType;
            this.range = range;
            this.isFirstWord = z;
        }

        public String toString() {
            return "token: " + this.type + SequenceUtils.SPACE + this.range + (this.isFirstWord ? " isFirst" : "");
        }

        public BasedSequence subSequence(BasedSequence basedSequence) {
            return this.range.basedSubSequence(basedSequence);
        }

        public CharSequence subSequence(CharSequence charSequence) {
            return this.range.charSubSequence(charSequence);
        }

        public static Token of(TextType textType, Range range) {
            return new Token(textType, range, false);
        }

        public static Token of(TextType textType, int i, int i2) {
            return new Token(textType, Range.of(i, i2), false);
        }

        public static Token of(TextType textType, Range range, boolean z) {
            return new Token(textType, range, z);
        }

        public static Token of(TextType textType, int i, int i2, boolean z) {
            return new Token(textType, Range.of(i, i2), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownParagraph$LeftAlignedWrapping.class */
    public class LeftAlignedWrapping {
        final BasedSequence baseSeq;
        final SequenceBuilder result;
        final TextTokenizer tokenizer;
        int col = 0;
        int lineCount = 0;
        final int spaceWidth;
        CharSequence lineIndent;
        final CharSequence nextIndent;
        int lineWidth;
        final int nextWidth;
        int wordsOnLine;
        BasedSequence lastSpace;
        List<? extends SpecialLeadInHandler> leadInHandlers;
        boolean unEscapeSpecialLeadInChars;
        boolean escapeSpecialLeadInChars;

        LeftAlignedWrapping(BasedSequence basedSequence) {
            this.spaceWidth = MarkdownParagraph.this.charWidthProvider.getSpaceWidth();
            this.lineIndent = MarkdownParagraph.this.getFirstIndent();
            this.nextIndent = MarkdownParagraph.this.getIndent();
            this.lineWidth = this.spaceWidth * MarkdownParagraph.this.getFirstWidth();
            this.nextWidth = MarkdownParagraph.this.width <= 0 ? Integer.MAX_VALUE : this.spaceWidth * MarkdownParagraph.this.width;
            this.wordsOnLine = 0;
            this.lastSpace = null;
            this.leadInHandlers = MarkdownParagraph.this.leadInHandlers;
            this.unEscapeSpecialLeadInChars = MarkdownParagraph.this.unEscapeSpecialLeadInChars;
            this.escapeSpecialLeadInChars = MarkdownParagraph.this.escapeSpecialLeadInChars;
            this.baseSeq = basedSequence;
            this.result = SequenceBuilder.emptyBuilder(basedSequence);
            this.tokenizer = new TextTokenizer(basedSequence);
        }

        void advance() {
            this.tokenizer.next();
        }

        void addToken(Token token) {
            addChars(this.baseSeq.subSequence(token.range.getStart(), token.range.getEnd()));
        }

        void addChars(CharSequence charSequence) {
            this.result.append(charSequence);
            this.col += MarkdownParagraph.this.charWidthProvider.getStringWidth(charSequence);
        }

        void addSpaces(int i) {
            this.result.append(' ', i);
            this.col += MarkdownParagraph.this.charWidthProvider.getSpaceWidth() * i;
        }

        BasedSequence addSpaces(BasedSequence basedSequence, int i) {
            if (i <= 0) {
                return basedSequence;
            }
            BasedSequence basedSequence2 = null;
            if (basedSequence != null) {
                addChars(basedSequence.subSequence(0, Math.min(basedSequence.length(), i)));
                if (basedSequence.length() > i) {
                    basedSequence2 = basedSequence.subSequence(i);
                }
                i = Math.max(0, i - basedSequence.length());
            }
            if (i > 0) {
                addSpaces(i);
            }
            return basedSequence2;
        }

        void afterLineBreak() {
            this.col = 0;
            this.wordsOnLine = 0;
            this.lineCount++;
            this.lineIndent = this.nextIndent;
            this.lineWidth = this.nextWidth;
            this.lastSpace = null;
        }

        void processLeadInEscape(List<? extends SpecialLeadInHandler> list, BasedSequence basedSequence) {
            if (basedSequence.isNotEmpty() && this.escapeSpecialLeadInChars) {
                Iterator<? extends SpecialLeadInHandler> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().escape(basedSequence, MarkdownParagraph.this.options, this::addChars)) {
                        return;
                    }
                }
            }
            addChars(basedSequence);
        }

        void processLeadInUnEscape(List<? extends SpecialLeadInHandler> list, BasedSequence basedSequence) {
            if (basedSequence.isNotEmpty() && this.unEscapeSpecialLeadInChars) {
                Iterator<? extends SpecialLeadInHandler> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().unEscape(basedSequence, MarkdownParagraph.this.options, this::addChars)) {
                        return;
                    }
                }
            }
            addChars(basedSequence);
        }

        BasedSequence wrapText() {
            while (true) {
                Token token = this.tokenizer.getToken();
                if (token != null) {
                    switch (token.type) {
                        case SPACE:
                            if (this.col != 0) {
                                this.lastSpace = this.baseSeq.subSequence(token.range);
                            }
                            advance();
                            break;
                        case WORD:
                            if (this.col == 0 || this.col + MarkdownParagraph.this.charWidthProvider.getStringWidth(token.subSequence(this.baseSeq)) + this.spaceWidth <= this.lineWidth) {
                                boolean z = this.col == 0;
                                if (this.col > 0) {
                                    this.lastSpace = addSpaces(this.lastSpace, 1);
                                } else if (!SequenceUtils.isEmpty(this.lineIndent)) {
                                    addChars(this.lineIndent);
                                }
                                if (z && !token.isFirstWord) {
                                    processLeadInEscape(this.leadInHandlers, this.baseSeq.subSequence(token.range));
                                } else if (!z && token.isFirstWord) {
                                    processLeadInUnEscape(this.leadInHandlers, this.baseSeq.subSequence(token.range));
                                } else {
                                    addToken(token);
                                }
                                advance();
                                this.wordsOnLine++;
                                break;
                            } else {
                                addChars(SequenceUtils.EOL);
                                afterLineBreak();
                                break;
                            }
                        case MARKDOWN_START_LINE:
                            if (this.col > 0) {
                                addChars(SequenceUtils.EOL);
                                afterLineBreak();
                            }
                            advance();
                            break;
                        case MARKDOWN_BREAK:
                            if (MarkdownParagraph.this.keepHardLineBreaks) {
                                if (this.col > 0) {
                                    addToken(token);
                                    afterLineBreak();
                                }
                            } else {
                                this.lastSpace = this.baseSeq.subSequence(token.range);
                            }
                            advance();
                            break;
                        case BREAK:
                            if (this.col > 0 && MarkdownParagraph.this.keepSoftLineBreaks) {
                                addToken(token);
                                afterLineBreak();
                            }
                            advance();
                            break;
                    }
                } else {
                    return this.result.toSequence();
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownParagraph$TextTokenizer.class */
    public static class TextTokenizer {
        private final CharSequence chars;
        private final int maxIndex;
        private int index = 0;
        private int lastPos = 0;
        private boolean isInWord = false;
        private boolean isFirstNonBlank = true;
        private int lastConsecutiveSpaces = 0;
        private Token token = null;

        TextTokenizer(CharSequence charSequence) {
            this.chars = charSequence;
            this.maxIndex = this.chars.length();
            reset();
        }

        public void reset() {
            this.index = 0;
            this.lastPos = 0;
            this.isInWord = false;
            this.token = null;
            this.lastConsecutiveSpaces = 0;
            this.isFirstNonBlank = true;
            next();
        }

        Token getToken() {
            return this.token;
        }

        public List<Token> asList() {
            ArrayList arrayList = new ArrayList();
            reset();
            while (this.token != null) {
                arrayList.add(this.token);
                next();
            }
            return arrayList;
        }

        void next() {
            this.token = null;
            while (true) {
                if (this.index >= this.maxIndex) {
                    break;
                }
                char charAt = this.chars.charAt(this.index);
                if (this.isInWord) {
                    if (charAt == ' ' || charAt == '\t' || charAt == '\n' || charAt == 8232) {
                        this.isInWord = false;
                        boolean z = this.isFirstNonBlank;
                        this.isFirstNonBlank = false;
                        if (this.lastPos < this.index) {
                            this.token = Token.of(TextType.WORD, this.lastPos, this.index, z);
                            this.lastPos = this.index;
                            break;
                        }
                    } else {
                        this.index++;
                    }
                } else if (charAt != ' ' && charAt != '\t' && charAt != '\n' && charAt != 8232) {
                    if (this.lastPos < this.index) {
                        this.token = Token.of(TextType.SPACE, this.lastPos, this.index);
                        this.lastPos = this.index;
                        this.isInWord = true;
                        this.lastConsecutiveSpaces = 0;
                        break;
                    }
                    this.isInWord = true;
                    this.lastConsecutiveSpaces = 0;
                } else if (charAt == '\n') {
                    if (this.lastConsecutiveSpaces >= 2) {
                        this.token = Token.of(TextType.MARKDOWN_BREAK, this.index - this.lastConsecutiveSpaces, this.index + 1);
                    } else {
                        this.token = Token.of(TextType.BREAK, this.index, this.index + 1);
                    }
                    this.lastPos = this.index + 1;
                    this.lastConsecutiveSpaces = 0;
                    this.isFirstNonBlank = true;
                    this.index++;
                } else {
                    if (charAt == 8232) {
                        this.token = Token.of(TextType.MARKDOWN_START_LINE, this.index, this.index + 1);
                        this.lastPos = this.index + 1;
                        this.lastConsecutiveSpaces = 0;
                        this.index++;
                        break;
                    }
                    if (charAt == ' ') {
                        this.lastConsecutiveSpaces++;
                    } else {
                        this.lastConsecutiveSpaces = 0;
                    }
                    this.index++;
                }
            }
            if (this.lastPos < this.index) {
                if (this.isInWord) {
                    this.token = Token.of(TextType.WORD, this.lastPos, this.index, this.isFirstNonBlank);
                    this.isFirstNonBlank = false;
                } else {
                    this.token = Token.of(TextType.SPACE, this.lastPos, this.index);
                }
                this.lastPos = this.index;
            }
        }
    }
}
