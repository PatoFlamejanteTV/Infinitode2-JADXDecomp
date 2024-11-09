package com.d.h;

import com.a.a.c.f.w;
import com.d.i.ab;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.awt.Point;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/r.class */
public final class r implements com.d.d.o {

    /* renamed from: a, reason: collision with root package name */
    private final com.d.d.p f1259a;

    /* renamed from: b, reason: collision with root package name */
    private final com.d.d.p f1260b;
    private final w.a c;
    private final m d;

    public r(m mVar, com.d.d.p pVar, w.a aVar, com.d.d.p pVar2) {
        this.d = mVar;
        this.f1259a = pVar;
        this.c = aVar;
        this.f1260b = pVar2;
    }

    /* JADX WARN: Type inference failed for: r0v25, types: [com.d.d.n, com.d.h.e] */
    @Override // com.d.d.o
    public final com.d.d.n a(com.d.e.v vVar, com.d.i.c cVar, com.d.d.s sVar, int i, int i2) {
        w.a x;
        Element ai = cVar.ai();
        if (ai == null) {
            return null;
        }
        String nodeName = ai.getNodeName();
        if (nodeName.equals(FlexmarkHtmlConverter.MATH_NODE) && this.f1260b != null) {
            return new s(ai, this.f1260b, i, i2, cVar, vVar, vVar.y());
        }
        if (nodeName.equals(FlexmarkHtmlConverter.SVG_NODE) && this.f1259a != null) {
            return new s(ai, this.f1259a, i, i2, cVar, vVar, vVar.y());
        }
        if (!nodeName.equals(FlexmarkHtmlConverter.IMG_NODE)) {
            if (!nodeName.equals(FlexmarkHtmlConverter.INPUT_NODE)) {
                if (nodeName.equals("bookmark")) {
                    ?? r0 = new com.d.d.n() { // from class: com.d.h.e

                        /* renamed from: a, reason: collision with root package name */
                        private Point f1220a = new Point(0, 0);

                        /* renamed from: b, reason: collision with root package name */
                        private String f1221b;

                        @Override // com.d.d.n
                        public int a() {
                            return 0;
                        }

                        @Override // com.d.d.n
                        public int b() {
                            return 0;
                        }

                        @Override // com.d.d.n
                        public Point c() {
                            return this.f1220a;
                        }

                        @Override // com.d.d.n
                        public void a(int i3, int i4) {
                            this.f1220a = new Point(i3, i4);
                        }

                        @Override // com.d.d.n
                        public void a(com.d.e.v vVar2) {
                            vVar2.a(d());
                        }

                        public String d() {
                            return this.f1221b;
                        }

                        public void a(String str) {
                            this.f1221b = str;
                        }

                        @Override // com.d.d.n
                        public void a(ab abVar, m mVar, com.d.i.c cVar2) {
                        }
                    };
                    if (ai.hasAttribute(Attribute.NAME_ATTR)) {
                        String attribute = ai.getAttribute(Attribute.NAME_ATTR);
                        vVar.a(attribute, cVar);
                        r0.a(attribute);
                    }
                    return r0;
                }
                if (nodeName.equals("object") && this.c != null && (x = this.c.x()) != null) {
                    return new l(ai, x, i, i2, vVar.y());
                }
                return null;
            }
            return null;
        }
        String attribute2 = ai.getAttribute("src");
        if (attribute2 != null && attribute2.length() > 0) {
            if (attribute2.endsWith(".svg") && this.f1259a != null) {
                com.d.j.g c = sVar.c(attribute2);
                if (c != null) {
                    return new s(c.d().getDocumentElement(), this.f1259a, i, i2, cVar, vVar, vVar.y());
                }
                return null;
            }
            if (attribute2.endsWith(".pdf")) {
                byte[] d = sVar.d(attribute2);
                if (d != null) {
                    return n.a(this.d.j(), d, ai, cVar, vVar, vVar.y());
                }
                return null;
            }
            com.d.d.c d2 = sVar.b(attribute2).d();
            if (d2 != null) {
                return new j(ai, d2, vVar.y(), cVar.a().al());
            }
            return null;
        }
        return null;
    }

    @Override // com.d.d.o
    public final boolean a(Element element) {
        if (element == null) {
            return false;
        }
        String nodeName = element.getNodeName();
        if (nodeName.equals(FlexmarkHtmlConverter.IMG_NODE)) {
            return true;
        }
        if (nodeName.equals(FlexmarkHtmlConverter.MATH_NODE) && this.f1260b != null) {
            return true;
        }
        if ((nodeName.equals(FlexmarkHtmlConverter.SVG_NODE) && this.f1259a != null) || nodeName.equals("bookmark")) {
            return true;
        }
        if (nodeName.equals("object") && this.c != null) {
            return this.c.y();
        }
        return false;
    }
}
