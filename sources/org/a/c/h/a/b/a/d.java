package org.a.c.h.a.b.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private final List<Object> f4465a = new ArrayList();

    public final void a(String str) {
        this.f4465a.add(str);
    }

    public final void a(int i) {
        this.f4465a.add(Integer.valueOf(i));
    }

    public final void a(float f) {
        this.f4465a.add(Float.valueOf(f));
    }

    public final void a(d dVar) {
        this.f4465a.add(dVar);
    }

    public final void a(a aVar) {
        Stack<Object> a2 = aVar.a();
        for (Object obj : this.f4465a) {
            if (obj instanceof String) {
                String str = (String) obj;
                f a3 = aVar.b().a(str);
                if (a3 != null) {
                    a3.a(aVar);
                } else {
                    throw new UnsupportedOperationException("Unknown operator or name: " + str);
                }
            } else {
                a2.push(obj);
            }
        }
        while (!a2.isEmpty() && (a2.peek() instanceof d)) {
            ((d) a2.pop()).a(aVar);
        }
    }
}
