package org.a.c.g;

import com.a.a.a.am;
import com.b.a.a.aa;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.a.c.b.e;
import org.a.c.b.f;
import org.a.c.b.i;
import org.a.c.b.j;
import org.a.c.b.k;
import org.a.c.b.m;
import org.a.c.b.n;
import org.a.c.b.p;
import org.a.c.b.s;
import org.a.c.b.t;
import org.a.c.b.u;
import org.a.c.f.h;

/* loaded from: infinitode-2.jar:org/a/c/g/b.class */
public class b implements Closeable, u {
    private static final org.a.a.a.a e = org.a.a.a.c.a(b.class);

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f4450a = "<<".getBytes(org.a.c.i.a.f4651a);

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f4451b = ">>".getBytes(org.a.c.i.a.f4651a);
    private static byte[] f = {32};
    private static byte[] g = {37};
    private static byte[] h;
    private static byte[] i;
    private static byte[] j;
    private static byte[] k;
    private static byte[] l;
    private static byte[] m;
    private static byte[] n;
    private static byte[] o;
    private static byte[] p;
    private static byte[] q;
    public static final byte[] c;
    public static final byte[] d;
    private static byte[] r;
    private static byte[] s;
    private OutputStream v;
    private a w;
    private long M;
    private long N;
    private long O;
    private long P;
    private OutputStream Q;
    private aa.a R;
    private org.a.c.b.a S;
    private final NumberFormat t = new DecimalFormat("0000000000", DecimalFormatSymbols.getInstance(Locale.US));
    private final NumberFormat u = new DecimalFormat("00000", DecimalFormatSymbols.getInstance(Locale.US));
    private long x = 0;
    private long y = 0;
    private final Map<org.a.c.b.b, n> z = new Hashtable();
    private final Map<n, org.a.c.b.b> A = new Hashtable();
    private final List<c> B = new ArrayList();
    private final Set<org.a.c.b.b> C = new HashSet();
    private final Deque<org.a.c.b.b> D = new LinkedList();
    private final Set<org.a.c.b.b> E = new HashSet();
    private final Set<org.a.c.b.b> F = new HashSet();
    private n G = null;
    private org.a.c.h.b H = null;
    private org.a.c.h.d.a I = null;
    private boolean J = false;
    private boolean K = false;
    private boolean L = false;

    static {
        "PDF-1.4".getBytes(org.a.c.i.a.f4651a);
        h = new byte[]{-10, -28, -4, -33};
        i = "%%EOF".getBytes(org.a.c.i.a.f4651a);
        j = "R".getBytes(org.a.c.i.a.f4651a);
        k = "xref".getBytes(org.a.c.i.a.f4651a);
        l = "f".getBytes(org.a.c.i.a.f4651a);
        m = "n".getBytes(org.a.c.i.a.f4651a);
        n = "trailer".getBytes(org.a.c.i.a.f4651a);
        o = "startxref".getBytes(org.a.c.i.a.f4651a);
        p = "obj".getBytes(org.a.c.i.a.f4651a);
        q = "endobj".getBytes(org.a.c.i.a.f4651a);
        c = "[".getBytes(org.a.c.i.a.f4651a);
        d = "]".getBytes(org.a.c.i.a.f4651a);
        r = "stream".getBytes(org.a.c.i.a.f4651a);
        s = "endstream".getBytes(org.a.c.i.a.f4651a);
    }

    public b(OutputStream outputStream) {
        a(outputStream);
        a(new a(this.v));
    }

    private void a(c cVar) {
        d().add(cVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (b() != null) {
            b().close();
        }
        if (this.Q != null) {
            this.Q.close();
        }
    }

    private long a() {
        return this.y;
    }

    private a b() {
        return this.w;
    }

    private long c() {
        return this.x;
    }

    private List<c> d() {
        return this.B;
    }

    private void a(long j2) {
        this.y = j2;
    }

    private void a(OutputStream outputStream) {
        this.v = outputStream;
    }

    private void a(a aVar) {
        this.w = aVar;
    }

    private void b(long j2) {
        this.x = j2;
    }

    private void b(e eVar) {
        org.a.c.b.d h2 = eVar.h();
        org.a.c.b.d d2 = h2.d(j.di);
        org.a.c.b.d d3 = h2.d(j.bI);
        org.a.c.b.d d4 = h2.d(j.aS);
        if (d2 != null) {
            a((org.a.c.b.b) d2);
        }
        if (d3 != null) {
            a((org.a.c.b.b) d3);
        }
        e();
        this.J = false;
        if (d4 != null) {
            a((org.a.c.b.b) d4);
        }
        e();
    }

    private void e() {
        while (this.D.size() > 0) {
            org.a.c.b.b removeFirst = this.D.removeFirst();
            this.C.remove(removeFirst);
            b(removeFirst);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(org.a.c.b.b bVar) {
        org.a.c.b.b bVar2 = bVar;
        if (bVar instanceof m) {
            bVar2 = ((m) bVar2).a();
        }
        if (!this.E.contains(bVar) && !this.C.contains(bVar) && !this.F.contains(bVar2)) {
            Object obj = null;
            n nVar = null;
            if (bVar2 != null) {
                nVar = this.z.get(bVar2);
            }
            if (nVar != null) {
                obj = (org.a.c.b.b) this.A.get(nVar);
            }
            if (bVar2 != null && this.z.containsKey(bVar2) && (bVar instanceof t) && !((t) bVar).c() && (obj instanceof t) && !((t) obj).c()) {
                return;
            }
            this.D.add(bVar);
            this.C.add(bVar);
            if (bVar2 != null) {
                this.F.add(bVar2);
            }
        }
    }

    private void b(org.a.c.b.b bVar) {
        this.E.add(bVar);
        this.G = c(bVar);
        a(new c(b().a(), bVar, this.G));
        b().write(String.valueOf(this.G.b()).getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(String.valueOf(this.G.a()).getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(p);
        b().c();
        bVar.a(this);
        b().c();
        b().write(q);
        b().c();
    }

    private void c(e eVar) {
        String str;
        if (this.I != null) {
            str = "%FDF-" + Float.toString(eVar.b());
        } else {
            str = "%PDF-" + Float.toString(eVar.b());
        }
        b().write(str.getBytes(org.a.c.i.a.d));
        b().c();
        b().write(g);
        b().write(h);
        b().c();
    }

    private void d(e eVar) {
        b().write(n);
        b().c();
        org.a.c.b.d h2 = eVar.h();
        Collections.sort(d());
        h2.a(j.dr, d().get(d().size() - 1).b().b() + 1);
        h2.m(j.cU);
        if (!eVar.m()) {
            h2.m(j.eg);
        }
        h2.m(j.aG);
        org.a.c.b.a f2 = h2.f(j.bA);
        if (f2 != null) {
            f2.a(true);
        }
        h2.a((u) this);
    }

    private void a(e eVar, long j2) {
        if (eVar.m() || j2 != -1) {
            h hVar = new h(eVar);
            Iterator<c> it = d().iterator();
            while (it.hasNext()) {
                hVar.a(it.next());
            }
            org.a.c.b.d h2 = eVar.h();
            h2.m(j.cU);
            hVar.a(h2);
            hVar.a(a() + 2);
            b(b().a());
            b(hVar.a());
        }
        if (!eVar.m() || j2 != -1) {
            org.a.c.b.d h3 = eVar.h();
            h3.a(j.cU, eVar.l());
            if (j2 != -1) {
                j jVar = j.eg;
                h3.m(jVar);
                h3.a(jVar, c());
            }
            f();
            d(eVar);
        }
    }

    private void f() {
        a(c.a());
        Collections.sort(d());
        b(b().a());
        b().write(k);
        b().c();
        Long[] a2 = a(d());
        int length = a2.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length && length % 2 == 0; i3 += 2) {
            a(a2[i3].longValue(), a2[i3 + 1].longValue());
            for (int i4 = 0; i4 < a2[i3 + 1].longValue(); i4++) {
                int i5 = i2;
                i2++;
                b(this.B.get(i5));
            }
        }
    }

    private void a(long j2, long j3) {
        b().write(String.valueOf(j2).getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(String.valueOf(j3).getBytes(org.a.c.i.a.d));
        b().c();
    }

    private void b(c cVar) {
        String format = this.t.format(cVar.c());
        String format2 = this.u.format(cVar.b().a());
        b().write(format.getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(format2.getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(cVar.d() ? l : m);
        b().b();
    }

    private static Long[] a(List<c> list) {
        long j2 = -2;
        long j3 = 1;
        ArrayList arrayList = new ArrayList();
        Iterator<c> it = list.iterator();
        while (it.hasNext()) {
            long b2 = (int) it.next().b().b();
            if (b2 == j2 + 1) {
                j3++;
                j2 = b2;
            } else if (j2 == -2) {
                j2 = b2;
            } else {
                arrayList.add(Long.valueOf((j2 - j3) + 1));
                arrayList.add(Long.valueOf(j3));
                j2 = b2;
                j3 = 1;
            }
        }
        if (list.size() > 0) {
            arrayList.add(Long.valueOf((j2 - j3) + 1));
            arrayList.add(Long.valueOf(j3));
        }
        return (Long[]) arrayList.toArray(new Long[arrayList.size()]);
    }

    private n c(org.a.c.b.b bVar) {
        org.a.c.b.b bVar2 = bVar;
        if (bVar instanceof m) {
            bVar2 = ((m) bVar).a();
        }
        n nVar = this.z.get(bVar);
        n nVar2 = nVar;
        if (nVar == null && bVar2 != null) {
            nVar2 = this.z.get(bVar2);
        }
        if (nVar2 == null) {
            a(a() + 1);
            nVar2 = new n(a(), 0);
            this.z.put(bVar, nVar2);
            if (bVar2 != null) {
                this.z.put(bVar2, nVar2);
            }
        }
        return nVar2;
    }

    @Override // org.a.c.b.u
    public final Object a(org.a.c.b.a aVar) {
        int i2 = 0;
        b().write(c);
        Iterator<org.a.c.b.b> it = aVar.iterator();
        while (it.hasNext()) {
            org.a.c.b.b next = it.next();
            if (next instanceof org.a.c.b.d) {
                if (next.g()) {
                    a((org.a.c.b.d) next);
                } else {
                    a(next);
                    d(next);
                }
            } else if (next instanceof m) {
                org.a.c.b.b a2 = ((m) next).a();
                if (this.J || (a2 instanceof org.a.c.b.d) || a2 == null) {
                    a(next);
                    d(next);
                } else {
                    a2.a(this);
                }
            } else if (next == null) {
                k.f4371a.a(this);
            } else {
                next.a(this);
            }
            i2++;
            if (it.hasNext()) {
                if (i2 % 10 == 0) {
                    b().c();
                } else {
                    b().write(f);
                }
            }
        }
        b().write(d);
        b().c();
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(org.a.c.b.c cVar) {
        cVar.a(b());
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(org.a.c.b.d dVar) {
        if (!this.L) {
            org.a.c.b.b n2 = dVar.n(j.dN);
            if (j.dq.equals(n2) || j.aH.equals(n2)) {
                this.L = true;
            }
        }
        b().write(f4450a);
        b().c();
        for (Map.Entry<j, org.a.c.b.b> entry : dVar.e()) {
            org.a.c.b.b value = entry.getValue();
            if (value != null) {
                entry.getKey().a(this);
                b().write(f);
                if (value instanceof org.a.c.b.d) {
                    org.a.c.b.d dVar2 = (org.a.c.b.d) value;
                    org.a.c.b.b n3 = dVar2.n(j.ee);
                    if (n3 != null && !j.ee.equals(entry.getKey())) {
                        n3.a(true);
                    }
                    org.a.c.b.b n4 = dVar2.n(j.dg);
                    if (n4 != null && !j.dg.equals(entry.getKey())) {
                        n4.a(true);
                    }
                    if (dVar2.g()) {
                        a(dVar2);
                    } else {
                        a((org.a.c.b.b) dVar2);
                        d(dVar2);
                    }
                } else if (value instanceof m) {
                    org.a.c.b.b a2 = ((m) value).a();
                    if (this.J || (a2 instanceof org.a.c.b.d) || a2 == null) {
                        a(value);
                        d(value);
                    } else {
                        a2.a(this);
                    }
                } else if (this.L && j.af.equals(entry.getKey())) {
                    this.M = b().a();
                    value.a(this);
                    this.N = b().a() - this.M;
                } else if (this.L && j.G.equals(entry.getKey())) {
                    this.S = (org.a.c.b.a) entry.getValue();
                    this.O = b().a() + 1;
                    value.a(this);
                    this.P = (b().a() - 1) - this.O;
                    this.L = false;
                } else {
                    value.a(this);
                }
                b().c();
            }
        }
        b().write(f4451b);
        b().c();
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(e eVar) {
        c(eVar);
        b(eVar);
        org.a.c.b.d h2 = eVar.h();
        long j2 = -1;
        if (h2 != null) {
            j2 = h2.k(j.eg);
        }
        if (eVar.m()) {
            a(eVar, j2);
        } else {
            f();
            d(eVar);
        }
        b().write(o);
        b().c();
        b().write(String.valueOf(c()).getBytes(org.a.c.i.a.d));
        b().c();
        b().write(i);
        b().c();
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(f fVar) {
        fVar.a(b());
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(i iVar) {
        iVar.a(b());
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(j jVar) {
        jVar.a(b());
        return null;
    }

    @Override // org.a.c.b.u
    public final Object a(k kVar) {
        k.a(b());
        return null;
    }

    private void d(org.a.c.b.b bVar) {
        n c2 = c(bVar);
        b().write(String.valueOf(c2.b()).getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(String.valueOf(c2.a()).getBytes(org.a.c.i.a.d));
        b().write(f);
        b().write(j);
    }

    @Override // org.a.c.b.u
    public final Object a(p pVar) {
        if (this.J) {
            this.H.d().a().a(pVar, this.G.b(), this.G.a());
        }
        InputStream inputStream = null;
        try {
            a((org.a.c.b.d) pVar);
            b().write(r);
            b().b();
            InputStream j2 = pVar.j();
            inputStream = j2;
            am.a(j2, b());
            b().b();
            b().write(s);
            b().c();
            inputStream.close();
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // org.a.c.b.u
    public final Object a(s sVar) {
        if (this.J) {
            this.H.d().a().a(sVar, this.G.b(), this.G.a());
        }
        a(sVar, b());
        return null;
    }

    public final void a(org.a.c.h.b bVar) {
        a(bVar, (aa.a) null);
    }

    private void a(org.a.c.h.b bVar, aa.a aVar) {
        Long valueOf = Long.valueOf(bVar.h() == null ? System.currentTimeMillis() : bVar.h().longValue());
        this.H = bVar;
        this.R = aVar;
        if (bVar.g()) {
            this.J = false;
            bVar.a().h().m(j.aS);
        } else if (this.H.d() != null) {
            org.a.c.h.c.k a2 = this.H.d().a();
            if (!a2.a()) {
                throw new IllegalStateException("PDF contains an encryption dictionary, please remove it with setAllSecurityToBeRemoved() or set a protection policy with protect()");
            }
            a2.a(this.H);
            this.J = true;
        } else {
            this.J = false;
        }
        e a3 = this.H.a();
        org.a.c.b.d h2 = a3.h();
        org.a.c.b.a aVar2 = null;
        boolean z = true;
        org.a.c.b.b a4 = h2.a(j.bA);
        if (a4 instanceof org.a.c.b.a) {
            org.a.c.b.a aVar3 = (org.a.c.b.a) a4;
            aVar2 = aVar3;
            if (aVar3.b() == 2) {
                z = false;
            }
        }
        if (aVar2 != null && aVar2.b() == 2) {
            z = false;
        }
        if (z) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(Long.toString(valueOf.longValue()).getBytes(org.a.c.i.a.d));
                org.a.c.b.d d2 = h2.d(j.bI);
                if (d2 != null) {
                    Iterator<org.a.c.b.b> it = d2.h().iterator();
                    while (it.hasNext()) {
                        messageDigest.update(it.next().toString().getBytes(org.a.c.i.a.d));
                    }
                }
                s sVar = z ? new s(messageDigest.digest()) : (s) aVar2.b(0);
                s sVar2 = z ? sVar : new s(messageDigest.digest());
                org.a.c.b.a aVar4 = new org.a.c.b.a();
                aVar4.a((org.a.c.b.b) sVar);
                aVar4.a((org.a.c.b.b) sVar2);
                h2.a(j.bA, (org.a.c.b.b) aVar4);
            } catch (NoSuchAlgorithmException e2) {
                throw new RuntimeException(e2);
            }
        }
        a3.a(this);
    }

    public static void a(s sVar, OutputStream outputStream) {
        a(sVar.c(), sVar.a(), outputStream);
    }

    public static void a(byte[] bArr, OutputStream outputStream) {
        a(bArr, false, outputStream);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:            r6 = false;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(byte[] r3, boolean r4, java.io.OutputStream r5) {
        /*
            r0 = 1
            r6 = r0
            r0 = r4
            if (r0 != 0) goto L40
            r0 = r3
            r1 = r0
            r7 = r1
            int r0 = r0.length
            r8 = r0
            r0 = 0
            r9 = r0
        L10:
            r0 = r9
            r1 = r8
            if (r0 >= r1) goto L40
            r0 = r7
            r1 = r9
            r0 = r0[r1]
            r1 = r0
            r10 = r1
            if (r0 >= 0) goto L27
            r0 = 0
            r6 = r0
            goto L40
        L27:
            r0 = r10
            r1 = 13
            if (r0 == r1) goto L35
            r0 = r10
            r1 = 10
            if (r0 != r1) goto L3a
        L35:
            r0 = 0
            r6 = r0
            goto L40
        L3a:
            int r9 = r9 + 1
            goto L10
        L40:
            r0 = r6
            if (r0 == 0) goto La1
            r0 = r4
            if (r0 != 0) goto La1
            r0 = r5
            r1 = 40
            r0.write(r1)
            r0 = r3
            r1 = r0
            r7 = r1
            int r0 = r0.length
            r8 = r0
            r0 = 0
            r9 = r0
        L58:
            r0 = r9
            r1 = r8
            if (r0 >= r1) goto L9a
            r0 = r7
            r1 = r9
            r0 = r0[r1]
            r1 = r0
            r10 = r1
            switch(r0) {
                case 40: goto L88;
                case 41: goto L88;
                case 92: goto L88;
                default: goto L8e;
            }
        L88:
            r0 = r5
            r1 = 92
            r0.write(r1)
        L8e:
            r0 = r5
            r1 = r10
            r0.write(r1)
            int r9 = r9 + 1
            goto L58
        L9a:
            r0 = r5
            r1 = 41
            r0.write(r1)
            return
        La1:
            r0 = r5
            r1 = 60
            r0.write(r1)
            r0 = r3
            r1 = r5
            org.a.c.i.c.a(r0, r1)
            r0 = r5
            r1 = 62
            r0.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.g.b.a(byte[], boolean, java.io.OutputStream):void");
    }
}
