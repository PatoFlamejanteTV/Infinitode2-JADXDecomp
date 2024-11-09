package com.a.a.c.c.a;

import com.a.a.c.c.v;
import java.lang.reflect.Constructor;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/j.class */
public final class j extends v.a {
    private transient Constructor<?> h;

    public j(com.a.a.c.c.v vVar, Constructor<?> constructor) {
        super(vVar);
        this.h = constructor;
    }

    @Override // com.a.a.c.c.v.a
    protected final com.a.a.c.c.v a(com.a.a.c.c.v vVar) {
        if (vVar == this.g) {
            return this;
        }
        return new j(vVar, this.h);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.a.a.c.i.e] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Object obj2;
        if (lVar.k() == com.a.a.b.o.VALUE_NULL) {
            obj2 = this.c.a(gVar);
        } else {
            ?? r0 = this.d;
            if (r0 != 0) {
                obj2 = this.c.a(lVar, gVar, this.d);
            } else {
                try {
                    r0 = this.h.newInstance(obj);
                    obj2 = r0;
                } catch (Exception e) {
                    com.a.a.c.m.i.a((Throwable) r0, String.format("Failed to instantiate class %s, problem: %s", this.h.getDeclaringClass().getName(), e.getMessage()));
                    obj2 = null;
                }
                this.c.a(lVar, gVar, (com.a.a.c.g) obj2);
            }
        }
        a(obj, obj2);
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return b(obj, a(lVar, gVar));
    }
}
