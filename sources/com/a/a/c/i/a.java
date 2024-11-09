package com.a.a.c.i;

import com.a.a.c.b.q;
import com.a.a.c.i.c;
import java.io.Closeable;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a.class */
public final class a extends c implements Serializable {
    @Override // com.a.a.c.i.c
    public final c.b a(q<?> qVar, com.a.a.c.j jVar) {
        if (a(jVar)) {
            return c.b.DENIED;
        }
        return c.b.INDETERMINATE;
    }

    @Override // com.a.a.c.i.c
    public final c.b a() {
        return c.b.INDETERMINATE;
    }

    @Override // com.a.a.c.i.c
    public final c.b a(q<?> qVar, com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        return c.b.ALLOWED;
    }

    private static boolean a(com.a.a.c.j jVar) {
        return C0011a.f499a.a(jVar.b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.a.a.c.i.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/i/a$a.class */
    public static final class C0011a {

        /* renamed from: a, reason: collision with root package name */
        public static final C0011a f499a = new C0011a();

        /* renamed from: b, reason: collision with root package name */
        private final Set<String> f500b = new HashSet();

        private C0011a() {
            this.f500b.add(Object.class.getName());
            this.f500b.add(Closeable.class.getName());
            this.f500b.add(Serializable.class.getName());
            this.f500b.add(AutoCloseable.class.getName());
            this.f500b.add(Cloneable.class.getName());
            this.f500b.add("java.util.logging.Handler");
            this.f500b.add("javax.naming.Referenceable");
            this.f500b.add("javax.sql.DataSource");
        }

        public final boolean a(Class<?> cls) {
            return this.f500b.contains(cls.getName());
        }
    }
}
