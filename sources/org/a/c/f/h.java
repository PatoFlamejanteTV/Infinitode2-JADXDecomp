package org.a.c.f;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.a.c.b.p;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/f/h.class */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Long, Object> f4435a;

    /* renamed from: b, reason: collision with root package name */
    private final Set<Long> f4436b;
    private final p c;
    private long d;

    @Deprecated
    public h() {
        this.d = -1L;
        this.c = new p();
        this.f4435a = new TreeMap();
        this.f4436b = new TreeSet();
    }

    public h(org.a.c.b.e eVar) {
        this.d = -1L;
        this.c = eVar.a();
        this.f4435a = new TreeMap();
        this.f4436b = new TreeSet();
    }

    public final p a() {
        this.c.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.ef);
        if (this.d == -1) {
            throw new IllegalArgumentException("size is not set in xrefstream");
        }
        this.c.a(org.a.c.b.j.dr, this.d);
        List<Long> c2 = c();
        org.a.c.b.a aVar = new org.a.c.b.a();
        Iterator<Long> it = c2.iterator();
        while (it.hasNext()) {
            aVar.a((org.a.c.b.b) org.a.c.b.i.a(it.next().longValue()));
        }
        this.c.a(org.a.c.b.j.bG, (org.a.c.b.b) aVar);
        int[] b2 = b();
        org.a.c.b.a aVar2 = new org.a.c.b.a();
        for (int i = 0; i < 3; i++) {
            aVar2.a((org.a.c.b.b) org.a.c.b.i.a(b2[i]));
        }
        this.c.a(org.a.c.b.j.dX, (org.a.c.b.b) aVar2);
        OutputStream a2 = this.c.a((org.a.c.b.b) org.a.c.b.j.bc);
        a(a2, b2);
        a2.flush();
        a2.close();
        for (org.a.c.b.j jVar : this.c.d()) {
            if (!org.a.c.b.j.di.equals(jVar) && !org.a.c.b.j.bI.equals(jVar) && !org.a.c.b.j.cU.equals(jVar) && !org.a.c.b.j.aS.equals(jVar)) {
                this.c.a(jVar).a(true);
            }
        }
        return this.c;
    }

    public final void a(org.a.c.b.d dVar) {
        for (Map.Entry<org.a.c.b.j, org.a.c.b.b> entry : dVar.e()) {
            org.a.c.b.j key = entry.getKey();
            if (org.a.c.b.j.bI.equals(key) || org.a.c.b.j.di.equals(key) || org.a.c.b.j.aS.equals(key) || org.a.c.b.j.bA.equals(key) || org.a.c.b.j.cU.equals(key)) {
                this.c.a(key, entry.getValue());
            }
        }
    }

    public final void a(org.a.c.g.c cVar) {
        this.f4436b.add(Long.valueOf(cVar.b().b()));
        if (cVar.d()) {
            a aVar = new a();
            aVar.f4437a = cVar.b().a();
            aVar.f4438b = cVar.b().b();
            this.f4435a.put(Long.valueOf(aVar.f4438b), aVar);
            return;
        }
        b bVar = new b();
        bVar.f4439a = cVar.b().a();
        bVar.f4440b = cVar.c();
        this.f4435a.put(Long.valueOf(cVar.b().b()), bVar);
    }

    private int[] b() {
        long[] jArr = new long[3];
        for (Object obj : this.f4435a.values()) {
            if (obj instanceof a) {
                jArr[0] = Math.max(jArr[0], 0L);
                jArr[1] = Math.max(jArr[1], ((a) obj).f4438b);
                jArr[2] = Math.max(jArr[2], r0.f4437a);
            } else if (obj instanceof b) {
                jArr[0] = Math.max(jArr[0], 1L);
                jArr[1] = Math.max(jArr[1], ((b) obj).f4440b);
                jArr[2] = Math.max(jArr[2], r0.f4439a);
            } else if (obj instanceof c) {
                jArr[0] = Math.max(jArr[0], 2L);
                jArr[1] = Math.max(jArr[1], 0L);
                jArr[2] = Math.max(jArr[2], 0L);
            } else {
                throw new RuntimeException("unexpected reference type");
            }
        }
        int[] iArr = new int[3];
        for (int i = 0; i < 3; i++) {
            while (jArr[i] > 0) {
                int i2 = i;
                iArr[i2] = iArr[i2] + 1;
                int i3 = i;
                jArr[i3] = jArr[i3] >> 8;
            }
        }
        return iArr;
    }

    public final void a(long j) {
        this.d = j;
    }

    private List<Long> c() {
        LinkedList linkedList = new LinkedList();
        Long l = null;
        Long l2 = null;
        TreeSet<Long> treeSet = new TreeSet();
        treeSet.add(0L);
        treeSet.addAll(this.f4436b);
        for (Long l3 : treeSet) {
            if (l == null) {
                l = l3;
                l2 = 1L;
            }
            if (l.longValue() + l2.longValue() == l3.longValue()) {
                l2 = Long.valueOf(l2.longValue() + 1);
            }
            if (l.longValue() + l2.longValue() < l3.longValue()) {
                linkedList.add(l);
                linkedList.add(l2);
                l = l3;
                l2 = 1L;
            }
        }
        linkedList.add(l);
        linkedList.add(l2);
        return linkedList;
    }

    private static void a(OutputStream outputStream, long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (j & 255);
            j >>= 8;
        }
        for (int i3 = 0; i3 < i; i3++) {
            outputStream.write(bArr[(i - i3) - 1]);
        }
    }

    private void a(OutputStream outputStream, int[] iArr) {
        a(outputStream, 0L, iArr[0]);
        a(outputStream, 0L, iArr[1]);
        a(outputStream, User32.HWND_BROADCAST, iArr[2]);
        for (Object obj : this.f4435a.values()) {
            if (obj instanceof a) {
                a(outputStream, 0L, iArr[0]);
                a(outputStream, ((a) obj).f4438b, iArr[1]);
                a(outputStream, r0.f4437a, iArr[2]);
            } else if (obj instanceof b) {
                a(outputStream, 1L, iArr[0]);
                a(outputStream, ((b) obj).f4440b, iArr[1]);
                a(outputStream, r0.f4439a, iArr[2]);
            } else if (obj instanceof c) {
                a(outputStream, 2L, iArr[0]);
                a(outputStream, 0L, iArr[1]);
                a(outputStream, 0L, iArr[2]);
            } else {
                throw new RuntimeException("unexpected reference type");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/f/h$c.class */
    public static class c {
        c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/f/h$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        int f4439a;

        /* renamed from: b, reason: collision with root package name */
        long f4440b;

        b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/f/h$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        int f4437a;

        /* renamed from: b, reason: collision with root package name */
        long f4438b;

        a() {
        }
    }
}
