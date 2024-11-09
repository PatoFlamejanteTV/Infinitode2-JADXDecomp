package com.a.a.c;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/w.class */
public final class w implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public static final w f770a = new w("", null);

    /* renamed from: b, reason: collision with root package name */
    public static final w f771b = new w(new String(""), null);
    private String c;
    private String d;
    private com.a.a.b.r e;

    public w(String str) {
        this(str, null);
    }

    private w(String str, String str2) {
        this.c = com.a.a.c.m.i.a(str);
        this.d = str2;
    }

    public static w a(String str) {
        if (str == null || str.isEmpty()) {
            return f770a;
        }
        return new w(com.a.a.b.g.g.f159a.a(str), null);
    }

    public static w a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null && str.isEmpty()) {
            return f770a;
        }
        return new w(com.a.a.b.g.g.f159a.a(str), str2);
    }

    public final w a() {
        if (this.c.isEmpty()) {
            return this;
        }
        String a2 = com.a.a.b.g.g.f159a.a(this.c);
        if (a2 != this.c) {
            return new w(a2, this.d);
        }
        return this;
    }

    public final w b(String str) {
        if (str == null) {
            str = "";
        }
        if (str.equals(this.c)) {
            return this;
        }
        return new w(str, this.d);
    }

    public final String b() {
        return this.c;
    }

    public final com.a.a.b.r a(com.a.a.c.b.q<?> qVar) {
        com.a.a.b.r rVar = this.e;
        com.a.a.b.r rVar2 = rVar;
        if (rVar == null) {
            if (qVar == null) {
                rVar2 = new com.a.a.b.c.k(this.c);
            } else {
                rVar2 = com.a.a.c.b.q.a(this.c);
            }
            this.e = rVar2;
        }
        return rVar2;
    }

    public final boolean c() {
        return !this.c.isEmpty();
    }

    public final boolean c(String str) {
        return this.c.equals(str);
    }

    public final boolean d() {
        return this.d != null;
    }

    public final boolean e() {
        return this.d == null && this.c.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        w wVar = (w) obj;
        if (this.c == null) {
            if (wVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(wVar.c)) {
            return false;
        }
        if (this.d == null) {
            return wVar.d == null;
        }
        return this.d.equals(wVar.d);
    }

    public final int hashCode() {
        if (this.d == null) {
            return this.c.hashCode();
        }
        return this.d.hashCode() ^ this.c.hashCode();
    }

    public final String toString() {
        if (this.d == null) {
            return this.c;
        }
        return "{" + this.d + "}" + this.c;
    }
}
