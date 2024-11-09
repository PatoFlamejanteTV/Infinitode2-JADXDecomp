package com.a.a.c.f;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/p.class */
public abstract class p {

    /* renamed from: a, reason: collision with root package name */
    protected static final com.a.a.c.m.b f462a = new c();

    /* renamed from: b, reason: collision with root package name */
    protected final Object f463b;

    public abstract com.a.a.c.m.b c();

    public abstract aa d();

    public abstract boolean a(Annotation annotation);

    public abstract p b(Annotation annotation);

    protected p(Object obj) {
        this.f463b = obj;
    }

    public static com.a.a.c.m.b a() {
        return f462a;
    }

    public static p b() {
        return a.c;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/p$a.class */
    static class a extends p {
        public static final a c = new a(null);

        private a(Object obj) {
            super(obj);
        }

        @Override // com.a.a.c.f.p
        public final com.a.a.c.m.b c() {
            return f462a;
        }

        @Override // com.a.a.c.f.p
        public final aa d() {
            return new aa();
        }

        @Override // com.a.a.c.f.p
        public final boolean a(Annotation annotation) {
            return false;
        }

        @Override // com.a.a.c.f.p
        public final p b(Annotation annotation) {
            return new e(this.f463b, annotation.annotationType(), annotation);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/p$e.class */
    static class e extends p {
        private Class<?> c;
        private Annotation d;

        public e(Object obj, Class<?> cls, Annotation annotation) {
            super(obj);
            this.c = cls;
            this.d = annotation;
        }

        @Override // com.a.a.c.f.p
        public final com.a.a.c.m.b c() {
            return new d(this.c, this.d);
        }

        @Override // com.a.a.c.f.p
        public final aa d() {
            return aa.a(this.c, this.d);
        }

        @Override // com.a.a.c.f.p
        public final boolean a(Annotation annotation) {
            return annotation.annotationType() == this.c;
        }

        @Override // com.a.a.c.f.p
        public final p b(Annotation annotation) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (this.c == annotationType) {
                this.d = annotation;
                return this;
            }
            return new b(this.f463b, this.c, this.d, annotationType, annotation);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/p$b.class */
    static class b extends p {
        private HashMap<Class<?>, Annotation> c;

        public b(Object obj, Class<?> cls, Annotation annotation, Class<?> cls2, Annotation annotation2) {
            super(obj);
            this.c = new HashMap<>();
            this.c.put(cls, annotation);
            this.c.put(cls2, annotation2);
        }

        @Override // com.a.a.c.f.p
        public final com.a.a.c.m.b c() {
            if (this.c.size() == 2) {
                Iterator<Map.Entry<Class<?>, Annotation>> it = this.c.entrySet().iterator();
                Map.Entry<Class<?>, Annotation> next = it.next();
                Map.Entry<Class<?>, Annotation> next2 = it.next();
                return new f(next.getKey(), next.getValue(), next2.getKey(), next2.getValue());
            }
            return new aa(this.c);
        }

        @Override // com.a.a.c.f.p
        public final aa d() {
            aa aaVar = new aa();
            Iterator<Annotation> it = this.c.values().iterator();
            while (it.hasNext()) {
                aaVar.a(it.next());
            }
            return aaVar;
        }

        @Override // com.a.a.c.f.p
        public final boolean a(Annotation annotation) {
            return this.c.containsKey(annotation.annotationType());
        }

        @Override // com.a.a.c.f.p
        public final p b(Annotation annotation) {
            this.c.put(annotation.annotationType(), annotation);
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/p$c.class */
    public static class c implements com.a.a.c.m.b, Serializable {
        c() {
        }

        @Override // com.a.a.c.m.b
        public final <A extends Annotation> A a(Class<A> cls) {
            return null;
        }

        @Override // com.a.a.c.m.b
        public final boolean b(Class<?> cls) {
            return false;
        }

        @Override // com.a.a.c.m.b
        public final boolean a(Class<? extends Annotation>[] clsArr) {
            return false;
        }

        @Override // com.a.a.c.m.b
        public final int a() {
            return 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/p$d.class */
    public static class d implements com.a.a.c.m.b, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?> f464a;

        /* renamed from: b, reason: collision with root package name */
        private final Annotation f465b;

        public d(Class<?> cls, Annotation annotation) {
            this.f464a = cls;
            this.f465b = annotation;
        }

        @Override // com.a.a.c.m.b
        public final <A extends Annotation> A a(Class<A> cls) {
            if (this.f464a == cls) {
                return (A) this.f465b;
            }
            return null;
        }

        @Override // com.a.a.c.m.b
        public final boolean b(Class<?> cls) {
            return this.f464a == cls;
        }

        @Override // com.a.a.c.m.b
        public final boolean a(Class<? extends Annotation>[] clsArr) {
            for (Class<? extends Annotation> cls : clsArr) {
                if (cls == this.f464a) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.a.a.c.m.b
        public final int a() {
            return 1;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/p$f.class */
    public static class f implements com.a.a.c.m.b, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?> f466a;

        /* renamed from: b, reason: collision with root package name */
        private final Class<?> f467b;
        private final Annotation c;
        private final Annotation d;

        public f(Class<?> cls, Annotation annotation, Class<?> cls2, Annotation annotation2) {
            this.f466a = cls;
            this.c = annotation;
            this.f467b = cls2;
            this.d = annotation2;
        }

        @Override // com.a.a.c.m.b
        public final <A extends Annotation> A a(Class<A> cls) {
            if (this.f466a == cls) {
                return (A) this.c;
            }
            if (this.f467b == cls) {
                return (A) this.d;
            }
            return null;
        }

        @Override // com.a.a.c.m.b
        public final boolean b(Class<?> cls) {
            return this.f466a == cls || this.f467b == cls;
        }

        @Override // com.a.a.c.m.b
        public final boolean a(Class<? extends Annotation>[] clsArr) {
            for (Class<? extends Annotation> cls : clsArr) {
                if (cls == this.f466a || cls == this.f467b) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.a.a.c.m.b
        public final int a() {
            return 2;
        }
    }
}
