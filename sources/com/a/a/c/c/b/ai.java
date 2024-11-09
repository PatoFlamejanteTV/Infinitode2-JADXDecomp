package com.a.a.c.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ai.class */
public abstract class ai<T> extends ae<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public ai(Class<?> cls) {
        super(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ai(ai<?> aiVar) {
        super(aiVar);
    }

    @Override // com.a.a.c.k
    public com.a.a.c.l.f b() {
        return com.a.a.c.l.f.OtherScalar;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.FALSE;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.CONSTANT;
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.c(lVar, gVar);
    }

    @Override // com.a.a.c.k
    public T a(com.a.a.b.l lVar, com.a.a.c.g gVar, T t) {
        gVar.a(this);
        return a(lVar, gVar);
    }
}
