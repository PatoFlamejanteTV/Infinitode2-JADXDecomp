package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/Handle.class */
public final class Handle {

    /* renamed from: a, reason: collision with root package name */
    final int f1448a;

    /* renamed from: b, reason: collision with root package name */
    final String f1449b;
    final String c;
    final String d;
    final boolean e;

    public Handle(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, i == 9);
    }

    public Handle(int i, String str, String str2, String str3, boolean z) {
        this.f1448a = i;
        this.f1449b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
    }

    public final int getTag() {
        return this.f1448a;
    }

    public final String getOwner() {
        return this.f1449b;
    }

    public final String getName() {
        return this.c;
    }

    public final String getDesc() {
        return this.d;
    }

    public final boolean isInterface() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Handle)) {
            return false;
        }
        Handle handle = (Handle) obj;
        return this.f1448a == handle.f1448a && this.e == handle.e && this.f1449b.equals(handle.f1449b) && this.c.equals(handle.c) && this.d.equals(handle.d);
    }

    public final int hashCode() {
        return this.f1448a + (this.e ? 64 : 0) + (this.f1449b.hashCode() * this.c.hashCode() * this.d.hashCode());
    }

    public final String toString() {
        return new StringBuffer().append(this.f1449b).append('.').append(this.c).append(this.d).append(" (").append(this.f1448a).append(this.e ? " itf" : "").append(')').toString();
    }
}
