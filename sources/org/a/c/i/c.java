package org.a.c.i;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/i/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f4655a;

    /* renamed from: b, reason: collision with root package name */
    private static final char[] f4656b;

    static {
        org.a.a.a.c.a(c.class);
        f4655a = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
        f4656b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    private c() {
    }

    public static char[] a(short s) {
        return new char[]{f4656b[(s >> 12) & 15], f4656b[(s >> 8) & 15], f4656b[(s >> 4) & 15], f4656b[s & 15]};
    }

    public static char[] a(String str) {
        char[] cArr = new char[str.length() << 2];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            int i3 = i;
            int i4 = i + 1;
            cArr[i3] = f4656b[(charAt >> '\f') & 15];
            int i5 = i4 + 1;
            cArr[i4] = f4656b[(charAt >> '\b') & 15];
            int i6 = i5 + 1;
            cArr[i5] = f4656b[(charAt >> 4) & 15];
            i = i6 + 1;
            cArr[i6] = f4656b[charAt & 15];
        }
        return cArr;
    }

    public static void a(byte b2, OutputStream outputStream) {
        outputStream.write(f4655a[a(b2)]);
        outputStream.write(f4655a[b(b2)]);
    }

    public static void a(byte[] bArr, OutputStream outputStream) {
        for (byte b2 : bArr) {
            a(b2, outputStream);
        }
    }

    private static int a(byte b2) {
        return (b2 & 240) >> 4;
    }

    private static int b(byte b2) {
        return b2 & 15;
    }
}
