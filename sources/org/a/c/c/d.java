package org.a.c.c;

import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/d.class */
final class d extends l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4389a = org.a.a.a.c.a(d.class);

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f4390b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        int i2;
        while (true) {
            int read = inputStream.read();
            int i3 = read;
            if (read == -1) {
                break;
            }
            while (a(i3)) {
                i3 = inputStream.read();
            }
            if (i3 == -1 || b(i3)) {
                break;
            }
            if (f4390b[i3] == -1) {
                new StringBuilder("Invalid hex, int: ").append(i3).append(" char: ").append((char) i3);
            }
            i2 = f4390b[i3] << 4;
            int read2 = inputStream.read();
            if (read2 == -1 || b(read2)) {
                break;
            }
            if (read2 >= 0) {
                if (f4390b[read2] == -1) {
                    new StringBuilder("Invalid hex, int: ").append(read2).append(" char: ").append((char) read2);
                }
                i2 += f4390b[read2];
            }
            outputStream.write(i2);
        }
        outputStream.write(i2);
        outputStream.flush();
        return new k(dVar);
    }

    private static boolean a(int i) {
        return i == 0 || i == 9 || i == 10 || i == 12 || i == 13 || i == 32;
    }

    private static boolean b(int i) {
        return i == 62;
    }

    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                org.a.c.i.c.a((byte) read, outputStream);
            } else {
                outputStream.flush();
                return;
            }
        }
    }
}
