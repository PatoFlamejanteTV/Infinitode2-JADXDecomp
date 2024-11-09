package com.d.k.a;

import com.badlogic.gdx.net.HttpResponseHeader;
import com.d.c.b.c;
import com.d.m.l;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.parser.Parser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: infinitode-2.jar:com/d/k/a/a.class */
public class a extends com.d.k.a {

    /* renamed from: a, reason: collision with root package name */
    private static com.d.l.b f1404a;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f1405b = false;
    private final Map c = null;

    @Override // com.d.k.a
    public final String a() {
        return Parser.NamespaceHtml;
    }

    @Override // com.d.k.a, com.d.d.l
    public final String a(Element element) {
        return element.getAttribute(Attribute.CLASS_ATTR);
    }

    @Override // com.d.k.a, com.d.d.l
    public final String b(Element element) {
        String trim = element.getAttribute(Attribute.ID_ATTR).trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String a(String str) {
        if (b(str)) {
            return str + "px";
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean b(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String b(Element element, String str) {
        String trim = element.getAttribute(str).trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim;
    }

    @Override // com.d.k.a, com.d.d.l
    public final String f(Element element) {
        String str = null;
        if (element.getNodeName().equalsIgnoreCase(FlexmarkHtmlConverter.A_NODE) && element.hasAttribute("href")) {
            str = element.getAttribute("href");
        }
        return str;
    }

    @Override // com.d.k.a, com.d.d.l
    public final String g(Element element) {
        if (element != null && element.getNodeName().equalsIgnoreCase(FlexmarkHtmlConverter.A_NODE) && element.hasAttribute(Attribute.NAME_ATTR)) {
            return element.getAttribute(Attribute.NAME_ATTR);
        }
        return null;
    }

    @Override // com.d.k.a, com.d.d.l
    public final String c(Element element) {
        StringBuilder sb = new StringBuilder();
        if (element.getNodeName().equals(FlexmarkHtmlConverter.TD_NODE) || element.getNodeName().equals(FlexmarkHtmlConverter.TH_NODE)) {
            String b2 = b(element, "colspan");
            if (b2 != null) {
                sb.append("-fs-table-cell-colspan: ");
                sb.append(b2);
                sb.append(";");
            }
            String b3 = b(element, "rowspan");
            if (b3 != null) {
                sb.append("-fs-table-cell-rowspan: ");
                sb.append(b3);
                sb.append(";");
            }
        } else if (element.getNodeName().equals(FlexmarkHtmlConverter.IMG_NODE)) {
            String b4 = b(element, "width");
            if (b4 != null) {
                sb.append("width: ");
                sb.append(a(b4));
                sb.append(";");
            }
            String b5 = b(element, "height");
            if (b5 != null) {
                sb.append("height: ");
                sb.append(a(b5));
                sb.append(";");
            }
        } else if (element.getNodeName().equals("colgroup") || element.getNodeName().equals("col")) {
            String b6 = b(element, FlexmarkHtmlConverter.SPAN_NODE);
            if (b6 != null) {
                sb.append("-fs-table-cell-colspan: ");
                sb.append(b6);
                sb.append(";");
            }
            String b7 = b(element, "width");
            if (b7 != null) {
                sb.append("width: ");
                sb.append(a(b7));
                sb.append(";");
            }
        }
        sb.append(element.getAttribute(Attribute.STYLE_ATTR));
        return sb.toString();
    }

    private static Element c(Element element, String str) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1 && item.getNodeName().equals(str)) {
                return (Element) item;
            }
        }
        return null;
    }

    private static com.d.l.b h(Element element) {
        String attribute = element.getAttribute("media");
        if ("".equals(attribute)) {
            attribute = "all";
        }
        com.d.l.b bVar = new com.d.l.b();
        bVar.c(attribute);
        element.getAttribute("type");
        element.getAttribute(Attribute.TITLE_ATTR);
        bVar.a(2);
        StringBuilder sb = new StringBuilder();
        Node firstChild = element.getFirstChild();
        while (true) {
            Node node = firstChild;
            if (node == null) {
                break;
            }
            if (node instanceof CharacterData) {
                sb.append(((CharacterData) node).getData());
            }
            firstChild = node.getNextSibling();
        }
        String trim = sb.toString().trim();
        if (trim.length() > 0) {
            bVar.e(trim);
            return bVar;
        }
        return null;
    }

    private static com.d.l.b i(Element element) {
        String lowerCase = element.getAttribute("rel").toLowerCase();
        if (lowerCase.indexOf("alternate") != -1 || lowerCase.indexOf("stylesheet") == -1) {
            return null;
        }
        String attribute = element.getAttribute("type");
        if (!attribute.equals("") && !attribute.equals("text/css")) {
            return null;
        }
        com.d.l.b bVar = new com.d.l.b();
        attribute.equals("");
        bVar.a(2);
        bVar.b(element.getAttribute("href"));
        String attribute2 = element.getAttribute("media");
        if ("".equals(attribute2)) {
            attribute2 = "all";
        }
        bVar.c(attribute2);
        element.getAttribute(Attribute.TITLE_ATTR);
        return bVar;
    }

    @Override // com.d.k.a, com.d.d.l
    public final com.d.l.b[] a(Document document) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(super.a(document)));
        Element c = c(document.getDocumentElement(), "head");
        if (c != null) {
            Node firstChild = c.getFirstChild();
            while (true) {
                Node node = firstChild;
                if (node == null) {
                    break;
                }
                if (node.getNodeType() == 1) {
                    Element element = (Element) node;
                    com.d.l.b bVar = null;
                    String localName = element.getLocalName();
                    String str = localName;
                    if (localName == null) {
                        str = element.getTagName();
                    }
                    if (str.equals("link")) {
                        bVar = i(element);
                    } else if (str.equals(Attribute.STYLE_ATTR)) {
                        bVar = h(element);
                    }
                    if (bVar != null) {
                        arrayList.add(bVar);
                    }
                }
                firstChild = node.getNextSibling();
            }
        }
        return (com.d.l.b[]) arrayList.toArray(new com.d.l.b[arrayList.size()]);
    }

    @Override // com.d.k.a, com.d.d.l
    public final com.d.l.b a(c cVar) {
        InputStream b2;
        synchronized (a.class) {
            if (f1404a != null) {
                return f1404a;
            }
            if (f1405b) {
                return null;
            }
            com.d.l.b bVar = new com.d.l.b();
            bVar.b(a());
            bVar.a(0);
            bVar.c("all");
            InputStream inputStream = null;
            try {
                try {
                    b2 = b();
                } catch (Exception e) {
                    f1405b = true;
                    l.a("Could not parse default stylesheet", e);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                }
                if (f1405b) {
                    if (b2 != null) {
                        try {
                            b2.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                }
                bVar.a(cVar.a(new InputStreamReader(b2), bVar));
                b2.close();
                inputStream = null;
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                f1404a = bVar;
                return bVar;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        }
    }

    private InputStream b() {
        String str = com.d.m.b.a("xr.css.user-agent-default-css") + "XhtmlNamespaceHandler.css";
        InputStream resourceAsStream = getClass().getResourceAsStream(str);
        if (resourceAsStream == null) {
            l.c("Can't load default CSS from " + str + ".This file must be on your CLASSPATH. Please check before continuing.");
            f1405b = true;
        }
        return resourceAsStream;
    }

    private Map b(Document document) {
        if (this.c != null) {
            return this.c;
        }
        HashMap hashMap = new HashMap();
        Element c = c(document.getDocumentElement(), "head");
        if (c != null) {
            Node firstChild = c.getFirstChild();
            while (true) {
                Node node = firstChild;
                if (node == null) {
                    break;
                }
                if (node.getNodeType() == 1) {
                    Element element = (Element) node;
                    String localName = element.getLocalName();
                    String str = localName;
                    if (localName == null) {
                        str = element.getTagName();
                    }
                    if (str.equals("meta")) {
                        String attribute = element.getAttribute("http-equiv");
                        String attribute2 = element.getAttribute("content");
                        if (!attribute.equals("") && !attribute2.equals("")) {
                            hashMap.put(attribute, attribute2);
                        }
                    }
                }
                firstChild = node.getNextSibling();
            }
        }
        return hashMap;
    }

    @Override // com.d.k.a, com.d.d.l
    public final String e(Element element) {
        String attribute = element.getAttribute("lang");
        String str = attribute;
        if (attribute.equals("")) {
            String str2 = (String) b(element.getOwnerDocument()).get(HttpResponseHeader.ContentLanguage);
            str = str2;
            if (str2 == null) {
                str = "";
            }
        }
        return str;
    }
}
