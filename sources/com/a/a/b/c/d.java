package com.a.a.b.c;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/b/c/d.class */
public final class d implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static d f109a = new d(false, null);

    /* renamed from: b, reason: collision with root package name */
    private transient Object f110b;
    private int c;
    private int d;
    private boolean e;

    private d(boolean z, Object obj) {
        this(z, obj, -1, -1);
    }

    private d(boolean z, Object obj, int i, int i2) {
        this.e = z;
        this.f110b = obj;
        this.c = -1;
        this.d = -1;
    }

    public static d a() {
        return f109a;
    }

    public static d a(boolean z, Object obj) {
        return new d(z, obj);
    }

    private static d b(boolean z, Object obj) {
        if (obj instanceof d) {
            return (d) obj;
        }
        return new d(false, obj);
    }

    public static d a(Object obj) {
        return b(false, obj);
    }

    public final boolean b() {
        return this.e;
    }

    public final Object c() {
        return this.f110b;
    }

    private int e() {
        return this.c;
    }

    private int f() {
        return this.d;
    }

    public final String d() {
        return a(new StringBuilder(200)).toString();
    }

    private StringBuilder a(StringBuilder sb) {
        Class<?> cls;
        String str;
        Object c = c();
        if (c == null) {
            sb.append("UNKNOWN");
            return sb;
        }
        if (c instanceof Class) {
            cls = (Class) c;
        } else {
            cls = c.getClass();
        }
        Class<?> cls2 = cls;
        String name = cls.getName();
        String str2 = name;
        if (name.startsWith("java.")) {
            str2 = cls2.getSimpleName();
        } else if (c instanceof byte[]) {
            str2 = "byte[]";
        } else if (c instanceof char[]) {
            str2 = "char[]";
        }
        sb.append('(').append(str2).append(')');
        if (b()) {
            String str3 = " chars";
            int[] iArr = {e(), f()};
            if (c instanceof CharSequence) {
                str = a((CharSequence) c, iArr, 500);
            } else if (c instanceof char[]) {
                str = a((char[]) c, iArr, 500);
            } else if (c instanceof byte[]) {
                str = a((byte[]) c, iArr, 500);
                str3 = " bytes";
            } else {
                str = null;
            }
            if (str != null) {
                a(sb, str);
                if (iArr[1] > 500) {
                    sb.append("[truncated ").append(iArr[1] - 500).append(str3).append(']');
                }
            }
        } else if (c instanceof byte[]) {
            int f = f();
            int i = f;
            if (f < 0) {
                i = ((byte[]) c).length;
            }
            sb.append('[').append(i).append(" bytes]");
        }
        return sb;
    }

    private String a(CharSequence charSequence, int[] iArr, int i) {
        a(iArr, charSequence.length());
        int i2 = iArr[0];
        return charSequence.subSequence(i2, i2 + Math.min(iArr[1], i)).toString();
    }

    private String a(char[] cArr, int[] iArr, int i) {
        a(iArr, cArr.length);
        return new String(cArr, iArr[0], Math.min(iArr[1], i));
    }

    private String a(byte[] bArr, int[] iArr, int i) {
        a(iArr, bArr.length);
        return new String(bArr, iArr[0], Math.min(iArr[1], i), Charset.forName("UTF-8"));
    }

    private static void a(int[] iArr, int i) {
        int i2 = iArr[0];
        int i3 = i2;
        if (i2 < 0) {
            i3 = 0;
        } else if (i3 >= i) {
            i3 = i;
        }
        iArr[0] = i3;
        int i4 = iArr[1];
        int i5 = i - i3;
        if (i4 < 0 || i4 > i5) {
            iArr[1] = i5;
        }
    }

    private int a(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (!Character.isISOControl(charAt) || !a(sb, charAt)) {
                sb.append(charAt);
            }
        }
        sb.append('\"');
        return str.length();
    }

    private static boolean a(StringBuilder sb, int i) {
        if (i == 13 || i == 10) {
            return false;
        }
        sb.append('\\');
        sb.append('u');
        sb.append(b.c((i >> 12) & 15));
        sb.append(b.c((i >> 8) & 15));
        sb.append(b.c((i >> 4) & 15));
        sb.append(b.c(i & 15));
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this.c != dVar.c || this.d != dVar.d) {
            return false;
        }
        Object obj2 = dVar.f110b;
        if (this.f110b == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        if ((this.f110b instanceof File) || (this.f110b instanceof URL) || (this.f110b instanceof URI)) {
            return this.f110b.equals(obj2);
        }
        return this.f110b == dVar.f110b;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f110b);
    }
}
