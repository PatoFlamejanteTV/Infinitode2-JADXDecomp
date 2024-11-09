package org.a.c.c;

import com.a.a.a.am;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/c/g.class */
public final class g extends l {
    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        int max;
        long j;
        int i2;
        org.a.c.b.d a2 = a(dVar, i);
        int b2 = a2.b(org.a.c.b.j.ad, 1728);
        int b3 = a2.b(org.a.c.b.j.dk, 0);
        int a3 = dVar.a(org.a.c.b.j.bx, org.a.c.b.j.bw, 0);
        if (b3 > 0 && a3 > 0) {
            max = a3;
        } else {
            max = Math.max(b3, a3);
        }
        int b4 = a2.b(org.a.c.b.j.bP, 0);
        boolean b5 = a2.b(org.a.c.b.j.aQ, false);
        byte[] bArr = new byte[((b2 + 7) / 8) * max];
        if (b4 == 0) {
            j = b5 ? 8L : 0L;
            i2 = 2;
        } else if (b4 > 0) {
            j = (b5 ? 8L : 0L) | 1;
            i2 = 3;
        } else {
            j = b5 ? 4L : 0L;
            i2 = 4;
        }
        a(new e(inputStream, b2, i2, 1, j), bArr);
        if (!a2.b(org.a.c.b.j.B, false)) {
            a(bArr);
        }
        outputStream.write(bArr);
        return new k(dVar);
    }

    private static void a(e eVar, byte[] bArr) {
        int i;
        int i2 = 0;
        do {
            int read = eVar.read(bArr, i2, bArr.length - i2);
            if (read < 0) {
                break;
            }
            i = i2 + read;
            i2 = i;
        } while (i < bArr.length);
        eVar.close();
    }

    private static void a(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ (-1));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        am.a(inputStream, new f(outputStream, dVar.j(org.a.c.b.j.ad), dVar.j(org.a.c.b.j.dk), 1));
        inputStream.close();
    }
}
