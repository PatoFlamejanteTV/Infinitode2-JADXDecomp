package com.d.h;

import com.a.a.c.f.w;
import com.a.a.c.k.b.ak;
import com.d.e.aa;
import com.d.h.t;
import com.d.h.x;
import com.d.i.a.c;
import com.d.i.ab;
import com.d.i.ae;
import com.vladsch.flexmark.util.html.Attribute;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.xml.transform.TransformerException;
import org.a.c.h.g;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/* loaded from: infinitode-2.jar:com/d/h/q.class */
public final class q implements com.d.h.a, Closeable {

    /* renamed from: a, reason: collision with root package name */
    private final aa f1257a;

    /* renamed from: b, reason: collision with root package name */
    private final m f1258b;
    private final List<w.a> c;
    private Document d;
    private com.d.i.c e;
    private final float f;
    private org.a.c.h.b g;
    private org.a.c.h.c.d h;
    private String i;
    private x.c j;
    private boolean k;
    private byte[] l;
    private boolean m;
    private OutputStream n;
    private com.d.d.p o;
    private com.d.d.p p;
    private com.d.a.c q;
    private byte r;
    private com.d.a.a s;
    private final boolean t;
    private com.d.h.a u;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(com.d.g.a.a aVar, com.d.g.a.h hVar, com.d.g.a.g gVar, y yVar) {
        this.r = (byte) 0;
        this.g = yVar.E != null ? yVar.E : new org.a.c.h.b();
        this.g.a(yVar.C);
        this.i = yVar.D;
        this.u = yVar.J != null ? yVar.J : this;
        this.o = yVar.g;
        this.p = yVar.h;
        this.j = yVar.G;
        this.k = false;
        this.l = yVar.I;
        this.f = 26.666666f;
        this.m = false;
        this.t = false;
        this.f1258b = new t(26.666666f, this.m);
        this.f1258b.a(this.g);
        this.f1258b.a(this.g.f());
        v vVar = new v(this.f1258b);
        vVar.a(yVar.f1198b);
        if (yVar.c != null) {
            vVar.a(yVar.c);
        }
        this.f1257a = new aa();
        this.f1257a.A();
        this.f1257a.f1106a = yVar.x;
        this.f1257a.f1107b = yVar.y;
        this.f1257a.a(vVar);
        this.f1257a.a(new com.d.e.a(vVar));
        vVar.a(this.f1257a);
        this.f1258b.a(this.f1257a);
        this.f1257a.a(new f(this.f1257a, this.g, yVar.F.get(x.b.PDF_FONT_METRICS), yVar.G, false));
        this.f1257a.a(new r(this.f1258b, yVar.g, yVar.w, yVar.h));
        this.f1257a.a(new u());
        this.f1257a.a(1920.0f);
        this.f1257a.a(20);
        this.f1257a.b(true);
        this.f1257a.a(false);
        g().a(gVar.f1208a, gVar.f1209b, gVar.c);
        if (yVar.i != null) {
            g().d(yVar.i);
        }
        if (hVar.f1211b != null) {
            this.q = hVar.f1211b;
        }
        if (hVar.f1210a != null) {
            this.s = hVar.f1210a;
            this.f1258b.a(this.s);
        }
        if (hVar.c != null) {
            this.f1257a.a(hVar.c);
        }
        if (hVar.d != null) {
            this.f1257a.b(hVar.d);
        }
        if (hVar.e != null) {
            this.f1257a.a(hVar.e);
        }
        if (hVar.f != null) {
            this.f1257a.b(hVar.f);
        }
        if (hVar.g != null) {
            this.f1257a.c(hVar.g);
        }
        this.r = hVar.h ? (byte) 1 : (byte) 0;
        this.c = yVar.f1197a;
        if (aVar.f1194a != null) {
            a(aVar.f1194a, aVar.e);
        } else if (aVar.f1195b != null) {
            a(aVar.f1195b, aVar.e);
        } else if (aVar.d != null) {
            b(aVar.d);
        } else if (aVar.c != null) {
            try {
                a(aVar.c);
            } catch (IOException e) {
                com.d.m.l.a("Problem trying to read input XHTML file", e);
                throw new RuntimeException("File IO problem", e);
            }
        }
        this.n = yVar.B;
    }

    public final org.a.c.h.b a() {
        return this.g;
    }

    public final f b() {
        return (f) this.f1257a.c();
    }

    private Document a(String str) {
        return this.f1257a.n().c(str).d();
    }

    private void b(String str) {
        a(a(str), str);
    }

    private void a(Document document, String str) {
        a(document, str, new com.d.k.a.b());
    }

    private void a(File file) {
        File parentFile = file.getAbsoluteFile().getParentFile();
        a(a(file.toURI().toURL().toExternalForm()), parentFile == null ? "" : parentFile.toURI().toURL().toExternalForm());
    }

    private void a(String str, String str2) {
        a(com.d.j.g.a(new InputSource(new BufferedReader(new StringReader(str)))).d(), str2);
    }

    private void a(Document document, String str, com.d.d.l lVar) {
        this.d = document;
        Iterator<w.a> it = this.c.iterator();
        while (it.hasNext()) {
            it.next();
        }
        b().b();
        if (com.d.m.b.a("xr.cache.stylesheets", true)) {
            this.f1257a.j().a();
        } else {
            this.f1257a.j().b();
        }
        this.f1257a.c(str);
        this.f1257a.a(lVar);
        this.f1257a.j().a(this.f1257a, this.f1257a.l(), document, new a((byte) 0));
        b().a(this.f1257a.j().c());
        if (this.o != null) {
            this.f1257a.j().c();
        }
        if (this.p != null) {
            this.f1257a.j().c();
        }
    }

    public final void c() {
        com.d.e.v f = f();
        com.d.i.c a2 = com.d.e.c.a(f, this.d);
        a2.g(new ae(a(f)));
        a2.b(f);
        a2.Z().c(a2.Z().a(f).height);
        a2.Z().d(f);
        this.e = a2;
    }

    private static Rectangle a(com.d.e.v vVar) {
        com.d.i.aa a2 = com.d.e.t.a(vVar, "first");
        return new Rectangle(0, 0, a2.d(vVar), a2.c((com.d.c.f.d) vVar));
    }

    private ab e() {
        ab b2 = this.f1257a.b();
        b2.a(new com.d.d.j());
        b2.a(this.f1258b);
        if (this.s != null) {
            b2.a(this.s);
        }
        this.f1258b.a(b2);
        this.f1257a.e();
        b2.q();
        b2.a(this.e.Z());
        return b2;
    }

    private com.d.e.v f() {
        com.d.e.v a2 = this.f1257a.a();
        a2.a(new com.d.d.j());
        if (this.q != null) {
            a2.a(this.q);
        }
        if (this.s != null) {
            a2.a(this.s);
        }
        a2.a(this.r);
        u uVar = (u) this.f1257a.e();
        a2.w();
        uVar.a(this.s != null ? this.s : new ak());
        return a2;
    }

    public final void d() {
        a(this.n);
    }

    @Deprecated
    private void a(OutputStream outputStream) {
        a(outputStream, true, 0);
    }

    @Deprecated
    private void a(OutputStream outputStream, boolean z, int i) {
        if (this.t) {
            a(true);
            return;
        }
        try {
            if (this.e == null) {
                c();
            }
            List<com.d.i.aa> k = this.e.Z().k();
            ab e = e();
            e.b(0);
            com.d.i.aa aaVar = k.get(0);
            Rectangle2D.Float r0 = new Rectangle2D.Float(0.0f, 0.0f, aaVar.a(e) / 26.666666f, aaVar.b(e) / 26.666666f);
            if (this.h != null) {
                this.g.a(this.h);
            }
            b(k, e, r0, this.g);
            this.g.a(outputStream);
        } finally {
            this.g.close();
            this.g = null;
        }
    }

    private void a(boolean z) {
        com.d.m.l.d(Level.INFO, "Using fast-mode renderer. Prepare to fly.");
        try {
            if (this.e == null) {
                c();
            }
            List<com.d.i.aa> k = this.e.Z().k();
            ab e = e();
            e.b(0);
            e.a(true);
            com.d.i.aa aaVar = k.get(0);
            Rectangle2D.Float r0 = new Rectangle2D.Float(0.0f, 0.0f, aaVar.a(e) / 26.666666f, aaVar.b(e) / 26.666666f);
            if (this.h != null) {
                this.g.a(this.h);
            }
            a(k, e, (Rectangle2D) r0, this.g);
            if (z) {
                this.g.a(this.n);
                this.g.close();
                this.g = null;
            }
        } catch (Throwable th) {
            if (z) {
                this.g.close();
                this.g = null;
            }
            throw th;
        }
    }

    private void a(List<com.d.i.aa> list, ab abVar, Rectangle2D rectangle2D, org.a.c.h.b bVar) {
        this.f1258b.a((com.d.i.f) this.e);
        this.f1258b.a(this.d);
        org.a.c.h.e a2 = this.u.a(bVar, (float) rectangle2D.getWidth(), (float) rectangle2D.getHeight());
        this.f1258b.a(new org.a.c.h.g(bVar, a2, g.a.APPEND, !this.m), a2, (float) rectangle2D.getHeight());
        this.e.Z().a((com.d.c.f.d) abVar, (short) 2);
        int size = this.e.Z().k().size();
        abVar.a(size);
        b(bVar);
        if (this.k) {
            a(bVar);
        } else if (this.j != x.c.NONE) {
            a(bVar, this.j.b(), this.j.a());
        }
        com.d.i.a.c a3 = new com.d.i.a.b(this.e.Z().k()).a(abVar, this.e.Z());
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            com.d.i.aa aaVar = list.get(i2);
            aaVar.a(i);
            c.a a4 = a3.a(i2);
            abVar.a(i2, aaVar);
            abVar.c(-1);
            a(abVar, aaVar, a4, 0);
            this.f1258b.k();
            i++;
            if (!a4.a().isEmpty()) {
                a4.a().size();
                int i3 = 0;
                int d = aaVar.d(abVar);
                int i4 = d * (aaVar.g() == com.d.c.a.c.ak ? 1 : -1);
                for (c.a aVar : a4.a()) {
                    org.a.c.h.e a5 = this.u.a(bVar, aaVar.a(abVar) / 26.666666f, aaVar.b(abVar) / 26.666666f);
                    this.f1258b.a(new org.a.c.h.g(bVar, a5, g.a.APPEND, !this.m), a5, aaVar.b(abVar) / 26.666666f);
                    abVar.c(i3);
                    a(abVar, aaVar, aVar, -i4);
                    this.f1258b.k();
                    i4 += d * (aaVar.g() == com.d.c.a.c.ak ? 1 : -1);
                    i++;
                    i3++;
                }
            }
            if (i2 != size - 1) {
                com.d.i.aa aaVar2 = list.get(i2 + 1);
                Rectangle2D.Float r0 = new Rectangle2D.Float(0.0f, 0.0f, aaVar2.a(abVar) / 26.666666f, aaVar2.b(abVar) / 26.666666f);
                org.a.c.h.e a6 = this.u.a(bVar, (float) r0.getWidth(), (float) r0.getHeight());
                this.f1258b.a(new org.a.c.h.g(bVar, a6, g.a.APPEND, !this.m), a6, (float) r0.getHeight());
            }
        }
        this.f1258b.c(abVar, this.e);
    }

    private void b(List<com.d.i.aa> list, ab abVar, Rectangle2D rectangle2D, org.a.c.h.b bVar) {
        this.f1258b.a((com.d.i.f) this.e);
        this.f1258b.a(this.d);
        org.a.c.h.e a2 = this.u.a(bVar, (float) rectangle2D.getWidth(), (float) rectangle2D.getHeight());
        this.f1258b.a(new org.a.c.h.g(bVar, a2, g.a.APPEND, !this.m), a2, (float) rectangle2D.getHeight());
        this.e.Z().a((com.d.c.f.d) abVar, (short) 2);
        int size = this.e.Z().k().size();
        abVar.a(size);
        b(bVar);
        if (this.j != x.c.NONE) {
            a(bVar, this.j.b(), this.j.a());
        }
        for (int i = 0; i < size; i++) {
            com.d.i.aa aaVar = list.get(i);
            abVar.a(i, aaVar);
            a(abVar, aaVar);
            this.f1258b.k();
            if (i != size - 1) {
                com.d.i.aa aaVar2 = list.get(i + 1);
                Rectangle2D.Float r0 = new Rectangle2D.Float(0.0f, 0.0f, aaVar2.a(abVar) / 26.666666f, aaVar2.b(abVar) / 26.666666f);
                org.a.c.h.e a3 = this.u.a(bVar, (float) r0.getWidth(), (float) r0.getHeight());
                this.f1258b.a(new org.a.c.h.g(bVar, a3, g.a.APPEND, !this.m), a3, (float) r0.getHeight());
            }
        }
        this.f1258b.c(abVar, this.e);
    }

    private void a(org.a.c.h.b bVar) {
        try {
            org.a.c.h.c c = bVar.c();
            String attribute = this.d.getDocumentElement().getAttribute("lang");
            c.a(!attribute.isEmpty() ? attribute : "EN-US");
            c.a(new org.a.c.h.g.f.a(new org.a.c.b.d()));
            c.b().a(true);
            org.a.c.h.b.a.a aVar = new org.a.c.h.b.a.a();
            aVar.a(true);
            c.a(aVar);
            org.a.c.h.d b2 = bVar.b();
            String b3 = b2.b() != null ? b2.b() : "";
            String str = b3;
            if (b3.isEmpty()) {
                com.d.m.l.d(Level.WARNING, "No document title provided. Document will not be PDF/UA compliant.");
            }
            org.a.d.b a2 = org.a.d.b.a();
            a2.j();
            a2.k().c(str);
            String a3 = this.f1258b.a("description");
            a2.k().b(a3 != null ? a3 : str);
            a2.g();
            a2.h().c("http://www.aiim.org/pdfa/ns/schema#", "pdfaSchema");
            a2.h().c("http://www.aiim.org/pdfa/ns/property#", "pdfaProperty");
            a2.h().c("http://www.aiim.org/pdfua/ns/id/", "pdfuaid");
            org.a.d.a.l lVar = new org.a.d.a.l(org.a.d.b.a(), "pdfaSchema", "pdfaSchema", "pdfaSchema");
            lVar.a("schema", "PDF/UA Universal Accessibility Schema");
            lVar.a("namespaceURI", "http://www.aiim.org/pdfua/ns/id/");
            lVar.a("prefix", "pdfuaid");
            org.a.d.a.l lVar2 = new org.a.d.a.l(org.a.d.b.a(), "pdfaProperty", "pdfaProperty", "pdfaProperty");
            lVar2.a(Attribute.NAME_ATTR, "part");
            lVar2.a("valueType", "Integer");
            lVar2.a("category", "internal");
            lVar2.a("description", "Indicates, which part of ISO 14289 standard is followed");
            lVar.b("property", lVar2);
            a2.h().a("schemas", (org.a.d.b.b) lVar);
            a2.h().f("pdfuaid");
            a2.h().a("part", "1");
            org.a.d.c.a aVar2 = new org.a.d.c.a();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            aVar2.a(a2, (OutputStream) byteArrayOutputStream, true);
            org.a.c.h.a.f fVar = new org.a.c.h.a.f(bVar);
            fVar.a(byteArrayOutputStream.toByteArray());
            bVar.c().a(fVar);
        } catch (IOException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private void a(org.a.c.h.b bVar, int i, String str) {
        org.a.c.h.d b2 = bVar.b();
        org.a.d.b a2 = org.a.d.b.a();
        try {
            String b3 = b2.b();
            String c = b2.c();
            String d = b2.d();
            String e = b2.e();
            String g = b2.g();
            org.a.d.a.e i2 = a2.i();
            i2.a(str);
            i2.a(Integer.valueOf(i));
            org.a.d.a.a m = a2.m();
            if (e != null) {
                m.a(e);
            }
            if (g != null) {
                m.b(g);
            }
            a2.l().a(b2.h());
            org.a.d.a.b j = a2.j();
            if (c != null) {
                j.a(c);
            }
            if (b3 != null) {
                j.c(b3);
            }
            if (d != null) {
                j.b(d);
            }
            org.a.c.h.a.f fVar = new org.a.c.h.a.f(bVar);
            org.a.c.h.b.a.a aVar = new org.a.c.h.b.a.a();
            aVar.a(true);
            org.a.c.h.c c2 = bVar.c();
            c2.a(fVar);
            c2.a(aVar);
            String attribute = this.d.getDocumentElement().getAttribute("lang");
            c2.a(!attribute.isEmpty() ? attribute : "EN-US");
            c2.a(new org.a.c.h.g.f.a(new org.a.c.b.d()));
            c2.b().a(true);
            org.a.d.c.a aVar2 = new org.a.d.c.a();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            aVar2.a(a2, (OutputStream) byteArrayOutputStream, true);
            fVar.a(byteArrayOutputStream.toByteArray());
            if (this.l != null) {
                org.a.c.h.f.a.s sVar = new org.a.c.h.f.a.s(bVar, new ByteArrayInputStream(this.l));
                sVar.a("sRGB IEC61966-2.1");
                sVar.b("sRGB IEC61966-2.1");
                sVar.c("sRGB IEC61966-2.1");
                sVar.d("http://www.color.org");
                c2.a(sVar);
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        } catch (TransformerException e3) {
            throw new RuntimeException(e3);
        } catch (org.a.d.b.h e4) {
            throw new RuntimeException(e4);
        }
    }

    private void b(org.a.c.h.b bVar) {
        String a2;
        org.a.c.h.d dVar = new org.a.c.h.d();
        dVar.a(Calendar.getInstance());
        if (this.i == null) {
            dVar.e("openhtmltopdf.com");
        } else {
            dVar.e(this.i);
        }
        for (t.c cVar : this.f1258b.n()) {
            String b2 = cVar.b();
            if (!b2.isEmpty() && (a2 = cVar.a()) != null) {
                if (b2.equals(Attribute.TITLE_ATTR)) {
                    dVar.a(a2);
                } else if (b2.equals("author")) {
                    dVar.b(a2);
                } else if (b2.equals("subject")) {
                    dVar.c(a2);
                } else if (b2.equals("keywords")) {
                    dVar.d(a2);
                } else {
                    dVar.a(b2, a2);
                }
            }
        }
        bVar.a(dVar);
    }

    private void a(ab abVar, com.d.i.aa aaVar, c.a aVar, int i) {
        aaVar.b(abVar, 0, (short) 2);
        abVar.b(true);
        aaVar.c(abVar, 0, (short) 2);
        abVar.b(false);
        aaVar.a(abVar, 0, (short) 2);
        this.f1258b.f(aaVar.f(abVar));
        int d = (-aaVar.d()) + aaVar.d(abVar, 3);
        this.f1258b.a(aaVar.d(abVar, 1) + i, d);
        new com.d.i.a.d().a(abVar, aVar);
        this.f1258b.a(-r0, -d);
        this.f1258b.h();
    }

    private void a(ab abVar, com.d.i.aa aaVar) {
        aaVar.b(abVar, 0, (short) 2);
        aaVar.c(abVar, 0, (short) 2);
        aaVar.a(abVar, 0, (short) 2);
        Shape c = this.f1258b.c();
        this.f1258b.d(aaVar.f(abVar));
        int d = (-aaVar.d()) + aaVar.d(abVar, 3);
        this.f1258b.a(aaVar.d(abVar, 1), d);
        this.e.Z().a(abVar);
        this.f1258b.a(-r0, -d);
        this.f1258b.e(c);
    }

    private aa g() {
        return this.f1257a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/q$a.class */
    public static final class a implements com.d.d.t {
        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    @Deprecated
    private void h() {
        this.f1258b.m();
        aa.B();
        com.d.m.i.b();
        ((f) g().c()).a();
        if (this.o != null) {
            try {
                this.o.close();
            } catch (IOException unused) {
            }
        }
        if (this.p != null) {
            try {
                this.p.close();
            } catch (IOException unused2) {
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        h();
    }

    @Override // com.d.h.a
    public final org.a.c.h.e a(org.a.c.h.b bVar, float f, float f2) {
        org.a.c.h.e eVar = new org.a.c.h.e(new org.a.c.h.a.h(f, f2));
        bVar.a(eVar);
        return eVar;
    }
}
