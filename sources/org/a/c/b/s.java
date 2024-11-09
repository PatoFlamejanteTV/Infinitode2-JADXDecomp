package org.a.c.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:org/a/c/b/s.class */
public final class s extends b {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4381a = org.a.a.a.c.a(s.class);

    /* renamed from: b, reason: collision with root package name */
    private static boolean f4382b = Boolean.getBoolean("org.apache.pdfbox.forceParsing");
    private byte[] c;

    public static s a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder(str.trim());
        if (sb.length() % 2 != 0) {
            sb.append('0');
        }
        int length = sb.length();
        for (int i = 0; i < length; i += 2) {
            try {
                int i2 = i;
                byteArrayOutputStream.write(Integer.parseInt(sb.substring(i2, i2 + 2), 16));
            } catch (NumberFormatException e) {
                if (f4382b) {
                    byteArrayOutputStream.write(63);
                } else {
                    throw new IOException("Invalid hex string: " + str, e);
                }
            }
        }
        return new s(byteArrayOutputStream.toByteArray());
    }

    public s(byte[] bArr) {
        a(bArr);
    }

    public s(String str) {
        boolean z = true;
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (v.a(charArray[i])) {
                i++;
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            this.c = v.a(str);
            return;
        }
        byte[] bytes = str.getBytes(org.a.c.i.a.f4652b);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length + 2);
        byteArrayOutputStream.write(254);
        byteArrayOutputStream.write(255);
        try {
            byteArrayOutputStream.write(bytes);
            this.c = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void a(byte[] bArr) {
        this.c = (byte[]) bArr.clone();
    }

    public final boolean a() {
        return false;
    }

    public final String b() {
        if (this.c.length >= 2) {
            if ((this.c[0] & 255) == 254 && (this.c[1] & 255) == 255) {
                return new String(this.c, 2, this.c.length - 2, org.a.c.i.a.f4652b);
            }
            if ((this.c[0] & 255) == 255 && (this.c[1] & 255) == 254) {
                return new String(this.c, 2, this.c.length - 2, org.a.c.i.a.c);
            }
        }
        return v.a(this.c);
    }

    public final byte[] c() {
        return this.c;
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof s) && b().equals(((s) obj).b());
    }

    public final int hashCode() {
        return Arrays.hashCode(this.c);
    }

    public final String toString() {
        return "COSString{" + b() + "}";
    }
}
