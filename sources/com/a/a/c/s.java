package com.a.a.c;

import com.a.a.b.l;
import com.a.a.c.c.n;
import com.a.a.c.f.am;
import com.a.a.c.f.w;
import com.a.a.c.k.l;
import com.a.a.c.m.ab;
import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/a/a/c/s.class */
public class s extends com.a.a.b.p implements Serializable {
    private static a f = new com.a.a.c.f.x();
    private static com.a.a.c.b.a g = new com.a.a.c.b.a(null, f, null, com.a.a.c.l.o.a(), null, ab.f705a, null, Locale.getDefault(), null, com.a.a.b.b.a(), com.a.a.c.i.a.l.f503a, new w.b());

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.b.f f756a;
    private com.a.a.c.l.o h;
    private com.a.a.c.k.a.d i;
    private com.a.a.c.i.d j;
    private com.a.a.c.b.h k;
    private com.a.a.c.b.d l;
    private y m;

    /* renamed from: b, reason: collision with root package name */
    protected com.a.a.c.k.l f757b;
    protected com.a.a.c.k.r c;
    private f n;
    protected com.a.a.c.c.n d;
    protected final ConcurrentHashMap<j, k<Object>> e;

    public s() {
        this(null, null, null);
    }

    public s(com.a.a.b.f fVar) {
        this(fVar, null, null);
    }

    private s(com.a.a.b.f fVar, com.a.a.c.k.l lVar, com.a.a.c.c.n nVar) {
        this.e = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (fVar == null) {
            this.f756a = new r(this);
        } else {
            this.f756a = fVar;
            if (fVar.a() == null) {
                this.f756a.a((com.a.a.b.p) this);
            }
        }
        this.j = new com.a.a.c.i.a.n();
        com.a.a.c.m.z zVar = new com.a.a.c.m.z();
        this.h = com.a.a.c.l.o.a();
        am amVar = new am(null);
        com.a.a.c.b.a a2 = g.a(b());
        this.k = new com.a.a.c.b.h();
        this.l = new com.a.a.c.b.d();
        this.m = new y(a2, this.j, amVar, zVar, this.k, com.a.a.c.b.l.a());
        this.n = new f(a2, this.j, amVar, zVar, this.k, this.l, com.a.a.c.b.l.a());
        if (false ^ this.m.a(q.SORT_PROPERTIES_ALPHABETICALLY)) {
            a(q.SORT_PROPERTIES_ALPHABETICALLY, false);
        }
        this.f757b = lVar == null ? new l.a() : lVar;
        this.d = nVar == null ? new n.a(com.a.a.c.c.h.f405b) : nVar;
        this.c = com.a.a.c.k.h.f645b;
    }

    private static com.a.a.c.f.t b() {
        return new com.a.a.c.f.r();
    }

    private t a(f fVar, j jVar, Object obj, w.a aVar, com.a.a.c.k.a.d dVar) {
        return new t(this, fVar, jVar, obj, aVar, dVar);
    }

    private u a(y yVar) {
        return new u(this, yVar);
    }

    public final com.a.a.b.l a(Reader reader) {
        a("r", reader);
        return this.n.a(this.f756a.a(reader));
    }

    private y c() {
        return this.m;
    }

    private f d() {
        return this.n;
    }

    private j a(Type type) {
        a("t", type);
        return this.h.a(type);
    }

    @Deprecated
    private s a(q qVar, boolean z) {
        this.m = z ? this.m.a(qVar) : this.m.b(qVar);
        this.n = z ? this.n.a(qVar) : this.n.b(qVar);
        return this;
    }

    public final s a(l.a... aVarArr) {
        for (int i = 0; i <= 0; i++) {
            this.f756a.a(aVarArr[0]);
        }
        return this;
    }

    public final m a(InputStream inputStream) {
        a("in", inputStream);
        return a(this.f756a.a(inputStream));
    }

    @Override // com.a.a.b.p
    public final void a(com.a.a.b.h hVar, Object obj) {
        a("g", hVar);
        y c = c();
        if (c.a(z.INDENT_OUTPUT) && hVar.c() == null) {
            hVar.a(c.a());
        }
        if (c.a(z.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            a(hVar, obj, c);
            return;
        }
        b(c).a(hVar, obj);
        if (c.a(z.FLUSH_AFTER_WRITE_VALUE)) {
            hVar.flush();
        }
    }

    public final u a() {
        return a(c());
    }

    public final t a(Class<?> cls) {
        return a(d(), this.h.a((Type) cls), null, null, this.i);
    }

    private com.a.a.c.k.l b(y yVar) {
        return this.f757b.a(yVar, this.c);
    }

    private final void a(com.a.a.b.h hVar, Object obj, y yVar) {
        Closeable closeable = (Closeable) obj;
        try {
            b(yVar).a(hVar, obj);
            if (yVar.a(z.FLUSH_AFTER_WRITE_VALUE)) {
                hVar.flush();
            }
            closeable.close();
        } catch (Exception e) {
            com.a.a.c.m.i.a((com.a.a.b.h) null, closeable, e);
        }
    }

    private m a(com.a.a.b.l lVar) {
        m mVar;
        Throwable th = null;
        try {
            j a2 = a(m.class);
            f d = d();
            d.a(lVar);
            com.a.a.b.o k = lVar.k();
            com.a.a.b.o oVar = k;
            if (k == null) {
                com.a.a.b.o g2 = lVar.g();
                oVar = g2;
                if (g2 == null) {
                    d.d();
                    m b2 = com.a.a.c.j.l.b();
                    if (lVar != null) {
                        if (0 != 0) {
                            try {
                                lVar.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            lVar.close();
                        }
                    }
                    return b2;
                }
            }
            com.a.a.c.c.n a3 = a(lVar, d);
            if (oVar == com.a.a.b.o.VALUE_NULL) {
                d.d();
                mVar = com.a.a.c.j.l.a();
            } else {
                mVar = (m) a3.a(lVar, a2, a(a3, a2), (Object) null);
            }
            if (d.a(i.FAIL_ON_TRAILING_TOKENS)) {
                a(lVar, a3, a2);
            }
            return mVar;
        } finally {
            if (lVar != null) {
                if (0 != 0) {
                    try {
                        lVar.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    lVar.close();
                }
            }
        }
    }

    private com.a.a.c.c.n a(com.a.a.b.l lVar, f fVar) {
        return this.d.a(fVar, lVar, this.i);
    }

    private static void a(com.a.a.b.l lVar, g gVar, j jVar) {
        com.a.a.b.o g2 = lVar.g();
        if (g2 != null) {
            g.a(com.a.a.c.m.i.a(jVar), lVar, g2);
        }
    }

    private k<Object> a(g gVar, j jVar) {
        k<Object> kVar = this.e.get(jVar);
        if (kVar != null) {
            return kVar;
        }
        k<Object> b2 = gVar.b(jVar);
        if (b2 != null) {
            this.e.put(jVar, b2);
            return b2;
        }
        return (k) gVar.a(jVar, "Cannot find a deserializer for type " + jVar);
    }

    private static void a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
        }
    }
}
