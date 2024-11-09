package com.d.h;

import com.d.h.w;
import com.d.i.ab;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/h/h.class */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private final o f1231a;

    /* renamed from: b, reason: collision with root package name */
    private final m f1232b;
    private final Element c;
    private final List<c> d = new ArrayList();
    private final List<b> e = new ArrayList(2);
    private final Map<String, List<b>> f = new LinkedHashMap();
    private final Map<String, d> g = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/h$d.class */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private org.a.c.h.g.e.j f1239a;

        /* renamed from: b, reason: collision with root package name */
        private String f1240b;
        private String c;
        private boolean d;

        private d() {
        }

        /* synthetic */ d(byte b2) {
            this();
        }
    }

    /* loaded from: infinitode-2.jar:com/d/h/h$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public final com.d.i.f f1235a;

        /* renamed from: b, reason: collision with root package name */
        private final org.a.c.h.e f1236b;
        private final AffineTransform c;
        private final ab d;
        private final float e;

        public b(com.d.i.f fVar, org.a.c.h.e eVar, AffineTransform affineTransform, ab abVar, float f) {
            this.f1235a = fVar;
            this.f1236b = eVar;
            this.c = affineTransform;
            this.d = abVar;
            this.e = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/h$c.class */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final String f1237a;

        /* renamed from: b, reason: collision with root package name */
        private final b f1238b;

        /* synthetic */ c(b bVar, String str, byte b2) {
            this(bVar, str);
        }

        private c(b bVar, String str) {
            this.f1238b = bVar;
            this.f1237a = str;
        }
    }

    private h(Element element, o oVar, m mVar) {
        this.c = element;
        this.f1232b = mVar;
        this.f1231a = oVar;
    }

    public static h a(Element element, o oVar, m mVar) {
        return new h(element, oVar, mVar);
    }

    public final void a(b bVar, String str) {
        this.d.add(new c(bVar, str, (byte) 0));
    }

    private void a() {
        for (c cVar : this.d) {
            if (cVar.f1238b.f1235a.ai().hasAttribute(Attribute.NAME_ATTR)) {
                String attribute = cVar.f1238b.f1235a.ai().getAttribute(Attribute.NAME_ATTR);
                if (!attribute.contains(".")) {
                    d dVar = new d((byte) 0);
                    dVar.f1240b = attribute;
                    dVar.c = attribute;
                    dVar.d = true;
                    this.g.put(attribute, dVar);
                } else {
                    String[] split = attribute.split(Pattern.quote("."));
                    int i = 1;
                    while (i <= split.length) {
                        String[] strArr = new String[i];
                        System.arraycopy(split, 0, strArr, 0, i);
                        String a2 = com.d.m.a.a(strArr, ".");
                        if (this.g.get(a2) == null) {
                            d dVar2 = new d((byte) 0);
                            dVar2.c = a2;
                            dVar2.f1240b = strArr[i - 1];
                            dVar2.d = i == split.length;
                            this.g.put(a2, dVar2);
                        }
                        i++;
                    }
                }
            }
        }
    }

    private void a(d dVar, org.a.c.h.g.e.d dVar2) {
        if (!dVar.d) {
            org.a.c.b.a aVar = new org.a.c.b.a();
            for (d dVar3 : this.g.values()) {
                if (dVar3.c.indexOf(dVar.c) == 0 && dVar3.c.length() > dVar.c.length() + 1 && !dVar3.c.substring(dVar.c.length() + 1).contains(".")) {
                    aVar.a((org.a.c.b.b) dVar3.f1239a.f());
                    dVar3.f1239a.f().a(org.a.c.b.j.cN, (org.a.c.b.b) dVar.f1239a.f());
                    a(dVar3, dVar2);
                }
            }
            dVar.f1239a.f().a(org.a.c.b.j.bR, (org.a.c.b.b) aVar);
        }
    }

    private void a(org.a.c.h.g.e.d dVar) {
        for (d dVar2 : this.g.values()) {
            if (!dVar2.d) {
                org.a.c.h.g.e.l lVar = new org.a.c.h.g.e.l(dVar);
                lVar.c(dVar2.f1240b);
                dVar2.f1239a = lVar;
            }
        }
        for (d dVar3 : this.g.values()) {
            if (!dVar3.c.contains(".")) {
                a(dVar3, dVar);
                dVar.c().add(dVar3.f1239a);
            }
        }
    }

    private static String a(com.d.c.d.g gVar) {
        String str = "";
        if (gVar instanceof com.d.c.d.h) {
            com.d.c.d.h hVar = (com.d.c.d.h) gVar;
            str = String.format(Locale.US, "%.4f", Float.valueOf(hVar.c() / 255.0f)) + ' ' + String.format(Locale.US, "%.4f", Float.valueOf(hVar.b() / 255.0f)) + ' ' + String.format(Locale.US, "%.4f", Float.valueOf(hVar.a() / 255.0f)) + " rg";
        } else if (gVar instanceof com.d.c.d.f) {
            com.d.c.d.f fVar = (com.d.c.d.f) gVar;
            str = String.format(Locale.US, "%.4f", Float.valueOf(fVar.a())) + ' ' + String.format(Locale.US, "%.4f", Float.valueOf(fVar.b())) + ' ' + String.format(Locale.US, "%.4f", Float.valueOf(fVar.c())) + ' ' + String.format(Locale.US, "%.4f", Float.valueOf(fVar.d())) + " k";
        }
        return str;
    }

    private static String a(Element element) {
        return com.a.a.b.c.a.a(element);
    }

    private static String a(Element element, List<String> list, List<String> list2, List<Integer> list3) {
        String str = "";
        int i = 0;
        for (Element element2 : com.a.a.b.c.a.b(element, "option")) {
            String a2 = com.a.a.b.c.a.a(element2);
            list.add(a2);
            if (element2.hasAttribute("value")) {
                list2.add(element2.getAttribute("value"));
            } else {
                list2.add(a2);
            }
            if (str.isEmpty()) {
                str = a2;
            }
            if (element2.hasAttribute("selected")) {
                str = a2;
            }
            if (element2.hasAttribute("selected") && list3 != null) {
                list3.add(Integer.valueOf(i));
            }
            i++;
        }
        return str;
    }

    private void a(c cVar, b bVar, org.a.c.h.g.e.d dVar, com.d.i.f fVar) {
        org.a.c.h.g.e.k kVar = new org.a.c.h.g.e.k(dVar);
        d dVar2 = this.g.get(bVar.f1235a.ai().getAttribute(Attribute.NAME_ATTR));
        dVar2.f1239a = kVar;
        kVar.c(dVar2.f1240b);
        kVar.a(true);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        a(bVar.f1235a.ai(), arrayList, arrayList2, arrayList3);
        kVar.a(arrayList2, arrayList);
        kVar.a(arrayList3);
        kVar.e(("/" + cVar.f1237a + " 0 Tf") + ' ' + a(bVar.f1235a.a().b()));
        if (bVar.f1235a.ai().hasAttribute("required")) {
            kVar.e(true);
        }
        if (bVar.f1235a.ai().hasAttribute("readonly")) {
            kVar.d(true);
        }
        if (bVar.f1235a.ai().hasAttribute(Attribute.TITLE_ATTR)) {
            kVar.d(bVar.f1235a.ai().getAttribute(Attribute.TITLE_ATTR));
        }
        org.a.c.h.g.b.m mVar = kVar.l().get(0);
        Rectangle2D a2 = k.a(bVar.d, bVar.f1235a, bVar.e, bVar.c, this.f1232b);
        mVar.a(new org.a.c.h.a.h((float) a2.getMinX(), (float) a2.getMinY(), (float) a2.getWidth(), (float) a2.getHeight()));
        mVar.a(bVar.f1236b);
        mVar.b(true);
        bVar.f1236b.i().add(mVar);
    }

    private void b(c cVar, b bVar, org.a.c.h.g.e.d dVar, com.d.i.f fVar) {
        org.a.c.h.g.e.h hVar = new org.a.c.h.g.e.h(dVar);
        d dVar2 = this.g.get(bVar.f1235a.ai().getAttribute(Attribute.NAME_ATTR));
        dVar2.f1239a = hVar;
        hVar.c(dVar2.f1240b);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String a2 = a(bVar.f1235a.ai(), arrayList, arrayList2, (List<Integer>) null);
        hVar.a(arrayList2, arrayList);
        hVar.a(a2);
        hVar.b(a2);
        hVar.e(("/" + cVar.f1237a + " 0 Tf") + ' ' + a(bVar.f1235a.a().b()));
        if (bVar.f1235a.ai().hasAttribute("required")) {
            hVar.e(true);
        }
        if (bVar.f1235a.ai().hasAttribute("readonly")) {
            hVar.d(true);
        }
        if (bVar.f1235a.ai().hasAttribute(Attribute.TITLE_ATTR)) {
            hVar.d(bVar.f1235a.ai().getAttribute(Attribute.TITLE_ATTR));
        }
        if (bVar.f1235a.ai().getNodeName().equals("openhtmltopdf-combo")) {
            hVar.c(true);
            hVar.b(true);
        }
        org.a.c.h.g.b.m mVar = hVar.l().get(0);
        Rectangle2D a3 = k.a(bVar.d, bVar.f1235a, bVar.e, bVar.c, this.f1232b);
        mVar.a(new org.a.c.h.a.h((float) a3.getMinX(), (float) a3.getMinY(), (float) a3.getWidth(), (float) a3.getHeight()));
        mVar.a(bVar.f1236b);
        mVar.b(true);
        bVar.f1236b.i().add(mVar);
    }

    private void a(b bVar, org.a.c.h.g.e.d dVar) {
        org.a.c.h.g.e.q qVar = new org.a.c.h.g.e.q(dVar);
        d dVar2 = this.g.get(bVar.f1235a.ai().getAttribute(Attribute.NAME_ATTR));
        dVar2.f1239a = qVar;
        qVar.c(dVar2.f1240b);
        String attribute = bVar.f1235a.ai().getAttribute("value");
        qVar.b(attribute);
        qVar.a(attribute);
        org.a.c.h.g.b.m mVar = qVar.l().get(0);
        mVar.a(bVar.f1236b);
        mVar.a(true);
        mVar.a(new org.a.c.h.a.h(0.0f, 0.0f, 1.0f, 1.0f));
        bVar.f1236b.i().add(mVar);
    }

    private void c(c cVar, b bVar, org.a.c.h.g.e.d dVar, com.d.i.f fVar) {
        String attribute;
        org.a.c.h.g.e.q qVar = new org.a.c.h.g.e.q(dVar);
        d dVar2 = this.g.get(bVar.f1235a.ai().getAttribute(Attribute.NAME_ATTR));
        dVar2.f1239a = qVar;
        qVar.c(dVar2.f1240b);
        qVar.e(("/" + cVar.f1237a + " 0 Tf") + ' ' + a(bVar.f1235a.a().b()));
        if (bVar.f1235a.ai().getNodeName().equals("textarea")) {
            attribute = a(bVar.f1235a.ai());
        } else {
            attribute = bVar.f1235a.ai().getAttribute("value");
        }
        String str = attribute;
        qVar.b(str);
        qVar.a(str);
        if (com.a.a.b.c.a.b(bVar.f1235a.ai().getAttribute("max-length")) != null) {
            qVar.a(com.a.a.b.c.a.b(bVar.f1235a.ai().getAttribute("max-length")).intValue());
        }
        if (bVar.f1235a.ai().hasAttribute("required")) {
            qVar.e(true);
        }
        if (bVar.f1235a.ai().hasAttribute("readonly")) {
            qVar.d(true);
        }
        if (bVar.f1235a.ai().getNodeName().equals("textarea")) {
            qVar.a(true);
        } else if (bVar.f1235a.ai().getAttribute("type").equals("password")) {
            qVar.b(true);
        } else if (bVar.f1235a.ai().getAttribute("type").equals("file")) {
            com.d.m.l.d(Level.WARNING, "Acrobat Reader does not support forms with file input controls");
            qVar.c(true);
        }
        if (bVar.f1235a.ai().hasAttribute(Attribute.TITLE_ATTR)) {
            qVar.d(bVar.f1235a.ai().getAttribute(Attribute.TITLE_ATTR));
        }
        org.a.c.h.g.b.m mVar = qVar.l().get(0);
        Rectangle2D a2 = k.a(bVar.d, bVar.f1235a, bVar.e, bVar.c, this.f1232b);
        mVar.a(new org.a.c.h.a.h((float) a2.getMinX(), (float) a2.getMinY(), (float) a2.getWidth(), (float) a2.getHeight()));
        mVar.a(bVar.f1236b);
        mVar.b(true);
        bVar.f1236b.i().add(mVar);
    }

    /* loaded from: infinitode-2.jar:com/d/h/h$a.class */
    public enum a {
        CHECK(52),
        CROSS(53),
        DIAMOND(117),
        CIRCLE(108),
        STAR(72),
        SQUARE(110);

        private final int g;

        a(int i) {
            this.g = i;
        }

        public static a a(com.d.c.a.c cVar) {
            if (cVar == com.d.c.a.c.bU) {
                return CHECK;
            }
            if (cVar == com.d.c.a.c.bV) {
                return CROSS;
            }
            if (cVar == com.d.c.a.c.aR) {
                return SQUARE;
            }
            if (cVar == com.d.c.a.c.o) {
                return CIRCLE;
            }
            if (cVar == com.d.c.a.c.bS) {
                return DIAMOND;
            }
            if (cVar == com.d.c.a.c.bT) {
                return STAR;
            }
            return CHECK;
        }
    }

    public static org.a.c.h.g.b.q a(a aVar, org.a.c.h.b bVar, org.a.c.h.j jVar) {
        return a("q\nBT\n1 0 0 1 15 20 Tm\n/OpenHTMLZap 100 Tf\n(" + ((char) aVar.g) + ") Tj\nET\nQ\n", bVar, jVar);
    }

    public static org.a.c.h.g.b.q a(String str, org.a.c.h.b bVar, org.a.c.h.j jVar) {
        org.a.c.h.g.b.q qVar = new org.a.c.h.g.b.q(bVar);
        qVar.a(new org.a.c.h.a.h(100.0f, 100.0f));
        OutputStream outputStream = null;
        try {
            try {
                OutputStream b2 = qVar.d().b();
                outputStream = b2;
                b2.write(str.getBytes("ASCII"));
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException unused) {
                    }
                }
                qVar.a(jVar);
                return qVar;
            } catch (IOException e) {
                throw new w.a("createCheckboxAppearance", (Exception) e);
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException unused2) {
                    throw th;
                }
            }
            throw th;
        }
    }

    private static org.a.c.b.s a(String str) {
        byte[] bytes = str.getBytes("UTF-16BE");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length + 2);
        byteArrayOutputStream.write(254);
        byteArrayOutputStream.write(255);
        try {
            byteArrayOutputStream.write(bytes);
            return new org.a.c.b.s(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void a(org.a.c.h.g.e.d dVar, b bVar, com.d.i.f fVar) {
        org.a.c.h.g.e.f fVar2 = new org.a.c.h.g.e.f(dVar);
        d dVar2 = this.g.get(bVar.f1235a.ai().getAttribute(Attribute.NAME_ATTR));
        dVar2.f1239a = fVar2;
        fVar2.c(dVar2.f1240b);
        if (bVar.f1235a.ai().hasAttribute("required")) {
            fVar2.e(true);
        }
        if (bVar.f1235a.ai().hasAttribute("readonly")) {
            fVar2.d(true);
        }
        org.a.c.b.a aVar = new org.a.c.b.a();
        aVar.a((org.a.c.b.b) a(bVar.f1235a.ai().getAttribute("value")));
        fVar2.f().a(org.a.c.b.j.cC, (org.a.c.b.b) aVar);
        if (bVar.f1235a.ai().hasAttribute(Attribute.TITLE_ATTR)) {
            fVar2.d(bVar.f1235a.ai().getAttribute(Attribute.TITLE_ATTR));
        }
        org.a.c.b.j a2 = org.a.c.b.j.a("0");
        if (bVar.f1235a.ai().hasAttribute("checked")) {
            fVar2.f().a(org.a.c.b.j.l, (org.a.c.b.b) a2);
            fVar2.f().a(org.a.c.b.j.dU, (org.a.c.b.b) a2);
            fVar2.f().a(org.a.c.b.j.aL, (org.a.c.b.b) a2);
        } else {
            fVar2.f().a(org.a.c.b.j.l, (org.a.c.b.b) org.a.c.b.j.cB);
            fVar2.f().a(org.a.c.b.j.dU, (org.a.c.b.b) org.a.c.b.j.cB);
            fVar2.f().a(org.a.c.b.j.aL, (org.a.c.b.b) org.a.c.b.j.cB);
        }
        Rectangle2D a3 = k.a(bVar.d, bVar.f1235a, bVar.e, bVar.c, this.f1232b);
        org.a.c.h.a.h hVar = new org.a.c.h.a.h((float) a3.getMinX(), (float) a3.getMinY(), (float) a3.getWidth(), (float) a3.getHeight());
        org.a.c.h.g.b.m mVar = fVar2.l().get(0);
        mVar.a(hVar);
        mVar.a(bVar.f1236b);
        mVar.b(true);
        a a4 = a.a(bVar.f1235a.a().e(com.d.c.a.a.n));
        org.a.c.h.g.b.n nVar = new org.a.c.h.g.b.n(new org.a.c.b.d());
        nVar.a(String.valueOf((char) a4.g));
        mVar.a(nVar);
        org.a.c.b.d dVar3 = new org.a.c.b.d();
        dVar3.a(a2, this.f1231a.a(a4));
        dVar3.a(org.a.c.b.j.cB, this.f1231a.a());
        org.a.c.h.g.b.o oVar = new org.a.c.h.g.b.o();
        oVar.f().a(org.a.c.b.j.co, (org.a.c.b.b) dVar3);
        mVar.a(oVar);
        bVar.f1236b.i().add(mVar);
    }

    private void a(List<b> list, org.a.c.h.g.e.d dVar, com.d.i.f fVar) {
        String attribute = list.get(0).f1235a.ai().getAttribute(Attribute.NAME_ATTR);
        org.a.c.h.g.e.n nVar = new org.a.c.h.g.e.n(dVar);
        d dVar2 = this.g.get(attribute);
        dVar2.f1239a = nVar;
        nVar.c(dVar2.f1240b);
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<b> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().f1235a.ai().getAttribute("value"));
        }
        nVar.a(arrayList);
        ArrayList arrayList2 = new ArrayList(list.size());
        int i = 0;
        for (b bVar : list) {
            Rectangle2D a2 = k.a(bVar.d, bVar.f1235a, bVar.e, bVar.c, this.f1232b);
            org.a.c.h.a.h hVar = new org.a.c.h.a.h((float) a2.getMinX(), (float) a2.getMinY(), (float) a2.getWidth(), (float) a2.getHeight());
            org.a.c.h.g.b.m mVar = new org.a.c.h.g.b.m();
            mVar.a(hVar);
            mVar.a(bVar.f1236b);
            mVar.b(true);
            org.a.c.b.d dVar3 = new org.a.c.b.d();
            dVar3.a(org.a.c.b.j.a(new StringBuilder().append(i).toString()), this.f1231a.c());
            dVar3.a(org.a.c.b.j.cB, this.f1231a.b());
            org.a.c.h.g.b.o oVar = new org.a.c.h.g.b.o();
            oVar.f().a(org.a.c.b.j.co, (org.a.c.b.b) dVar3);
            if (bVar.f1235a.ai().hasAttribute("checked")) {
                mVar.f().a(org.a.c.b.j.l, (org.a.c.b.b) org.a.c.b.j.a(new StringBuilder().append(i).toString()));
            } else {
                mVar.f().a(org.a.c.b.j.l, (org.a.c.b.b) org.a.c.b.j.cB);
            }
            mVar.a(oVar);
            arrayList2.add(mVar);
            bVar.f1236b.i().add(mVar);
            i++;
        }
        nVar.b(arrayList2);
        for (b bVar2 : list) {
            if (bVar2.f1235a.ai().hasAttribute("checked")) {
                nVar.a(bVar2.f1235a.ai().getAttribute("value"));
            }
        }
    }

    private void a(org.a.c.h.g.e.d dVar, int i, b bVar, com.d.i.f fVar) {
        org.a.c.h.g.e.m mVar = new org.a.c.h.g.e.m(dVar);
        mVar.a(true);
        if (bVar.f1235a.ai().hasAttribute(Attribute.NAME_ATTR)) {
            org.a.c.h.g.e.q qVar = new org.a.c.h.g.e.q(dVar);
            d dVar2 = this.g.get(bVar.f1235a.ai().getAttribute(Attribute.NAME_ATTR));
            dVar2.f1239a = qVar;
            qVar.c(dVar2.f1240b);
            String attribute = bVar.f1235a.ai().getAttribute("value");
            qVar.b(attribute);
            qVar.a(attribute);
            org.a.c.h.g.b.m mVar2 = qVar.l().get(0);
            mVar2.a(bVar.f1236b);
            mVar2.a(true);
            mVar2.a(new org.a.c.h.a.h(0.0f, 0.0f, 1.0f, 1.0f));
            bVar.f1236b.i().add(mVar2);
        }
        mVar.c("OpenHTMLCtrl" + i);
        org.a.c.h.g.b.m mVar3 = mVar.l().get(0);
        Rectangle2D a2 = k.a(bVar.d, bVar.f1235a, bVar.e, bVar.c, this.f1232b);
        mVar3.a(new org.a.c.h.a.h((float) a2.getMinX(), (float) a2.getMinY(), (float) a2.getWidth(), (float) a2.getHeight()));
        mVar3.a(bVar.f1236b);
        org.a.c.h.a.a aVar = new org.a.c.h.a.a();
        for (d dVar3 : this.g.values()) {
            if (dVar3.d) {
                aVar.add(dVar3.c);
            }
        }
        if (bVar.f1235a.ai().getAttribute("type").equals("reset")) {
            org.a.c.h.g.a.k kVar = new org.a.c.h.g.a.k();
            kVar.a(aVar.a());
            mVar3.a(kVar);
        } else {
            org.a.c.h.a.a.a a3 = org.a.c.h.a.a.a.a(new org.a.c.b.s(this.c.getAttribute("action")));
            org.a.c.h.g.a.m mVar4 = new org.a.c.h.g.a.m();
            mVar4.a(aVar.a());
            mVar4.a(a3);
            if (!this.c.getAttribute("method").equalsIgnoreCase("post")) {
                com.d.m.l.d(Level.WARNING, "Using GET request method for form. You probably meant to add a method=\"post\" attribute to your form");
                mVar4.a(12);
            } else {
                mVar4.a(4);
            }
            mVar3.a(mVar4);
        }
        dVar.c().add(mVar);
        bVar.f1236b.i().add(mVar3);
    }

    public final int a(org.a.c.h.g.e.d dVar, int i, com.d.i.f fVar) {
        a();
        int i2 = i;
        for (c cVar : this.d) {
            i2++;
            b bVar = cVar.f1238b;
            Element ai = bVar.f1235a.ai();
            if ((ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("text")) || ai.getNodeName().equals("textarea") || ((ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("password")) || (ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("file")))) {
                c(cVar, bVar, dVar, fVar);
            } else if ((ai.getNodeName().equals("select") && !ai.hasAttribute("multiple")) || ai.getNodeName().equals("openhtmltopdf-combo")) {
                b(cVar, bVar, dVar, fVar);
            } else if (ai.getNodeName().equals("select") && ai.hasAttribute("multiple")) {
                a(cVar, bVar, dVar, fVar);
            } else if (ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("checkbox")) {
                a(dVar, bVar, fVar);
            } else if (ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("hidden")) {
                a(bVar, dVar);
            } else if (!ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) || !ai.getAttribute("type").equals("radio")) {
                if ((ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("submit")) || ((ai.getNodeName().equals("button") && !ai.getAttribute("type").equals("button")) || (ai.getNodeName().equals(FlexmarkHtmlConverter.INPUT_NODE) && ai.getAttribute("type").equals("reset")))) {
                    this.e.add(bVar);
                }
            } else {
                List<b> list = this.f.get(ai.getAttribute(Attribute.NAME_ATTR));
                List<b> list2 = list;
                if (list == null) {
                    list2 = new ArrayList();
                    this.f.put(ai.getAttribute(Attribute.NAME_ATTR), list2);
                }
                list2.add(bVar);
            }
        }
        Iterator<List<b>> it = this.f.values().iterator();
        while (it.hasNext()) {
            i2++;
            a(it.next(), dVar, fVar);
        }
        Iterator<b> it2 = this.e.iterator();
        while (it2.hasNext()) {
            i2++;
            a(dVar, i2, it2.next(), fVar);
        }
        a(dVar);
        return i2;
    }
}
