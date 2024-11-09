package org.jsoup.nodes;

import org.jsoup.nodes.Document;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/DataNode.class */
public class DataNode extends LeafNode {
    public DataNode(String str) {
        this.value = str;
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#data";
    }

    public String getWholeData() {
        return coreValue();
    }

    public DataNode setWholeData(String str) {
        coreValue(str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        String wholeData = getWholeData();
        if (outputSettings.syntax() == Document.OutputSettings.Syntax.xml && !wholeData.contains("<![CDATA[")) {
            if (parentNameIs("script")) {
                appendable.append("//<![CDATA[\n").append(wholeData).append("\n//]]>");
                return;
            } else if (parentNameIs(com.vladsch.flexmark.util.html.Attribute.STYLE_ATTR)) {
                appendable.append("/*<![CDATA[*/\n").append(wholeData).append("\n/*]]>*/");
                return;
            } else {
                appendable.append("<![CDATA[").append(wholeData).append("]]>");
                return;
            }
        }
        appendable.append(getWholeData());
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
    public DataNode mo2530clone() {
        return (DataNode) super.mo2530clone();
    }
}
