package org.a.c.c;

import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/u.class */
final class u extends l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4413a = org.a.a.a.c.a(u.class);

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        int read;
        byte[] bArr = new byte[128];
        while (true) {
            int read2 = inputStream.read();
            if (read2 == -1 || read2 == 128) {
                break;
            }
            if (read2 <= 127) {
                int i2 = read2 + 1;
                while (true) {
                    int i3 = i2;
                    if (i3 > 0 && (read = inputStream.read(bArr, 0, i3)) != -1) {
                        outputStream.write(bArr, 0, read);
                        i2 = i3 - read;
                    }
                }
            } else {
                int read3 = inputStream.read();
                if (read3 == -1) {
                    break;
                }
                for (int i4 = 0; i4 < 257 - read2; i4++) {
                    outputStream.write(read3);
                }
            }
        }
        return new k(dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
    }
}
