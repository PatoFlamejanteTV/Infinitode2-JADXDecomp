package org.a.c.h.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.a.b.f.ap;
import org.a.b.f.aq;
import org.a.b.f.ar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/e/r.class */
public final class r extends aj {
    private static final org.a.a.a.a d = org.a.a.a.c.a(r.class);
    private final org.a.c.h.b e;
    private final ac f;
    private final org.a.c.b.d g;
    private final org.a.c.b.d h;
    private final boolean i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/e/r$a.class */
    public enum a {
        FIRST,
        BRACKET,
        SERIAL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(org.a.c.h.b bVar, org.a.c.b.d dVar, ap apVar, boolean z, ac acVar, boolean z2) {
        super(bVar, dVar, apVar, z);
        this.e = bVar;
        this.g = dVar;
        this.f = acVar;
        this.i = z2;
        dVar.a(org.a.c.b.j.dE, org.a.c.b.j.dO);
        dVar.a(org.a.c.b.j.v, this.f4528b.g());
        dVar.a(org.a.c.b.j.aR, z2 ? org.a.c.b.j.bD : org.a.c.b.j.bC);
        this.h = d();
        org.a.c.b.a aVar = new org.a.c.b.a();
        aVar.a((org.a.c.b.b) this.h);
        dVar.a(org.a.c.b.j.aw, (org.a.c.b.b) aVar);
        if (!z) {
            a((Map<Integer, Integer>) null);
        }
    }

    @Override // org.a.c.h.e.aj
    protected final void a(InputStream inputStream, String str, Map<Integer, Integer> map) {
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            hashMap.put(Integer.valueOf(entry.getValue().intValue()), Integer.valueOf(entry.getKey().intValue()));
        }
        a(map);
        if (this.i) {
            e(hashMap);
        }
        a(inputStream);
        a(str);
        d(hashMap);
        b(hashMap);
        c(hashMap);
    }

    private void a(Map<Integer, Integer> map) {
        int i;
        ai aiVar = new ai();
        boolean z = false;
        int n = this.f4527a.m().n();
        for (int i2 = 1; i2 <= n; i2++) {
            if (map != null) {
                if (map.containsKey(Integer.valueOf(i2))) {
                    i = map.get(Integer.valueOf(i2)).intValue();
                }
            } else {
                i = i2;
            }
            List<Integer> b2 = this.c.b(i);
            if (b2 != null) {
                int intValue = b2.get(0).intValue();
                if (intValue > 65535) {
                    z = true;
                }
                aiVar.a(i, new String(new int[]{intValue}, 0, 1));
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        aiVar.a(byteArrayOutputStream);
        org.a.c.h.a.i iVar = new org.a.c.h.a.i(this.e, (InputStream) new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), org.a.c.b.j.bc);
        if (z && this.e.i() < 1.5d) {
            this.e.a(1.5f);
        }
        this.g.a(org.a.c.b.j.dJ, iVar);
    }

    private static org.a.c.b.d a(String str, String str2, int i) {
        org.a.c.b.d dVar = new org.a.c.b.d();
        dVar.b(org.a.c.b.j.de, str);
        dVar.b(org.a.c.b.j.cD, str2);
        dVar.a(org.a.c.b.j.dF, 0);
        return dVar;
    }

    private org.a.c.b.d d() {
        org.a.c.b.d dVar = new org.a.c.b.d();
        dVar.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.be);
        dVar.a(org.a.c.b.j.dE, (org.a.c.b.b) org.a.c.b.j.W);
        dVar.a(org.a.c.b.j.v, this.f4528b.g());
        dVar.a(org.a.c.b.j.Z, (org.a.c.b.b) a("Adobe", "Identity", 0));
        dVar.a(org.a.c.b.j.bg, (org.a.c.b.b) this.f4528b.f());
        b(dVar);
        if (this.i) {
            c(dVar);
        }
        dVar.a(org.a.c.b.j.X, (org.a.c.b.b) org.a.c.b.j.bB);
        return dVar;
    }

    private void a(String str) {
        String str2 = str + this.f4528b.g();
        this.g.a(org.a.c.b.j.v, str2);
        this.f4528b.a(str2);
        this.h.a(org.a.c.b.j.v, str2);
    }

    private void b(Map<Integer, Integer> map) {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int intValue = ((Integer) Collections.max(map.keySet())).intValue();
        for (int i2 = 0; i2 <= intValue; i2++) {
            if (map.containsKey(Integer.valueOf(i2))) {
                i = map.get(Integer.valueOf(i2)).intValue();
            } else {
                i = 0;
            }
            int i3 = i;
            byteArrayOutputStream.write(new byte[]{(byte) (i3 >> 8), (byte) i3});
        }
        this.h.a(org.a.c.b.j.X, new org.a.c.h.a.i(this.e, (InputStream) new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), org.a.c.b.j.bc));
    }

    private void c(Map<Integer, Integer> map) {
        int intValue = ((Integer) Collections.max(map.keySet())).intValue();
        byte[] bArr = new byte[(intValue / 8) + 1];
        for (int i = 0; i <= intValue; i++) {
            int i2 = i / 8;
            bArr[i2] = (byte) (bArr[i2] | (1 << (7 - (i % 8))));
        }
        this.f4528b.b(new org.a.c.h.a.i(this.e, (InputStream) new ByteArrayInputStream(bArr), org.a.c.b.j.bc));
    }

    private void d(Map<Integer, Integer> map) {
        float k = 1000.0f / this.f4527a.n().k();
        org.a.c.b.a aVar = new org.a.c.b.a();
        org.a.c.b.a aVar2 = new org.a.c.b.a();
        int i = Integer.MIN_VALUE;
        Iterator it = new TreeSet(map.keySet()).iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            long round = Math.round(this.f4527a.p().a(map.get(Integer.valueOf(intValue)).intValue()) * k);
            if (round != 1000) {
                if (i != intValue - 1) {
                    aVar2 = new org.a.c.b.a();
                    aVar.a((org.a.c.b.b) org.a.c.b.i.a(intValue));
                    aVar.a((org.a.c.b.b) aVar2);
                }
                aVar2.a((org.a.c.b.b) org.a.c.b.i.a(round));
                i = intValue;
            }
        }
        this.h.a(org.a.c.b.j.dX, (org.a.c.b.b) aVar);
    }

    private boolean a(org.a.c.b.d dVar) {
        if (this.f4527a.s() == null) {
            return false;
        }
        float k = 1000.0f / this.f4527a.n().k();
        long round = Math.round(r0.b() * k);
        long round2 = Math.round((-r0.a()) * k);
        if (round != 880 || round2 != -1000) {
            org.a.c.b.a aVar = new org.a.c.b.a();
            aVar.a((org.a.c.b.b) org.a.c.b.i.a(round));
            aVar.a((org.a.c.b.b) org.a.c.b.i.a(round2));
            dVar.a(org.a.c.b.j.aN, (org.a.c.b.b) aVar);
            return true;
        }
        return true;
    }

    private void e(Map<Integer, Integer> map) {
        if (!a(this.h)) {
            return;
        }
        float k = 1000.0f / this.f4527a.n().k();
        aq s = this.f4527a.s();
        ar t = this.f4527a.t();
        org.a.b.f.p e = this.f4527a.e();
        org.a.b.f.s p = this.f4527a.p();
        long round = Math.round(s.b() * k);
        long round2 = Math.round((-s.a()) * k);
        org.a.c.b.a aVar = new org.a.c.b.a();
        org.a.c.b.a aVar2 = new org.a.c.b.a();
        int i = Integer.MIN_VALUE;
        Iterator it = new TreeSet(map.keySet()).iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (e.a(intValue) != null) {
                long round3 = Math.round((r0.c() + t.a(intValue)) * k);
                long round4 = Math.round((-t.b(intValue)) * k);
                if (round3 != round || round4 != round2) {
                    if (i != intValue - 1) {
                        aVar2 = new org.a.c.b.a();
                        aVar.a((org.a.c.b.b) org.a.c.b.i.a(intValue));
                        aVar.a((org.a.c.b.b) aVar2);
                    }
                    aVar2.a((org.a.c.b.b) org.a.c.b.i.a(round4));
                    aVar2.a((org.a.c.b.b) org.a.c.b.i.a(Math.round(p.a(intValue) * k) / 2));
                    aVar2.a((org.a.c.b.b) org.a.c.b.i.a(round3));
                    i = intValue;
                }
            }
        }
        this.h.a(org.a.c.b.j.dY, (org.a.c.b.b) aVar);
    }

    private void b(org.a.c.b.d dVar) {
        int w = this.f4527a.w();
        int[] iArr = new int[w << 1];
        for (int i = 0; i < w; i++) {
            iArr[i << 1] = i;
            iArr[(i << 1) + 1] = this.f4527a.p().a(i);
        }
        dVar.a(org.a.c.b.j.dX, (org.a.c.b.b) a(iArr));
    }

    private org.a.c.b.a a(int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException("length of widths must be > 0");
        }
        float k = 1000.0f / this.f4527a.n().k();
        long j = iArr[0];
        long round = Math.round(iArr[1] * k);
        org.a.c.b.a aVar = new org.a.c.b.a();
        org.a.c.b.a aVar2 = new org.a.c.b.a();
        aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j));
        a aVar3 = a.FIRST;
        for (int i = 2; i < iArr.length; i += 2) {
            long j2 = iArr[i];
            long round2 = Math.round(iArr[i + 1] * k);
            switch (aVar3) {
                case FIRST:
                    if (j2 != j + 1 || round2 != round) {
                        if (j2 == j + 1) {
                            aVar3 = a.BRACKET;
                            org.a.c.b.a aVar4 = new org.a.c.b.a();
                            aVar = aVar4;
                            aVar4.a((org.a.c.b.b) org.a.c.b.i.a(round));
                            break;
                        } else {
                            org.a.c.b.a aVar5 = new org.a.c.b.a();
                            aVar = aVar5;
                            aVar5.a((org.a.c.b.b) org.a.c.b.i.a(round));
                            aVar2.a((org.a.c.b.b) aVar);
                            aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j2));
                            break;
                        }
                    } else {
                        aVar3 = a.SERIAL;
                        break;
                    }
                    break;
                case BRACKET:
                    if (j2 != j + 1 || round2 != round) {
                        if (j2 == j + 1) {
                            aVar.a((org.a.c.b.b) org.a.c.b.i.a(round));
                            break;
                        } else {
                            aVar3 = a.FIRST;
                            aVar.a((org.a.c.b.b) org.a.c.b.i.a(round));
                            aVar2.a((org.a.c.b.b) aVar);
                            aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j2));
                            break;
                        }
                    } else {
                        aVar3 = a.SERIAL;
                        aVar2.a((org.a.c.b.b) aVar);
                        aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j));
                        break;
                    }
                case SERIAL:
                    if (j2 != j + 1 || round2 != round) {
                        aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j));
                        aVar2.a((org.a.c.b.b) org.a.c.b.i.a(round));
                        aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j2));
                        aVar3 = a.FIRST;
                        break;
                    } else {
                        break;
                    }
            }
            round = round2;
            j = j2;
        }
        switch (aVar3) {
            case FIRST:
                org.a.c.b.a aVar6 = new org.a.c.b.a();
                aVar6.a((org.a.c.b.b) org.a.c.b.i.a(round));
                aVar2.a((org.a.c.b.b) aVar6);
                break;
            case BRACKET:
                aVar.a((org.a.c.b.b) org.a.c.b.i.a(round));
                aVar2.a((org.a.c.b.b) aVar);
                break;
            case SERIAL:
                aVar2.a((org.a.c.b.b) org.a.c.b.i.a(j));
                aVar2.a((org.a.c.b.b) org.a.c.b.i.a(round));
                break;
        }
        return aVar2;
    }

    private void c(org.a.c.b.d dVar) {
        if (!a(dVar)) {
            return;
        }
        int w = this.f4527a.w();
        int[] iArr = new int[w << 2];
        for (int i = 0; i < w; i++) {
            org.a.b.f.k a2 = this.f4527a.e().a(i);
            if (a2 == null) {
                iArr[i << 2] = Integer.MIN_VALUE;
            } else {
                iArr[i << 2] = i;
                iArr[(i << 2) + 1] = this.f4527a.t().b(i);
                iArr[(i << 2) + 2] = this.f4527a.p().a(i);
                iArr[(i << 2) + 3] = a2.c() + this.f4527a.t().a(i);
            }
        }
        dVar.a(org.a.c.b.j.dY, (org.a.c.b.b) b(iArr));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x025f, code lost:            r12 = r0;        r14 = r0;        r16 = r0;        r10 = r0;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.a.c.b.a b(int[] r8) {
        /*
            Method dump skipped, instructions count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.h.e.r.b(int[]):org.a.c.b.a");
    }

    public final o a() {
        return new q(this.h, this.f, this.f4527a);
    }
}
