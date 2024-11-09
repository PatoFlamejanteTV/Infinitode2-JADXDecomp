package org.a.c.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/b/a.class */
public final class a extends b implements Iterable<b>, t {

    /* renamed from: a, reason: collision with root package name */
    private final List<b> f4355a = new ArrayList();

    public final void a(b bVar) {
        this.f4355a.add(bVar);
    }

    public final void a(org.a.c.h.a.c cVar) {
        this.f4355a.add(cVar.f());
    }

    public final void a(int i, b bVar) {
        this.f4355a.add(i, bVar);
    }

    public final void a() {
        this.f4355a.clear();
    }

    public final void a(Collection<b> collection) {
        this.f4355a.removeAll(collection);
    }

    public final void b(Collection<b> collection) {
        this.f4355a.retainAll(collection);
    }

    public final void c(Collection<b> collection) {
        this.f4355a.addAll(collection);
    }

    public final void a(int i, Collection<b> collection) {
        this.f4355a.addAll(i, collection);
    }

    public final void b(int i, b bVar) {
        this.f4355a.set(i, bVar);
    }

    public final void a(int i, org.a.c.h.a.c cVar) {
        b bVar = null;
        if (cVar != null) {
            bVar = cVar.f();
        }
        this.f4355a.set(0, bVar);
    }

    public final b a(int i) {
        b bVar = this.f4355a.get(i);
        b bVar2 = bVar;
        if (bVar instanceof m) {
            bVar2 = ((m) bVar2).a();
        }
        if (bVar2 instanceof k) {
            bVar2 = null;
        }
        return bVar2;
    }

    public final b b(int i) {
        return this.f4355a.get(i);
    }

    public final int c(int i) {
        return a(i, -1);
    }

    public final int a(int i, int i2) {
        int i3 = i2;
        if (i < b()) {
            b bVar = this.f4355a.get(i);
            if (bVar instanceof l) {
                i3 = ((l) bVar).c();
            }
        }
        return i3;
    }

    public final void b(int i, int i2) {
        b(3, i.a(i2));
    }

    public final void a(int i, String str) {
        b(1, j.a(str));
    }

    public final int b() {
        return this.f4355a.size();
    }

    public final b d(int i) {
        return this.f4355a.remove(i);
    }

    public final boolean b(b bVar) {
        return this.f4355a.remove(bVar);
    }

    public final String toString() {
        return "COSArray{" + this.f4355a + "}";
    }

    @Override // java.lang.Iterable
    public final Iterator<b> iterator() {
        return this.f4355a.iterator();
    }

    public final void e(int i) {
        c(i, null);
    }

    private void c(int i, b bVar) {
        while (b() < i) {
            a(bVar);
        }
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    @Override // org.a.c.b.t
    public final boolean c() {
        return false;
    }

    public final float[] d() {
        float[] fArr = new float[b()];
        for (int i = 0; i < b(); i++) {
            b a2 = a(i);
            fArr[i] = a2 instanceof l ? ((l) a2).a() : 0.0f;
        }
        return fArr;
    }

    public final void a(float[] fArr) {
        a();
        for (float f : fArr) {
            a((b) new f(f));
        }
    }

    public final List<? extends b> e() {
        ArrayList arrayList = new ArrayList(b());
        for (int i = 0; i < b(); i++) {
            arrayList.add(b(i));
        }
        return arrayList;
    }
}
