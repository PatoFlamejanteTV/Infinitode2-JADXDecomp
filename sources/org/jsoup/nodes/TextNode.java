package org.jsoup.nodes;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/TextNode.class */
public class TextNode extends LeafNode {
    public TextNode(String str) {
        this.value = str;
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return FlexmarkHtmlConverter.TEXT_NODE;
    }

    public String text() {
        return StringUtil.normaliseWhitespace(getWholeText());
    }

    public TextNode text(String str) {
        coreValue(str);
        return this;
    }

    public String getWholeText() {
        return coreValue();
    }

    public boolean isBlank() {
        return StringUtil.isBlank(coreValue());
    }

    public TextNode splitText(int i) {
        String coreValue = coreValue();
        Validate.isTrue(i >= 0, "Split offset must be not be negative");
        Validate.isTrue(i < coreValue.length(), "Split offset must not be greater than current text length");
        String substring = coreValue.substring(0, i);
        String substring2 = coreValue.substring(i);
        text(substring);
        TextNode textNode = new TextNode(substring2);
        if (this.parentNode != null) {
            this.parentNode.addChildren(siblingIndex() + 1, textNode);
        }
        return textNode;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        boolean prettyPrint = outputSettings.prettyPrint();
        Element element = this.parentNode instanceof Element ? (Element) this.parentNode : null;
        boolean z = prettyPrint && !Element.preserveWhitespace(this.parentNode);
        boolean z2 = element != null && (element.tag().isBlock() || element.tag().formatAsBlock());
        boolean z3 = false;
        boolean z4 = false;
        if (z) {
            z3 = (z2 && this.siblingIndex == 0) || (this.parentNode instanceof Document);
            z4 = z2 && nextSibling() == null;
            Node nextSibling = nextSibling();
            Node previousSibling = previousSibling();
            boolean isBlank = isBlank();
            if ((((nextSibling instanceof Element) && ((Element) nextSibling).shouldIndent(outputSettings)) || ((nextSibling instanceof TextNode) && ((TextNode) nextSibling).isBlank()) || ((previousSibling instanceof Element) && (((Element) previousSibling).isBlock() || previousSibling.nameIs(FlexmarkHtmlConverter.BR_NODE)))) && isBlank) {
                return;
            }
            if ((previousSibling == null && element != null && element.tag().formatAsBlock() && !isBlank) || ((outputSettings.outline() && siblingNodes().size() > 0 && !isBlank) || (previousSibling != null && previousSibling.nameIs(FlexmarkHtmlConverter.BR_NODE)))) {
                indent(appendable, i, outputSettings);
            }
        }
        Entities.escape(appendable, coreValue(), outputSettings, false, z, z3, z4);
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    @Override // org.jsoup.nodes.Node
    /* renamed from: clone */
    public TextNode mo2530clone() {
        return (TextNode) super.mo2530clone();
    }

    public static TextNode createFromEncoded(String str) {
        return new TextNode(Entities.unescape(str));
    }

    static String normaliseWhitespace(String str) {
        return StringUtil.normaliseWhitespace(str);
    }

    static String stripLeadingWhitespace(String str) {
        return str.replaceFirst("^\\s+", "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean lastCharIsWhitespace(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ';
    }
}
