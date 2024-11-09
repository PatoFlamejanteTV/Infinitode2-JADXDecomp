package com.d.k;

import com.d.c.b.c;
import com.d.d.l;
import com.d.l.b;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.parser.Parser;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;

/* loaded from: infinitode-2.jar:com/d/k/a.class */
public class a implements l {

    /* renamed from: a, reason: collision with root package name */
    private Pattern f1402a = Pattern.compile("type\\s?=\\s?");

    /* renamed from: b, reason: collision with root package name */
    private Pattern f1403b = Pattern.compile("href\\s?=\\s?");
    private Pattern c = Pattern.compile("title\\s?=\\s?");
    private Pattern d = Pattern.compile("alternate\\s?=\\s?");
    private Pattern e = Pattern.compile("media\\s?=\\s?");

    public String a() {
        return Parser.NamespaceXml;
    }

    @Override // com.d.d.l
    public final String a(Element element, String str) {
        if (element.hasAttribute(str)) {
            return element.getAttribute(str);
        }
        return null;
    }

    @Override // com.d.d.l
    public final String a(Element element, String str, String str2) {
        if (str == "") {
            return a(element, str2);
        }
        if (str == null) {
            if (element.getLocalName() == null) {
                return a(element, str2);
            }
            NamedNodeMap attributes = element.getAttributes();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                Attr attr = (Attr) attributes.item(i);
                if (str2.equals(attr.getLocalName())) {
                    return attr.getValue();
                }
            }
            return null;
        }
        if (element.hasAttributeNS(str, str2)) {
            return element.getAttributeNS(str, str2);
        }
        return null;
    }

    @Override // com.d.d.l
    public String a(Element element) {
        return null;
    }

    @Override // com.d.d.l
    public String b(Element element) {
        return null;
    }

    @Override // com.d.d.l
    public String e(Element element) {
        return element.getAttribute("lang");
    }

    @Override // com.d.d.l
    public String c(Element element) {
        return null;
    }

    @Override // com.d.d.l
    public String d(Element element) {
        return null;
    }

    @Override // com.d.d.l
    public String f(Element element) {
        return null;
    }

    @Override // com.d.d.l
    public String g(Element element) {
        return null;
    }

    @Override // com.d.d.l
    public b[] a(Document document) {
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = document.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 7) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) item;
                if (processingInstruction.getTarget().equals("xml-stylesheet")) {
                    b bVar = new b();
                    bVar.a(2);
                    String data = processingInstruction.getData();
                    Matcher matcher = this.d.matcher(data);
                    if (matcher.matches()) {
                        int end = matcher.end();
                        if (data.substring(end + 1, data.indexOf(data.charAt(end), end + 1)).equals("yes")) {
                        }
                    }
                    Matcher matcher2 = this.f1402a.matcher(data);
                    if (matcher2.find()) {
                        int end2 = matcher2.end();
                        if (!data.substring(end2 + 1, data.indexOf(data.charAt(end2), end2 + 1)).equals("text/css")) {
                        }
                    }
                    Matcher matcher3 = this.f1403b.matcher(data);
                    if (matcher3.find()) {
                        int end3 = matcher3.end();
                        bVar.b(data.substring(end3 + 1, data.indexOf(data.charAt(end3), end3 + 1)));
                    }
                    Matcher matcher4 = this.c.matcher(data);
                    if (matcher4.find()) {
                        int end4 = matcher4.end();
                        data.substring(end4 + 1, data.indexOf(data.charAt(end4), end4 + 1));
                    }
                    Matcher matcher5 = this.e.matcher(data);
                    if (matcher5.find()) {
                        int end5 = matcher5.end();
                        bVar.c(data.substring(end5 + 1, data.indexOf(data.charAt(end5), end5 + 1)));
                    } else {
                        bVar.d("screen");
                    }
                    arrayList.add(bVar);
                }
            }
        }
        return (b[]) arrayList.toArray(new b[arrayList.size()]);
    }

    @Override // com.d.d.l
    public b a(c cVar) {
        return null;
    }
}
