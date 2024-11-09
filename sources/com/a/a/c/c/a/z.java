package com.a.a.c.c.a;

import com.a.a.a.al;
import com.a.a.a.an;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/z.class */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    private al.a f291a;

    /* renamed from: b, reason: collision with root package name */
    private LinkedList<a> f292b;
    private an c;

    public z(al.a aVar) {
        this.f291a = aVar;
    }

    public final void a(an anVar) {
        this.c = anVar;
    }

    public final al.a a() {
        return this.f291a;
    }

    public final void a(a aVar) {
        if (this.f292b == null) {
            this.f292b = new LinkedList<>();
        }
        this.f292b.add(aVar);
    }

    public final void a(Object obj) {
        this.c.a(this.f291a, obj);
        Object obj2 = this.f291a.f46a;
        if (this.f292b != null) {
            Iterator<a> it = this.f292b.iterator();
            this.f292b = null;
            while (it.hasNext()) {
                it.next().a(obj2, obj);
            }
        }
    }

    public final Object b() {
        return this.c.a(this.f291a);
    }

    public final String toString() {
        return String.valueOf(this.f291a);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/z$a.class */
    public static abstract class a {

        /* renamed from: a, reason: collision with root package name */
        private final com.a.a.c.c.w f293a;

        public abstract void a(Object obj, Object obj2);

        public a(com.a.a.c.c.w wVar, Class<?> cls) {
            this.f293a = wVar;
        }

        public a(com.a.a.c.c.w wVar, com.a.a.c.j jVar) {
            this.f293a = wVar;
            jVar.b();
        }

        public final boolean b(Object obj) {
            return obj.equals(this.f293a.f());
        }
    }
}
