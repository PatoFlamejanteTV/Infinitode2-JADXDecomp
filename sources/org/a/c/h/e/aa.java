package org.a.c.h.e;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:org/a/c/h/e/aa.class */
public abstract class aa extends u {
    private static final org.a.a.a.a e = org.a.a.a.c.a(aa.class);
    protected org.a.c.h.e.a.c c;
    protected org.a.c.h.e.a.d d;
    private Boolean f;
    private final Set<Integer> g;

    protected abstract org.a.c.h.e.a.c m();

    aa() {
        this.g = new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(String str) {
        super(str);
        this.g = new HashSet();
        if ("ZapfDingbats".equals(str)) {
            this.d = org.a.c.h.e.a.d.b();
        } else {
            this.d = org.a.c.h.e.a.d.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(org.a.c.b.d dVar) {
        super(dVar);
        this.g = new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l() {
        org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.aR);
        if (a2 != null) {
            if (a2 instanceof org.a.c.b.j) {
                org.a.c.b.j jVar = (org.a.c.b.j) a2;
                this.c = org.a.c.h.e.a.c.a(jVar);
                if (this.c == null) {
                    new StringBuilder("Unknown encoding: ").append(jVar.a());
                    this.c = m();
                }
            } else if (a2 instanceof org.a.c.b.d) {
                org.a.c.b.d dVar = (org.a.c.b.d) a2;
                org.a.c.h.e.a.c cVar = null;
                Boolean r = r();
                Boolean bool = r;
                boolean z = r != null && bool.booleanValue();
                org.a.c.b.j b2 = dVar.b(org.a.c.b.j.u);
                if (!((b2 == null || org.a.c.h.e.a.c.a(b2) == null) ? false : true) && z) {
                    cVar = m();
                }
                if (bool == null) {
                    bool = Boolean.FALSE;
                }
                this.c = new org.a.c.h.e.a.b(dVar, !bool.booleanValue(), cVar);
            }
        } else {
            this.c = m();
        }
        if ("ZapfDingbats".equals(ah.c(d()))) {
            this.d = org.a.c.h.e.a.d.b();
        } else {
            this.d = org.a.c.h.e.a.d.a();
        }
    }

    public final org.a.c.h.e.a.c n() {
        return this.c;
    }

    public final org.a.c.h.e.a.d o() {
        return this.d;
    }

    public final boolean p() {
        if (this.f == null) {
            Boolean q = q();
            if (q != null) {
                this.f = q;
            } else {
                this.f = Boolean.TRUE;
            }
        }
        return this.f.booleanValue();
    }

    protected Boolean q() {
        Boolean r = r();
        if (r != null) {
            return r;
        }
        if (i()) {
            String c = ah.c(d());
            return Boolean.valueOf(c.equals("Symbol") || c.equals("ZapfDingbats"));
        }
        if (this.c == null) {
            if (!(this instanceof ab)) {
                throw new IllegalStateException("PDFBox bug: encoding should not be null!");
            }
            return Boolean.TRUE;
        }
        if ((this.c instanceof org.a.c.h.e.a.k) || (this.c instanceof org.a.c.h.e.a.g) || (this.c instanceof org.a.c.h.e.a.h)) {
            return Boolean.FALSE;
        }
        if (this.c instanceof org.a.c.h.e.a.b) {
            for (String str : ((org.a.c.h.e.a.b) this.c).c().values()) {
                if (!".notdef".equals(str) && (!org.a.c.h.e.a.k.c.a(str) || !org.a.c.h.e.a.g.d.a(str) || !org.a.c.h.e.a.h.c.a(str))) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Boolean r() {
        if (b() != null) {
            return Boolean.valueOf(b().c());
        }
        return null;
    }

    @Override // org.a.c.h.e.u
    public final String e(int i) {
        return a(i, org.a.c.h.e.a.d.a());
    }

    @Override // org.a.c.h.e.u
    public final String a(int i, org.a.c.h.e.a.d dVar) {
        org.a.c.h.e.a.d dVar2;
        if (this.d == org.a.c.h.e.a.d.a()) {
            dVar2 = dVar;
        } else {
            dVar2 = this.d;
        }
        String e2 = super.e(i);
        if (e2 != null) {
            return e2;
        }
        String str = null;
        if (this.c != null) {
            str = this.c.a(i);
            String a2 = dVar2.a(str);
            if (a2 != null) {
                return a2;
            }
        }
        if (e.c() && !this.g.contains(Integer.valueOf(i))) {
            this.g.add(Integer.valueOf(i));
            if (str != null) {
                new StringBuilder("No Unicode mapping for ").append(str).append(" (").append(i).append(") in font ").append(d());
                return null;
            }
            new StringBuilder("No Unicode mapping for character code ").append(i).append(" in font ").append(d());
            return null;
        }
        return null;
    }

    @Override // org.a.c.h.e.u
    protected final float b(int i) {
        if (a() != null) {
            String a2 = n().a(i);
            if (".notdef".equals(a2)) {
                return 250.0f;
            }
            return a().a(a2);
        }
        throw new IllegalStateException("No AFM");
    }

    @Override // org.a.c.h.e.u
    public final boolean i() {
        if (n() instanceof org.a.c.h.e.a.b) {
            org.a.c.h.e.a.b bVar = (org.a.c.h.e.a.b) n();
            if (bVar.c().size() > 0) {
                org.a.c.h.e.a.c b2 = bVar.b();
                for (Map.Entry<Integer, String> entry : bVar.c().entrySet()) {
                    if (!entry.getValue().equals(b2.a(entry.getKey().intValue()))) {
                        return false;
                    }
                }
            }
        }
        return super.i();
    }

    @Override // org.a.c.h.e.u
    public final void f(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.a.c.h.e.u
    public final void j() {
        throw new UnsupportedOperationException();
    }

    @Override // org.a.c.h.e.u
    public final boolean k() {
        return false;
    }
}
