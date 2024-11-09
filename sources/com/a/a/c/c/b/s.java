package com.a.a.c.c.b;

import com.a.a.c.c.x;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/s.class */
public final class s extends x.a {
    public s() {
        super((Class<?>) com.a.a.b.j.class);
    }

    @Override // com.a.a.c.c.x
    public final boolean o() {
        return true;
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.c.v[] a(com.a.a.c.f fVar) {
        com.a.a.c.j b2 = fVar.b(Integer.TYPE);
        com.a.a.c.j b3 = fVar.b(Long.TYPE);
        return new com.a.a.c.c.v[]{a("sourceRef", fVar.b(Object.class), 0), a("byteOffset", b3, 1), a("charOffset", b3, 2), a("lineNr", b2, 3), a("columnNr", b2, 4)};
    }

    private static com.a.a.c.c.m a(String str, com.a.a.c.j jVar, int i) {
        return com.a.a.c.c.m.a(com.a.a.c.w.a(str), jVar, null, null, null, null, i, null, com.a.a.c.v.f766a);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, Object[] objArr) {
        return new com.a.a.b.j(com.a.a.b.c.d.a(objArr[0]), a(objArr[1]), a(objArr[2]), b(objArr[3]), b(objArr[4]));
    }

    private static final long a(Object obj) {
        if (obj == null) {
            return 0L;
        }
        return ((Number) obj).longValue();
    }

    private static final int b(Object obj) {
        if (obj == null) {
            return 0;
        }
        return ((Number) obj).intValue();
    }
}
