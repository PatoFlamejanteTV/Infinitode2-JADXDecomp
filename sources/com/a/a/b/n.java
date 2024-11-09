package com.a.a.b;

import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/a/a/b/n.class */
public abstract class n {

    /* renamed from: a, reason: collision with root package name */
    protected int f183a;

    /* renamed from: b, reason: collision with root package name */
    protected int f184b;

    public abstract n a();

    public abstract String g();

    /* JADX INFO: Access modifiers changed from: protected */
    public n() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n(n nVar) {
        this.f183a = nVar.f183a;
        this.f184b = nVar.f184b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n(int i, int i2) {
        this.f183a = i;
        this.f184b = i2;
    }

    public final boolean b() {
        return this.f183a == 1;
    }

    public final boolean c() {
        return this.f183a == 0;
    }

    public final boolean d() {
        return this.f183a == 2;
    }

    public final String e() {
        switch (this.f183a) {
            case 0:
                return "root";
            case 1:
                return "Array";
            case 2:
                return "Object";
            default:
                return TypeDescription.Generic.OfWildcardType.SYMBOL;
        }
    }

    public final int f() {
        return this.f184b + 1;
    }

    private int i() {
        if (this.f184b < 0) {
            return 0;
        }
        return this.f184b;
    }

    public Object h() {
        return null;
    }

    public void a(Object obj) {
    }

    public j a(com.a.a.b.c.d dVar) {
        return j.f174a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        switch (this.f183a) {
            case 0:
                sb.append("/");
                break;
            case 1:
                sb.append('[');
                sb.append(i());
                sb.append(']');
                break;
            default:
                sb.append('{');
                String g = g();
                if (g != null) {
                    sb.append('\"');
                    com.a.a.b.c.b.a(sb, g);
                    sb.append('\"');
                } else {
                    sb.append('?');
                }
                sb.append('}');
                break;
        }
        return sb.toString();
    }
}
