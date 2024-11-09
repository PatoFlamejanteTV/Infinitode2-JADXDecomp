package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.format.MarkdownWriterBase;
import com.vladsch.flexmark.util.format.NodeContext;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.LineAppendableImpl;
import com.vladsch.flexmark.util.sequence.LineInfo;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/MarkdownWriterBase.class */
public abstract class MarkdownWriterBase<T extends MarkdownWriterBase<T, N, C>, N, C extends NodeContext<N, C>> implements LineAppendable {
    protected final LineAppendableImpl appendable;
    protected C context;

    public abstract BasedSequence lastBlockQuoteChildPrefix(BasedSequence basedSequence);

    public MarkdownWriterBase() {
        this(0);
    }

    public String toString() {
        return this.appendable.toString();
    }

    public MarkdownWriterBase(int i) {
        this(null, i);
    }

    public MarkdownWriterBase(Appendable appendable, int i) {
        this.appendable = new LineAppendableImpl(appendable, i);
        this.appendable.setOptions(this.appendable.getOptions() | LineAppendable.F_PREFIX_PRE_FORMATTED);
    }

    public void setContext(C c) {
        this.context = c;
    }

    public C getContext() {
        return this.context;
    }

    public T tailBlankLine() {
        return tailBlankLine(1);
    }

    public T tailBlankLine(int i) {
        BasedSequence prefix = this.appendable.getPrefix();
        BasedSequence lastBlockQuoteChildPrefix = lastBlockQuoteChildPrefix(prefix);
        if (!lastBlockQuoteChildPrefix.equals(prefix)) {
            this.appendable.setPrefix(lastBlockQuoteChildPrefix, false);
            this.appendable.blankLine(i);
            this.appendable.setPrefix(prefix, false);
        } else {
            this.appendable.blankLine(i);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Iterable
    public Iterator<LineInfo> iterator() {
        return this.appendable.iterator();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public Iterable<BasedSequence> getLines(int i, int i2, int i3, boolean z) {
        return this.appendable.getLines(i, i2, i3, true);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public Iterable<LineInfo> getLinesInfo(int i, int i2, int i3) {
        return this.appendable.getLinesInfo(i, i2, i3);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public void setPrefixLength(int i, int i2) {
        this.appendable.setPrefixLength(i, i2);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public void insertLine(int i, CharSequence charSequence, CharSequence charSequence2) {
        this.appendable.insertLine(i, charSequence, charSequence2);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public void setLine(int i, CharSequence charSequence, CharSequence charSequence2) {
        this.appendable.setLine(i, charSequence, charSequence2);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public <T extends Appendable> T appendTo(T t, boolean z, int i, int i2, int i3, int i4) {
        return (T) this.appendable.appendTo(t, z, i, i2, i3, i4);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public boolean endsWithEOL() {
        return this.appendable.endsWithEOL();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public boolean isPendingSpace() {
        return this.appendable.isPendingSpace();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public boolean isPreFormatted() {
        return this.appendable.isPreFormatted();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getTrailingBlankLines(int i) {
        return this.appendable.getTrailingBlankLines(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int column() {
        return this.appendable.column();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getLineCount() {
        return this.appendable.getLineCount();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getLineCountWithPending() {
        return this.appendable.getLineCountWithPending();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getOptions() {
        return this.appendable.getOptions();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getPendingSpace() {
        return this.appendable.getPendingSpace();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getPendingEOL() {
        return this.appendable.getPendingEOL();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int offset() {
        return this.appendable.offset();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int offsetWithPending() {
        return this.appendable.offsetWithPending();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public int getAfterEolPrefixDelta() {
        return this.appendable.getAfterEolPrefixDelta();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public ISequenceBuilder<?, ?> getBuilder() {
        return this.appendable.getBuilder();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getPrefix() {
        return this.appendable.getPrefix();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getBeforeEolPrefix() {
        return this.appendable.getBeforeEolPrefix();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public LineInfo getLineInfo(int i) {
        return this.appendable.getLineInfo(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getLine(int i) {
        return this.appendable.getLine(i);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BasedSequence getIndentPrefix() {
        return this.appendable.getIndentPrefix();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public CharSequence toSequence(int i, int i2, boolean z) {
        return this.appendable.toSequence(i, i2, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public String toString(int i, int i2, boolean z) {
        return this.appendable.toString(i, i2, z);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public BitFieldSet<LineAppendable.Options> getOptionSet() {
        return this.appendable.getOptionSet();
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T removeExtraBlankLines(int i, int i2, int i3, int i4) {
        this.appendable.removeExtraBlankLines(i, i2, i3, i4);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T removeLines(int i, int i2) {
        this.appendable.removeLines(i, i2);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T pushOptions() {
        this.appendable.pushOptions();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T popOptions() {
        this.appendable.popOptions();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T changeOptions(int i, int i2) {
        this.appendable.changeOptions(i, i2);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T addIndentOnFirstEOL(Runnable runnable) {
        this.appendable.addIndentOnFirstEOL(runnable);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T addPrefix(CharSequence charSequence) {
        this.appendable.addPrefix(charSequence);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T addPrefix(CharSequence charSequence, boolean z) {
        this.appendable.addPrefix(charSequence, z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public T append(char c) {
        this.appendable.append(c);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public T append(CharSequence charSequence) {
        this.appendable.append(charSequence);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public T append(CharSequence charSequence, int i, int i2) {
        this.appendable.append(charSequence, i, i2);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T append(LineAppendable lineAppendable, int i, int i2, boolean z) {
        this.appendable.append(lineAppendable, i, i2, z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T blankLine() {
        this.appendable.blankLine();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T blankLine(int i) {
        this.appendable.blankLine(i);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T blankLineIf(boolean z) {
        this.appendable.blankLineIf(z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T closePreFormatted() {
        this.appendable.closePreFormatted();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T indent() {
        this.appendable.indent();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T line() {
        this.appendable.line();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T lineIf(boolean z) {
        this.appendable.lineIf(z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T lineOnFirstText(boolean z) {
        this.appendable.lineOnFirstText(z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T lineWithTrailingSpaces(int i) {
        this.appendable.lineWithTrailingSpaces(i);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T openPreFormatted(boolean z) {
        this.appendable.openPreFormatted(z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T popPrefix() {
        this.appendable.popPrefix();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T popPrefix(boolean z) {
        this.appendable.popPrefix(z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T pushPrefix() {
        this.appendable.pushPrefix();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T removeIndentOnFirstEOL(Runnable runnable) {
        this.appendable.removeIndentOnFirstEOL(runnable);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T append(char c, int i) {
        this.appendable.append(c, i);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T setIndentPrefix(CharSequence charSequence) {
        this.appendable.setIndentPrefix(charSequence);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T setOptions(int i) {
        this.appendable.setOptions(i);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T setPrefix(CharSequence charSequence) {
        this.appendable.setPrefix(charSequence);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T setPrefix(CharSequence charSequence, boolean z) {
        this.appendable.setPrefix(charSequence, z);
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T unIndent() {
        this.appendable.unIndent();
        return this;
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public T unIndentNoEol() {
        this.appendable.unIndentNoEol();
        return this;
    }
}
