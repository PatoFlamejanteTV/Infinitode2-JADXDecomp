package com.d.c.f;

/* loaded from: infinitode-2.jar:com/d/c/f/i.class */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private int f1092a;

    /* renamed from: b, reason: collision with root package name */
    private long f1093b;

    public i() {
        this.f1092a = 1;
        this.f1093b = 0L;
    }

    public i(long j, int i) {
        this.f1092a = 1;
        this.f1093b = 0L;
        this.f1093b = j;
        this.f1092a = i;
    }

    public final void a(long j) {
        this.f1093b = j;
    }

    public final long a() {
        return this.f1093b;
    }

    public final void a(int i) {
        this.f1092a = i;
    }

    public final int b() {
        return this.f1092a;
    }

    public final boolean c() {
        return this.f1092a == 1;
    }

    public final boolean d() {
        return this.f1092a == 2;
    }

    public final boolean e() {
        return this.f1092a == 3;
    }

    public final long b(int i) {
        switch (this.f1092a) {
            case 2:
                return this.f1093b;
            case 3:
                return (i * this.f1093b) / 100;
            default:
                return 0L;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(type=");
        switch (this.f1092a) {
            case 1:
                sb.append("variable");
                break;
            case 2:
                sb.append("fixed");
                break;
            case 3:
                sb.append("percent");
                break;
            default:
                sb.append("unknown");
                break;
        }
        sb.append(", value=");
        sb.append(this.f1093b);
        sb.append(")");
        return sb.toString();
    }
}
