package com.a.a.c.i;

import com.a.a.a.af;
import com.a.a.c.i.h;
import com.a.a.c.y;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/i/h.class */
public interface h<T extends h<T>> {
    Class<?> a();

    i a(y yVar, com.a.a.c.j jVar, Collection<b> collection);

    e a(com.a.a.c.f fVar, com.a.a.c.j jVar, Collection<b> collection);

    T a(af.b bVar, g gVar);

    T a(af.a aVar);

    T a(String str);

    T a(Class<?> cls);

    T a(boolean z);

    default T b(Class<?> cls) {
        return a(cls);
    }
}
