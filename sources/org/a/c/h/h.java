package org.a.c.h;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/* loaded from: infinitode-2.jar:org/a/c/h/h.class */
public class h implements Iterable<e>, org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4643a = org.a.a.a.c.a(h.class);

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.d f4644b;
    private final b c;

    static /* synthetic */ boolean a(h hVar, org.a.c.b.d dVar) {
        return d(dVar);
    }

    static /* synthetic */ List b(h hVar, org.a.c.b.d dVar) {
        return b(dVar);
    }

    public h() {
        this.f4644b = new org.a.c.b.d();
        this.f4644b.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.cL);
        this.f4644b.a(org.a.c.b.j.bR, (org.a.c.b.b) new org.a.c.b.a());
        this.f4644b.a(org.a.c.b.j.ag, (org.a.c.b.b) org.a.c.b.i.f4366a);
        this.c = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(org.a.c.b.d dVar, b bVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("page tree root cannot be null");
        }
        if (org.a.c.b.j.cK.equals(dVar.b(org.a.c.b.j.dN))) {
            org.a.c.b.a aVar = new org.a.c.b.a();
            aVar.a((org.a.c.b.b) dVar);
            this.f4644b = new org.a.c.b.d();
            this.f4644b.a(org.a.c.b.j.bR, (org.a.c.b.b) aVar);
            this.f4644b.a(org.a.c.b.j.ag, 1);
        } else {
            this.f4644b = dVar;
        }
        this.c = bVar;
    }

    public static org.a.c.b.b a(org.a.c.b.d dVar, org.a.c.b.j jVar) {
        org.a.c.b.b a2 = dVar.a(jVar);
        if (a2 != null) {
            return a2;
        }
        org.a.c.b.b a3 = dVar.a(org.a.c.b.j.cN, org.a.c.b.j.cJ);
        if (a3 instanceof org.a.c.b.d) {
            org.a.c.b.d dVar2 = (org.a.c.b.d) a3;
            if (org.a.c.b.j.cL.equals(dVar2.a(org.a.c.b.j.dN))) {
                return a(dVar2, jVar);
            }
            return null;
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<e> iterator() {
        return new a(this, this.f4644b, (byte) 0);
    }

    private static List<org.a.c.b.d> b(org.a.c.b.d dVar) {
        ArrayList arrayList = new ArrayList();
        org.a.c.b.a f = dVar.f(org.a.c.b.j.bR);
        if (f == null) {
            return arrayList;
        }
        int b2 = f.b();
        for (int i = 0; i < b2; i++) {
            org.a.c.b.b a2 = f.a(i);
            if (a2 instanceof org.a.c.b.d) {
                arrayList.add((org.a.c.b.d) a2);
            } else {
                new StringBuilder("COSDictionary expected, but got ").append(a2 == null ? "null" : a2.getClass().getSimpleName());
            }
        }
        return arrayList;
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/h$a.class */
    final class a implements Iterator<e> {

        /* renamed from: a, reason: collision with root package name */
        private final Queue<org.a.c.b.d> f4645a;

        /* synthetic */ a(h hVar, org.a.c.b.d dVar, byte b2) {
            this(dVar);
        }

        private a(org.a.c.b.d dVar) {
            this.f4645a = new ArrayDeque();
            a(dVar);
        }

        private void a(org.a.c.b.d dVar) {
            if (h.a(h.this, dVar)) {
                Iterator it = h.b(h.this, dVar).iterator();
                while (it.hasNext()) {
                    a((org.a.c.b.d) it.next());
                }
                return;
            }
            this.f4645a.add(dVar);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return !this.f4645a.isEmpty();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e next() {
            org.a.c.b.d poll = this.f4645a.poll();
            h.c(poll);
            return new e(poll, h.this.c != null ? h.this.c.j() : null);
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final e a(int i) {
        org.a.c.b.d a2 = a(i + 1, this.f4644b, 0);
        c(a2);
        return new e(a2, this.c != null ? this.c.j() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(org.a.c.b.d dVar) {
        org.a.c.b.j b2 = dVar.b(org.a.c.b.j.dN);
        if (b2 != null) {
            if (!org.a.c.b.j.cK.equals(b2)) {
                throw new IllegalStateException("Expected 'Page' but found " + b2);
            }
        } else {
            dVar.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.cK);
        }
    }

    private org.a.c.b.d a(int i, org.a.c.b.d dVar, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        if (d(dVar)) {
            if (i <= i2 + dVar.b(org.a.c.b.j.ag, 0)) {
                for (org.a.c.b.d dVar2 : b(dVar)) {
                    if (d(dVar2)) {
                        int b2 = dVar2.b(org.a.c.b.j.ag, 0);
                        if (i <= i2 + b2) {
                            return a(i, dVar2, i2);
                        }
                        i2 += b2;
                    } else {
                        i2++;
                        if (i == i2) {
                            return a(i, dVar2, i2);
                        }
                    }
                }
                throw new IllegalStateException("Index not found: " + i);
            }
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        if (i2 == i) {
            return dVar;
        }
        throw new IllegalStateException("Index not found: " + i);
    }

    private static boolean d(org.a.c.b.d dVar) {
        if (dVar != null) {
            return dVar.b(org.a.c.b.j.dN) == org.a.c.b.j.cL || dVar.o(org.a.c.b.j.bR);
        }
        return false;
    }

    public final int a() {
        return this.f4644b.b(org.a.c.b.j.ag, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.f4644b;
    }

    public final void a(e eVar) {
        org.a.c.b.d f = eVar.f();
        org.a.c.b.d dVar = f;
        f.a(org.a.c.b.j.cN, (org.a.c.b.b) this.f4644b);
        ((org.a.c.b.a) this.f4644b.a(org.a.c.b.j.bR)).a((org.a.c.b.b) dVar);
        do {
            org.a.c.b.d dVar2 = (org.a.c.b.d) dVar.a(org.a.c.b.j.cN, org.a.c.b.j.cJ);
            dVar = dVar2;
            if (dVar2 != null) {
                dVar.a(org.a.c.b.j.ag, dVar.j(org.a.c.b.j.ag) + 1);
            }
        } while (dVar != null);
    }
}
