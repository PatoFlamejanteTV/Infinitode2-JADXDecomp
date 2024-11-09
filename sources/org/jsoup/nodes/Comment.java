package org.jsoup.nodes;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import net.bytebuddy.description.type.TypeDescription;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Comment.class */
public class Comment extends LeafNode {
    public Comment(String str) {
        this.value = str;
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return FlexmarkHtmlConverter.COMMENT_NODE;
    }

    public String getData() {
        return coreValue();
    }

    public Comment setData(String str) {
        coreValue(str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        if (outputSettings.prettyPrint() && ((isEffectivelyFirst() && (this.parentNode instanceof Element) && ((Element) this.parentNode).tag().formatAsBlock()) || outputSettings.outline())) {
            indent(appendable, i, outputSettings);
        }
        appendable.append("<!--").append(getData()).append("-->");
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
    public Comment mo2530clone() {
        return (Comment) super.mo2530clone();
    }

    public boolean isXmlDeclaration() {
        return isXmlDeclarationData(getData());
    }

    private static boolean isXmlDeclarationData(String str) {
        if (str.length() > 1) {
            return str.startsWith("!") || str.startsWith(TypeDescription.Generic.OfWildcardType.SYMBOL);
        }
        return false;
    }

    public XmlDeclaration asXmlDeclaration() {
        String data = getData();
        XmlDeclaration xmlDeclaration = null;
        String substring = data.substring(1, data.length() - 1);
        if (!isXmlDeclarationData(substring)) {
            Document parseInput = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput("<" + substring + ">", baseUri());
            if (parseInput.body().childrenSize() > 0) {
                Element child = parseInput.body().child(0);
                XmlDeclaration xmlDeclaration2 = new XmlDeclaration(NodeUtils.parser(parseInput).settings().normalizeTag(child.tagName()), data.startsWith("!"));
                xmlDeclaration = xmlDeclaration2;
                xmlDeclaration2.attributes().addAll(child.attributes());
            }
            return xmlDeclaration;
        }
        return null;
    }
}
