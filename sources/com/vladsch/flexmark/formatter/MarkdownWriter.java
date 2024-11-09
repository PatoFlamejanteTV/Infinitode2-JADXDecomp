package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.BlockQuoteLike;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.format.MarkdownWriterBase;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/MarkdownWriter.class */
public class MarkdownWriter extends MarkdownWriterBase<MarkdownWriter, Node, NodeFormatterContext> {
    public MarkdownWriter() {
        this(null, 0);
    }

    public MarkdownWriter(int i) {
        this(null, i);
    }

    public MarkdownWriter(Appendable appendable, int i) {
        super(appendable, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public MarkdownWriter getEmptyAppendable() {
        return new MarkdownWriter(this.appendable, this.appendable.getOptions());
    }

    @Override // com.vladsch.flexmark.util.format.MarkdownWriterBase
    public BasedSequence lastBlockQuoteChildPrefix(BasedSequence basedSequence) {
        Node parent;
        int lastIndexOfAny;
        Node node = (Node) ((NodeFormatterContext) this.context).getCurrentNode();
        while (true) {
            Node node2 = node;
            if (node2 == null || node2.getNextAnyNot(BlankLine.class) != null || (parent = node2.getParent()) == null || (parent instanceof Document)) {
                break;
            }
            if ((parent instanceof BlockQuoteLike) && (lastIndexOfAny = basedSequence.lastIndexOfAny(((NodeFormatterContext) this.context).getBlockQuoteLikePrefixPredicate())) >= 0) {
                basedSequence = basedSequence.getBuilder().append((CharSequence) basedSequence.subSequence(0, lastIndexOfAny)).append(' ').append((CharSequence) basedSequence.subSequence(lastIndexOfAny + 1)).toSequence();
            }
            node = parent;
        }
        return basedSequence;
    }

    public MarkdownWriter appendNonTranslating(CharSequence charSequence) {
        return appendNonTranslating(null, charSequence, null, null);
    }

    public MarkdownWriter appendNonTranslating(CharSequence charSequence, CharSequence charSequence2) {
        return appendNonTranslating(charSequence, charSequence2, null, null);
    }

    public MarkdownWriter appendNonTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return appendNonTranslating(charSequence, charSequence2, charSequence3, null);
    }

    public MarkdownWriter appendNonTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        if (((NodeFormatterContext) this.context).isTransformingText()) {
            append(((NodeFormatterContext) this.context).transformNonTranslating(charSequence, charSequence2, charSequence3, charSequence4));
        } else {
            append(charSequence2);
        }
        return this;
    }

    public MarkdownWriter appendTranslating(CharSequence charSequence) {
        return appendTranslating(null, charSequence, null, null);
    }

    public MarkdownWriter appendTranslating(CharSequence charSequence, CharSequence charSequence2) {
        return appendTranslating(charSequence, charSequence2, null, null);
    }

    public MarkdownWriter appendTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return appendTranslating(charSequence, charSequence2, charSequence3, null);
    }

    public MarkdownWriter appendTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        if (((NodeFormatterContext) this.context).isTransformingText()) {
            append(((NodeFormatterContext) this.context).transformTranslating(charSequence, charSequence2, charSequence3, charSequence4));
        } else {
            if (charSequence != null) {
                append(charSequence);
            }
            append(charSequence2);
            if (charSequence3 != null) {
                append(charSequence3);
            }
            if (charSequence4 != null) {
                append(charSequence4);
            }
        }
        return this;
    }
}
