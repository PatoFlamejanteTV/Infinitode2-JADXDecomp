package com.a.a.c.i;

import com.a.a.a.af;
import com.a.a.b.f.a;
import com.a.a.b.g.q;
import com.a.a.b.o;

/* loaded from: infinitode-2.jar:com/a/a/c/i/i.class */
public abstract class i {
    public abstract i a(com.a.a.c.c cVar);

    public abstract af.a a();

    public abstract String b();

    public abstract com.a.a.b.f.a a(com.a.a.b.h hVar, com.a.a.b.f.a aVar);

    public abstract com.a.a.b.f.a b(com.a.a.b.h hVar, com.a.a.b.f.a aVar);

    public final com.a.a.b.f.a a(Object obj, o oVar) {
        com.a.a.b.f.a aVar = new com.a.a.b.f.a(obj, oVar);
        switch (j.f521a[a().ordinal()]) {
            case 1:
                aVar.e = a.EnumC0003a.PAYLOAD_PROPERTY;
                aVar.d = b();
                break;
            case 2:
                aVar.e = a.EnumC0003a.PARENT_PROPERTY;
                aVar.d = b();
                break;
            case 3:
                aVar.e = a.EnumC0003a.METADATA_PROPERTY;
                aVar.d = b();
                break;
            case 4:
                aVar.e = a.EnumC0003a.WRAPPER_ARRAY;
                break;
            case 5:
                aVar.e = a.EnumC0003a.WRAPPER_OBJECT;
                break;
            default:
                q.a();
                break;
        }
        return aVar;
    }

    public final com.a.a.b.f.a a(Object obj, o oVar, Object obj2) {
        com.a.a.b.f.a a2 = a(obj, oVar);
        a2.c = obj2;
        return a2;
    }

    public final com.a.a.b.f.a a(Object obj, Class<?> cls, o oVar) {
        com.a.a.b.f.a a2 = a(obj, oVar);
        a2.f145b = cls;
        return a2;
    }
}
