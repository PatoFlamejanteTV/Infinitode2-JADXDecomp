package com.a.a.c.e;

import com.a.a.c.f.n;
import com.a.a.c.f.o;
import com.a.a.c.w;
import java.beans.ConstructorProperties;
import java.beans.Transient;

/* loaded from: infinitode-2.jar:com/a/a/c/e/d.class */
public class d extends c {
    @Override // com.a.a.c.e.c
    public final Boolean a(com.a.a.c.f.b bVar) {
        Transient a2 = bVar.a((Class<Transient>) Transient.class);
        if (a2 != null) {
            return Boolean.valueOf(a2.value());
        }
        return null;
    }

    @Override // com.a.a.c.e.c
    public final Boolean b(com.a.a.c.f.b bVar) {
        if (bVar.a(ConstructorProperties.class) != null) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override // com.a.a.c.e.c
    public final w a(n nVar) {
        ConstructorProperties a2;
        o e = nVar.e();
        if (e != null && (a2 = e.a((Class<ConstructorProperties>) ConstructorProperties.class)) != null) {
            String[] value = a2.value();
            int f = nVar.f();
            if (f < value.length) {
                return w.a(value[f]);
            }
            return null;
        }
        return null;
    }
}
