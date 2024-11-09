package org.a.c.h.e;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.a.b.f.al;
import org.a.b.f.ap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/ac.class */
public class ac extends u {
    private static final org.a.a.a.a c = org.a.a.a.c.a(ac.class);
    private final o d;
    private org.a.b.c.a e;
    private org.a.b.c.a f;
    private boolean g;
    private boolean h;
    private r i;
    private final Set<Integer> j;
    private ap k;

    public static ac a(org.a.c.h.b bVar, File file) {
        return new ac(bVar, new al().b(file), true, true, false);
    }

    public static ac a(org.a.c.h.b bVar, InputStream inputStream, boolean z) {
        return new ac(bVar, new al().b(inputStream), z, true, false);
    }

    public static ac a(org.a.c.h.b bVar, ap apVar, boolean z) {
        return new ac(bVar, apVar, z, false, false);
    }

    public ac(org.a.c.b.d dVar) {
        super(dVar);
        this.j = new HashSet();
        org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.aw);
        if (!(a2 instanceof org.a.c.b.a)) {
            throw new IOException("Missing descendant font array");
        }
        org.a.c.b.a aVar = (org.a.c.b.a) a2;
        if (aVar.b() == 0) {
            throw new IOException("Descendant font array is empty");
        }
        org.a.c.b.b a3 = aVar.a(0);
        if (!(a3 instanceof org.a.c.b.d)) {
            throw new IOException("Missing descendant font dictionary");
        }
        this.d = w.a((org.a.c.b.d) a3, this);
        n();
        o();
    }

    private ac(org.a.c.h.b bVar, ap apVar, boolean z, boolean z2, boolean z3) {
        this.j = new HashSet();
        this.i = new r(bVar, this.f4561b, apVar, z, this, false);
        this.d = this.i.a();
        n();
        o();
        if (z2) {
            if (z) {
                this.k = apVar;
                bVar.a(apVar);
            } else {
                apVar.close();
            }
        }
    }

    @Override // org.a.c.h.e.u
    public final void f(int i) {
        if (!k()) {
            throw new IllegalStateException("This font was created with subsetting disabled");
        }
        this.i.a(i);
    }

    @Override // org.a.c.h.e.u
    public final void j() {
        if (!k()) {
            throw new IllegalStateException("This font was created with subsetting disabled");
        }
        this.i.b();
        if (this.k != null) {
            this.k.close();
            this.k = null;
        }
    }

    @Override // org.a.c.h.e.u
    public final boolean k() {
        return this.i != null && this.i.c();
    }

    private void n() {
        org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.aR);
        if (a2 instanceof org.a.c.b.j) {
            this.e = c.a(((org.a.c.b.j) a2).a());
            if (this.e != null) {
                this.g = true;
            } else {
                throw new IOException("Missing required CMap");
            }
        } else if (a2 != null) {
            this.e = a(a2);
            if (this.e == null) {
                throw new IOException("Missing required CMap");
            }
            if (!this.e.a()) {
                new StringBuilder("Invalid Encoding CMap in font ").append(d());
            }
        }
        t h = this.d.h();
        if (h != null) {
            this.h = "Adobe".equals(h.a()) && ("GB1".equals(h.b()) || "CNS1".equals(h.b()) || "Japan1".equals(h.b()) || "Korea1".equals(h.b()));
        }
    }

    private void o() {
        org.a.c.b.j b2 = this.f4561b.b(org.a.c.b.j.aR);
        if ((this.g && b2 != org.a.c.b.j.bC && b2 != org.a.c.b.j.bD) || this.h) {
            String str = null;
            if (this.h) {
                str = this.d.h().a() + "-" + this.d.h().b() + "-" + this.d.h().c();
            } else if (b2 != null) {
                str = b2.a();
            }
            if (str != null) {
                org.a.b.c.a a2 = c.a(str);
                this.f = c.a(a2.d() + "-" + a2.e() + "-UCS2");
            }
        }
    }

    private String p() {
        return this.f4561b.g(org.a.c.b.j.v);
    }

    private o q() {
        return this.d;
    }

    public final org.a.b.c.a l() {
        return this.e;
    }

    public final org.a.b.c.a m() {
        return this.f;
    }

    @Override // org.a.c.h.e.u
    public final v b() {
        return this.d.c();
    }

    @Override // org.a.c.h.e.u
    public final org.a.c.i.d h() {
        return this.d.d();
    }

    @Override // org.a.c.h.e.u
    protected final byte[] d(int i) {
        return this.d.d(i);
    }

    @Override // org.a.c.h.e.u
    public final float a(int i) {
        return this.d.a(i);
    }

    @Override // org.a.c.h.e.u
    protected final float b(int i) {
        throw new UnsupportedOperationException("not suppported");
    }

    @Override // org.a.c.h.e.u
    public final float c(int i) {
        return this.d.b(i);
    }

    @Override // org.a.c.h.e.u
    public final boolean c() {
        return this.d.g();
    }

    @Override // org.a.c.h.e.u
    public final String e(int i) {
        String e = super.e(i);
        if (e != null) {
            return e;
        }
        if ((this.g || this.h) && this.f != null) {
            return this.f.a(g(i));
        }
        if (c.c() && !this.j.contains(Integer.valueOf(i))) {
            new StringBuilder("No Unicode mapping for ").append("CID+" + g(i)).append(" (").append(i).append(") in font ").append(d());
            this.j.add(Integer.valueOf(i));
            return null;
        }
        return null;
    }

    @Override // org.a.c.h.e.u
    public final String d() {
        return p();
    }

    @Override // org.a.c.h.e.u
    public final org.a.b.h.a e() {
        return this.d.e();
    }

    @Override // org.a.c.h.e.u
    public final int a(InputStream inputStream) {
        return this.e.a(inputStream);
    }

    private int g(int i) {
        return this.d.c(i);
    }

    @Override // org.a.c.h.e.u
    public final boolean i() {
        return false;
    }

    @Override // org.a.c.h.e.u
    public String toString() {
        String str = null;
        if (q() != null) {
            str = q().getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "/" + str + ", PostScript name: " + p();
    }
}
