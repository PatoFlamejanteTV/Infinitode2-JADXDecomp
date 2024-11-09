package org.a.d.c;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.a.d.a.l;
import org.a.d.b;
import org.a.d.b.c;
import org.a.d.b.d;
import org.a.d.b.f;
import org.a.d.b.g;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: infinitode-2.jar:org/a/d/c/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private DocumentBuilder f4687a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4688b = true;

    public a() {
        this.f4687a = null;
        try {
            this.f4687a = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public final void a(b bVar, OutputStream outputStream, boolean z) {
        Document newDocument = this.f4687a.newDocument();
        Element a2 = a(newDocument, bVar, true);
        Iterator<l> it = bVar.e().iterator();
        while (it.hasNext()) {
            a2.appendChild(a(newDocument, it.next()));
        }
        a(newDocument, outputStream, "UTF-8");
    }

    private Element a(Document document, l lVar) {
        Element createElementNS = document.createElementNS("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf:Description");
        createElementNS.setAttributeNS("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf:about", lVar.a());
        createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + lVar.i(), lVar.h());
        a(createElementNS, lVar);
        a(document, createElementNS, lVar.d(), lVar.i(), null, true);
        return createElementNS;
    }

    private void a(Document document, Element element, List<org.a.d.b.b> list, String str, String str2, boolean z) {
        String b2;
        for (org.a.d.b.b bVar : list) {
            if (bVar instanceof c) {
                c cVar = (c) bVar;
                if (str2 != null && !str2.isEmpty()) {
                    b2 = str2;
                } else {
                    b2 = cVar.b();
                }
                Element createElement = document.createElement(b2 + ":" + cVar.e());
                createElement.setTextContent(cVar.a());
                for (g gVar : cVar.f()) {
                    createElement.setAttributeNS(gVar.b(), gVar.a(), gVar.c());
                }
                element.appendChild(createElement);
            } else if (bVar instanceof f) {
                f fVar = (f) bVar;
                Element createElement2 = document.createElement(fVar.h() + ":" + fVar.e());
                element.appendChild(createElement2);
                a(createElement2, fVar);
                Element createElement3 = document.createElement("rdf:" + fVar.a());
                createElement2.appendChild(createElement3);
                a(document, createElement3, fVar.d(), str, "rdf", false);
            } else if (!(bVar instanceof d)) {
                System.err.println(">> TODO >> " + bVar.getClass());
            } else {
                d dVar = (d) bVar;
                List<org.a.d.b.b> d = dVar.d();
                Element element2 = element;
                if (z) {
                    Element createElement4 = document.createElement(str + ":" + dVar.e());
                    element.appendChild(createElement4);
                    element2 = createElement4;
                }
                Element createElement5 = document.createElement("rdf:li");
                element2.appendChild(createElement5);
                if (this.f4688b) {
                    createElement5.setAttribute("rdf:parseType", "Resource");
                    a(document, createElement5, d, str, null, true);
                } else {
                    Element createElement6 = document.createElement("rdf:Description");
                    createElement5.appendChild(createElement6);
                    a(document, createElement6, d, str, null, true);
                }
            }
        }
    }

    private void a(Element element, org.a.d.b.a aVar) {
        for (g gVar : a(aVar)) {
            if ("http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(gVar.b())) {
                element.setAttribute("rdf:" + gVar.a(), gVar.c());
            } else {
                element.setAttribute(gVar.a(), gVar.c());
            }
        }
        for (Map.Entry<String, String> entry : aVar.b().entrySet()) {
            element.setAttribute("xmlns:" + entry.getValue(), entry.getKey());
        }
    }

    private static List<g> a(org.a.d.b.a aVar) {
        List<g> f = aVar.f();
        ArrayList arrayList = new ArrayList();
        List<org.a.d.b.b> d = aVar.d();
        for (g gVar : f) {
            boolean z = false;
            Iterator<org.a.d.b.b> it = d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (gVar.a().compareTo(it.next().e()) == 0) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                arrayList.add(gVar);
            }
        }
        return arrayList;
    }

    private static Element a(Document document, b bVar, boolean z) {
        if (z) {
            document.appendChild(document.createProcessingInstruction("xpacket", "begin=\"" + bVar.c() + "\" id=\"" + bVar.d() + "\""));
        }
        Element createElementNS = document.createElementNS("adobe:ns:meta/", "x:xmpmeta");
        createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:x", "adobe:ns:meta/");
        document.appendChild(createElementNS);
        if (z) {
            document.appendChild(document.createProcessingInstruction("xpacket", "end=\"" + bVar.f() + "\""));
        }
        Element createElementNS2 = document.createElementNS("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf:RDF");
        createElementNS.appendChild(createElementNS2);
        return createElementNS2;
    }

    private static void a(Node node, OutputStream outputStream, String str) {
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
        newTransformer.setOutputProperty("indent", "yes");
        newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        newTransformer.setOutputProperty("encoding", str);
        newTransformer.setOutputProperty("omit-xml-declaration", "yes");
        newTransformer.transform(new DOMSource(node), new StreamResult(outputStream));
    }
}
