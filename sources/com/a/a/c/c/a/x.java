package com.a.a.c.c.a;

import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/x.class */
public abstract class x {

    /* renamed from: a, reason: collision with root package name */
    public final x f287a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f288b;

    public abstract void a(Object obj);

    protected x(x xVar, Object obj) {
        this.f287a = xVar;
        this.f288b = obj;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/x$c.class */
    static final class c extends x {
        private com.a.a.c.c.v c;

        public c(x xVar, Object obj, com.a.a.c.c.v vVar) {
            super(xVar, obj);
            this.c = vVar;
        }

        @Override // com.a.a.c.c.a.x
        public final void a(Object obj) {
            this.c.a(obj, this.f288b);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/x$a.class */
    static final class a extends x {
        private com.a.a.c.c.u c;
        private String d;

        public a(x xVar, Object obj, com.a.a.c.c.u uVar, String str) {
            super(xVar, obj);
            this.c = uVar;
            this.d = str;
        }

        @Override // com.a.a.c.c.a.x
        public final void a(Object obj) {
            this.c.a(obj, this.d, this.f288b);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/x$b.class */
    static final class b extends x {
        private Object c;

        public b(x xVar, Object obj, Object obj2) {
            super(xVar, obj);
            this.c = obj2;
        }

        @Override // com.a.a.c.c.a.x
        public final void a(Object obj) {
            ((Map) obj).put(this.c, this.f288b);
        }
    }
}
