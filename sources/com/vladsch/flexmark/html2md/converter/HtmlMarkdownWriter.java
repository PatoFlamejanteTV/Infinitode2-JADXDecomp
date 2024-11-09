package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.util.format.MarkdownWriterBase;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlMarkdownWriter.class */
public class HtmlMarkdownWriter extends MarkdownWriterBase<HtmlMarkdownWriter, Node, HtmlNodeConverterContext> {
    public HtmlMarkdownWriter() {
        this(null, 0);
    }

    public HtmlMarkdownWriter(int i) {
        this(null, i);
    }

    public HtmlMarkdownWriter(Appendable appendable, int i) {
        super(appendable, i);
    }

    @Override // com.vladsch.flexmark.util.sequence.LineAppendable
    public HtmlMarkdownWriter getEmptyAppendable() {
        return new HtmlMarkdownWriter(this.appendable, this.appendable.getOptions());
    }

    @Override // com.vladsch.flexmark.util.format.MarkdownWriterBase
    public BasedSequence lastBlockQuoteChildPrefix(BasedSequence basedSequence) {
        Element parent;
        int lastIndexOf;
        Node currentNode = ((HtmlNodeConverterContext) this.context).getCurrentNode();
        if (currentNode instanceof Element) {
            Element element = (Element) currentNode;
            while (true) {
                Element element2 = element;
                if (element2.nextElementSibling() != null || (parent = element2.parent()) == null) {
                    break;
                }
                if (parent.nodeName().toLowerCase().equals(FlexmarkHtmlConverter.BLOCKQUOTE_NODE) && (lastIndexOf = basedSequence.lastIndexOf('>')) >= 0) {
                    basedSequence = basedSequence.getBuilder().append((CharSequence) basedSequence.subSequence(0, lastIndexOf)).append(' ').append((CharSequence) basedSequence.subSequence(lastIndexOf + 1)).toSequence();
                }
                element = parent;
            }
        }
        return basedSequence;
    }
}
