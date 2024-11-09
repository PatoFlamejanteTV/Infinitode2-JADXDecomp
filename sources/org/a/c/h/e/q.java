package org.a.c.h.e;

import java.io.IOException;
import org.a.b.f.ap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/q.class */
public class q extends o {

    /* renamed from: b, reason: collision with root package name */
    private static final org.a.a.a.a f4555b = org.a.a.a.c.a(q.class);
    private final ap c;
    private final int[] d;
    private final boolean e;
    private final boolean f;
    private final org.a.b.f.c g;
    private org.a.c.i.d h;
    private org.a.b.h.a i;

    public q(org.a.c.b.d dVar, ac acVar) {
        this(dVar, acVar, null);
    }

    public q(org.a.c.b.d dVar, ac acVar, ap apVar) {
        super(dVar, acVar);
        v c = c();
        if (apVar != null) {
            this.c = apVar;
            this.e = true;
            this.f = false;
        } else {
            boolean z = false;
            ap apVar2 = null;
            org.a.c.h.a.i iVar = null;
            if (c != null) {
                org.a.c.h.a.i p = c.p();
                iVar = p == null ? c.q() : p;
                if (iVar == null) {
                    iVar = c.o();
                }
            }
            if (iVar != null) {
                try {
                    org.a.b.f.ad b2 = new org.a.b.f.ab(true).b(iVar.c());
                    apVar2 = b2;
                    if (b2.f()) {
                        z = true;
                        new StringBuilder("Found CFF/OTF but expected embedded TTF font ").append(c.g());
                    }
                    if (b2.g()) {
                        new StringBuilder("OpenType Layout tables used in font ").append(a()).append(" are not implemented in PDFBox and will be ignored");
                    }
                } catch (IOException unused) {
                    z = true;
                    new StringBuilder("Could not read embedded OTF for font ").append(a());
                } catch (NullPointerException unused2) {
                    z = true;
                    new StringBuilder("Could not read embedded OTF for font ").append(a());
                }
            }
            this.e = apVar2 != null;
            this.f = z;
            this.c = apVar2 == null ? j() : apVar2;
        }
        this.g = this.c.a(false);
        this.d = i();
    }

    private ap j() {
        ap apVar;
        a a2 = l.a().a(a(), c(), h());
        if (a2.b()) {
            apVar = a2.c();
        } else {
            apVar = (ap) a2.a();
        }
        if (a2.d()) {
            new StringBuilder("Using fallback font ").append(apVar.b()).append(" for CID-keyed TrueType font ").append(a());
        }
        return apVar;
    }

    @Override // org.a.c.h.e.o
    public final org.a.c.i.d d() {
        if (this.h == null) {
            this.h = new org.a.c.i.d(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
        }
        return this.h;
    }

    @Override // org.a.c.h.e.o
    public final org.a.b.h.a e() {
        if (this.i == null) {
            this.i = k();
        }
        return this.i;
    }

    private org.a.b.h.a k() {
        org.a.c.h.a.h j;
        if (c() != null && (j = c().j()) != null && (Float.compare(j.c(), 0.0f) != 0 || Float.compare(j.d(), 0.0f) != 0 || Float.compare(j.e(), 0.0f) != 0 || Float.compare(j.g(), 0.0f) != 0)) {
            return new org.a.b.h.a(j.c(), j.d(), j.e(), j.g());
        }
        return this.c.c();
    }

    @Override // org.a.c.h.e.o
    public final int c(int i) {
        org.a.b.c.a l = this.f4552a.l();
        if (!l.a() && l.b()) {
            return l.a(i).codePointAt(0);
        }
        return l.b(i);
    }

    private int e(int i) {
        if (!this.e) {
            if (this.d != null && !this.f) {
                new StringBuilder("Using non-embedded GIDs in font ").append(b());
                return this.d[c(i)];
            }
            String e = this.f4552a.e(i);
            if (e == null) {
                new StringBuilder("Failed to find a character mapping for ").append(i).append(" in ").append(b());
                return c(i);
            }
            e.length();
            return this.g.a(e.codePointAt(0));
        }
        int c = c(i);
        if (this.d != null) {
            if (c < this.d.length) {
                return this.d[c];
            }
            return 0;
        }
        if (c < this.c.w()) {
            return c;
        }
        return 0;
    }

    @Override // org.a.c.h.e.o
    public final float b(int i) {
        int a2 = this.c.a(e(i));
        int x = this.c.x();
        if (x != 1000) {
            a2 = (int) (a2 * (1000.0f / x));
        }
        return a2;
    }

    @Override // org.a.c.h.e.o
    public final byte[] d(int i) {
        int i2 = -1;
        if (this.e) {
            if (this.f4552a.l().c().startsWith("Identity-")) {
                if (this.g != null) {
                    i2 = this.g.a(i);
                }
            } else if (this.f4552a.m() != null) {
                i2 = this.f4552a.m().b(i);
            }
            if (i2 == -1) {
                i2 = 0;
            }
        } else {
            i2 = this.g.a(i);
        }
        if (i2 == 0) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), b()));
        }
        return new byte[]{(byte) (i2 >> 8), (byte) i2};
    }

    @Override // org.a.c.h.e.o
    public final boolean g() {
        return this.e;
    }
}
