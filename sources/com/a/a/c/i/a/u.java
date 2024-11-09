package com.a.a.c.i.a;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/u.class */
public abstract class u extends com.a.a.c.i.i {

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.i.g f515b;
    protected final com.a.a.c.c c;

    /* JADX INFO: Access modifiers changed from: protected */
    public u(com.a.a.c.i.g gVar, com.a.a.c.c cVar) {
        this.f515b = gVar;
        this.c = cVar;
    }

    @Override // com.a.a.c.i.i
    public String b() {
        return null;
    }

    @Override // com.a.a.c.i.i
    public final com.a.a.b.f.a a(com.a.a.b.h hVar, com.a.a.b.f.a aVar) {
        a(aVar);
        if (aVar.c == null) {
            return null;
        }
        return hVar.a(aVar);
    }

    @Override // com.a.a.c.i.i
    public final com.a.a.b.f.a b(com.a.a.b.h hVar, com.a.a.b.f.a aVar) {
        if (aVar == null) {
            return null;
        }
        return hVar.b(aVar);
    }

    private void a(com.a.a.b.f.a aVar) {
        String a2;
        if (aVar.c == null) {
            Object obj = aVar.f144a;
            Class<?> cls = aVar.f145b;
            if (cls == null) {
                a2 = a(obj);
            } else {
                a2 = a(obj, cls);
            }
            aVar.c = a2;
        }
    }

    private String a(Object obj) {
        return this.f515b.a(obj);
    }

    private String a(Object obj, Class<?> cls) {
        return this.f515b.a(obj, cls);
    }
}
