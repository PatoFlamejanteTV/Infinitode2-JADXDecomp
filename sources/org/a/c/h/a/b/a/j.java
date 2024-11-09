package org.a.c.h.a.b.a;

import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j.class */
final class j {

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$b.class */
    static class b implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            a2.push(Boolean.valueOf(a(a2.pop(), a2.pop())));
        }

        protected boolean a(Object obj, Object obj2) {
            boolean equals;
            if ((obj instanceof Number) && (obj2 instanceof Number)) {
                equals = ((Number) obj).floatValue() == ((Number) obj2).floatValue();
            } else {
                equals = obj.equals(obj2);
            }
            return equals;
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$a.class */
    static abstract class a implements org.a.c.h.a.b.a.f {
        protected abstract boolean a(Number number, Number number2);

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            a2.push(Boolean.valueOf(a((Number) a2.pop(), (Number) a2.pop())));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$c.class */
    static class c extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public c() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.j.a
        protected final boolean a(Number number, Number number2) {
            return number.floatValue() >= number2.floatValue();
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$d.class */
    static class d extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public d() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.j.a
        protected final boolean a(Number number, Number number2) {
            return number.floatValue() > number2.floatValue();
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$e.class */
    static class e extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public e() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.j.a
        protected final boolean a(Number number, Number number2) {
            return number.floatValue() <= number2.floatValue();
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$f.class */
    static class f extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public f() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.j.a
        protected final boolean a(Number number, Number number2) {
            return number.floatValue() < number2.floatValue();
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/j$g.class */
    static class g extends b {
        @Override // org.a.c.h.a.b.a.j.b
        protected final boolean a(Object obj, Object obj2) {
            return !super.a(obj, obj2);
        }
    }
}
