package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.html.HtmlAppendableBase;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.LineAppendableImpl;
import com.vladsch.flexmark.util.sequence.LineInfo;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/HtmlAppendableBase.class */
public class HtmlAppendableBase<T extends HtmlAppendableBase<T>> implements HtmlAppendable {
    private final LineAppendable appendable;
    private MutableAttributes currentAttributes;
    private boolean indentOnFirstEol;
    private boolean lineOnChildText;
    private boolean withAttributes;
    private boolean suppressOpenTagLine;
    private boolean suppressCloseTagLine;
    private final Stack<String> openTags;

    public HtmlAppendableBase(LineAppendable lineAppendable, boolean z) {
        this(lineAppendable, z ? lineAppendable.getIndentPrefix().length() : 0, lineAppendable.getOptions());
    }

    public HtmlAppendableBase(int i, int i2) {
        this(null, i, i2);
    }

    public HtmlAppendableBase(Appendable appendable, int i, int i2) {
        this.indentOnFirstEol = false;
        this.lineOnChildText = false;
        this.withAttributes = false;
        this.suppressOpenTagLine = false;
        this.suppressCloseTagLine = false;
        this.openTags = new Stack<>();
        this.appendable = new LineAppendableImpl(appendable, i2);
        this.appendable.setIndentPrefix(RepeatedSequence.repeatOf(SequenceUtils.SPACE, i).toString());
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public HtmlAppendable getEmptyAppendable() {
        return new HtmlAppendableBase(this.appendable, this.appendable.getIndentPrefix().length(), this.appendable.getOptions());
    }

    public boolean isSuppressOpenTagLine() {
        return this.suppressOpenTagLine;
    }

    public void setSuppressOpenTagLine(boolean z) {
        this.suppressOpenTagLine = z;
    }

    public boolean isSuppressCloseTagLine() {
        return this.suppressCloseTagLine;
    }

    public T setSuppressCloseTagLine(boolean z) {
        this.suppressCloseTagLine = z;
        return this;
    }

    public String toString() {
        return this.appendable.toString();
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T openPre() {
        this.appendable.openPreFormatted(true);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T closePre() {
        this.appendable.closePreFormatted();
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public boolean inPre() {
        return this.appendable.isPreFormatted();
    }

    private boolean haveOptions(int i) {
        return (this.appendable.getOptions() & i) != 0;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T raw(CharSequence charSequence) {
        this.appendable.append(charSequence);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T raw(CharSequence charSequence, int i) {
        int i2 = i;
        while (true) {
            int i3 = i2;
            i2--;
            if (i3 <= 0) {
                return this;
            }
            this.appendable.append(charSequence);
        }
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T rawPre(CharSequence charSequence) {
        if (this.appendable.getPendingEOL() == 0 && charSequence.length() > 0 && charSequence.charAt(0) == '\n') {
            this.appendable.line();
            charSequence = charSequence.subSequence(1, charSequence.length());
        }
        this.appendable.openPreFormatted(true).append(charSequence).closePreFormatted();
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T rawIndentedPre(CharSequence charSequence) {
        this.appendable.openPreFormatted(true).append(charSequence).closePreFormatted();
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T text(CharSequence charSequence) {
        this.appendable.append((CharSequence) Escaping.escapeHtml(charSequence, false));
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T attr(CharSequence charSequence, CharSequence charSequence2) {
        if (this.currentAttributes == null) {
            this.currentAttributes = new MutableAttributes();
        }
        this.currentAttributes.addValue(charSequence, charSequence2);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T attr(Attribute... attributeArr) {
        if (this.currentAttributes == null) {
            this.currentAttributes = new MutableAttributes();
        }
        for (Attribute attribute : attributeArr) {
            this.currentAttributes.addValue(attribute.getName(), attribute.getValue());
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T attr(Attributes attributes) {
        if (!attributes.isEmpty()) {
            if (this.currentAttributes == null) {
                this.currentAttributes = new MutableAttributes(attributes);
            } else {
                this.currentAttributes.addValues(attributes);
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T withAttr() {
        this.withAttributes = true;
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public Attributes getAttributes() {
        return this.currentAttributes;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T setAttributes(Attributes attributes) {
        this.currentAttributes = attributes.toMutable();
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T withCondLineOnChildText() {
        this.lineOnChildText = true;
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T withCondIndent() {
        this.indentOnFirstEol = true;
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tag(CharSequence charSequence) {
        return tag(charSequence, false);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tag(CharSequence charSequence, Runnable runnable) {
        return tag(charSequence, false, false, runnable);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagVoid(CharSequence charSequence) {
        return tag(charSequence, true);
    }

    protected String getOpenTagText() {
        return Utils.splice(this.openTags, ", ", true);
    }

    protected void pushTag(CharSequence charSequence) {
        this.openTags.push(String.valueOf(charSequence));
    }

    protected void popTag(CharSequence charSequence) {
        if (this.openTags.isEmpty()) {
            throw new IllegalStateException("Close tag '" + ((Object) charSequence) + "' with no tags open");
        }
        String peek = this.openTags.peek();
        if (!peek.equals(String.valueOf(charSequence))) {
            throw new IllegalStateException("Close tag '" + ((Object) charSequence) + "' does not match '" + peek + "' in " + getOpenTagText());
        }
        this.openTags.pop();
    }

    protected void tagOpened(CharSequence charSequence) {
        pushTag(charSequence);
    }

    protected void tagClosed(CharSequence charSequence) {
        popTag(charSequence);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public Stack<String> getOpenTags() {
        return this.openTags;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public List<String> getOpenTagsAfterLast(CharSequence charSequence) {
        if (this.openTags.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(this.openTags);
        int size = arrayList.size();
        int i = size;
        String valueOf = String.valueOf(charSequence);
        int i2 = size;
        while (true) {
            int i3 = i2;
            i2--;
            if (i3 <= 0) {
                break;
            }
            if (((String) arrayList.get(i2)).equals(valueOf)) {
                i = i2 + 1;
                break;
            }
        }
        return arrayList.subList(i, size);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tag(CharSequence charSequence, boolean z) {
        if (charSequence.length() == 0 || charSequence.charAt(0) == '/') {
            return closeTag(charSequence);
        }
        MutableAttributes mutableAttributes = null;
        if (this.withAttributes) {
            mutableAttributes = this.currentAttributes;
            this.currentAttributes = null;
            this.withAttributes = false;
        }
        this.appendable.append((CharSequence) "<");
        this.appendable.append(charSequence);
        if (mutableAttributes != null && !mutableAttributes.isEmpty()) {
            for (Attribute attribute : mutableAttributes.values()) {
                String value = attribute.getValue();
                if (!attribute.isNonRendering()) {
                    this.appendable.append((CharSequence) SequenceUtils.SPACE);
                    this.appendable.append((CharSequence) Escaping.escapeHtml(attribute.getName(), true));
                    this.appendable.append((CharSequence) "=\"");
                    this.appendable.append((CharSequence) Escaping.escapeHtml(value, true));
                    this.appendable.append((CharSequence) "\"");
                }
            }
        }
        if (z) {
            this.appendable.append((CharSequence) " />");
        } else {
            this.appendable.append((CharSequence) ">");
            tagOpened(charSequence);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T closeTag(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            throw new IllegalStateException("closeTag called with tag:'" + ((Object) charSequence) + "'");
        }
        if (charSequence.charAt(0) == '/') {
            this.appendable.append((CharSequence) "<").append(charSequence).append((CharSequence) ">");
            tagClosed(charSequence.subSequence(1, charSequence.length()));
        } else {
            this.appendable.append((CharSequence) "</").append(charSequence).append((CharSequence) ">");
            tagClosed(charSequence);
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tag(CharSequence charSequence, boolean z, boolean z2, Runnable runnable) {
        boolean z3 = this.lineOnChildText;
        boolean z4 = this.indentOnFirstEol;
        this.lineOnChildText = false;
        this.indentOnFirstEol = false;
        if (z && !this.suppressOpenTagLine) {
            this.appendable.line();
        }
        tag(charSequence, false);
        if (z && !z4) {
            this.appendable.indent();
        }
        if ((this.appendable.getOptions() & F_PASS_THROUGH) != 0) {
            runnable.run();
        } else {
            boolean[] zArr = {false};
            Runnable runnable2 = () -> {
                zArr[0] = true;
            };
            if (z3) {
                this.appendable.setLineOnFirstText();
            }
            if (z4) {
                this.appendable.addIndentOnFirstEOL(runnable2);
            }
            runnable.run();
            if (z3) {
                this.appendable.clearLineOnFirstText();
            }
            if (zArr[0]) {
                this.appendable.unIndentNoEol();
            } else {
                this.appendable.removeIndentOnFirstEOL(runnable2);
            }
        }
        if (z && !z4) {
            this.appendable.unIndent();
        }
        if (z2 && !this.suppressCloseTagLine) {
            this.appendable.line();
        }
        closeTag(charSequence);
        if (z && !this.suppressCloseTagLine) {
            this.appendable.line();
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagVoidLine(CharSequence charSequence) {
        lineIf(!this.suppressOpenTagLine).tagVoid(charSequence).lineIf(!this.suppressCloseTagLine);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagLine(CharSequence charSequence) {
        lineIf(!this.suppressOpenTagLine).tag(charSequence).lineIf(!this.suppressCloseTagLine);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagLine(CharSequence charSequence, boolean z) {
        lineIf(!this.suppressOpenTagLine).tag(charSequence, z).lineIf(!this.suppressCloseTagLine);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagLine(CharSequence charSequence, Runnable runnable) {
        lineIf(!this.suppressOpenTagLine).tag(charSequence, false, false, runnable).lineIf(!this.suppressCloseTagLine);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagIndent(CharSequence charSequence, Runnable runnable) {
        tag(charSequence, true, false, runnable);
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendable
    public T tagLineIndent(CharSequence charSequence, Runnable runnable) {
        tag(charSequence, true, true, runnable);
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
