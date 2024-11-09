package org.a.c.h.e;

import com.a.a.a.am;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.a.b.b.l;

/* loaded from: infinitode-2.jar:org/a/c/h/e/p.class */
public class p extends o {

    /* renamed from: b, reason: collision with root package name */
    private static final org.a.a.a.a f4554b = org.a.a.a.c.a(p.class);
    private final org.a.b.b.a c;
    private final org.a.b.b d;
    private final boolean e;
    private org.a.c.i.d f;
    private final AffineTransform g;
    private org.a.b.h.a h;

    public p(org.a.c.b.d dVar, ac acVar) {
        super(dVar, acVar);
        org.a.b.b bVar;
        org.a.c.h.a.i q;
        new HashMap();
        v c = c();
        byte[] bArr = null;
        if (c != null && (q = c.q()) != null) {
            bArr = am.a((InputStream) q.c());
        }
        org.a.b.b.i iVar = null;
        if (bArr != null && bArr.length > 0 && (bArr[0] & 255) == 37) {
            new StringBuilder("Found PFB but expected embedded CFF font ").append(c.g());
        } else if (bArr != null) {
            try {
                iVar = new org.a.b.b.l().a(bArr, new a(this, (byte) 0)).get(0);
            } catch (IOException unused) {
                new StringBuilder("Can't read the embedded CFF font ").append(c.g());
            }
        }
        if (iVar != null) {
            if (iVar instanceof org.a.b.b.a) {
                this.c = (org.a.b.b.a) iVar;
                this.d = null;
            } else {
                this.c = null;
                this.d = iVar;
            }
            i();
            this.e = true;
        } else {
            org.a.c.h.e.a a2 = l.a().a(a(), c(), h());
            if (a2.b()) {
                org.a.b.b.i a3 = a2.c().a().a();
                if (a3 instanceof org.a.b.b.a) {
                    this.c = (org.a.b.b.a) a3;
                    this.d = null;
                    bVar = this.c;
                } else {
                    org.a.b.b.o oVar = (org.a.b.b.o) a3;
                    this.c = null;
                    this.d = oVar;
                    bVar = oVar;
                }
            } else {
                this.c = null;
                this.d = a2.a();
                bVar = this.d;
            }
            if (a2.d()) {
                new StringBuilder("Using fallback ").append(bVar.b()).append(" for CID-keyed font ").append(a());
            }
            this.e = false;
        }
        this.g = d().a();
        this.g.scale(1000.0d, 1000.0d);
    }

    @Override // org.a.c.h.e.o
    public final org.a.c.i.d d() {
        List<Number> d;
        if (this.f == null) {
            if (this.c != null) {
                d = this.c.d();
            } else {
                try {
                    d = this.d.d();
                } catch (IOException unused) {
                    return new org.a.c.i.d(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
                }
            }
            if (d != null && d.size() == 6) {
                this.f = new org.a.c.i.d(d.get(0).floatValue(), d.get(1).floatValue(), d.get(2).floatValue(), d.get(3).floatValue(), d.get(4).floatValue(), d.get(5).floatValue());
            } else {
                this.f = new org.a.c.i.d(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
            }
        }
        return this.f;
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/e/p$a.class */
    class a implements l.a {
        private a(p pVar) {
        }

        /* synthetic */ a(p pVar, byte b2) {
            this(pVar);
        }
    }

    @Override // org.a.c.h.e.o
    public final org.a.b.h.a e() {
        if (this.h == null) {
            this.h = j();
        }
        return this.h;
    }

    private org.a.b.h.a j() {
        if (c() != null) {
            org.a.c.h.a.h j = c().j();
            if (j.c() != 0.0f || j.d() != 0.0f || j.e() != 0.0f || j.g() != 0.0f) {
                return new org.a.b.h.a(j.c(), j.d(), j.e(), j.g());
            }
        }
        if (this.c != null) {
            return this.c.c();
        }
        try {
            return this.d.c();
        } catch (IOException unused) {
            return new org.a.b.h.a();
        }
    }

    private org.a.b.b.w e(int i) {
        if (this.c != null) {
            return this.c.c(i);
        }
        if (this.d instanceof org.a.b.b.o) {
            return ((org.a.b.b.o) this.d).c(i);
        }
        return null;
    }

    private String f(int i) {
        String e = this.f4552a.e(i);
        if (e == null) {
            return ".notdef";
        }
        return l.a(e.codePointAt(0));
    }

    @Override // org.a.c.h.e.o
    public final int c(int i) {
        return this.f4552a.l().b(i);
    }

    @Override // org.a.c.h.e.o
    public final byte[] d(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.a.c.h.e.o
    public final float b(int i) {
        float a2;
        int c = c(i);
        if (this.c != null) {
            a2 = e(c).a();
        } else if (this.e && (this.d instanceof org.a.b.b.o)) {
            a2 = ((org.a.b.b.o) this.d).c(c).a();
        } else {
            a2 = this.d.a(f(i));
        }
        Point2D.Float r0 = new Point2D.Float(a2, 0.0f);
        this.g.transform(r0, r0);
        return (float) r0.getX();
    }

    @Override // org.a.c.h.e.o
    public final boolean g() {
        return this.e;
    }
}
