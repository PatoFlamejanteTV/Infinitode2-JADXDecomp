package com.a.a.c.k.a;

import com.a.a.c.aa;
import com.a.a.c.k.b.ar;
import com.a.a.c.m.w;
import com.a.a.c.z;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/r.class */
public final class r extends ar {
    public r() {
        super((Class<?>) Object.class);
    }

    public r(Class<?> cls) {
        super(cls);
    }

    @Override // com.a.a.c.k.b.ar, com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        if (aaVar.a(z.FAIL_ON_EMPTY_BEANS)) {
            b(aaVar, obj);
        }
        super.a(obj, hVar, aaVar);
    }

    @Override // com.a.a.c.k.b.ar, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        if (aaVar.a(z.FAIL_ON_EMPTY_BEANS)) {
            b(aaVar, obj);
        }
        super.a(obj, hVar, aaVar, iVar);
    }

    private void b(aa aaVar, Object obj) {
        Class<?> cls = obj.getClass();
        if (w.a(cls)) {
            aaVar.a(a(), String.format("No serializer found for class %s and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS). This appears to be a native image, in which case you may need to configure reflection for the class that is to be serialized", cls.getName()));
        } else {
            aaVar.a(a(), String.format("No serializer found for class %s and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)", cls.getName()));
        }
    }
}
