package com.a.a.c;

import com.a.a.a.ak;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/v.class */
public final class v implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public static final v f766a = new v(Boolean.TRUE, null, null, null, null, null, null);

    /* renamed from: b, reason: collision with root package name */
    public static final v f767b = new v(Boolean.FALSE, null, null, null, null, null, null);
    public static final v c = new v(null, null, null, null, null, null, null);
    private Boolean d;
    private String e;
    private Integer f;
    private String g;
    private transient a h;
    private ak i;
    private ak j;

    /* loaded from: infinitode-2.jar:com/a/a/c/v$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final com.a.a.c.f.j f768a;

        /* renamed from: b, reason: collision with root package name */
        public final boolean f769b;

        private a(com.a.a.c.f.j jVar, boolean z) {
            this.f768a = jVar;
            this.f769b = z;
        }

        public static a a(com.a.a.c.f.j jVar) {
            return new a(jVar, true);
        }

        public static a b(com.a.a.c.f.j jVar) {
            return new a(jVar, false);
        }

        public static a c(com.a.a.c.f.j jVar) {
            return new a(jVar, false);
        }
    }

    private v(Boolean bool, String str, Integer num, String str2, a aVar, ak akVar, ak akVar2) {
        this.d = bool;
        this.e = str;
        this.f = num;
        this.g = (str2 == null || str2.isEmpty()) ? null : str2;
        this.h = aVar;
        this.i = akVar;
        this.j = akVar2;
    }

    public static v a(Boolean bool, String str, Integer num, String str2) {
        if (str != null || num != null || str2 != null) {
            return new v(bool, str, num, str2, null, null, null);
        }
        if (bool == null) {
            return c;
        }
        return bool.booleanValue() ? f766a : f767b;
    }

    public final v a(String str) {
        return new v(this.d, str, this.f, this.g, this.h, this.i, this.j);
    }

    public final v a(a aVar) {
        return new v(this.d, this.e, this.f, this.g, aVar, this.i, this.j);
    }

    public final v a(ak akVar, ak akVar2) {
        return new v(this.d, this.e, this.f, this.g, this.h, akVar, akVar2);
    }

    public final boolean a() {
        return this.d != null && this.d.booleanValue();
    }

    public final Integer b() {
        return this.f;
    }

    public final boolean c() {
        return this.f != null;
    }

    public final a d() {
        return this.h;
    }

    public final ak e() {
        return this.i;
    }

    public final ak f() {
        return this.j;
    }
}
