package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SafePools.class */
public final class SafePools {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3899a = TLog.forClass(SafePools.class);

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadLocal<SafePools> f3900b = new ThreadLocal<SafePools>() { // from class: com.prineside.tdi2.utils.SafePools.1
        @Override // java.lang.ThreadLocal
        protected /* synthetic */ SafePools initialValue() {
            return a();
        }

        private static SafePools a() {
            return new SafePools((byte) 0);
        }
    };
    private final ObjectMap<Class<?>, Pool<?>> c;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SafePools$Pool.class */
    public interface Pool<T> {
        T newObject();

        T obtain();

        void free(T t);
    }

    /* synthetic */ SafePools(byte b2) {
        this();
    }

    public static SafePools getInstance() {
        return f3900b.get();
    }

    private SafePools() {
        this.c = new ObjectMap<>();
    }

    public final <T> Pool<T> create(int i, int i2, final ObjectSupplier<T> objectSupplier) {
        return new RegularPool<T>(this, i, i2) { // from class: com.prineside.tdi2.utils.SafePools.2
            {
                byte b2 = 0;
            }

            @Override // com.prineside.tdi2.utils.SafePools.Pool
            public T newObject() {
                return (T) objectSupplier.get();
            }
        };
    }

    public final <T> Pool<T> get(Class<T> cls, int i) {
        Pool<?> pool = this.c.get(cls);
        Pool<?> pool2 = pool;
        if (pool == null) {
            pool2 = new ReflectionPool(cls, 4, i, (byte) 0);
            this.c.put(cls, pool2);
            f3899a.i("Creating ReflectionPool for " + cls, new Object[0]);
        }
        return (Pool<T>) pool2;
    }

    public final <T> Pool<T> get(Class<T> cls) {
        return get(cls, 100);
    }

    public final <T> void set(Class<T> cls, Pool<T> pool) {
        this.c.put(cls, pool);
    }

    public final <T> T obtain(Class<T> cls) {
        return get(cls).obtain();
    }

    public final void free(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        Pool<?> pool = this.c.get(obj.getClass());
        if (pool == null) {
            return;
        }
        pool.free(obj);
    }

    public final void freeAll(Array array) {
        freeAll(array, false);
    }

    public final void freeAll(Array array, boolean z) {
        if (array == null) {
            throw new IllegalArgumentException("objects cannot be null.");
        }
        Pool<?> pool = null;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = array.get(i2);
            if (obj != null) {
                if (pool == null) {
                    Pool<?> pool2 = this.c.get(obj.getClass());
                    pool = pool2;
                    if (pool2 == null) {
                    }
                }
                pool.free(obj);
                if (!z) {
                    pool = null;
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SafePools$AlwaysAllocatingPool.class */
    public static final class AlwaysAllocatingPool<T> implements Pool<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Constructor f3902a;

        @Override // com.prineside.tdi2.utils.SafePools.Pool
        public final T newObject() {
            try {
                return (T) this.f3902a.newInstance((Object[]) null);
            } catch (Exception e) {
                throw new GdxRuntimeException("Unable to create new instance: " + this.f3902a.getDeclaringClass().getName(), e);
            }
        }

        @Override // com.prineside.tdi2.utils.SafePools.Pool
        public final T obtain() {
            return newObject();
        }

        @Override // com.prineside.tdi2.utils.SafePools.Pool
        public final void free(T t) {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SafePools$RegularPool.class */
    public static abstract class RegularPool<T> implements Pool<T> {

        /* renamed from: a, reason: collision with root package name */
        private int f3904a;

        /* renamed from: b, reason: collision with root package name */
        private Object[] f3905b;
        private int c;

        /* synthetic */ RegularPool(int i, int i2, byte b2) {
            this(i, i2);
        }

        private RegularPool(int i, int i2) {
            this.f3905b = new Object[Math.min(i, 4)];
            this.f3904a = i2;
        }

        @Override // com.prineside.tdi2.utils.SafePools.Pool
        public T obtain() {
            T t;
            synchronized (this) {
                if (this.c == 0) {
                    t = newObject();
                } else {
                    Object[] objArr = this.f3905b;
                    int i = this.c - 1;
                    this.c = i;
                    t = (T) objArr[i];
                }
            }
            return t;
        }

        @Override // com.prineside.tdi2.utils.SafePools.Pool
        public void free(T t) {
            if (t == null) {
                throw new IllegalArgumentException("object cannot be null.");
            }
            if (this.c < this.f3904a) {
                synchronized (this) {
                    if (this.c == this.f3905b.length) {
                        Object[] objArr = this.f3905b;
                        this.f3905b = new Object[Math.min((int) (objArr.length * 1.5f), this.f3904a)];
                        System.arraycopy(objArr, 0, this.f3905b, 0, objArr.length);
                    }
                    Object[] objArr2 = this.f3905b;
                    int i = this.c;
                    this.c = i + 1;
                    objArr2[i] = t;
                }
                a(t);
                return;
            }
            b(t);
        }

        private static void a(T t) {
            if (t instanceof Pool.Poolable) {
                ((Pool.Poolable) t).reset();
            }
        }

        private void b(T t) {
            a(t);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void clear() {
            synchronized (this) {
                for (Object obj : this.f3905b) {
                    b(obj);
                }
                Arrays.fill(this.f3905b, (Object) null);
            }
        }

        public int getFree() {
            return this.c;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SafePools$ReflectionPool.class */
    public static final class ReflectionPool<T> extends RegularPool<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Constructor f3903a;

        /* synthetic */ ReflectionPool(Class cls, int i, int i2, byte b2) {
            this(cls, 4, i2);
        }

        private ReflectionPool(Class<T> cls, int i, int i2) {
            super(i, i2, (byte) 0);
            this.f3903a = a((Class) cls);
            if (this.f3903a == null) {
                throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + cls.getName());
            }
            try {
                this.f3903a.setAccessible(true);
            } catch (Exception unused) {
            }
        }

        @Null
        private static Constructor a(Class<T> cls) {
            try {
                return ClassReflection.getConstructor(cls, (Class[]) null);
            } catch (Exception unused) {
                try {
                    Constructor declaredConstructor = ClassReflection.getDeclaredConstructor(cls, (Class[]) null);
                    declaredConstructor.setAccessible(true);
                    return declaredConstructor;
                } catch (ReflectionException unused2) {
                    return null;
                }
            }
        }

        @Override // com.prineside.tdi2.utils.SafePools.Pool
        public final T newObject() {
            try {
                return (T) this.f3903a.newInstance((Object[]) null);
            } catch (Exception e) {
                throw new GdxRuntimeException("Unable to create new instance: " + this.f3903a.getDeclaringClass().getName(), e);
            }
        }
    }
}
