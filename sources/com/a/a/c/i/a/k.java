package com.a.a.c.i.a;

import java.util.EnumMap;
import java.util.EnumSet;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/k.class */
public class k extends s {
    private com.a.a.c.i.c c;

    public k(com.a.a.c.j jVar, com.a.a.c.l.o oVar, com.a.a.c.i.c cVar) {
        super(jVar, oVar);
        this.c = cVar;
    }

    public static k a(com.a.a.c.j jVar, com.a.a.c.b.q<?> qVar, com.a.a.c.i.c cVar) {
        return new k(jVar, qVar.p(), cVar);
    }

    @Override // com.a.a.c.i.g
    public String a(Object obj) {
        return a(obj, obj.getClass(), this.f513a);
    }

    @Override // com.a.a.c.i.g
    public final String a(Object obj, Class<?> cls) {
        return a(obj, cls, this.f513a);
    }

    @Override // com.a.a.c.i.a.s, com.a.a.c.i.g
    public final com.a.a.c.j a(com.a.a.c.d dVar, String str) {
        return a(str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public com.a.a.c.j a(String str, com.a.a.c.d dVar) {
        com.a.a.c.j a2 = dVar.a(this.f514b, str, this.c);
        if (a2 == null && (dVar instanceof com.a.a.c.g)) {
            return ((com.a.a.c.g) dVar).a(this.f514b, str, this, "no such class found");
        }
        return a2;
    }

    private String a(Object obj, Class<?> cls, com.a.a.c.l.o oVar) {
        if (com.a.a.c.m.i.k(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        String str = name;
        if (name.startsWith("java.util.")) {
            if (obj instanceof EnumSet) {
                str = oVar.a(EnumSet.class, com.a.a.c.m.i.a((EnumSet<?>) obj)).G();
            } else if (obj instanceof EnumMap) {
                str = oVar.a(EnumMap.class, com.a.a.c.m.i.a((EnumMap<?, ?>) obj), Object.class).G();
            }
        } else if (str.indexOf(36) >= 0 && com.a.a.c.m.i.b(cls) != null && com.a.a.c.m.i.b(this.f514b.b()) == null) {
            str = this.f514b.b().getName();
        }
        return str;
    }

    @Override // com.a.a.c.i.a.s, com.a.a.c.i.g
    public final String b() {
        return "class name used as type id";
    }
}
