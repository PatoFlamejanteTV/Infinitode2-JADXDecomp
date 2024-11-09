package org.a.b.f;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/f/d.class */
public class d implements c {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4291a = org.a.a.a.c.a(d.class);

    /* renamed from: b, reason: collision with root package name */
    private int f4292b;
    private int c;
    private long d;
    private int[] e;
    private final Map<Integer, List<Integer>> f = new HashMap();
    private Map<Integer, Integer> g = new HashMap();

    public final void a(ak akVar) {
        this.f4292b = akVar.c();
        this.c = akVar.c();
        this.d = akVar.k();
    }

    public final void a(f fVar, int i, ak akVar) {
        akVar.a(fVar.D() + this.d);
        int c = akVar.c();
        if (c < 8) {
            akVar.c();
            akVar.c();
        } else {
            akVar.c();
            akVar.k();
            akVar.k();
        }
        switch (c) {
            case 0:
                c(akVar);
                return;
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            default:
                throw new IOException("Unknown cmap format:" + c);
            case 2:
                f(akVar, i);
                return;
            case 4:
                e(akVar, i);
                return;
            case 6:
                d(akVar, i);
                return;
            case 8:
                a(akVar, i);
                return;
            case 10:
                b(akVar);
                return;
            case 12:
                b(akVar, i);
                return;
            case 13:
                c(akVar, i);
                return;
            case 14:
                return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0104, code lost:            throw new java.io.IOException("CMap contains an invalid glyph index");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(org.a.b.f.ak r8, int r9) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.b.f.d.a(org.a.b.f.ak, int):void");
    }

    private static void b(ak akVar) {
        long k = akVar.k();
        long k2 = akVar.k();
        if (k2 > 2147483647L) {
            throw new IOException("Invalid number of Characters");
        }
        if (k < 0 || k > 1114111 || k + k2 > 1114111 || (k + k2 >= 55296 && k + k2 <= 57343)) {
            throw new IOException("Invalid Characters codes");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0094, code lost:            throw new java.io.IOException("Invalid characters codes");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(org.a.b.f.ak r8, int r9) {
        /*
            Method dump skipped, instructions count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.b.f.d.b(org.a.b.f.ak, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0093, code lost:            throw new java.io.IOException("Invalid Characters codes");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(org.a.b.f.ak r8, int r9) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.b.f.d.c(org.a.b.f.ak, int):void");
    }

    private void d(ak akVar, int i) {
        int c = akVar.c();
        int c2 = akVar.c();
        if (c2 == 0) {
            return;
        }
        this.g = new HashMap(i);
        int[] c3 = akVar.c(c2);
        int i2 = 0;
        for (int i3 = 0; i3 < c2; i3++) {
            i2 = Math.max(i2, c3[i3]);
            this.g.put(Integer.valueOf(c + i3), Integer.valueOf(c3[i3]));
        }
        c(i2);
    }

    private void e(ak akVar, int i) {
        int c = akVar.c() / 2;
        akVar.c();
        akVar.c();
        akVar.c();
        int[] c2 = akVar.c(c);
        akVar.c();
        int[] c3 = akVar.c(c);
        int[] c4 = akVar.c(c);
        long e = akVar.e();
        int[] c5 = akVar.c(c);
        this.g = new HashMap(i);
        int i2 = 0;
        for (int i3 = 0; i3 < c; i3++) {
            int i4 = c3[i3];
            int i5 = c2[i3];
            int i6 = c4[i3];
            int i7 = c5[i3];
            long j = e + (i3 << 1) + i7;
            if (i4 != 65535 && i5 != 65535) {
                for (int i8 = i4; i8 <= i5; i8++) {
                    if (i7 == 0) {
                        int i9 = (i8 + i6) & 65535;
                        i2 = Math.max(i9, i2);
                        this.g.put(Integer.valueOf(i8), Integer.valueOf(i9));
                    } else {
                        akVar.a(j + ((i8 - i4) << 1));
                        int c6 = akVar.c();
                        if (c6 != 0) {
                            int i10 = (c6 + i6) & 65535;
                            i2 = Math.max(i10, i2);
                            this.g.put(Integer.valueOf(i8), Integer.valueOf(i10));
                        }
                    }
                }
            }
        }
        if (this.g.isEmpty()) {
            return;
        }
        c(i2);
    }

    private void c(int i) {
        this.e = d(i + 1);
        for (Map.Entry<Integer, Integer> entry : this.g.entrySet()) {
            if (this.e[entry.getValue().intValue()] == -1) {
                this.e[entry.getValue().intValue()] = entry.getKey().intValue();
            } else {
                List<Integer> list = this.f.get(entry.getValue());
                List<Integer> list2 = list;
                if (list == null) {
                    list2 = new ArrayList();
                    this.f.put(entry.getValue(), list2);
                    list2.add(Integer.valueOf(this.e[entry.getValue().intValue()]));
                    this.e[entry.getValue().intValue()] = Integer.MIN_VALUE;
                }
                list2.add(entry.getKey());
            }
        }
    }

    private void f(ak akVar, int i) {
        int[] iArr = new int[256];
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            iArr[i3] = akVar.c();
            i2 = Math.max(i2, iArr[i3] / 8);
        }
        a[] aVarArr = new a[i2 + 1];
        for (int i4 = 0; i4 <= i2; i4++) {
            aVarArr[i4] = new a(akVar.c(), akVar.c(), akVar.d(), (akVar.c() - ((((i2 + 1) - i4) - 1) << 3)) - 2, (byte) 0);
        }
        long e = akVar.e();
        this.e = d(i);
        this.g = new HashMap(i);
        for (int i5 = 0; i5 <= i2; i5++) {
            a aVar = aVarArr[i5];
            int a2 = aVar.a();
            int d = aVar.d();
            short c = aVar.c();
            int b2 = aVar.b();
            akVar.a(e + d);
            for (int i6 = 0; i6 < b2; i6++) {
                int i7 = (i5 << 8) + a2 + i6;
                int c2 = akVar.c();
                int i8 = c2;
                if (c2 > 0) {
                    int i9 = (i8 + c) % 65536;
                    i8 = i9;
                    if (i9 < 0) {
                        i8 += 65536;
                    }
                }
                if (i8 >= i) {
                    new StringBuilder("glyphId ").append(i8).append(" for charcode ").append(i7).append(" ignored, numGlyphs is ").append(i);
                } else {
                    this.e[i8] = i7;
                    this.g.put(Integer.valueOf(i7), Integer.valueOf(i8));
                }
            }
        }
    }

    private void c(ak akVar) {
        byte[] d = akVar.d(256);
        this.e = d(256);
        this.g = new HashMap(d.length);
        for (int i = 0; i < d.length; i++) {
            int i2 = d[i] & 255;
            this.e[i2] = i;
            this.g.put(Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    private static int[] d(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public final int a() {
        return this.c;
    }

    public final int b() {
        return this.f4292b;
    }

    @Override // org.a.b.f.c
    public final int a(int i) {
        Integer num = this.g.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private int e(int i) {
        if (i < 0 || i >= this.e.length) {
            return -1;
        }
        return this.e[i];
    }

    @Override // org.a.b.f.c
    public final List<Integer> b(int i) {
        int e = e(i);
        if (e == -1) {
            return null;
        }
        ArrayList arrayList = null;
        if (e == Integer.MIN_VALUE) {
            List<Integer> list = this.f.get(Integer.valueOf(i));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList(list);
                arrayList = arrayList2;
                Collections.sort(arrayList2);
            }
        } else {
            ArrayList arrayList3 = new ArrayList(1);
            arrayList = arrayList3;
            arrayList3.add(Integer.valueOf(e));
        }
        return arrayList;
    }

    public String toString() {
        return "{" + b() + SequenceUtils.SPACE + a() + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/d$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final int f4293a;

        /* renamed from: b, reason: collision with root package name */
        private final int f4294b;
        private final short c;
        private final int d;

        /* synthetic */ a(int i, int i2, short s, int i3, byte b2) {
            this(i, i2, s, i3);
        }

        private a(int i, int i2, short s, int i3) {
            this.f4293a = i;
            this.f4294b = i2;
            this.c = s;
            this.d = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a() {
            return this.f4293a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int b() {
            return this.f4294b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public short c() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int d() {
            return this.d;
        }
    }
}
