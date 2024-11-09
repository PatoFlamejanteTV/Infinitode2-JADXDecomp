package com.a.a.c.l;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/a/a/c/l/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private c f655a;

    /* renamed from: b, reason: collision with root package name */
    private Class<?> f656b;
    private ArrayList<k> c;

    public c(Class<?> cls) {
        this(null, cls);
    }

    private c(c cVar, Class<?> cls) {
        this.f655a = cVar;
        this.f656b = cls;
    }

    public final c a(Class<?> cls) {
        return new c(this, cls);
    }

    public final void a(k kVar) {
        if (this.c == null) {
            this.c = new ArrayList<>();
        }
        this.c.add(kVar);
    }

    public final void a(com.a.a.c.j jVar) {
        if (this.c != null) {
            Iterator<k> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().e(jVar);
            }
        }
    }

    public final c b(Class<?> cls) {
        if (this.f656b == cls) {
            return this;
        }
        c cVar = this.f655a;
        while (true) {
            c cVar2 = cVar;
            if (cVar2 != null) {
                if (cVar2.f656b != cls) {
                    cVar = cVar2.f655a;
                } else {
                    return cVar2;
                }
            } else {
                return null;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ClassStack (self-refs: ").append(this.c == null ? "0" : String.valueOf(this.c.size())).append(')');
        c cVar = this;
        while (true) {
            c cVar2 = cVar;
            if (cVar2 != null) {
                sb.append(' ').append(cVar2.f656b.getName());
                cVar = cVar2.f655a;
            } else {
                sb.append(']');
                return sb.toString();
            }
        }
    }
}
