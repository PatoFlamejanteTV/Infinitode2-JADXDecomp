package com.a.a.a;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/a/al.class */
public abstract class al<T> implements Serializable {
    public abstract Class<?> a();

    public abstract boolean a(al<?> alVar);

    public abstract al<T> a(Class<?> cls);

    public abstract al<T> b();

    public abstract a a(Object obj);

    public abstract T b(Object obj);

    /* loaded from: infinitode-2.jar:com/a/a/a/al$a.class */
    public static final class a implements Serializable {

        /* renamed from: b, reason: collision with root package name */
        private Class<?> f45b;
        private Class<?> c;

        /* renamed from: a, reason: collision with root package name */
        public final Object f46a;
        private final int d;

        public a(Class<?> cls, Class<?> cls2, Object obj) {
            if (obj == null) {
                throw new IllegalArgumentException("Can not construct IdKey for null key");
            }
            this.f45b = cls;
            this.c = cls2;
            this.f46a = obj;
            int hashCode = obj.hashCode() + cls.getName().hashCode();
            this.d = cls2 != null ? hashCode ^ cls2.getName().hashCode() : hashCode;
        }

        public final int hashCode() {
            return this.d;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return aVar.f46a.equals(this.f46a) && aVar.f45b == this.f45b && aVar.c == this.c;
        }

        public final String toString() {
            Object[] objArr = new Object[3];
            objArr[0] = this.f46a;
            objArr[1] = this.f45b == null ? "NONE" : this.f45b.getName();
            objArr[2] = this.c == null ? "NONE" : this.c.getName();
            return String.format("[ObjectId: key=%s, type=%s, scope=%s]", objArr);
        }
    }
}
