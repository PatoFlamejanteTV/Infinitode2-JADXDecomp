package com.a.a.c.c.b;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/am.class */
public final class am extends ai<String> {

    /* renamed from: a, reason: collision with root package name */
    public static final am f325a = new am();

    @Override // com.a.a.c.c.b.ai, com.a.a.c.c.b.ae, com.a.a.c.k
    public final /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return e(lVar, gVar);
    }

    public am() {
        super((Class<?>) String.class);
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Textual;
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
            return lVar.w();
        }
        if (lVar.a(com.a.a.b.o.START_ARRAY)) {
            return d(lVar, gVar);
        }
        return a(lVar, gVar, (com.a.a.c.c.s) this);
    }

    private String e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return a(lVar, gVar);
    }
}
