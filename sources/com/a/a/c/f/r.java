package com.a.a.c.f;

import com.a.a.c.f.t;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/r.class */
public final class r extends t implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final Class<?> f470a = Object.class;

    /* renamed from: b, reason: collision with root package name */
    private static final Class<?> f471b = String.class;
    private static final Class<?> c = com.a.a.c.m.class;
    private static q d = q.a(null, com.a.a.c.l.l.e((Class<?>) String.class), e.a(f471b));
    private static q e = q.a(null, com.a.a.c.l.l.e((Class<?>) Boolean.TYPE), e.a((Class<?>) Boolean.TYPE));
    private static q f = q.a(null, com.a.a.c.l.l.e((Class<?>) Integer.TYPE), e.a((Class<?>) Integer.TYPE));
    private static q g = q.a(null, com.a.a.c.l.l.e((Class<?>) Long.TYPE), e.a((Class<?>) Long.TYPE));
    private static q h = q.a(null, com.a.a.c.l.l.e((Class<?>) Object.class), e.a(f470a));

    @Override // com.a.a.c.f.t
    public final /* synthetic */ com.a.a.c.b a(com.a.a.c.b.q qVar, com.a.a.c.j jVar, t.a aVar) {
        return b((com.a.a.c.b.q<?>) qVar, jVar, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.t
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public q a(com.a.a.c.y yVar, com.a.a.c.j jVar, t.a aVar) {
        q a2 = a(yVar, jVar);
        q qVar = a2;
        if (a2 == null) {
            q b2 = b(yVar, jVar);
            qVar = b2;
            if (b2 == null) {
                qVar = q.b(a((com.a.a.c.b.q<?>) yVar, jVar, aVar, true));
            }
        }
        return qVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.t
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public q b(com.a.a.c.f fVar, com.a.a.c.j jVar, t.a aVar) {
        q a2 = a(fVar, jVar);
        q qVar = a2;
        if (a2 == null) {
            q b2 = b(fVar, jVar);
            qVar = b2;
            if (b2 == null) {
                qVar = q.a(a((com.a.a.c.b.q<?>) fVar, jVar, aVar, false));
            }
        }
        return qVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.t
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public q a(com.a.a.c.f fVar, com.a.a.c.j jVar, t.a aVar, com.a.a.c.b bVar) {
        return q.a(a((com.a.a.c.b.q<?>) fVar, jVar, aVar, bVar, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.t
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public q a(com.a.a.c.f fVar, com.a.a.c.j jVar, t.a aVar) {
        q a2 = a(fVar, jVar);
        q qVar = a2;
        if (a2 == null) {
            q b2 = b(fVar, jVar);
            qVar = b2;
            if (b2 == null) {
                qVar = q.a(a((com.a.a.c.b.q<?>) fVar, jVar, aVar, false));
            }
        }
        return qVar;
    }

    private q b(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, t.a aVar) {
        q a2 = a(qVar, jVar);
        q qVar2 = a2;
        if (a2 == null) {
            qVar2 = q.a(qVar, jVar, c(qVar, jVar, aVar));
        }
        return qVar2;
    }

    private ae a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, t.a aVar, boolean z) {
        a a2;
        d c2 = c(qVar, jVar, aVar);
        if (jVar.j()) {
            a2 = qVar.l().c(qVar, c2);
        } else {
            a2 = qVar.l().a(qVar, c2);
        }
        return a(qVar, c2, jVar, z, a2);
    }

    private ae a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, t.a aVar, com.a.a.c.b bVar, boolean z) {
        d c2 = c(qVar, jVar, aVar);
        return a(qVar, c2, jVar, false, qVar.l().b(qVar, c2));
    }

    private static ae a(com.a.a.c.b.q<?> qVar, d dVar, com.a.a.c.j jVar, boolean z, a aVar) {
        return new ae(qVar, z, jVar, dVar, aVar);
    }

    private static q a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar) {
        Class<?> b2 = jVar.b();
        if (b2.isPrimitive()) {
            if (b2 == Integer.TYPE) {
                return f;
            }
            if (b2 == Long.TYPE) {
                return g;
            }
            if (b2 == Boolean.TYPE) {
                return e;
            }
            return null;
        }
        if (!com.a.a.c.m.i.m(b2)) {
            if (c.isAssignableFrom(b2)) {
                return q.a(qVar, jVar, e.a(b2));
            }
            return null;
        }
        if (b2 == f470a) {
            return h;
        }
        if (b2 == f471b) {
            return d;
        }
        if (b2 == Integer.class) {
            return f;
        }
        if (b2 == Long.class) {
            return g;
        }
        if (b2 == Boolean.class) {
            return e;
        }
        return null;
    }

    private static boolean a(com.a.a.c.j jVar) {
        if (!jVar.n() || jVar.g()) {
            return false;
        }
        Class<?> b2 = jVar.b();
        if (com.a.a.c.m.i.m(b2)) {
            if (Collection.class.isAssignableFrom(b2) || Map.class.isAssignableFrom(b2)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private q b(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar) {
        if (a(jVar)) {
            return q.a(qVar, jVar, c(qVar, jVar, qVar));
        }
        return null;
    }

    private static d c(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, t.a aVar) {
        return e.a(qVar, jVar, aVar);
    }
}
