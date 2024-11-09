package org.a.c.c;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* loaded from: infinitode-2.jar:org/a/c/c/n.class */
final class n extends l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4407a = org.a.a.a.c.a(n.class);

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        try {
            a(inputStream, t.a(outputStream, a(dVar, i)));
            return new k(dVar);
        } catch (DataFormatException e) {
            throw new IOException(e);
        }
    }

    private static void a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[2048];
        inputStream.read(bArr, 0, 2);
        int read = inputStream.read(bArr);
        if (read > 0) {
            Inflater inflater = new Inflater(true);
            inflater.setInput(bArr, 0, read);
            byte[] bArr2 = new byte[1024];
            boolean z = false;
            while (true) {
                try {
                    int inflate = inflater.inflate(bArr2);
                    if (inflate != 0) {
                        outputStream.write(bArr2, 0, inflate);
                        z = true;
                    } else if (inflater.finished() || inflater.needsDictionary() || inputStream.available() == 0) {
                        break;
                    } else {
                        inflater.setInput(bArr, 0, inputStream.read(bArr));
                    }
                } catch (DataFormatException e) {
                    if (!z) {
                        throw e;
                    }
                }
            }
            inflater.end();
        }
        outputStream.flush();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        Deflater deflater = new Deflater(a());
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, deflater);
        int available = inputStream.available();
        if (available > 0) {
            byte[] bArr = new byte[Math.min(available, 16384)];
            while (true) {
                int read = inputStream.read(bArr, 0, Math.min(available, 16384));
                if (read == -1) {
                    break;
                } else {
                    deflaterOutputStream.write(bArr, 0, read);
                }
            }
        }
        deflaterOutputStream.close();
        outputStream.flush();
        deflater.end();
    }
}
