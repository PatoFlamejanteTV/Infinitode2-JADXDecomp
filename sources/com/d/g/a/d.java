package com.d.g.a;

import com.d.g.a.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/g/a/d.class */
public final class d<T extends f> {

    /* renamed from: a, reason: collision with root package name */
    private List<T> f1207a;

    public final List<T> a() {
        return this.f1207a;
    }

    public final void a(T t) {
        if (this.f1207a == null) {
            this.f1207a = new ArrayList();
        }
        this.f1207a.add(t);
        Collections.sort(this.f1207a, new e(this));
    }

    public final T a(int i, com.d.c.a.c cVar) {
        if (this.f1207a == null) {
            throw new RuntimeException("fontDescriptions is null");
        }
        ArrayList arrayList = new ArrayList();
        for (T t : this.f1207a) {
            if (t.b() == cVar) {
                arrayList.add(t);
            }
        }
        if (arrayList.size() == 0) {
            if (cVar == com.d.c.a.c.W) {
                return a(i, com.d.c.a.c.at);
            }
            if (cVar == com.d.c.a.c.at) {
                return a(i, com.d.c.a.c.aq);
            }
            arrayList.addAll(this.f1207a);
        }
        T t2 = (T) a(arrayList, i, 1);
        if (t2 != null) {
            return t2;
        }
        if (i <= 500) {
            return (T) a(arrayList, i, 2);
        }
        return (T) a(arrayList, i, 3);
    }

    private static T a(List<T> list, int i, int i2) {
        if (i2 == 1) {
            for (T t : list) {
                if (t.a() == i) {
                    return t;
                }
            }
            return null;
        }
        if (i2 == 2) {
            T t2 = null;
            int i3 = 0;
            while (i3 < list.size()) {
                T t3 = list.get(i3);
                t2 = t3;
                if (t3.a() > i) {
                    break;
                }
                i3++;
            }
            if (i3 > 0 && t2.a() > i) {
                return list.get(i3 - 1);
            }
            return t2;
        }
        if (i2 == 3) {
            T t4 = null;
            int size = list.size() - 1;
            while (size >= 0) {
                T t5 = list.get(size);
                t4 = t5;
                if (t5.a() < i) {
                    break;
                }
                size--;
            }
            if (size != list.size() - 1 && t4.a() < i) {
                return list.get(size + 1);
            }
            return t4;
        }
        return null;
    }
}
