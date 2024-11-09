package com.a.a.b.c;

import com.a.a.b.r;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/b/c/k.class */
public final class k implements r, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final f f120a = f.a();

    /* renamed from: b, reason: collision with root package name */
    private String f121b;
    private char[] c;

    public k(String str) {
        if (str == null) {
            throw new IllegalStateException("Null String illegal for SerializedString");
        }
        this.f121b = str;
    }

    @Override // com.a.a.b.r
    public final String a() {
        return this.f121b;
    }

    @Override // com.a.a.b.r
    public final char[] b() {
        char[] cArr = this.c;
        char[] cArr2 = cArr;
        if (cArr == null) {
            char[] a2 = f120a.a(this.f121b);
            cArr2 = a2;
            this.c = a2;
        }
        return cArr2;
    }

    @Override // com.a.a.b.r
    public final int a(char[] cArr, int i) {
        char[] cArr2 = this.c;
        char[] cArr3 = cArr2;
        if (cArr2 == null) {
            char[] a2 = f120a.a(this.f121b);
            cArr3 = a2;
            this.c = a2;
        }
        int length = cArr3.length;
        if (i + length > cArr.length) {
            return -1;
        }
        System.arraycopy(cArr3, 0, cArr, i, length);
        return length;
    }

    @Override // com.a.a.b.r
    public final int b(char[] cArr, int i) {
        String str = this.f121b;
        int length = str.length();
        if (i + length > cArr.length) {
            return -1;
        }
        str.getChars(0, length, cArr, i);
        return length;
    }

    public final String toString() {
        return this.f121b;
    }

    public final int hashCode() {
        return this.f121b.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.f121b.equals(((k) obj).f121b);
    }
}
