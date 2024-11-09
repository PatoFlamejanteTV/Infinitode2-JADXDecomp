package org.a.c.f;

import com.a.a.a.am;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import org.a.c.b.m;
import org.a.c.b.n;
import org.a.c.b.p;
import org.a.c.f.l;

/* loaded from: infinitode-2.jar:org/a/c/f/b.class */
public class b extends a {
    private final byte[] k;
    protected final org.a.c.d.e c;
    private org.a.c.h.c.j l;
    private InputStream m;
    private String n;
    private String o;
    private long t;
    protected long d;
    private boolean u;
    protected boolean e;
    private boolean v;
    private Map<n, Long> w;
    private Long x;
    private List<Long> y;
    private List<Long> z;
    private org.a.c.h.c.d A;
    private org.a.c.h.c.k B;
    private int C;
    private l E;
    private final byte[] F;
    private static final char[] f = {'x', 'r', 'e', 'f'};
    private static final char[] g = {'/', 'X', 'R', 'e', 'f'};
    private static final char[] h = {'s', 't', 'a', 'r', 't', 'x', 'r', 'e', 'f'};
    private static final byte[] i = {101, 110, 100, 115, 116, 114, 101, 97, 109};
    private static final byte[] j = {101, 110, 100, 111, 98, 106};
    private static char[] p = {'%', '%', 'E', 'O', 'F'};
    private static char[] q = {'o', 'b', 'j'};
    private static final char[] r = {'t', 'r', 'a', 'i', 'l', 'e', 'r'};
    private static final char[] s = {'/', 'O', 'b', 'j', 'S', 't', 'm'};
    private static final org.a.a.a.a D = org.a.a.a.c.a(b.class);

    public b(org.a.c.d.e eVar, String str, InputStream inputStream, String str2) {
        super(new j(eVar));
        this.k = new byte[2048];
        this.m = null;
        this.n = "";
        this.o = null;
        this.u = true;
        this.e = false;
        this.v = false;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = 2048;
        this.E = new l();
        this.F = new byte[8192];
        this.c = eVar;
        this.n = str;
        this.o = str2;
        this.m = inputStream;
    }

    public final void e(int i2) {
        if (i2 > 15) {
            this.C = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.d p() {
        org.a.c.b.d dVar = null;
        boolean z = false;
        try {
            long w = w();
            if (w > -1) {
                dVar = a(w);
            } else {
                z = q();
            }
        } catch (IOException e) {
            if (q()) {
                z = true;
            } else {
                throw e;
            }
        }
        if (dVar != null && dVar.n(org.a.c.b.j.di) == null) {
            z = q();
        }
        if (z) {
            dVar = D();
        } else {
            G();
            if (this.w != null && !this.w.isEmpty()) {
                A();
            }
        }
        return dVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    private org.a.c.b.d a(long j2) {
        ?? r2;
        this.c.a(j2);
        long max = Math.max(0L, E());
        long c = c(max);
        if (c > -1) {
            max = c;
        }
        this.f4430b.b(max);
        long j3 = max;
        HashSet hashSet = new HashSet();
        while (j3 > 0) {
            this.c.a(j3);
            l();
            if (this.c.f() == 120) {
                if (!e(j3) || !F()) {
                    throw new IOException("Expected trailer object at offset " + this.c.a());
                }
                org.a.c.b.d b2 = this.E.b();
                ?? r22 = r2;
                if (b2.o(org.a.c.b.j.eg)) {
                    int j4 = b2.j(org.a.c.b.j.eg);
                    long c2 = c(j4);
                    if (c2 > -1 && c2 != j4) {
                        new StringBuilder("/XRefStm offset ").append(j4).append(" is incorrect, corrected to ").append(c2);
                        j4 = (int) c2;
                        b2.a(org.a.c.b.j.eg, j4);
                    }
                    if (j4 > 0) {
                        this.c.a(j4);
                        l();
                        try {
                            r22 = 0;
                            a(j3, false);
                        } catch (IOException e) {
                            if (this.u) {
                                new StringBuilder("Failed to parse /XRefStm at offset ").append(j4);
                                r22 = "Failed to parse /XRefStm at offset ";
                            } else {
                                throw e;
                            }
                        }
                    } else if (this.u) {
                        new StringBuilder("Skipped XRef stream due to a corrupt offset:").append(j4);
                        r22 = "Skipped XRef stream due to a corrupt offset:";
                    } else {
                        throw new IOException("Skipped XRef stream due to a corrupt offset:" + j4);
                    }
                }
                long k = b2.k(org.a.c.b.j.cU);
                long j5 = r22;
                j3 = k;
                r2 = r22;
                if (j5 > 0) {
                    long c3 = c(j3);
                    long j6 = r22;
                    r2 = r22;
                    if (j6 > -1) {
                        r2 = r22;
                        if (c3 != j3) {
                            j3 = c3;
                            long j7 = j3;
                            b2.a(org.a.c.b.j.cU, j7);
                            r2 = j7;
                        }
                    }
                }
            } else {
                r2 = 1;
                r2 = 1;
                r2 = 1;
                j3 = a(j3, true);
                if (1 > 0) {
                    long c4 = c(j3);
                    if (1 > -1 && c4 != j3) {
                        j3 = c4;
                        long j8 = j3;
                        this.E.b().a(org.a.c.b.j.cU, j8);
                        r2 = j8;
                    }
                }
            }
            if (hashSet.contains(Long.valueOf(j3))) {
                throw new IOException("/Prev loop at offset " + j3);
            }
            hashSet.add(Long.valueOf(j3));
        }
        this.E.a(max);
        org.a.c.b.d c5 = this.E.c();
        this.f4430b.c(c5);
        this.f4430b.b(l.a.STREAM == this.E.a());
        x();
        this.f4430b.a(this.E.d());
        return c5;
    }

    private long a(long j2, boolean z) {
        long m = m();
        this.f4430b.a(Math.max(this.f4430b.i(), m));
        n();
        a(q);
        org.a.c.b.d a2 = a();
        p c = c(a2);
        a(c, j2, z);
        c.close();
        return a2.k(org.a.c.b.j.cU);
    }

    private long w() {
        try {
            int i2 = this.d < ((long) this.C) ? (int) this.d : this.C;
            int i3 = i2;
            byte[] bArr = new byte[i2];
            long j2 = this.d - i3;
            this.c.a(j2);
            int i4 = 0;
            while (i4 < i3) {
                int a2 = this.c.a(bArr, i4, i3 - i4);
                if (a2 > 0) {
                    i4 += a2;
                } else {
                    throw new IOException("No more bytes to read for trailing buffer, but expected: " + (i3 - i4));
                }
            }
            int a3 = a(p, bArr, bArr.length);
            int i5 = a3;
            if (a3 < 0) {
                if (this.u) {
                    i5 = bArr.length;
                    new StringBuilder("Missing end of file marker '").append(new String(p)).append("'");
                } else {
                    throw new IOException("Missing end of file marker '" + new String(p) + "'");
                }
            }
            int a4 = a(h, bArr, i5);
            if (a4 < 0) {
                throw new IOException("Missing 'startxref' marker.");
            }
            return j2 + a4;
        } finally {
            this.c.a(0L);
        }
    }

    private static int a(char[] cArr, byte[] bArr, int i2) {
        int length = cArr.length - 1;
        int i3 = i2;
        int i4 = length;
        char c = cArr[length];
        while (true) {
            i3--;
            if (i3 >= 0) {
                if (bArr[i3] == c) {
                    i4--;
                    if (i4 < 0) {
                        return i3;
                    }
                    c = cArr[i4];
                } else if (i4 < length) {
                    i4 = length;
                    c = cArr[length];
                }
            } else {
                return -1;
            }
        }
    }

    public final boolean q() {
        return this.u;
    }

    private static long a(m mVar) {
        return (mVar.b() << 32) | mVar.d();
    }

    private void a(Queue<org.a.c.b.b> queue, Collection<org.a.c.b.b> collection, Set<Long> set) {
        Iterator<org.a.c.b.b> it = collection.iterator();
        while (it.hasNext()) {
            a(queue, it.next(), set);
        }
    }

    private void a(Queue<org.a.c.b.b> queue, org.a.c.b.b bVar, Set<Long> set) {
        if (bVar instanceof m) {
            if (!set.add(Long.valueOf(a((m) bVar)))) {
                return;
            }
            queue.add(bVar);
        } else if ((bVar instanceof org.a.c.b.d) || (bVar instanceof org.a.c.b.a)) {
            queue.add(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x02a4, code lost:            r0 = (org.a.c.b.m) r0.next();        r0 = a(r0, false);     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x02b9, code lost:            if (r0 == null) goto L111;     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02bc, code lost:            r0.a(r0);        a(r0, r0, r0);        r0.add(java.lang.Long.valueOf(a(r0)));     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02e2, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0286, code lost:            if (r0.isEmpty() != false) goto L77;     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0289, code lost:            r0 = ((java.util.List) r0.remove(r0.firstKey())).iterator();     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x02a1, code lost:            if (r0.hasNext() == false) goto L82;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(org.a.c.b.d r7, org.a.c.b.j... r8) {
        /*
            Method dump skipped, instructions count: 739
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.f.b.a(org.a.c.b.d, org.a.c.b.j[]):void");
    }

    private void a(org.a.c.b.j[] jVarArr, org.a.c.b.d dVar, Set<Long> set) {
        if (jVarArr != null) {
            for (org.a.c.b.j jVar : jVarArr) {
                org.a.c.b.b n = dVar.n(jVar);
                if (n instanceof m) {
                    set.add(Long.valueOf(a((m) n)));
                }
            }
        }
    }

    private org.a.c.b.b a(m mVar, boolean z) {
        return a(mVar.b(), mVar.d(), z);
    }

    private org.a.c.b.b a(long j2, int i2, boolean z) {
        n nVar = new n(j2, i2);
        m a2 = this.f4430b.a(nVar);
        if (a2.a() == null) {
            Long l = this.f4430b.k().get(nVar);
            Long l2 = l;
            if (l == null && this.u && this.w != null) {
                Long l3 = this.w.get(nVar);
                l2 = l3;
                if (l3 != null) {
                    new StringBuilder("Set missing offset ").append(l2).append(" for object ").append(nVar);
                    this.f4430b.k().put(nVar, l2);
                }
            }
            if (z && (l2 == null || l2.longValue() <= 0)) {
                throw new IOException("Object must be defined and must not be compressed object: " + nVar.b() + ":" + nVar.a());
            }
            if (l2 == null && this.u && this.w == null) {
                y();
                if (this.w != null && !this.w.isEmpty()) {
                    Map<n, Long> k = this.f4430b.k();
                    for (Map.Entry<n, Long> entry : this.w.entrySet()) {
                        n key = entry.getKey();
                        if (!k.containsKey(key)) {
                            k.put(key, entry.getValue());
                        }
                    }
                    l2 = k.get(nVar);
                }
            }
            if (l2 == null) {
                a2.a(org.a.c.b.k.f4371a);
            } else if (l2.longValue() > 0) {
                a(l2, nVar, a2);
            } else {
                f((int) (-l2.longValue()));
            }
        }
        return a2.a();
    }

    private void a(Long l, n nVar, m mVar) {
        this.c.a(l.longValue());
        long m = m();
        int n = n();
        a(q);
        if (m != nVar.b() || n != nVar.a()) {
            throw new IOException("XREF for " + nVar.b() + ":" + nVar.a() + " points to wrong object: " + m + ":" + n + " at offset " + l);
        }
        l();
        org.a.c.b.b f2 = f();
        String g2 = g();
        String str = g2;
        if (g2.equals("stream")) {
            this.c.b(str.getBytes(org.a.c.i.a.d).length);
            if (f2 instanceof org.a.c.b.d) {
                p c = c((org.a.c.b.d) f2);
                if (this.B != null) {
                    this.B.a(c, nVar.b(), nVar.a());
                }
                f2 = c;
                l();
                String h2 = h();
                str = h2;
                if (!h2.startsWith("endobj") && str.startsWith("endstream")) {
                    String trim = str.substring(9).trim();
                    str = trim;
                    if (trim.length() == 0) {
                        str = h();
                    }
                }
            } else {
                throw new IOException("Stream not preceded by dictionary (offset: " + l + ").");
            }
        } else if (this.B != null) {
            this.B.a(f2, nVar.b(), nVar.a());
        }
        mVar.a(f2);
        if (!str.startsWith("endobj")) {
            if (this.u) {
                new StringBuilder("Object (").append(m).append(":").append(n).append(") at offset ").append(l).append(" does not end with 'endobj' but with '").append(str).append("'");
                return;
            }
            throw new IOException("Object (" + m + ":" + n + ") at offset " + l + " does not end with 'endobj' but with '" + str + "'");
        }
    }

    private void f(int i2) {
        org.a.c.b.b a2 = a(i2, 0, true);
        if (a2 instanceof p) {
            try {
                e eVar = new e((p) a2, this.f4430b);
                try {
                    eVar.p();
                    for (m mVar : eVar.q()) {
                        n nVar = new n(mVar);
                        Long l = this.E.d().get(nVar);
                        if (l != null && l.longValue() == (-i2)) {
                            this.f4430b.a(nVar).a(mVar.a());
                        }
                    }
                } catch (IOException e) {
                    if (this.u) {
                        new StringBuilder("Stop reading object stream ").append(i2).append(" due to an exception");
                        return;
                    }
                    throw e;
                }
            } catch (IOException e2) {
                if (this.u) {
                    new StringBuilder("object stream ").append(i2).append(" could not be parsed due to an exception");
                    return;
                }
                throw e2;
            }
        }
    }

    private org.a.c.b.l a(org.a.c.b.b bVar, org.a.c.b.j jVar) {
        org.a.c.b.l lVar;
        if (bVar == null) {
            return null;
        }
        if (bVar instanceof org.a.c.b.l) {
            lVar = (org.a.c.b.l) bVar;
        } else if (bVar instanceof m) {
            m mVar = (m) bVar;
            org.a.c.b.b a2 = mVar.a();
            org.a.c.b.b bVar2 = a2;
            if (a2 == null) {
                long a3 = this.c.a();
                a(mVar, org.a.c.b.j.cv.equals(jVar));
                this.c.a(a3);
                bVar2 = mVar.a();
            }
            if (bVar2 == null) {
                throw new IOException("Length object content was not read.");
            }
            if (org.a.c.b.k.f4371a == bVar2) {
                new StringBuilder("Length object (").append(mVar.b()).append(SequenceUtils.SPACE).append(mVar.d()).append(") not found");
                return null;
            }
            if (!(bVar2 instanceof org.a.c.b.l)) {
                throw new IOException("Wrong type of referenced length object " + mVar + ": " + bVar2.getClass().getSimpleName());
            }
            lVar = (org.a.c.b.l) bVar2;
        } else {
            throw new IOException("Wrong type of length object: " + bVar.getClass().getSimpleName());
        }
        return lVar;
    }

    private p c(org.a.c.b.d dVar) {
        p a2 = this.f4430b.a(dVar);
        g();
        b();
        org.a.c.b.l a3 = a(dVar.n(org.a.c.b.j.bX), dVar.b(org.a.c.b.j.dN));
        if (a3 == null) {
            if (this.u) {
                new StringBuilder("The stream doesn't provide any stream length, using fallback readUntilEnd, at offset ").append(this.c.a());
            } else {
                throw new IOException("Missing length for stream.");
            }
        }
        if (a3 != null && b(a3.b())) {
            OutputStream m = a2.m();
            try {
                a(m, a3);
            } finally {
                m.close();
                a2.a(org.a.c.b.j.bX, (org.a.c.b.b) a3);
            }
        } else {
            OutputStream m2 = a2.m();
            try {
                a(new c(m2));
            } finally {
                m2.close();
                if (a3 != null) {
                    a2.a(org.a.c.b.j.bX, (org.a.c.b.b) a3);
                }
            }
        }
        String g2 = g();
        if (g2.equals("endobj") && this.u) {
            new StringBuilder("stream ends with 'endobj' instead of 'endstream' at offset ").append(this.c.a());
            this.c.b(j.length);
        } else if (g2.length() > 9 && this.u && g2.substring(0, 9).equals("endstream")) {
            new StringBuilder("stream ends with '").append(g2).append("' instead of 'endstream' at offset ").append(this.c.a());
            this.c.b(g2.substring(9).getBytes(org.a.c.i.a.d).length);
        } else if (!g2.equals("endstream")) {
            throw new IOException("Error reading stream, expected='endstream' actual='" + g2 + "' at offset " + this.c.a());
        }
        return a2;
    }

    private void a(OutputStream outputStream) {
        int i2;
        byte b2;
        int i3 = 0;
        byte[] bArr = i;
        while (true) {
            int a2 = this.c.a(this.k, i3, 2048 - i3);
            if (a2 <= 0) {
                break;
            }
            int i4 = a2 + i3;
            int i5 = i3;
            int i6 = i4 - 5;
            while (true) {
                if (i5 >= i4) {
                    break;
                }
                int i7 = i5 + 5;
                if (i3 == 0 && i7 < i6 && ((b2 = this.k[i7]) > 116 || b2 < 97)) {
                    i5 = i7;
                } else {
                    byte b3 = this.k[i5];
                    if (b3 == bArr[i3]) {
                        i3++;
                        if (i3 == bArr.length) {
                            i5++;
                            break;
                        }
                    } else if (i3 == 3 && b3 == j[i3]) {
                        bArr = j;
                        i3++;
                    } else {
                        if (b3 == 101) {
                            i2 = 1;
                        } else {
                            i2 = (b3 == 110 && i3 == 7) ? 2 : 0;
                        }
                        i3 = i2;
                        bArr = i;
                    }
                }
                i5++;
            }
            int max = Math.max(0, i5 - i3);
            if (max > 0) {
                outputStream.write(this.k, 0, max);
            }
            if (i3 == bArr.length) {
                this.c.b(i4 - max);
                break;
            }
            System.arraycopy(bArr, 0, this.k, 0, i3);
        }
        outputStream.flush();
    }

    private void a(OutputStream outputStream, org.a.c.b.l lVar) {
        long b2 = lVar.b();
        while (true) {
            long j2 = b2;
            if (j2 > 0) {
                int i2 = j2 > 8192 ? 8192 : (int) j2;
                int a2 = this.c.a(this.F, 0, i2);
                if (a2 <= 0) {
                    throw new IOException("read error at offset " + this.c.a() + ": expected " + i2 + " bytes, but read() returns " + a2);
                }
                outputStream.write(this.F, 0, a2);
                b2 = j2 - a2;
            } else {
                return;
            }
        }
    }

    private boolean b(long j2) {
        boolean z = true;
        long a2 = this.c.a();
        long j3 = a2 + j2;
        if (j3 <= this.d) {
            this.c.a(j3);
            l();
            if (!a(i)) {
                z = false;
                new StringBuilder("The end of the stream doesn't point to the correct offset, using workaround to read the stream, stream start position: ").append(a2).append(", length: ").append(j2).append(", expected end position: ").append(j3);
            }
            this.c.a(a2);
        } else {
            z = false;
            new StringBuilder("The end of the stream is out of range, using workaround to read the stream, stream start position: ").append(a2).append(", length: ").append(j2).append(", expected end position: ").append(j3);
        }
        return z;
    }

    private long c(long j2) {
        if (!this.u) {
            return j2;
        }
        this.c.a(j2);
        l();
        if (this.c.f() == 120 && b(f)) {
            return j2;
        }
        if (j2 > 0) {
            if (d(j2)) {
                return j2;
            }
            return b(j2, false);
        }
        return -1L;
    }

    private boolean d(long j2) {
        if (!this.u || j2 == 0) {
            return true;
        }
        this.c.a(j2 - 1);
        if (c(this.c.b())) {
            l();
            if (k()) {
                try {
                    m();
                    n();
                    a(q);
                    org.a.c.b.d a2 = a();
                    this.c.a(j2);
                    if ("XRef".equals(a2.g(org.a.c.b.j.dN))) {
                        return true;
                    }
                    return false;
                } catch (IOException unused) {
                    this.c.a(j2);
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private long b(long j2, boolean z) {
        if (j2 < 0) {
            new StringBuilder("Invalid object offset ").append(j2).append(" when searching for a xref table/stream");
            return 0L;
        }
        long c = c(j2, false);
        if (0 > -1) {
            new StringBuilder("Fixed reference for xref table/stream ").append(j2).append(" -> ").append(c);
            return c;
        }
        new StringBuilder("Can't find the object xref table/stream at offset ").append(j2);
        return 0L;
    }

    private boolean a(Map<n, Long> map) {
        if (map == null) {
            return true;
        }
        for (Map.Entry<n, Long> entry : map.entrySet()) {
            n key = entry.getKey();
            Long value = entry.getValue();
            if (value != null && value.longValue() >= 0 && !a(key, value.longValue())) {
                new StringBuilder("Stop checking xref offsets as at least one (").append(key).append(") couldn't be dereferenced");
                return false;
            }
        }
        return true;
    }

    private void x() {
        if (!this.u) {
            return;
        }
        Map<n, Long> d = this.E.d();
        if (!a(d)) {
            y();
            if (this.w != null && !this.w.isEmpty()) {
                d.clear();
                d.putAll(this.w);
            }
        }
    }

    private boolean a(n nVar, long j2) {
        if (j2 < 6) {
            return false;
        }
        boolean z = false;
        try {
            this.c.a(j2);
        } catch (IOException unused) {
        }
        if (nVar.b() == m()) {
            int n = n();
            if (n == nVar.a()) {
                a(q);
                z = true;
                return z;
            }
            if (this.u && n > nVar.a()) {
                a(q);
                z = true;
                nVar.a(n);
            }
        }
        return z;
    }

    /* JADX WARN: Type inference failed for: r0v101, types: [org.a.c.d.e] */
    /* JADX WARN: Type inference failed for: r0v106, types: [org.a.c.d.e] */
    private void y() {
        boolean z;
        if (this.w == null) {
            z();
            this.w = new HashMap();
            long a2 = this.c.a();
            long j2 = 6;
            long j3 = Long.MIN_VALUE;
            int i2 = Integer.MIN_VALUE;
            long j4 = Long.MIN_VALUE;
            char[] charArray = "ndo".toCharArray();
            char[] charArray2 = "bj".toCharArray();
            boolean z2 = false;
            do {
                this.c.a(j2);
                int b2 = this.c.b();
                j2++;
                if (c(b2) && b(q)) {
                    long j5 = j2 - 2;
                    this.c.a(j5);
                    int f2 = this.c.f();
                    if (d(f2)) {
                        int i3 = f2 - 48;
                        long j6 = j5 - 1;
                        this.c.a(j6);
                        if (i()) {
                            while (j6 > 6 && i()) {
                                ?? r0 = this.c;
                                long j7 = j6 - 1;
                                j6 = r0;
                                r0.a(j7);
                            }
                            boolean z3 = false;
                            while (true) {
                                z = z3;
                                if (j6 <= 6 || !k()) {
                                    break;
                                }
                                ?? r02 = this.c;
                                long j8 = j6 - 1;
                                j6 = r02;
                                r02.a(j8);
                                z3 = true;
                            }
                            if (z) {
                                this.c.b();
                                long m = m();
                                if (j4 > 0) {
                                    this.w.put(new n(j3, i2), Long.valueOf(j4));
                                }
                                j3 = m;
                                i2 = i3;
                                j4 = j6 + 1;
                                j2 += q.length - 1;
                                z2 = false;
                            }
                        }
                    }
                } else if (b2 == 101 && b(charArray)) {
                    j2 += charArray.length;
                    this.c.a(j2);
                    if (this.c.e()) {
                        z2 = true;
                    } else if (b(charArray2)) {
                        j2 += charArray2.length;
                        z2 = true;
                    }
                }
                if (j2 >= this.x.longValue()) {
                    break;
                }
            } while (!this.c.e());
            if ((this.x.longValue() < Long.MAX_VALUE || z2) && j4 > 0) {
                this.w.put(new n(j3, i2), Long.valueOf(j4));
            }
            this.c.a(a2);
        }
    }

    private long c(long j2, boolean z) {
        long j3 = -1;
        long j4 = -1;
        long j5 = -1;
        if (!z) {
            B();
        }
        C();
        if (!z && this.y != null) {
            j4 = a(this.y, j2);
        }
        if (this.z != null) {
            j5 = a(this.z, j2);
        }
        if (j4 > -1 && j5 > -1) {
            if (Math.abs(j2 - j4) > Math.abs(j2 - j5)) {
                j3 = j5;
                this.z.remove(Long.valueOf(j5));
            } else {
                j3 = j4;
                this.y.remove(Long.valueOf(j4));
            }
        } else if (j4 > -1) {
            j3 = j4;
            this.y.remove(Long.valueOf(j4));
        } else if (j5 > -1) {
            j3 = j5;
            this.z.remove(Long.valueOf(j5));
        }
        return j3;
    }

    private static long a(List<Long> list, long j2) {
        long j3 = -1;
        Long l = null;
        int i2 = -1;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            long longValue = j2 - list.get(i3).longValue();
            if (l == null || Math.abs(l.longValue()) > Math.abs(longValue)) {
                l = Long.valueOf(longValue);
                i2 = i3;
            }
        }
        if (i2 >= 0) {
            j3 = list.get(i2).longValue();
        }
        return j3;
    }

    private boolean d(org.a.c.b.d dVar) {
        org.a.c.b.d b2;
        org.a.c.b.d b3;
        HashMap hashMap = new HashMap();
        long a2 = this.c.a();
        this.c.a(6L);
        while (!this.c.e()) {
            if (b(r)) {
                this.c.a(this.c.a() + r.length);
                try {
                    boolean z = false;
                    boolean z2 = false;
                    l();
                    org.a.c.b.d a3 = a();
                    StringBuilder sb = new StringBuilder();
                    m c = a3.c(org.a.c.b.j.di);
                    if (c != null) {
                        long b4 = c.b();
                        int d = c.d();
                        sb.append(b4).append(SequenceUtils.SPACE);
                        sb.append(d).append(SequenceUtils.SPACE);
                        z = true;
                    }
                    m c2 = a3.c(org.a.c.b.j.bI);
                    if (c2 != null) {
                        long b5 = c2.b();
                        int d2 = c2.d();
                        sb.append(b5).append(SequenceUtils.SPACE);
                        sb.append(d2).append(SequenceUtils.SPACE);
                        z2 = true;
                    }
                    if (z && z2) {
                        hashMap.put(sb.toString(), a3);
                    }
                } catch (IOException unused) {
                }
            }
            this.c.b();
        }
        this.c.a(a2);
        int size = hashMap.size();
        String str = null;
        if (size > 0) {
            String[] strArr = new String[size];
            hashMap.keySet().toArray(strArr);
            str = strArr[0];
            for (int i2 = 1; i2 < size; i2++) {
                if (str.equals(strArr[i2])) {
                    hashMap.remove(strArr[i2]);
                }
            }
        }
        if (hashMap.size() == 1) {
            boolean z3 = false;
            boolean z4 = false;
            org.a.c.b.d dVar2 = (org.a.c.b.d) hashMap.get(str);
            org.a.c.b.b n = dVar2.n(org.a.c.b.j.di);
            if ((n instanceof m) && (b3 = b((m) n)) != null && f(b3)) {
                z3 = true;
            }
            org.a.c.b.b n2 = dVar2.n(org.a.c.b.j.bI);
            if ((n2 instanceof m) && (b2 = b((m) n2)) != null && g(b2)) {
                z4 = true;
            }
            if (z3 && z4) {
                dVar.a(org.a.c.b.j.di, n);
                dVar.a(org.a.c.b.j.bI, n2);
                if (dVar2.o(org.a.c.b.j.aS)) {
                    org.a.c.b.b n3 = dVar2.n(org.a.c.b.j.aS);
                    if ((n3 instanceof m) && b((m) n3) != null) {
                        dVar.a(org.a.c.b.j.aS, n3);
                    }
                }
                if (dVar2.o(org.a.c.b.j.bA)) {
                    org.a.c.b.b n4 = dVar2.n(org.a.c.b.j.bA);
                    if (n4 instanceof org.a.c.b.a) {
                        dVar.a(org.a.c.b.j.bA, n4);
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void z() {
        if (this.x == null) {
            long a2 = this.c.a();
            this.c.a(6L);
            while (!this.c.e()) {
                if (b(p)) {
                    long a3 = this.c.a();
                    this.c.a(a3 + 5);
                    try {
                        l();
                        if (!b(f)) {
                            m();
                            n();
                        }
                    } catch (IOException unused) {
                        this.x = Long.valueOf(a3);
                    }
                }
                this.c.b();
            }
            this.c.a(a2);
            if (this.x == null) {
                this.x = Long.MAX_VALUE;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v168, types: [org.a.c.d.e] */
    /* JADX WARN: Type inference failed for: r0v188, types: [org.a.c.d.e] */
    private void A() {
        HashMap hashMap = new HashMap();
        long a2 = this.c.a();
        this.c.a(6L);
        char[] charArray = " obj".toCharArray();
        while (!this.c.e()) {
            if (b(s)) {
                long a3 = this.c.a();
                long j2 = -1;
                boolean z = false;
                for (int i2 = 1; i2 < 40 && !z; i2++) {
                    long j3 = a3 - (i2 * 10);
                    long j4 = j3;
                    if (j3 > 0) {
                        this.c.a(j4);
                        int i3 = 0;
                        while (true) {
                            if (i3 >= 10) {
                                break;
                            }
                            if (b(charArray)) {
                                long j5 = j4 - 1;
                                this.c.a(j5);
                                if (d(this.c.f())) {
                                    long j6 = j5 - 1;
                                    this.c.a(j6);
                                    if (j()) {
                                        int i4 = 0;
                                        ?? r0 = this.c;
                                        long j7 = r0;
                                        r0.a(j6 - 1);
                                        while (j7 > 6 && k()) {
                                            ?? r02 = this.c;
                                            long j8 = j7 - 1;
                                            j7 = r02;
                                            r02.a(j8);
                                            i4++;
                                        }
                                        if (i4 > 0) {
                                            this.c.b();
                                            j2 = this.c.a();
                                            hashMap.put(Long.valueOf(j2), new n(m(), n()));
                                        }
                                    }
                                }
                                new StringBuilder("Dictionary start for object stream -> ").append(j2);
                                z = true;
                            } else {
                                j4++;
                                this.c.b();
                                i3++;
                            }
                        }
                    }
                }
                this.c.a(a3 + s.length);
            }
            this.c.b();
        }
        for (Long l : hashMap.keySet()) {
            Long l2 = this.w.get(hashMap.get(l));
            if (l2 == null) {
                new StringBuilder("Skipped incomplete object stream:").append(hashMap.get(l)).append(" at ").append(l);
            } else if (l.equals(l2)) {
                this.c.a(l.longValue());
                long m = m();
                int n = n();
                a(q);
                AutoCloseable autoCloseable = null;
                org.a.c.b.g gVar = null;
                try {
                    try {
                        org.a.c.b.d a4 = a();
                        int j9 = a4.j(org.a.c.b.j.aZ);
                        int j10 = a4.j(org.a.c.b.j.co);
                        if (j9 == -1 || j10 == -1) {
                            if (0 != 0) {
                                gVar.close();
                            }
                            if (0 != 0) {
                                autoCloseable.close();
                            }
                        } else {
                            p c = c(a4);
                            if (this.B != null) {
                                this.B.a(c, m, n);
                            }
                            org.a.c.b.g k = c.k();
                            byte[] bArr = new byte[j9];
                            k.read(bArr);
                            if (k != null) {
                                k.close();
                            }
                            if (c != null) {
                                c.close();
                            }
                            int i5 = 0;
                            while (i5 < bArr.length && bArr[i5] == 32) {
                                i5++;
                            }
                            String[] split = new String(bArr, i5, bArr.length - i5, "ISO-8859-1").replaceAll(SequenceUtils.EOL, SequenceUtils.SPACE).replaceAll("  ", SequenceUtils.SPACE).split(SequenceUtils.SPACE);
                            if (split.length < (j10 << 1)) {
                                new StringBuilder("Skipped corrupt stream: (").append(m).append(" 0 at offset ").append(l);
                            } else {
                                Map<n, Long> d = this.E.d();
                                for (int i6 = 0; i6 < j10; i6++) {
                                    try {
                                        n nVar = new n(Long.parseLong(split[i6 << 1]), 0);
                                        Long l3 = this.w.get(nVar);
                                        Long l4 = l3;
                                        if (l3 != null && l4.longValue() < 0) {
                                            l4 = this.w.get(new n(Math.abs(l4.longValue()), 0));
                                        }
                                        if (l4 == null || l.longValue() > l4.longValue()) {
                                            this.w.put(nVar, Long.valueOf(-m));
                                            d.put(nVar, Long.valueOf(-m));
                                        }
                                    } catch (NumberFormatException unused) {
                                        new StringBuilder("Skipped corrupt object key in stream: ").append(m);
                                    }
                                }
                            }
                        }
                    } catch (IOException unused2) {
                        new StringBuilder("Skipped corrupt stream: (").append(m).append(" 0 at offset ").append(l);
                        if (0 != 0) {
                            gVar.close();
                        }
                        if (0 != 0) {
                            autoCloseable.close();
                        }
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        gVar.close();
                    }
                    if (0 != 0) {
                        autoCloseable.close();
                    }
                    throw th;
                }
            }
        }
        this.c.a(a2);
    }

    private void B() {
        if (this.y == null) {
            this.y = new Vector();
            long a2 = this.c.a();
            this.c.a(6L);
            while (!this.c.e()) {
                if (b(f)) {
                    long a3 = this.c.a();
                    this.c.a(a3 - 1);
                    if (i()) {
                        this.y.add(Long.valueOf(a3));
                    }
                    this.c.a(a3 + 4);
                }
                this.c.b();
            }
            this.c.a(a2);
        }
    }

    /* JADX WARN: Type inference failed for: r0v65, types: [org.a.c.d.e] */
    /* JADX WARN: Type inference failed for: r0v78, types: [org.a.c.d.e] */
    private void C() {
        if (this.z == null) {
            this.z = new Vector();
            long a2 = this.c.a();
            this.c.a(6L);
            char[] charArray = " obj".toCharArray();
            while (!this.c.e()) {
                if (b(g)) {
                    long j2 = -1;
                    long a3 = this.c.a();
                    boolean z = false;
                    for (int i2 = 1; i2 < 40 && !z; i2++) {
                        long j3 = a3 - (i2 * 10);
                        long j4 = j3;
                        if (j3 > 0) {
                            this.c.a(j4);
                            int i3 = 0;
                            while (true) {
                                if (i3 >= 10) {
                                    break;
                                }
                                if (b(charArray)) {
                                    long j5 = j4 - 1;
                                    this.c.a(j5);
                                    if (d(this.c.f())) {
                                        long j6 = j5 - 1;
                                        this.c.a(j6);
                                        if (j()) {
                                            int i4 = 0;
                                            ?? r0 = this.c;
                                            long j7 = r0;
                                            r0.a(j6 - 1);
                                            while (j7 > 6 && k()) {
                                                ?? r02 = this.c;
                                                long j8 = j7 - 1;
                                                j7 = r02;
                                                r02.a(j8);
                                                i4++;
                                            }
                                            if (i4 > 0) {
                                                this.c.b();
                                                j2 = this.c.a();
                                            }
                                        }
                                    }
                                    new StringBuilder("Fixed reference for xref stream ").append(a3).append(" -> ").append(j2);
                                    z = true;
                                } else {
                                    j4++;
                                    this.c.b();
                                    i3++;
                                }
                            }
                        }
                    }
                    if (j2 > -1) {
                        this.z.add(Long.valueOf(j2));
                    }
                    this.c.a(a3 + 5);
                }
                this.c.b();
            }
            this.c.a(a2);
        }
    }

    private org.a.c.b.d D() {
        org.a.c.b.d dVar = null;
        y();
        if (this.w != null) {
            this.E.e();
            this.E.a(0L, l.a.TABLE);
            for (Map.Entry<n, Long> entry : this.w.entrySet()) {
                this.E.a(entry.getKey(), entry.getValue().longValue());
            }
            this.E.a(0L);
            dVar = this.E.c();
            t().c(dVar);
            boolean z = false;
            if (!d(dVar) && !e(dVar)) {
                A();
                z = true;
                e(dVar);
            }
            G();
            if (!z) {
                A();
            }
        }
        this.v = true;
        return dVar;
    }

    private boolean e(org.a.c.b.d dVar) {
        boolean z = false;
        for (Map.Entry<n, Long> entry : this.w.entrySet()) {
            org.a.c.b.d b2 = b(entry.getKey(), entry.getValue().longValue());
            if (b2 != null) {
                if (f(b2)) {
                    dVar.a(org.a.c.b.j.di, (org.a.c.b.b) this.f4430b.a(entry.getKey()));
                    z = true;
                } else if (g(b2)) {
                    dVar.a(org.a.c.b.j.bI, (org.a.c.b.b) this.f4430b.a(entry.getKey()));
                }
            }
        }
        return z;
    }

    private org.a.c.b.d b(m mVar) {
        n nVar = new n(mVar);
        Long l = this.w.get(nVar);
        if (l != null) {
            return b(nVar, l.longValue());
        }
        return null;
    }

    private org.a.c.b.d b(n nVar, long j2) {
        org.a.c.b.d dVar = null;
        if (j2 < 0) {
            m a2 = this.f4430b.a(nVar);
            if (a2.a() == null) {
                f((int) (-j2));
            }
            org.a.c.b.b a3 = a2.a();
            if (a3 instanceof org.a.c.b.d) {
                dVar = (org.a.c.b.d) a3;
            }
        } else {
            this.c.a(j2);
            m();
            n();
            a(q);
            if (this.c.f() != 60) {
                return null;
            }
            try {
                dVar = a();
            } catch (IOException unused) {
                new StringBuilder("Skipped object ").append(nVar).append(", either it's corrupt or not a dictionary");
            }
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(org.a.c.b.d dVar) {
        if (this.v && dVar != null) {
            org.a.c.b.b a2 = dVar.a(org.a.c.b.j.cL);
            if (a2 instanceof org.a.c.b.d) {
                a((org.a.c.b.d) a2, new HashSet());
            }
        }
    }

    private int a(org.a.c.b.d dVar, Set<m> set) {
        org.a.c.b.b a2 = dVar.a(org.a.c.b.j.bR);
        int i2 = 0;
        if (a2 instanceof org.a.c.b.a) {
            org.a.c.b.a aVar = (org.a.c.b.a) a2;
            for (org.a.c.b.b bVar : aVar.e()) {
                if (!(bVar instanceof m) || set.contains((m) bVar)) {
                    aVar.b(bVar);
                } else {
                    m mVar = (m) bVar;
                    org.a.c.b.b a3 = mVar.a();
                    if (a3 == null || a3.equals(org.a.c.b.k.f4371a)) {
                        new StringBuilder("Removed null object ").append(bVar).append(" from pages dictionary");
                        aVar.b(bVar);
                    } else if (a3 instanceof org.a.c.b.d) {
                        org.a.c.b.d dVar2 = (org.a.c.b.d) a3;
                        org.a.c.b.j b2 = dVar2.b(org.a.c.b.j.dN);
                        if (org.a.c.b.j.cL.equals(b2)) {
                            set.add(mVar);
                            i2 += a(dVar2, set);
                        } else if (org.a.c.b.j.cK.equals(b2)) {
                            i2++;
                        }
                    }
                }
            }
        }
        dVar.a(org.a.c.b.j.ag, i2);
        return i2;
    }

    private static boolean f(org.a.c.b.d dVar) {
        return org.a.c.b.j.N.equals(dVar.b(org.a.c.b.j.dN));
    }

    private static boolean g(org.a.c.b.d dVar) {
        if (dVar.o(org.a.c.b.j.cN) || dVar.o(org.a.c.b.j.f4368a) || dVar.o(org.a.c.b.j.ay)) {
            return false;
        }
        if (!dVar.o(org.a.c.b.j.cn) && !dVar.o(org.a.c.b.j.dI) && !dVar.o(org.a.c.b.j.r) && !dVar.o(org.a.c.b.j.dD) && !dVar.o(org.a.c.b.j.bQ) && !dVar.o(org.a.c.b.j.ai) && !dVar.o(org.a.c.b.j.cW) && !dVar.o(org.a.c.b.j.ah)) {
            return false;
        }
        return true;
    }

    private long E() {
        long j2 = -1;
        if (b(h)) {
            g();
            l();
            j2 = o();
        }
        return j2;
    }

    private boolean a(byte[] bArr) {
        int i2;
        int a2;
        boolean z = false;
        if (this.c.f() == bArr[0]) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            int a3 = this.c.a(bArr2, 0, length);
            while (true) {
                i2 = a3;
                if (i2 >= length || (a2 = this.c.a(bArr2, i2, length - i2)) < 0) {
                    break;
                }
                a3 = i2 + a2;
            }
            z = Arrays.equals(bArr, bArr2);
            this.c.b(i2);
        }
        return z;
    }

    private boolean b(char[] cArr) {
        boolean z = true;
        long a2 = this.c.a();
        int length = cArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (this.c.b() == cArr[i2]) {
                i2++;
            } else {
                z = false;
                break;
            }
        }
        this.c.a(a2);
        return z;
    }

    private boolean F() {
        this.t = this.c.a();
        if (this.u) {
            int f2 = this.c.f();
            while (true) {
                int i2 = f2;
                if (i2 == 116 || !d(i2)) {
                    break;
                }
                if (this.c.a() == this.t) {
                    new StringBuilder("Expected trailer object at offset ").append(this.t).append(", keep trying");
                }
                h();
                f2 = this.c.f();
            }
        }
        if (this.c.f() != 116) {
            return false;
        }
        long a2 = this.c.a();
        String h2 = h();
        if (!h2.trim().equals("trailer")) {
            if (h2.startsWith("trailer")) {
                this.c.a(a2 + 7);
            } else {
                return false;
            }
        }
        l();
        this.E.a(a());
        l();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean r() {
        return a("%PDF-", "1.4");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean s() {
        return a("%FDF-", "1.0");
    }

    private boolean a(String str, String str2) {
        String h2 = h();
        String str3 = h2;
        if (!h2.contains(str)) {
            String h3 = h();
            while (true) {
                str3 = h3;
                if (str3.contains(str) || (str3.length() > 0 && Character.isDigit(str3.charAt(0)))) {
                    break;
                }
                h3 = h();
            }
        }
        if (!str3.contains(str)) {
            this.c.a(0L);
            return false;
        }
        int indexOf = str3.indexOf(str);
        if (indexOf > 0) {
            str3 = str3.substring(indexOf, str3.length());
        }
        if (str3.startsWith(str) && !str3.matches(str + "\\d.\\d")) {
            if (str3.length() < str.length() + 3) {
                str3 = str + str2;
                new StringBuilder("No version found, set to ").append(str2).append(" as default.");
            } else {
                String str4 = str3.substring(str.length() + 3, str3.length()) + SequenceUtils.EOL;
                str3 = str3.substring(0, str.length() + 3);
                this.c.b(str4.getBytes(org.a.c.i.a.d).length);
            }
        }
        float f2 = -1.0f;
        try {
            String[] split = str3.split("-");
            if (split.length == 2) {
                f2 = Float.parseFloat(split[1]);
            }
        } catch (NumberFormatException unused) {
        }
        if (f2 < 0.0f) {
            if (this.u) {
                f2 = 1.7f;
            } else {
                throw new IOException("Error getting header version: " + str3);
            }
        }
        this.f4430b.a(f2);
        this.c.a(0L);
        return true;
    }

    private boolean e(long j2) {
        if (this.c.f() != 120 || !g().trim().equals("xref")) {
            return false;
        }
        String g2 = g();
        this.c.b(g2.getBytes(org.a.c.i.a.d).length);
        this.E.a(j2, l.a.TABLE);
        if (g2.startsWith("trailer")) {
            return false;
        }
        do {
            String h2 = h();
            String[] split = h2.split("\\s");
            if (split.length != 2) {
                new StringBuilder("Unexpected XRefTable Entry: ").append(h2);
                return false;
            }
            try {
                long parseLong = Long.parseLong(split[0]);
                try {
                    int parseInt = Integer.parseInt(split[1]);
                    l();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= parseInt || this.c.e() || a((int) ((char) this.c.f())) || this.c.f() == 116) {
                            break;
                        }
                        String h3 = h();
                        String[] split2 = h3.split("\\s");
                        if (split2.length < 3) {
                            new StringBuilder("invalid xref line: ").append(h3);
                            break;
                        }
                        if (split2[split2.length - 1].equals("n")) {
                            try {
                                this.E.a(new n(parseLong, Integer.parseInt(split2[1])), Long.parseLong(split2[0]));
                            } catch (NumberFormatException e) {
                                throw new IOException(e);
                            }
                        } else if (!split2[2].equals("f")) {
                            throw new IOException("Corrupt XRefTable Entry - ObjID:" + parseLong);
                        }
                        parseLong++;
                        l();
                        i2++;
                    }
                    l();
                } catch (NumberFormatException unused) {
                    new StringBuilder("XRefTable: invalid number of objects: ").append(h2);
                    return false;
                }
            } catch (NumberFormatException unused2) {
                new StringBuilder("XRefTable: invalid ID for the first object: ").append(h2);
                return false;
            }
        } while (k());
        return true;
    }

    private void a(p pVar, long j2, boolean z) {
        if (z) {
            this.E.a(j2, l.a.STREAM);
            this.E.a(pVar);
        }
        new i(pVar, this.f4430b, this.E).p();
    }

    public final org.a.c.b.e t() {
        if (this.f4430b == null) {
            throw new IOException("You must parse the document first before calling getDocument()");
        }
        return this.f4430b;
    }

    public final org.a.c.h.c.d u() {
        if (this.f4430b == null) {
            throw new IOException("You must parse the document first before calling getEncryption()");
        }
        return this.A;
    }

    public final org.a.c.h.c.j v() {
        if (this.f4430b == null) {
            throw new IOException("You must parse the document first before calling getAccessPermission()");
        }
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.c.b.b b(org.a.c.b.d dVar) {
        for (org.a.c.b.b bVar : dVar.h()) {
            if (bVar instanceof m) {
                a((m) bVar, false);
            }
        }
        m c = dVar.c(org.a.c.b.j.di);
        if (c == null) {
            throw new IOException("Missing root object specification in trailer.");
        }
        return c.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    private void G() {
        org.a.c.b.b n;
        org.a.c.h.c.a nVar;
        if (this.A == null && (n = this.f4430b.h().n(org.a.c.b.j.aS)) != null && !(n instanceof org.a.c.b.k)) {
            boolean z = n instanceof m;
            ?? r0 = z;
            if (z) {
                m mVar = (m) n;
                b bVar = this;
                bVar.c(mVar);
                r0 = bVar;
            }
            try {
                try {
                    this.A = new org.a.c.h.c.d(this.f4430b.d());
                    if (this.m != null) {
                        KeyStore keyStore = KeyStore.getInstance("PKCS12");
                        keyStore.load(this.m, this.n.toCharArray());
                        nVar = new org.a.c.h.c.f(keyStore, this.o, this.n);
                    } else {
                        nVar = new org.a.c.h.c.n(this.n);
                    }
                    this.B = this.A.a();
                    this.B.a(this.A, this.f4430b.e(), nVar);
                    this.l = this.B.b();
                    if (this.m != null) {
                        am.a((Closeable) this.m);
                    }
                } catch (IOException e) {
                    throw r0;
                } catch (Exception e2) {
                    throw new IOException("Error (" + e2.getClass().getSimpleName() + ") while creating security handler for decryption", e2);
                }
            } catch (Throwable th) {
                if (this.m != null) {
                    am.a((Closeable) this.m);
                }
                throw th;
            }
        }
    }

    private void c(m mVar) {
        a(mVar, true);
        if (!(mVar.a() instanceof org.a.c.b.d)) {
            throw new IOException("Dictionary object expected at offset " + this.c.a());
        }
        for (org.a.c.b.b bVar : ((org.a.c.b.d) mVar.a()).h()) {
            if (bVar instanceof m) {
                m mVar2 = (m) bVar;
                if (mVar2.a() == null) {
                    c(mVar2);
                }
            }
        }
    }
}
