package com.a.a.c;

import com.a.a.c.f.w;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

/* loaded from: infinitode-2.jar:com/a/a/c/u.class */
public class u implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected static final com.a.a.b.q f760a = new com.a.a.b.g.l();

    /* renamed from: b, reason: collision with root package name */
    private y f761b;
    private com.a.a.c.k.l c;
    private com.a.a.c.k.r d;
    private com.a.a.b.f e;
    private a f;
    private b g;

    /* JADX INFO: Access modifiers changed from: protected */
    public u(s sVar, y yVar) {
        this.f761b = yVar;
        this.c = sVar.f757b;
        this.d = sVar.c;
        this.e = sVar.f756a;
        this.f = a.f762a;
        this.g = b.f764a;
    }

    private u(u uVar, y yVar, a aVar, b bVar) {
        this.f761b = yVar;
        this.c = uVar.c;
        this.d = uVar.d;
        this.e = uVar.e;
        this.f = aVar;
        this.g = bVar;
    }

    private u a(a aVar, b bVar) {
        if (this.f == aVar && this.g == bVar) {
            return this;
        }
        return new u(this, this.f761b, aVar, bVar);
    }

    public final u a() {
        return a(this.f761b.c());
    }

    private u a(com.a.a.b.q qVar) {
        return a(this.f.a(qVar), this.g);
    }

    private com.a.a.b.h a(Writer writer) {
        a("w", writer);
        return a(this.e.a(writer));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.a.a.b.c.j, java.io.Writer] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.a.a.c.u] */
    public final String a(Object obj) {
        ?? jVar = new com.a.a.b.c.j(this.e.b());
        try {
            jVar = this;
            jVar.a(jVar.a(jVar), obj);
            return jVar.a();
        } catch (com.a.a.b.m e) {
            throw jVar;
        } catch (IOException e2) {
            throw l.a((IOException) jVar);
        }
    }

    private com.a.a.c.k.l b() {
        return this.c.a(this.f761b, this.d);
    }

    private void a(com.a.a.b.h hVar, Object obj) {
        if (this.f761b.a(z.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            b(hVar, obj);
            return;
        }
        try {
            this.g.a(hVar, obj, b());
            hVar.close();
        } catch (Exception e) {
            com.a.a.c.m.i.a(hVar, e);
        }
    }

    private final void b(com.a.a.b.h hVar, Object obj) {
        Closeable closeable = (Closeable) obj;
        try {
            this.g.a(hVar, obj, b());
            closeable = null;
            closeable.close();
            hVar.close();
        } catch (Exception e) {
            com.a.a.c.m.i.a(hVar, closeable, e);
        }
    }

    private com.a.a.b.h a(com.a.a.b.h hVar) {
        this.f761b.a(hVar);
        this.f.a(hVar);
        return hVar;
    }

    private static void a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/u$a.class */
    public static final class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public static final a f762a = new a(null, null, null, null);

        /* renamed from: b, reason: collision with root package name */
        private com.a.a.b.q f763b;
        private w.a c;
        private com.a.a.b.c.c d;
        private com.a.a.b.r e;

        private a(com.a.a.b.q qVar, w.a aVar, com.a.a.b.c.c cVar, com.a.a.b.r rVar) {
            this.f763b = qVar;
            this.c = aVar;
            this.d = cVar;
            this.e = rVar;
        }

        public final a a(com.a.a.b.q qVar) {
            if (qVar == null) {
                qVar = u.f760a;
            }
            return qVar == this.f763b ? this : new a(qVar, this.c, this.d, this.e);
        }

        public final void a(com.a.a.b.h hVar) {
            com.a.a.b.q qVar = this.f763b;
            if (this.f763b != null) {
                if (qVar == u.f760a) {
                    hVar.a((com.a.a.b.q) null);
                } else {
                    if (qVar instanceof com.a.a.b.g.f) {
                        qVar = (com.a.a.b.q) ((com.a.a.b.g.f) qVar).a();
                    }
                    hVar.a(qVar);
                }
            }
            if (this.d != null) {
                hVar.a(this.d);
            }
            if (this.c != null) {
                hVar.a(this.c);
            }
            if (this.e != null) {
                hVar.a(this.e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/u$b.class */
    public static final class b implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public static final b f764a = new b(null, null, null);

        /* renamed from: b, reason: collision with root package name */
        private final j f765b;
        private final o<Object> c;
        private final com.a.a.c.i.i d;

        private b(j jVar, o<Object> oVar, com.a.a.c.i.i iVar) {
            this.f765b = jVar;
            this.c = oVar;
            this.d = iVar;
        }

        public final void a(com.a.a.b.h hVar, Object obj, com.a.a.c.k.l lVar) {
            if (this.d != null) {
                lVar.a(hVar, obj, this.f765b, this.c, this.d);
                return;
            }
            if (this.c != null) {
                lVar.a(hVar, obj, this.f765b, this.c);
            } else if (this.f765b != null) {
                lVar.a(hVar, obj, this.f765b);
            } else {
                lVar.a(hVar, obj);
            }
        }
    }
}
