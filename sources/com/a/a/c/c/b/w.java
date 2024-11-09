package com.a.a.c.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/w.class */
public final class w extends ae<Object> {

    /* renamed from: a, reason: collision with root package name */
    public static final w f370a = new w();

    public w() {
        super((Class<?>) Object.class);
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.FALSE;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (!lVar.a(com.a.a.b.o.FIELD_NAME)) {
            lVar.j();
            return null;
        }
        while (true) {
            com.a.a.b.o g = lVar.g();
            if (g != null && g != com.a.a.b.o.END_OBJECT) {
                lVar.j();
            } else {
                return null;
            }
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        switch (lVar.l()) {
            case 1:
            case 3:
            case 5:
                return eVar.d(lVar, gVar);
            case 2:
            case 4:
            default:
                return null;
        }
    }
}
