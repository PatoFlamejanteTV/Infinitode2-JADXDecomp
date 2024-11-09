package org.a.c.h.e;

import com.a.a.a.am;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.a.b.b.l;

/* loaded from: infinitode-2.jar:org/a/c/h/e/ad.class */
public class ad extends aa {
    private static final org.a.a.a.a e = org.a.a.a.c.a(ad.class);
    private org.a.c.i.d f;
    private final AffineTransform g;
    private final org.a.b.b.o h;
    private final org.a.b.b i;
    private final boolean j;
    private org.a.b.h.a k;

    public ad(org.a.c.b.d dVar) {
        super(dVar);
        org.a.c.h.a.i q;
        new HashMap();
        v b2 = b();
        byte[] bArr = null;
        if (b2 != null && (q = b2.q()) != null) {
            byte[] a2 = am.a((InputStream) q.c());
            bArr = a2;
            if (a2.length == 0) {
                new StringBuilder("Invalid data for embedded Type1C font ").append(d());
                bArr = null;
            }
        }
        org.a.b.b.o oVar = null;
        if (bArr != null) {
            try {
                oVar = (org.a.b.b.o) new org.a.b.b.l().a(bArr, new a(this, (byte) 0)).get(0);
            } catch (IOException unused) {
                new StringBuilder("Can't read the embedded Type1C font ").append(d());
            }
        }
        this.h = oVar;
        if (this.h != null) {
            this.i = this.h;
            this.j = true;
        } else {
            m<org.a.b.b> b3 = l.a().b(s(), b2);
            this.i = b3.c();
            if (b3.d()) {
                new StringBuilder("Using fallback font ").append(this.i.b()).append(" for ").append(s());
            }
            this.j = false;
        }
        l();
        this.g = h().a();
        this.g.scale(1000.0d, 1000.0d);
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/e/ad$a.class */
    class a implements l.a {
        private a(ad adVar) {
        }

        /* synthetic */ a(ad adVar, byte b2) {
            this(adVar);
        }
    }

    private String s() {
        return this.f4561b.g(org.a.c.b.j.v);
    }

    @Override // org.a.c.h.e.u
    public final String d() {
        return s();
    }

    @Override // org.a.c.h.e.u
    public final org.a.b.h.a e() {
        if (this.k == null) {
            this.k = t();
        }
        return this.k;
    }

    private org.a.b.h.a t() {
        org.a.c.h.a.h j;
        if (b() != null && (j = b().j()) != null && (j.c() != 0.0f || j.d() != 0.0f || j.e() != 0.0f || j.g() != 0.0f)) {
            return new org.a.b.h.a(j.c(), j.d(), j.e(), j.g());
        }
        return this.i.c();
    }

    private String g(int i) {
        return n().a(i);
    }

    @Override // org.a.c.h.e.aa
    protected final org.a.c.h.e.a.c m() {
        if (!c() && a() != null) {
            return new org.a.c.h.e.a.j(a());
        }
        if (this.i instanceof org.a.b.a) {
            return org.a.c.h.e.a.j.a(((org.a.b.a) this.i).a());
        }
        return org.a.c.h.e.a.h.c;
    }

    @Override // org.a.c.h.e.u
    public final int a(InputStream inputStream) {
        return inputStream.read();
    }

    @Override // org.a.c.h.e.u
    public final org.a.c.i.d h() {
        if (this.f == null) {
            List<Number> list = null;
            try {
                list = this.i.d();
            } catch (IOException unused) {
                this.f = f4560a;
            }
            if (list != null && list.size() == 6) {
                this.f = new org.a.c.i.d(list.get(0).floatValue(), list.get(1).floatValue(), list.get(2).floatValue(), list.get(3).floatValue(), list.get(4).floatValue(), list.get(5).floatValue());
            } else {
                return super.h();
            }
        }
        return this.f;
    }

    @Override // org.a.c.h.e.u
    public final float c(int i) {
        Point2D.Float r0 = new Point2D.Float(this.i.a(c(g(i))), 0.0f);
        this.g.transform(r0, r0);
        return (float) r0.getX();
    }

    @Override // org.a.c.h.e.u
    public final boolean c() {
        return this.j;
    }

    @Override // org.a.c.h.e.u
    protected final byte[] d(int i) {
        String a2 = o().a(i);
        if (!this.c.a(a2)) {
            throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in this font's encoding: %s", Integer.valueOf(i), a2, this.c.a()));
        }
        String c = c(a2);
        Map<String, Integer> e2 = this.c.e();
        if (c.equals(".notdef") || !this.i.b(c)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), d()));
        }
        return new byte[]{(byte) e2.get(a2).intValue()};
    }

    @Override // org.a.c.h.e.u
    public final float b(String str) {
        float f = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            f += this.h.c(o().a(str.codePointAt(i))).a();
        }
        return f;
    }

    private String c(String str) {
        if (c() || this.i.b(str)) {
            return str;
        }
        String a2 = o().a(str);
        if (a2 != null && a2.length() == 1) {
            String a3 = l.a(a2.codePointAt(0));
            if (this.i.b(a3)) {
                return a3;
            }
            return ".notdef";
        }
        return ".notdef";
    }
}
