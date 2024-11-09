package com.a.a.b.d;

import com.a.a.b.l;
import java.util.HashSet;

/* loaded from: infinitode-2.jar:com/a/a/b/d/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private Object f126a;

    /* renamed from: b, reason: collision with root package name */
    private String f127b;
    private String c;
    private HashSet<String> d;

    private b(Object obj) {
        this.f126a = obj;
    }

    public static b a(l lVar) {
        return new b(lVar);
    }

    public static b a(com.a.a.b.h hVar) {
        return new b(hVar);
    }

    public final b a() {
        return new b(this.f126a);
    }

    public final void b() {
        this.f127b = null;
        this.c = null;
        this.d = null;
    }

    public final Object c() {
        return this.f126a;
    }

    public final boolean a(String str) {
        if (this.f127b == null) {
            this.f127b = str;
            return false;
        }
        if (str.equals(this.f127b)) {
            return true;
        }
        if (this.c == null) {
            this.c = str;
            return false;
        }
        if (str.equals(this.c)) {
            return true;
        }
        if (this.d == null) {
            this.d = new HashSet<>(16);
            this.d.add(this.f127b);
            this.d.add(this.c);
        }
        return !this.d.add(str);
    }
}
