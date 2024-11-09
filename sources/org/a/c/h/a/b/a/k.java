package org.a.c.h.a.b.a;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k.class */
final class k {

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k$a.class */
    static class a implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            int intValue = ((Number) a2.pop()).intValue();
            if (intValue > 0) {
                int size = a2.size();
                a2.addAll(new ArrayList(a2.subList(size - intValue, size)));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k$b.class */
    static class b implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            a2.push(a2.peek());
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k$c.class */
    static class c implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            Object pop = a2.pop();
            Object pop2 = a2.pop();
            a2.push(pop);
            a2.push(pop2);
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k$d.class */
    static class d implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            int intValue = ((Number) a2.pop()).intValue();
            if (intValue < 0) {
                throw new IllegalArgumentException("rangecheck: " + intValue);
            }
            a2.push(a2.get((a2.size() - intValue) - 1));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k$e.class */
    static class e implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            aVar.a().pop();
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/k$f.class */
    static class f implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            int intValue = ((Number) a2.pop()).intValue();
            int intValue2 = ((Number) a2.pop()).intValue();
            if (intValue == 0) {
                return;
            }
            if (intValue2 < 0) {
                throw new IllegalArgumentException("rangecheck: " + intValue2);
            }
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            if (intValue < 0) {
                int i = intValue2 + intValue;
                for (int i2 = 0; i2 < i; i2++) {
                    linkedList2.addFirst(a2.pop());
                }
                for (int i3 = intValue; i3 < 0; i3++) {
                    linkedList.addFirst(a2.pop());
                }
                a2.addAll(linkedList2);
                a2.addAll(linkedList);
                return;
            }
            int i4 = intValue2 - intValue;
            for (int i5 = intValue; i5 > 0; i5--) {
                linkedList.addFirst(a2.pop());
            }
            for (int i6 = 0; i6 < i4; i6++) {
                linkedList2.addFirst(a2.pop());
            }
            a2.addAll(linkedList);
            a2.addAll(linkedList2);
        }
    }
}
