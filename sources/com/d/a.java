package com.d;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.w3c.dom.Document;

@Deprecated
/* loaded from: infinitode-2.jar:com/d/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static /* synthetic */ boolean f950a;

    static {
        f950a = !a.class.desiredAssertionStatus();
    }

    private a() {
    }

    @Deprecated
    public static Document a(org.jsoup.nodes.Document document) {
        try {
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            a(document, newDocument, newDocument, new HashMap());
            return newDocument;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void a(Node node, org.w3c.dom.Node node2, Document document, Map<String, String> map) {
        if (node instanceof org.jsoup.nodes.Document) {
            Iterator<Node> it = ((org.jsoup.nodes.Document) node).childNodes().iterator();
            while (it.hasNext()) {
                a(it.next(), node2, document, map);
            }
            return;
        }
        if (!(node instanceof Element)) {
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                if (!(node2 instanceof Document)) {
                    node2.appendChild(document.createTextNode(textNode.getWholeText()));
                    return;
                }
                return;
            }
            if (node instanceof DataNode) {
                node2.appendChild(document.createCDATASection(((DataNode) node).getWholeData()));
                return;
            } else {
                if (!(node instanceof DocumentType) && !(node instanceof Comment) && !f950a) {
                    throw new AssertionError();
                }
                return;
            }
        }
        Element element = (Element) node;
        org.w3c.dom.Element createElement = document.createElement(element.tagName());
        node2.appendChild(createElement);
        Iterator<Attribute> it2 = element.attributes().iterator();
        while (it2.hasNext()) {
            Attribute next = it2.next();
            String key = next.getKey();
            String str = key;
            if (!key.equals("xmlns")) {
                String a2 = a(str);
                if (a2 != null) {
                    if (a2.equals("xmlns")) {
                        map.put(b(str), next.getValue());
                    } else if (!a2.equals("xml") && map.get(a2) == null) {
                        str = str.replace(':', '_');
                    }
                }
                createElement.setAttribute(str, next.getValue());
                if (com.vladsch.flexmark.util.html.Attribute.ID_ATTR.equals(str)) {
                    createElement.setIdAttribute(str, true);
                }
            }
        }
        Iterator<Node> it3 = element.childNodes().iterator();
        while (it3.hasNext()) {
            a(it3.next(), createElement, document, map);
        }
    }

    private static String a(String str) {
        int indexOf;
        if (str != null && (indexOf = str.indexOf(58)) > 0) {
            return str.substring(0, indexOf);
        }
        return null;
    }

    private static String b(String str) {
        int lastIndexOf;
        if (str != null && (lastIndexOf = str.lastIndexOf(58)) > 0) {
            return str.substring(lastIndexOf + 1);
        }
        return str;
    }
}
