package org.a.c.h.a.b.a;

import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/c.class */
final class c {

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/c$a.class */
    static class a implements f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            d dVar = (d) a2.pop();
            if (((Boolean) a2.pop()).booleanValue()) {
                dVar.a(aVar);
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/c$b.class */
    static class b implements f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            d dVar = (d) a2.pop();
            d dVar2 = (d) a2.pop();
            if (((Boolean) a2.pop()).booleanValue()) {
                dVar2.a(aVar);
            } else {
                dVar.a(aVar);
            }
        }
    }
}
