package com.a.a.c.i;

import java.io.Serializable;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/i/b.class */
public final class b implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private Class<?> f516a;

    /* renamed from: b, reason: collision with root package name */
    private int f517b;
    private String c;

    public b(Class<?> cls) {
        this(cls, null);
    }

    public b(Class<?> cls, String str) {
        this.f516a = cls;
        this.f517b = cls.getName().hashCode() + (str == null ? 0 : str.hashCode());
        a(str);
    }

    public final Class<?> a() {
        return this.f516a;
    }

    public final String b() {
        return this.c;
    }

    private void a(String str) {
        this.c = (str == null || str.isEmpty()) ? null : str;
    }

    public final boolean c() {
        return this.c != null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f516a == bVar.f516a && Objects.equals(this.c, bVar.c);
    }

    public final int hashCode() {
        return this.f517b;
    }

    public final String toString() {
        return "[NamedType, class " + this.f516a.getName() + ", name: " + (this.c == null ? "null" : "'" + this.c + "'") + "]";
    }
}
