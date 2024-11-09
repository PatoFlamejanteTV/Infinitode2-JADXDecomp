package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Pools.class */
public class Pools {
    private static final ObjectMap<Class, Pool> typePools = new ObjectMap<>();

    public static <T> Pool<T> get(Class<T> cls, int i) {
        Pool<T> pool = typePools.get(cls);
        Pool<T> pool2 = pool;
        if (pool == null) {
            pool2 = new ReflectionPool(cls, 4, i);
            typePools.put(cls, pool2);
        }
        return pool2;
    }

    public static <T> Pool<T> get(Class<T> cls) {
        return get(cls, 100);
    }

    public static <T> void set(Class<T> cls, Pool<T> pool) {
        typePools.put(cls, pool);
    }

    public static <T> T obtain(Class<T> cls) {
        return (T) get(cls).obtain();
    }

    public static void free(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        Pool pool = typePools.get(obj.getClass());
        if (pool == null) {
            return;
        }
        pool.free(obj);
    }

    public static void freeAll(Array array) {
        freeAll(array, false);
    }

    public static void freeAll(Array array, boolean z) {
        if (array == null) {
            throw new IllegalArgumentException("objects cannot be null.");
        }
        Pool pool = null;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = array.get(i2);
            if (obj != null) {
                if (pool == null) {
                    Pool pool2 = typePools.get(obj.getClass());
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

    private Pools() {
    }
}
