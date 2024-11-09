package com.a.a.c.e;

import com.a.a.c.k;
import com.a.a.c.o;
import java.nio.file.Path;

/* loaded from: infinitode-2.jar:com/a/a/c/e/b.class */
public class b extends a {

    /* renamed from: a, reason: collision with root package name */
    private final Class<?> f420a = Path.class;

    @Override // com.a.a.c.e.a
    public final k<?> a(Class<?> cls) {
        if (cls == this.f420a) {
            return new e();
        }
        return null;
    }

    @Override // com.a.a.c.e.a
    public final o<?> b(Class<?> cls) {
        if (this.f420a.isAssignableFrom(cls)) {
            return new f();
        }
        return null;
    }
}
