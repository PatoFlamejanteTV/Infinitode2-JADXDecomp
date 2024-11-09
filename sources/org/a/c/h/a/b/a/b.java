package org.a.c.h.a.b.a;

import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b.class */
final class b {

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$a.class */
    static abstract class a implements org.a.c.h.a.b.a.f {
        protected abstract boolean a(boolean z, boolean z2);

        protected abstract int a(int i, int i2);

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            Object pop = a2.pop();
            Object pop2 = a2.pop();
            if ((pop2 instanceof Boolean) && (pop instanceof Boolean)) {
                a2.push(Boolean.valueOf(a(((Boolean) pop2).booleanValue(), ((Boolean) pop).booleanValue())));
            } else {
                if ((pop2 instanceof Integer) && (pop instanceof Integer)) {
                    a2.push(Integer.valueOf(a(((Integer) pop2).intValue(), ((Integer) pop).intValue())));
                    return;
                }
                throw new ClassCastException("Operands must be bool/bool or int/int");
            }
        }
    }

    /* renamed from: org.a.c.h.a.b.a.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$b.class */
    static class C0047b extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C0047b() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.b.a
        protected final boolean a(boolean z, boolean z2) {
            return z & z2;
        }

        @Override // org.a.c.h.a.b.a.b.a
        protected final int a(int i, int i2) {
            return i & i2;
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$c.class */
    static class c implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            int intValue = ((Integer) a2.pop()).intValue();
            int intValue2 = ((Integer) a2.pop()).intValue();
            if (intValue < 0) {
                a2.push(Integer.valueOf(intValue2 >> Math.abs(intValue)));
            } else {
                a2.push(Integer.valueOf(intValue2 << intValue));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$d.class */
    static class d implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            aVar.a().push(Boolean.FALSE);
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$e.class */
    static class e implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            Stack<Object> a2 = aVar.a();
            Object pop = a2.pop();
            if (pop instanceof Boolean) {
                a2.push(Boolean.valueOf(!((Boolean) pop).booleanValue()));
            } else {
                if (pop instanceof Integer) {
                    a2.push(Integer.valueOf(-((Integer) pop).intValue()));
                    return;
                }
                throw new ClassCastException("Operand must be bool or int");
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$f.class */
    static class f extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public f() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.b.a
        protected final boolean a(boolean z, boolean z2) {
            return z | z2;
        }

        @Override // org.a.c.h.a.b.a.b.a
        protected final int a(int i, int i2) {
            return i | i2;
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$g.class */
    static class g implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(org.a.c.h.a.b.a.a aVar) {
            aVar.a().push(Boolean.TRUE);
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/b$h.class */
    static class h extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public h() {
            super((byte) 0);
        }

        @Override // org.a.c.h.a.b.a.b.a
        protected final boolean a(boolean z, boolean z2) {
            return z ^ z2;
        }

        @Override // org.a.c.h.a.b.a.b.a
        protected final int a(int i, int i2) {
            return i ^ i2;
        }
    }
}
