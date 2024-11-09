package org.a.b.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:org/a/b/e/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f4267a = {1, 2, 1};

    /* renamed from: b, reason: collision with root package name */
    private byte[] f4268b;
    private int[] c;

    public a(InputStream inputStream) {
        a(a(inputStream));
    }

    public a(byte[] bArr) {
        a(bArr);
    }

    private void a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        this.f4268b = new byte[bArr.length - 18];
        this.c = new int[f4267a.length];
        int i = 0;
        for (int i2 = 0; i2 < f4267a.length; i2++) {
            if (byteArrayInputStream.read() != 128) {
                throw new IOException("Start marker missing");
            }
            if (byteArrayInputStream.read() != f4267a[i2]) {
                throw new IOException("Incorrect record type");
            }
            int read = byteArrayInputStream.read() + (byteArrayInputStream.read() << 8) + (byteArrayInputStream.read() << 16) + (byteArrayInputStream.read() << 24);
            this.c[i2] = read;
            if (i >= this.f4268b.length) {
                throw new EOFException("attempted to read past EOF");
            }
            int read2 = byteArrayInputStream.read(this.f4268b, i, read);
            if (read2 >= 0) {
                i += read2;
            } else {
                throw new EOFException();
            }
        }
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[65535];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public final byte[] a() {
        return Arrays.copyOfRange(this.f4268b, 0, this.c[0]);
    }

    public final byte[] b() {
        return Arrays.copyOfRange(this.f4268b, this.c[0], this.c[0] + this.c[1]);
    }
}
