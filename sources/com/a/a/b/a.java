package com.a.a.b;

import java.io.Serializable;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/b/a.class */
public final class a implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private final transient int[] f83a;

    /* renamed from: b, reason: collision with root package name */
    private final transient char[] f84b;
    private final transient byte[] c;
    private String d;
    private final char e;
    private final int f;
    private final boolean g;
    private final EnumC0000a h;

    /* renamed from: com.a.a.b.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/b/a$a.class */
    public enum EnumC0000a {
        PADDING_FORBIDDEN,
        PADDING_REQUIRED,
        PADDING_ALLOWED
    }

    public a(String str, String str2, boolean z, char c, int i) {
        this.f83a = new int[128];
        this.f84b = new char[64];
        this.c = new byte[64];
        this.d = str;
        this.g = z;
        this.e = c;
        this.f = i;
        int length = str2.length();
        if (length != 64) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        str2.getChars(0, length, this.f84b, 0);
        Arrays.fill(this.f83a, -1);
        for (int i2 = 0; i2 < length; i2++) {
            char c2 = this.f84b[i2];
            this.c[i2] = (byte) c2;
            this.f83a[c2] = i2;
        }
        if (z) {
            this.f83a[c] = -2;
        }
        this.h = z ? EnumC0000a.PADDING_REQUIRED : EnumC0000a.PADDING_FORBIDDEN;
    }

    public a(a aVar, String str, int i) {
        this(aVar, str, aVar.g, aVar.e, Integer.MAX_VALUE);
    }

    public a(a aVar, String str, boolean z, char c, int i) {
        this(aVar, str, z, c, aVar.h, i);
    }

    private a(a aVar, String str, boolean z, char c, EnumC0000a enumC0000a, int i) {
        this.f83a = new int[128];
        this.f84b = new char[64];
        this.c = new byte[64];
        this.d = str;
        byte[] bArr = aVar.c;
        System.arraycopy(bArr, 0, this.c, 0, bArr.length);
        char[] cArr = aVar.f84b;
        System.arraycopy(cArr, 0, this.f84b, 0, cArr.length);
        int[] iArr = aVar.f83a;
        System.arraycopy(iArr, 0, this.f83a, 0, iArr.length);
        this.g = z;
        this.e = c;
        this.f = i;
        this.h = enumC0000a;
    }

    private String e() {
        return this.d;
    }

    public final boolean a() {
        return this.g;
    }

    private boolean f() {
        return this.h == EnumC0000a.PADDING_REQUIRED;
    }

    private boolean g() {
        return this.h != EnumC0000a.PADDING_FORBIDDEN;
    }

    public final boolean a(char c) {
        return c == this.e;
    }

    public final boolean a(int i) {
        return i == this.e;
    }

    public final char b() {
        return this.e;
    }

    public final int c() {
        return this.f;
    }

    public final int b(char c) {
        if (c <= 127) {
            return this.f83a[c];
        }
        return -1;
    }

    public final int b(int i) {
        if (i <= 127) {
            return this.f83a[i];
        }
        return -1;
    }

    public final int a(int i, char[] cArr, int i2) {
        int i3 = i2 + 1;
        cArr[i2] = this.f84b[(i >> 18) & 63];
        int i4 = i3 + 1;
        cArr[i3] = this.f84b[(i >> 12) & 63];
        int i5 = i4 + 1;
        cArr[i4] = this.f84b[(i >> 6) & 63];
        int i6 = i5 + 1;
        cArr[i5] = this.f84b[i & 63];
        return i6;
    }

    private void a(StringBuilder sb, int i) {
        sb.append(this.f84b[(i >> 18) & 63]);
        sb.append(this.f84b[(i >> 12) & 63]);
        sb.append(this.f84b[(i >> 6) & 63]);
        sb.append(this.f84b[i & 63]);
    }

    public final int a(int i, int i2, char[] cArr, int i3) {
        int i4 = i3 + 1;
        cArr[i3] = this.f84b[(i >> 18) & 63];
        int i5 = i4 + 1;
        cArr[i4] = this.f84b[(i >> 12) & 63];
        if (a()) {
            int i6 = i5 + 1;
            cArr[i5] = i2 == 2 ? this.f84b[(i >> 6) & 63] : this.e;
            i5 = i6 + 1;
            cArr[i6] = this.e;
        } else if (i2 == 2) {
            i5++;
            cArr[i5] = this.f84b[(i >> 6) & 63];
        }
        return i5;
    }

    private void a(StringBuilder sb, int i, int i2) {
        sb.append(this.f84b[(i >> 18) & 63]);
        sb.append(this.f84b[(i >> 12) & 63]);
        if (a()) {
            sb.append(i2 == 2 ? this.f84b[(i >> 6) & 63] : this.e);
            sb.append(this.e);
        } else if (i2 == 2) {
            sb.append(this.f84b[(i >> 6) & 63]);
        }
    }

    public final String a(byte[] bArr) {
        return a(bArr, false);
    }

    public final String a(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + (length >> 2) + (length >> 3));
        int c = c() >> 2;
        int i = 0;
        int i2 = length - 3;
        while (i <= i2) {
            int i3 = i;
            int i4 = i + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i3] << 8) | (bArr[i4] & 255)) << 8;
            i = i5 + 1;
            a(sb, i6 | (bArr[i5] & 255));
            c--;
            if (c <= 0) {
                sb.append('\\');
                sb.append('n');
                c = c() >> 2;
            }
        }
        int i7 = length - i;
        if (i7 > 0) {
            int i8 = i;
            int i9 = i + 1;
            int i10 = bArr[i8] << 16;
            if (i7 == 2) {
                i10 |= (bArr[i9] & 255) << 8;
            }
            a(sb, i10, i7);
        }
        return sb.toString();
    }

    public final byte[] a(String str) {
        com.a.a.b.g.c cVar = new com.a.a.b.g.c();
        a(str, cVar);
        return cVar.b();
    }

    public final void a(String str, com.a.a.b.g.c cVar) {
        int i = 0;
        int length = str.length();
        while (i < length) {
            int i2 = i;
            i++;
            char charAt = str.charAt(i2);
            if (charAt > ' ') {
                int b2 = b(charAt);
                if (b2 < 0) {
                    a(charAt, 0, (String) null);
                }
                if (i >= length) {
                    h();
                }
                int i3 = i + 1;
                char charAt2 = str.charAt(i);
                int b3 = b(charAt2);
                if (b3 < 0) {
                    a(charAt2, 1, (String) null);
                }
                int i4 = (b2 << 6) | b3;
                if (i3 >= length) {
                    if (!f()) {
                        cVar.a(i4 >> 4);
                        return;
                    }
                    h();
                }
                int i5 = i3 + 1;
                char charAt3 = str.charAt(i3);
                int b4 = b(charAt3);
                if (b4 >= 0) {
                    int i6 = (i4 << 6) | b4;
                    if (i5 >= length) {
                        if (!f()) {
                            cVar.b(i6 >> 2);
                            return;
                        }
                        h();
                    }
                    i = i5 + 1;
                    char charAt4 = str.charAt(i5);
                    int b5 = b(charAt4);
                    if (b5 < 0) {
                        if (b5 != -2) {
                            a(charAt4, 3, (String) null);
                        }
                        if (!g()) {
                            i();
                        }
                        cVar.b(i6 >> 2);
                    } else {
                        cVar.c((i6 << 6) | b5);
                    }
                } else {
                    if (b4 != -2) {
                        a(charAt3, 2, (String) null);
                    }
                    if (!g()) {
                        i();
                    }
                    if (i5 >= length) {
                        h();
                    }
                    i = i5 + 1;
                    char charAt5 = str.charAt(i5);
                    if (!a(charAt5)) {
                        a(charAt5, 3, "expected padding character '" + b() + "'");
                    }
                    cVar.a(i4 >> 4);
                }
            }
        }
    }

    public final String toString() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return aVar.e == this.e && aVar.f == this.f && aVar.g == this.g && aVar.h == this.h && this.d.equals(aVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    private void a(char c, int i, String str) {
        String str2;
        if (c <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units";
        } else if (a(c)) {
            str2 = "Unexpected padding character ('" + b() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw new IllegalArgumentException(str2);
    }

    private void h() {
        throw new IllegalArgumentException(d());
    }

    private void i() {
        throw new IllegalArgumentException(j());
    }

    private String j() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects no padding at the end while decoding. This Base64Variant might have been incorrectly configured", e());
    }

    public final String d() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects padding (one or more '%c' characters) at the end. This Base64Variant might have been incorrectly configured", e(), Character.valueOf(b()));
    }
}
