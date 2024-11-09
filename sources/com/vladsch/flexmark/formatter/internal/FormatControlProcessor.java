package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.ast.HtmlCommentBlock;
import com.vladsch.flexmark.ast.HtmlInnerBlockComment;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.formatter.FormatterOptions;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/FormatControlProcessor.class */
public class FormatControlProcessor {
    public static final String OPEN_COMMENT = "<!--";
    public static final String CLOSE_COMMENT = "-->";
    private final String formatterOnTag;
    private final String formatterOffTag;
    private final boolean formatterTagsEnabled;
    private boolean myFormatterOff = false;
    private boolean justTurnedOffFormatting = false;
    private boolean justTurnedOnFormatting = false;
    private boolean formatterTagsAcceptRegexp;
    private volatile Pattern formatterOffPattern;
    private volatile Pattern formatterOnPattern;

    public FormatControlProcessor(Document document, DataHolder dataHolder) {
        FormatterOptions formatterOptions = new FormatterOptions(dataHolder);
        this.formatterOnTag = formatterOptions.formatterOnTag;
        this.formatterOffTag = formatterOptions.formatterOffTag;
        this.formatterTagsEnabled = formatterOptions.formatterTagsEnabled;
        this.formatterTagsAcceptRegexp = formatterOptions.formatterTagsAcceptRegexp;
    }

    public boolean isFormattingOff() {
        return this.myFormatterOff;
    }

    public Pattern getFormatterOffPattern() {
        if (this.formatterOffPattern == null && this.formatterTagsEnabled && this.formatterTagsAcceptRegexp) {
            this.formatterOffPattern = getPatternOrDisableRegexp(this.formatterOffTag);
        }
        return this.formatterOffPattern;
    }

    public Pattern getFormatterOnPattern() {
        if (this.formatterOffPattern == null && this.formatterTagsEnabled && this.formatterTagsAcceptRegexp) {
            this.formatterOnPattern = getPatternOrDisableRegexp(this.formatterOnTag);
        }
        return this.formatterOnPattern;
    }

    private Pattern getPatternOrDisableRegexp(String str) {
        try {
            return Pattern.compile(str);
        } catch (PatternSyntaxException unused) {
            this.formatterTagsAcceptRegexp = false;
            return null;
        }
    }

    public boolean isFormattingRegion() {
        return !this.myFormatterOff;
    }

    public String getFormatterOnTag() {
        return this.formatterOnTag;
    }

    public String getFormatterOffTag() {
        return this.formatterOffTag;
    }

    public boolean getFormatterTagsEnabled() {
        return this.formatterTagsEnabled;
    }

    public boolean getFormatterRegExEnabled() {
        return this.formatterTagsAcceptRegexp;
    }

    public boolean isJustTurnedOffFormatting() {
        return this.justTurnedOffFormatting;
    }

    public boolean isJustTurnedOnFormatting() {
        return this.justTurnedOnFormatting;
    }

    private Boolean isFormatterOffTag(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        String trim = charSequence.toString().trim();
        String trim2 = trim.substring(4, trim.length() - 3).trim();
        if (this.formatterTagsAcceptRegexp && this.formatterOffPattern != null && this.formatterOnPattern != null) {
            if (this.formatterOnPattern.matcher(trim2).matches()) {
                return Boolean.FALSE;
            }
            if (this.formatterOffPattern.matcher(trim2).matches()) {
                return Boolean.TRUE;
            }
            return null;
        }
        if (this.formatterTagsEnabled) {
            if (trim2.equals(this.formatterOnTag)) {
                return Boolean.FALSE;
            }
            if (trim2.equals(this.formatterOffTag)) {
                return Boolean.TRUE;
            }
            return null;
        }
        return null;
    }

    public void initializeFrom(Node node) {
        this.myFormatterOff = !isFormattingRegion(node.getStartOffset(), node, true);
    }

    public void processFormatControl(Node node) {
        this.justTurnedOffFormatting = false;
        this.justTurnedOnFormatting = false;
        if (((node instanceof HtmlCommentBlock) || (node instanceof HtmlInnerBlockComment)) && this.formatterTagsEnabled) {
            boolean z = this.myFormatterOff;
            Boolean isFormatterOffTag = isFormatterOffTag(node.getChars());
            if (isFormatterOffTag == null) {
                return;
            }
            this.myFormatterOff = isFormatterOffTag.booleanValue();
            if (!z && this.myFormatterOff) {
                this.justTurnedOffFormatting = true;
            }
            if (!z || this.myFormatterOff) {
                return;
            }
            this.justTurnedOnFormatting = true;
        }
    }

    private boolean isFormattingRegion(int i, Node node, boolean z) {
        Boolean isFormatterOffTag;
        while (node != null) {
            if (node.getStartOffset() <= i) {
                if ((node instanceof Block) && !(node instanceof Paragraph) && node.hasChildren()) {
                    Node lastChild = node.getLastChild();
                    return lastChild != null && isFormattingRegion(i, lastChild, false);
                }
                if (((node instanceof HtmlCommentBlock) || (node instanceof HtmlInnerBlockComment)) && (isFormatterOffTag = isFormatterOffTag(node.getChars())) != null) {
                    return !isFormatterOffTag.booleanValue();
                }
            }
            if (node.getPrevious() == null && z) {
                Node parent = node.getParent();
                node = parent;
                if (!(parent instanceof Document)) {
                    if (node != null) {
                        node = node.getPrevious();
                    }
                } else {
                    return true;
                }
            } else {
                node = node.getPrevious();
            }
        }
        return true;
    }

    public boolean isFormattingRegion(Node node) {
        if (!this.formatterTagsEnabled || node.getStartOffset() == 0) {
            return true;
        }
        return isFormattingRegion(node.getStartOffset(), node, true);
    }
}
