package com.esotericsoftware.kryonet.util;

import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/InputStreamSender.class */
public abstract class InputStreamSender extends TcpIdleSender {
    private final InputStream input;
    private final byte[] chunk;

    protected abstract Object next(byte[] bArr);

    public InputStreamSender(InputStream inputStream, int i) {
        this.input = inputStream;
        this.chunk = new byte[i];
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:            if (r7 != 0) goto L11;     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:            return null;     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:            r0 = new byte[r7];        java.lang.System.arraycopy(r6.chunk, 0, r0, 0, r7);     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003d, code lost:            return next(r0);     */
    @Override // com.esotericsoftware.kryonet.util.TcpIdleSender
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final java.lang.Object next() {
        /*
            r6 = this;
            r0 = 0
            r7 = r0
        L2:
            r0 = r7
            r1 = r6
            byte[] r1 = r1.chunk     // Catch: java.io.IOException -> L48
            int r1 = r1.length     // Catch: java.io.IOException -> L48
            if (r0 >= r1) goto L45
            r0 = r6
            java.io.InputStream r0 = r0.input     // Catch: java.io.IOException -> L48
            r1 = r6
            byte[] r1 = r1.chunk     // Catch: java.io.IOException -> L48
            r2 = r7
            r3 = r6
            byte[] r3 = r3.chunk     // Catch: java.io.IOException -> L48
            int r3 = r3.length     // Catch: java.io.IOException -> L48
            r4 = r7
            int r3 = r3 - r4
            int r0 = r0.read(r1, r2, r3)     // Catch: java.io.IOException -> L48
            r1 = r0
            r8 = r1
            if (r0 >= 0) goto L3e
            r0 = r7
            if (r0 != 0) goto L29
            r0 = 0
            return r0
        L29:
            r0 = r7
            byte[] r0 = new byte[r0]     // Catch: java.io.IOException -> L48
            r8 = r0
            r0 = r6
            byte[] r0 = r0.chunk     // Catch: java.io.IOException -> L48
            r1 = 0
            r2 = r8
            r3 = 0
            r4 = r7
            java.lang.System.arraycopy(r0, r1, r2, r3, r4)     // Catch: java.io.IOException -> L48
            r0 = r6
            r1 = r8
            java.lang.Object r0 = r0.next(r1)     // Catch: java.io.IOException -> L48
            return r0
        L3e:
            r0 = r7
            r1 = r8
            int r0 = r0 + r1
            r7 = r0
            goto L2
        L45:
            goto L52
        L48:
            r7 = move-exception
            com.esotericsoftware.kryonet.KryoNetException r0 = new com.esotericsoftware.kryonet.KryoNetException
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            throw r0
        L52:
            r0 = r6
            r1 = r0
            byte[] r1 = r1.chunk
            java.lang.Object r0 = r0.next(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.kryonet.util.InputStreamSender.next():java.lang.Object");
    }
}
