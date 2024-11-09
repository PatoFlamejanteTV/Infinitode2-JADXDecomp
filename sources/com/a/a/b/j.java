package com.a.a.b;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/b/j.class */
public class j implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public static final j f174a = new j(com.a.a.b.c.d.a(), -1, -1, -1, -1);

    /* renamed from: b, reason: collision with root package name */
    private long f175b;
    private long c;
    private int d;
    private int e;
    private com.a.a.b.c.d f;
    private transient String g;

    public j(com.a.a.b.c.d dVar, long j, int i, int i2) {
        this(dVar, -1L, -1L, i, i2);
    }

    public j(com.a.a.b.c.d dVar, long j, long j2, int i, int i2) {
        this.f = dVar == null ? com.a.a.b.c.d.a() : dVar;
        this.f175b = j;
        this.c = j2;
        this.d = i;
        this.e = i2;
    }

    private String a() {
        if (this.g == null) {
            this.g = this.f.d();
        }
        return this.g;
    }

    private StringBuilder a(StringBuilder sb) {
        if (this.f.b()) {
            sb.append("line: ");
            if (this.d >= 0) {
                sb.append(this.d);
            } else {
                sb.append("UNKNOWN");
            }
            sb.append(", column: ");
            if (this.e >= 0) {
                sb.append(this.e);
            } else {
                sb.append("UNKNOWN");
            }
        } else if (this.d > 0) {
            sb.append("line: ").append(this.d);
            if (this.e > 0) {
                sb.append(", column: ");
                sb.append(this.e);
            }
        } else {
            sb.append("byte offset: #");
            if (this.f175b >= 0) {
                sb.append(this.f175b);
            } else {
                sb.append("UNKNOWN");
            }
        }
        return sb;
    }

    public int hashCode() {
        return ((((this.f == null ? 1 : 2) ^ this.d) + this.e) ^ ((int) this.c)) + ((int) this.f175b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (this.f == null) {
            if (jVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(jVar.f)) {
            return false;
        }
        return this.d == jVar.d && this.e == jVar.e && this.c == jVar.c && this.f175b == jVar.f175b;
    }

    public String toString() {
        String a2 = a();
        return a(new StringBuilder(40 + a2.length()).append("[Source: ").append(a2).append("; ")).append(']').toString();
    }
}
